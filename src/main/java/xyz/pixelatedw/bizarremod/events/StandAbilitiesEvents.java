package xyz.pixelatedw.bizarremod.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.bizarremod.Env;
import xyz.pixelatedw.bizarremod.abilities.Ability;
import xyz.pixelatedw.bizarremod.abilities.PassiveAbility;
import xyz.pixelatedw.bizarremod.api.StandInfo;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.helpers.StandLogicHelper;

@Mod.EventBusSubscriber(modid = Env.PROJECT_ID)
public class StandAbilitiesEvents
{

	@SubscribeEvent
	public static void onLivingUpdate(LivingUpdateEvent event)
	{
		if (event.getEntityLiving() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getEntityLiving();
			IStandData props = StandDataCapability.get(player);

			if (!props.hasStandSummoned())
				return;

			//System.out.println(props.getPrimaryAbility());
			
			StandInfo info = StandLogicHelper.getStandInfo(props.getStand());

			if (info.getAbilities() == null || info.getAbilities().length <= 0)
				return;

			for (Ability ability : info.getAbilities())
			{
				if (ability instanceof PassiveAbility)
					((PassiveAbility) ability).tick(player);
				else
					ability.cooldown(player);
			}
		}
	}

}
