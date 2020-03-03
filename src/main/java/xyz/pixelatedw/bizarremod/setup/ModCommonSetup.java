package xyz.pixelatedw.bizarremod.setup;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import xyz.pixelatedw.bizarremod.init.ModCapabilities;
import xyz.pixelatedw.bizarremod.init.ModNetwork;
import xyz.pixelatedw.wypi.APIConfig;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, bus = Bus.MOD)
public class ModCommonSetup
{

	@SubscribeEvent
	public static void commonSetup(FMLCommonSetupEvent event)
	{
		ModCapabilities.init();
		
		ModNetwork.init();
	}
	
}
