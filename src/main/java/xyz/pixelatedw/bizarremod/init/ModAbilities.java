package xyz.pixelatedw.bizarremod.init;

import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import xyz.pixelatedw.bizarremod.abilities.StandPunchAbility;
import xyz.pixelatedw.bizarremod.abilities.aerosmith.BombAbility;
import xyz.pixelatedw.bizarremod.abilities.aerosmith.CarbonDioxideRadarAbility;
import xyz.pixelatedw.bizarremod.abilities.aerosmith.MachineGunsAbility;
import xyz.pixelatedw.bizarremod.abilities.greenday.MoldInfestationAbility;
import xyz.pixelatedw.bizarremod.abilities.highwaystar.RoomTrapAbility;
import xyz.pixelatedw.bizarremod.abilities.magiciansred.CrossFireHurricaneAbility;
import xyz.pixelatedw.bizarremod.abilities.magiciansred.RedBindAbility;
import xyz.pixelatedw.bizarremod.abilities.silverchariot.ArmorOffAbility;
import xyz.pixelatedw.bizarremod.abilities.silverchariot.RapierStabAbility;
import xyz.pixelatedw.bizarremod.abilities.silverchariot.ShootingSwordAbility;
import xyz.pixelatedw.bizarremod.abilities.sun.HeatPassiveAbility;
import xyz.pixelatedw.bizarremod.abilities.sun.LightRayAbility;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.Ability;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(APIConfig.PROJECT_ID)
public class ModAbilities
{
	public static final Ability ROOM_TRAP = null;
	
	public static final Ability RAPIER_STAB = null;
	public static final Ability SHOOTING_SWORD = null;
	public static final Ability ARMOR_OFF = null;

	public static final Ability RED_BIND = null;
	public static final Ability CROSS_FIRE_HURRICANE = null;
	
	public static final Ability BOMB = null;
	public static final Ability CARBON_DIOXIDE_RADAR = null;
	public static final Ability MACHINE_GUNS = null;

	public static final Ability MOLD_INFESTATION = null;
	
	public static final Ability HEAT = null;
	public static final Ability LIGHT_RAYS = null;

	public static final Ability PUNCH = null;
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void registerAbilities(final RegistryEvent.Register<Ability> event)
	{
		WyRegistry.registerAbilities(event,
				
				new RoomTrapAbility(),
				
				new RapierStabAbility(),
				new ArmorOffAbility(),
				new ShootingSwordAbility(),
				
				new RedBindAbility(),
				new CrossFireHurricaneAbility(),
				
				new BombAbility(),
				new CarbonDioxideRadarAbility(),
				new MachineGunsAbility(),			
				
				new MoldInfestationAbility(),
				
				new HeatPassiveAbility(),
				new LightRayAbility(),
				
				new StandPunchAbility()
		);
	}
}
