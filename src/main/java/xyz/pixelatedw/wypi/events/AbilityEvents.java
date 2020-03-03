package xyz.pixelatedw.wypi.events;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ChargeableAbility;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.wypi.abilities.PassiveAbility;
import xyz.pixelatedw.wypi.abilities.PunchAbility;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class AbilityEvents
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
