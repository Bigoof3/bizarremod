package xyz.pixelatedw.wypi;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ChargeableAbility;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.wypi.abilities.PassiveAbility;
import xyz.pixelatedw.wypi.abilities.PunchAbility;
import xyz.pixelatedw.wypi.data.ability.AbilityDataBase;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataProvider;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.client.CSyncAbilityDataPacket;
import xyz.pixelatedw.wypi.network.packets.server.SSyncAbilityDataPacket;
import xyz.pixelatedw.wypi.network.packets.server.SUpdateAbilityStatePacket;

public class APIDefaults
{

	public static void initI18n()
	{
		WyRegistry.registerName("ability.item.empty_stack", "Cannot equip because it's an empty stack!");
		WyRegistry.registerName("ability.item.another_item_in_hand", "Cannot equip while holding another item in hand!");
	}

	public static void initPackets()
	{
		// Client
		WyNetwork.registerPacket(CSyncAbilityDataPacket.class, CSyncAbilityDataPacket::encode, CSyncAbilityDataPacket::decode, CSyncAbilityDataPacket::handle);

		// Server
		WyNetwork.registerPacket(SSyncAbilityDataPacket.class, SSyncAbilityDataPacket::encode, SSyncAbilityDataPacket::decode, SSyncAbilityDataPacket::handle);
		WyNetwork.registerPacket(SUpdateAbilityStatePacket.class, SUpdateAbilityStatePacket::encode, SUpdateAbilityStatePacket::decode, SUpdateAbilityStatePacket::handle);
	}

	public static void initCapabilities()
	{
		AbilityDataCapability.register();
	}

	@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
	public static class Registry
	{
		@SubscribeEvent
		public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event)
		{
			if (event.getObject() instanceof PlayerEntity)
			{
				final AbilityDataBase abilityData = new AbilityDataBase();
				event.addCapability(new ResourceLocation(APIConfig.PROJECT_ID, "ability_data"), new AbilityDataProvider());
			}
		}
	}

	@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
	public static class AbilityEvents
	{		
		@SubscribeEvent
		public static void onLivingUpdate(LivingUpdateEvent event)
		{
			if (event.getEntityLiving() instanceof PlayerEntity)
			{
				PlayerEntity player = (PlayerEntity) event.getEntityLiving();
				IAbilityData props = AbilityDataCapability.get(player);
				
				for (Ability ability : props.getUnlockedAbilities(AbilityCategory.ALL))
				{
					if (ability == null)
						continue;
					if (ability instanceof PassiveAbility)
						((PassiveAbility) props.getUnlockedAbility(ability)).tick(player);
				}
				
				for (Ability ability : props.getEquippedAbilities(AbilityCategory.ALL))
				{
					if (ability == null)
						continue;

					if (ability instanceof ChargeableAbility && ability.isCharging())
						((ChargeableAbility) props.getEquippedAbility(ability)).charging(player);

					if (ability instanceof ContinuousAbility && ability.isContinuous())
						((ContinuousAbility) props.getEquippedAbility(ability)).tick(player);

					if (ability.isOnCooldown())
						props.getEquippedAbility(ability).cooldown(player);
				}
			}
		}

		@SubscribeEvent
		public static void onLivingDamage(LivingDamageEvent event)
		{
			if (event.getSource().getTrueSource() instanceof PlayerEntity)
			{
				PlayerEntity player = (PlayerEntity) event.getSource().getTrueSource();
				IAbilityData props = AbilityDataCapability.get(player);
				LivingEntity target = event.getEntityLiving();
				ItemStack heldItem = player.getHeldItemMainhand();

				for (Ability ability : props.getEquippedAbilities(AbilityCategory.ALL))
				{
					if (ability == null)
						continue;

					if (ability instanceof PunchAbility && ability.isContinuous() && heldItem.isEmpty())
					{
						float damage = ((PunchAbility) props.getEquippedAbility(ability)).hitEntity(player, target);
						event.setAmount(damage);
					}
				}
			}
		}
	}
}
