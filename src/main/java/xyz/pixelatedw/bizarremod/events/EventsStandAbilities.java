package xyz.pixelatedw.bizarremod.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import xyz.pixelatedw.bizarremod.ModValues;
import xyz.pixelatedw.bizarremod.abilities.Ability;
import xyz.pixelatedw.bizarremod.abilities.PassiveAbility;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.entities.stands.info.StandInfo;
import xyz.pixelatedw.bizarremod.helpers.StandLogicHelper;

public class EventsStandAbilities
{

	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event)
	{
		if(event.getEntityLiving() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getEntityLiving();
			IStandData props = StandDataCapability.get(player);
			
			if(!props.hasStandSummoned() || player.world.isRemote)
				return;

			StandInfo info = StandLogicHelper.getStandInfo(ModValues.STAND_ID_GREEN_DAY);
			
			for(Ability ability : info.getAbilities())
			{
				if(ability instanceof PassiveAbility)
					((PassiveAbility) ability).tick(player);
			}
		}
	}
	
}
