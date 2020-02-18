package xyz.pixelatedw.bizarremod.init;

import java.util.HashMap;

import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.bizarremod.Consts;
import xyz.pixelatedw.bizarremod.api.stands.StandInfo;
import xyz.pixelatedw.bizarremod.entities.projectiles.AnkhEntity;
import xyz.pixelatedw.bizarremod.entities.projectiles.BombEntity;
import xyz.pixelatedw.bizarremod.entities.projectiles.BulletEntity;
import xyz.pixelatedw.bizarremod.entities.projectiles.PunchEntity;
import xyz.pixelatedw.bizarremod.entities.projectiles.RapierEntity;
import xyz.pixelatedw.bizarremod.entities.stands.AerosmithEntity;
import xyz.pixelatedw.bizarremod.entities.stands.AerosmithEntity.AerosmithStandInfo;
import xyz.pixelatedw.bizarremod.entities.stands.GreenDayEntity;
import xyz.pixelatedw.bizarremod.entities.stands.GreenDayEntity.GreenDayStandInfo;
import xyz.pixelatedw.bizarremod.entities.stands.HighwayStarEntity;
import xyz.pixelatedw.bizarremod.entities.stands.HighwayStarEntity.HighwayStarStandInfo;
import xyz.pixelatedw.bizarremod.entities.stands.MagiciansRedEntity;
import xyz.pixelatedw.bizarremod.entities.stands.MagiciansRedEntity.MagiciansRedStandInfo;
import xyz.pixelatedw.bizarremod.entities.stands.SilverChariotEntity;
import xyz.pixelatedw.bizarremod.entities.stands.SilverChariotEntity.SilverChariotStandInfo;
import xyz.pixelatedw.wypi.WyRegistry;

public class ModEntities
{

	public static final EntityType PUNCH = WyRegistry.registerEntityType("punch", PunchEntity::new, 1, 1);
	public static final EntityType BULLET = WyRegistry.registerEntityType("bullet", BulletEntity::new, 1, 1);
	public static final EntityType BOMB = WyRegistry.registerEntityType("bomb", BombEntity::new, 1, 1);
	public static final EntityType ANKH = WyRegistry.registerEntityType("ankh", AnkhEntity::new, 1, 1);
	public static final EntityType RAPIER = WyRegistry.registerEntityType("rapier", RapierEntity::new, 2, 2);

	public static final EntityType GREEN_DAY = WyRegistry.registerEntityType(Consts.STAND_ID_GREEN_DAY, GreenDayEntity::new);
	public static final EntityType AEROSMITH = WyRegistry.registerEntityType(Consts.STAND_ID_AEROSMITH, AerosmithEntity::new);
	public static final EntityType MAGICIAN_RED = WyRegistry.registerEntityType(Consts.STAND_ID_MAGICIANS_RED, MagiciansRedEntity::new);
	public static final EntityType SILVER_CHARIOT = WyRegistry.registerEntityType(Consts.STAND_ID_SILVER_CHARIOT, SilverChariotEntity::new);
	public static final EntityType HIGHWAY_STAR = WyRegistry.registerEntityType(Consts.STAND_ID_HIGHWAY_STAR, HighwayStarEntity::new);

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
				registerStand(GREEN_DAY, new GreenDayStandInfo()), 
				registerStand(AEROSMITH, new AerosmithStandInfo()),
				registerStand(MAGICIAN_RED, new MagiciansRedStandInfo()),
				registerStand(SILVER_CHARIOT, new SilverChariotStandInfo()),
				registerStand(HIGHWAY_STAR, new HighwayStarStandInfo()),
				
				PUNCH, BULLET, BOMB, ANKH, RAPIER
			);
		}
		
	}
	
	private static EntityType registerStand(EntityType type, StandInfo info)
	{
		stands.put(type.getRegistryName().getPath(), info);
		return type;
	}
	
	public static HashMap<String, StandInfo> getRegisteredStands()
	{
		return stands;
	}
}
