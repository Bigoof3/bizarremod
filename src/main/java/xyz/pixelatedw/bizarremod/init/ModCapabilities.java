package xyz.pixelatedw.bizarremod.init;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.bizarremod.data.entity.standdata.StandDataBase;
import xyz.pixelatedw.bizarremod.data.entity.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.data.entity.standdata.StandDataProvider;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.APIDefaults;

public class ModCapabilities
{
	public static void init()
	{
		APIDefaults.initCapabilities();
		StandDataCapability.register();
	}

	@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
	public static class Registry
	{
		@SubscribeEvent
		public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event)
		{
			if(event.getObject() instanceof LivingEntity)
			{
				final StandDataBase abilityData = new StandDataBase();
				event.addCapability(new ResourceLocation(APIConfig.PROJECT_ID, "stand_data"), new StandDataProvider());
			}
		}
	}
}
