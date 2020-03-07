package xyz.pixelatedw.bizarremod.events.stands;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.bizarremod.entities.stands.HighwayStarEntity;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class HighwayStarEvents
{

	@SubscribeEvent
	public static void onStandUpdate(LivingUpdateEvent event)
	{
		if(event.getEntityLiving() instanceof HighwayStarEntity)
		{
			HighwayStarEntity stand = (HighwayStarEntity) event.getEntityLiving();
			
			if(stand.getTrapPosition() == null || stand.isChasing())
				return;
			
			List<LivingEntity> nearbyTargets = WyHelper.<LivingEntity>getEntitiesNear(stand.getTrapPosition(), stand.world, 5);
			nearbyTargets = nearbyTargets.parallelStream().filter(entity -> stand.getOwner() != entity).collect(Collectors.toList());
			nearbyTargets.remove(stand);
			
			if(nearbyTargets.size() <= 0)
				return;
			
			Optional<LivingEntity> optional = nearbyTargets.stream().findFirst();
			
			if(!optional.isPresent())
				return;
						
			stand.setChaseTarget(optional.get());
			stand.setIsChasing(true);
		}
	}
	
}
