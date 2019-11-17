package xyz.pixelatedw.bizarremod.init;

import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.bizarremod.api.WyRegistry;
import xyz.pixelatedw.bizarremod.entities.projectiles.PunchEntity;
import xyz.pixelatedw.bizarremod.entities.stands.AerosmithEntity;
import xyz.pixelatedw.bizarremod.entities.stands.GreenDayEntity;

public class ModEntities
{

	public static final EntityType PUNCH = WyRegistry.registerEntityType("punch", PunchEntity::new);
	
	public static final EntityType GREEN_DAY = WyRegistry.registerEntityType("green_day", GreenDayEntity::new);
	public static final EntityType AEROSMITH = WyRegistry.registerEntityType("aerosmith", AerosmithEntity::new);

	
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class Registry
	{
		@SubscribeEvent
		public static void registerEntityTypes(RegistryEvent.Register<EntityType<?>> event)
		{
			if (!event.getName().equals(ForgeRegistries.ENTITIES.getRegistryName()))
				return;
			
			event.getRegistry().registerAll
			(
				GREEN_DAY, AEROSMITH
			);
			
			event.getRegistry().registerAll
			(
				PUNCH
			);
		}
		
	}
	
}
