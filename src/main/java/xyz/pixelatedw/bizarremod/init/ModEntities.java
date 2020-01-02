package xyz.pixelatedw.bizarremod.init;

import java.util.HashMap;

import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.bizarremod.Consts;
import xyz.pixelatedw.bizarremod.api.StandInfo;
import xyz.pixelatedw.bizarremod.api.WyRegistry;
import xyz.pixelatedw.bizarremod.entities.projectiles.BulletEntity;
import xyz.pixelatedw.bizarremod.entities.projectiles.PunchEntity;
import xyz.pixelatedw.bizarremod.entities.stands.AerosmithEntity;
import xyz.pixelatedw.bizarremod.entities.stands.AerosmithEntity.AerosmithStandInfo;
import xyz.pixelatedw.bizarremod.entities.stands.GreenDayEntity;
import xyz.pixelatedw.bizarremod.entities.stands.GreenDayEntity.GreenDayStandInfo;
import xyz.pixelatedw.bizarremod.entities.stands.MagiciansRedEntity;
import xyz.pixelatedw.bizarremod.entities.stands.MagiciansRedEntity.MagiciansRedStandInfo;

public class ModEntities
{

	public static final EntityType PUNCH = WyRegistry.registerEntityType("punch", PunchEntity::new, 1, 1);
	public static final EntityType BULLET = WyRegistry.registerEntityType("bullet", BulletEntity::new, 1, 1);

	public static final EntityType GREEN_DAY = WyRegistry.registerEntityType(Consts.STAND_ID_GREEN_DAY, GreenDayEntity::new);
	public static final EntityType AEROSMITH = WyRegistry.registerEntityType(Consts.STAND_ID_AEROSMITH, AerosmithEntity::new);
	public static final EntityType MAGICIAN_RED = WyRegistry.registerEntityType(Consts.STAND_ID_MAGICIANS_RED, MagiciansRedEntity::new);

	private static HashMap<String, StandInfo> stands = new HashMap<String, StandInfo>();
	
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
				GREEN_DAY, AEROSMITH, MAGICIAN_RED,
				
				PUNCH, BULLET
			);
			
			stands.put(Consts.STAND_ID_GREEN_DAY, new GreenDayStandInfo());
			stands.put(Consts.STAND_ID_AEROSMITH, new AerosmithStandInfo());
			stands.put(Consts.STAND_ID_MAGICIANS_RED, new MagiciansRedStandInfo());
		}
		
	}
	
	public static HashMap<String, StandInfo> getRegisteredStands()
	{
		return stands;
	}
}
