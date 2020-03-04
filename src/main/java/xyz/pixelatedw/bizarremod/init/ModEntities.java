package xyz.pixelatedw.bizarremod.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
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
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.WyRegistry;

public class ModEntities
{

	public static final EntityType PUNCH = WyRegistry.registerEntityType("punch", PunchEntity::new, 1, 1);
	public static final EntityType BULLET = WyRegistry.registerEntityType("bullet", BulletEntity::new, 1, 1);
	public static final EntityType BOMB = WyRegistry.registerEntityType("bomb", BombEntity::new, 1, 1);
	public static final EntityType ANKH = WyRegistry.registerEntityType("ankh", AnkhEntity::new, 1, 1);
	public static final EntityType RAPIER = WyRegistry.registerEntityType("rapier", RapierEntity::new, 2, 2);

	public static final EntityType GREEN_DAY = WyRegistry.registerEntityType(Consts.STAND_ID_GREEN_DAY, GreenDayEntity::new);
	public static final StandInfo GREEN_DAY_INFO = registerStandInfo(new GreenDayStandInfo(), "Green Day");
	public static final EntityType AEROSMITH = WyRegistry.registerEntityType(Consts.STAND_ID_AEROSMITH, AerosmithEntity::new);
	public static final StandInfo AEROSMITH_INFO = registerStandInfo(new AerosmithStandInfo(), "Aerosmith");
	public static final EntityType MAGICIAN_RED = WyRegistry.registerEntityType(Consts.STAND_ID_MAGICIANS_RED, MagiciansRedEntity::new);
	public static final StandInfo MAGICIAN_RED_INFO = registerStandInfo(new MagiciansRedStandInfo(), "Magician Red");
	public static final EntityType SILVER_CHARIOT = WyRegistry.registerEntityType(Consts.STAND_ID_SILVER_CHARIOT, SilverChariotEntity::new);
	public static final StandInfo SILVER_CHARIOT_INFO = registerStandInfo(new SilverChariotStandInfo(),  "Silver Chariot");
	public static final EntityType HIGHWAY_STAR = WyRegistry.registerEntityType(Consts.STAND_ID_HIGHWAY_STAR, HighwayStarEntity::new);
	public static final StandInfo HIGHWAY_STAR_INFO = registerStandInfo(new HighwayStarStandInfo(), "Highway Star");

	public static final List<StandInfo> STANDS = new ArrayList<StandInfo>();
	
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
				GREEN_DAY, AEROSMITH, MAGICIAN_RED, SILVER_CHARIOT, HIGHWAY_STAR,
				
				PUNCH, BULLET, BOMB, ANKH, RAPIER
			);
		}
		
		@SubscribeEvent
		public static void registerStandInfos(RegistryEvent.Register<StandInfo> event)
		{
			STANDS.add(GREEN_DAY_INFO);
			STANDS.add(AEROSMITH_INFO);
			STANDS.add(MAGICIAN_RED_INFO);
			STANDS.add(SILVER_CHARIOT_INFO);
			STANDS.add(HIGHWAY_STAR_INFO);

			for(StandInfo info : STANDS)
				event.getRegistry().register(info);
		}
	}
	
	private static StandInfo registerStandInfo(StandInfo info, String name)
	{
		String resourceName = WyHelper.getResourceName(name);
		info.setRegistryName(new ResourceLocation(APIConfig.PROJECT_ID, resourceName));
		return info;
	}
	
	
}
