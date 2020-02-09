package xyz.pixelatedw.bizarremod.setup;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import xyz.pixelatedw.bizarremod.init.ModCapabilities;
import xyz.pixelatedw.wypi.APIConfig;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = { Dist.CLIENT, Dist.DEDICATED_SERVER })
public class ModCommonSetup
{

	@SubscribeEvent
	public static void commonSetup(FMLCommonSetupEvent event)
	{
		ModCapabilities.init();
	}
	
}
