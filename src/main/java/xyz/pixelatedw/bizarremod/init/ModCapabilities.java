package xyz.pixelatedw.bizarremod.init;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.bizarremod.ModValues;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataBase;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataProvider;

public class ModCapabilities
{
	public static void init()
	{
		StandDataCapability.register();
	}

	@Mod.EventBusSubscriber(modid = ModValues.PROJECT_ID)
	public static class Registry
	{
		@SubscribeEvent
		public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event)
		{
			if(event.getObject() instanceof LivingEntity)
			{
				final StandDataBase abilityData = new StandDataBase();
				event.addCapability(new ResourceLocation(ModValues.PROJECT_ID, "stand_data"), new StandDataProvider());
			}
		}
	}
}
