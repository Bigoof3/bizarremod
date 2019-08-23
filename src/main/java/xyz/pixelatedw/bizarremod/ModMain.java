package xyz.pixelatedw.bizarremod;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import xyz.pixelatedw.bizarremod.config.CommonConfig;
import xyz.pixelatedw.bizarremod.init.ModCapabilities;
import xyz.pixelatedw.bizarremod.proxy.ClientProxy;
import xyz.pixelatedw.bizarremod.proxy.IProxy;
import xyz.pixelatedw.bizarremod.proxy.ServerProxy;

@Mod(ModValues.PROJECT_ID)
public class ModMain
{

	public static ModMain instance;
	public static IProxy proxy;
	
	public ModMain()
	{
		instance = this;
		proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());
		
		CommonConfig.init();
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(ModMain::commonSetup);	
		MinecraftForge.EVENT_BUS.addListener(this::serverAboutToStart);
	}
	
	private static void commonSetup(FMLCommonSetupEvent event)
	{
		ModCapabilities.init();
	}
	
	private void serverAboutToStart(FMLServerAboutToStartEvent event)
	{
		
	}
}
