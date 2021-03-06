package xyz.pixelatedw.bizarremod.items;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import xyz.pixelatedw.bizarremod.api.StandLogicHelper;
import xyz.pixelatedw.bizarremod.api.stands.StandInfo;
import xyz.pixelatedw.bizarremod.config.CommonConfig;
import xyz.pixelatedw.bizarremod.data.entity.standdata.IStandData;
import xyz.pixelatedw.bizarremod.data.entity.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.data.world.ExtendedWorldData;
import xyz.pixelatedw.bizarremod.init.ModEntities;
import xyz.pixelatedw.bizarremod.packets.server.SOpenScreenPacket;
import xyz.pixelatedw.bizarremod.packets.server.SSyncStandDataPacket;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.server.SSyncAbilityDataPacket;

public class StandArrowItem extends Item
{
	public StandArrowItem()
	{
		super(new Properties().group(ItemGroup.TOOLS).maxStackSize(1));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		if (!world.isRemote)
		{
			if (player.isCreative())
			{
				// WyNetwork.sendTo(new SUpdateWorldDataPacket(world, ExtendedWorldData.get(world)), player);
				WyNetwork.sendTo(new SOpenScreenPacket().openStandSelectScreen(), player);
			}
			if (!player.isCreative())
			{
				IStandData props = StandDataCapability.get(player);

				if (!this.giveRandomStand(player))
					return new ActionResult<>(ActionResultType.FAIL, player.getHeldItem(hand));

				player.getHeldItemMainhand().setCount(0);

				WyNetwork.sendTo(new SSyncStandDataPacket(props), (ServerPlayerEntity) player);
			}

			return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
		}

		return new ActionResult<>(ActionResultType.FAIL, player.getHeldItem(hand));
	}

	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker)
	{
		if (target instanceof PlayerEntity)
		{
			PlayerEntity playerTarget = ((PlayerEntity) target);
			if (!playerTarget.isCreative())
			{
				IStandData props = StandDataCapability.get(playerTarget);

				if (!this.giveRandomStand(playerTarget))
					return false;

				attacker.getHeldItemMainhand().setCount(0);

				WyNetwork.sendTo(new SSyncStandDataPacket(props), (ServerPlayerEntity) target);
			}
		}
		return true;
	}

	private boolean giveRandomStand(PlayerEntity player)
	{
		IStandData props = StandDataCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);

		if (!WyHelper.isNullOrEmpty(props.getStand()))
			return false;

		int chanceOfDying = CommonConfig.instance.getChanceOfDying();
		if(chanceOfDying > 0)
		{
			double chance = WyHelper.randomWithRange(0, 100) + WyHelper.randomDouble();
			if(chance <= chanceOfDying)
			{
				player.attackEntityFrom(DamageSource.MAGIC, Float.POSITIVE_INFINITY);
				return false;
			}
		}
		
		StandInfo info = null;
		int tries = 0;

		while (info == null)
		{
			StandInfo tempInfo = this.getAvailableStand(player);

			if (!ExtendedWorldData.get(player.world).isStandUsed(tempInfo))
			{
				info = tempInfo;
				break;
			}

			tries++;

			if (tries >= 3)
				break;
		}

		if (info == null)
			return false;

		List<Ability> activeAbilities = StandLogicHelper.getActiveAbilities(abilityProps, info);

		props.setStand(info.getDefaultStandId());
		ExtendedWorldData.get(player.world).addUsedStand(info);

		abilityProps.clearEquippedAbilities(AbilityCategory.ALL);
		abilityProps.clearUnlockedAbilities(AbilityCategory.ALL);

		for (Ability abl : info.getAbilities())
			abilityProps.addUnlockedAbility(abl);

		if (activeAbilities.size() >= 1)
			abilityProps.setEquippedAbility(0, activeAbilities.get(0) != null ? activeAbilities.get(0) : null);
		if (activeAbilities.size() >= 2)
			abilityProps.setEquippedAbility(1, activeAbilities.get(1) != null ? activeAbilities.get(1) : null);

		WyNetwork.sendTo(new SSyncAbilityDataPacket(abilityProps), (ServerPlayerEntity) player);

		return true;
	}

	private StandInfo getAvailableStand(PlayerEntity player)
	{
		int totalStands = ModEntities.STANDS.size();
		int randomStand = (int) WyHelper.randomWithRange(0, totalStands - 1);
		StandInfo info = (StandInfo) ModEntities.STANDS.toArray()[randomStand];

		return info;
	}
}
