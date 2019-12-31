package xyz.pixelatedw.bizarremod;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import xyz.pixelatedw.bizarremod.init.ModCapabilities;

@Mod.EventBusSubscriber(modid = Env.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = { Dist.CLIENT, Dist.DEDICATED_SERVER })
public class CommonSetup
{

	@SubscribeEvent
	public static void commonSetup(FMLCommonSetupEvent event)
	{
		ModCapabilities.init();
	}
	
}
