package xyz.pixelatedw.bizarremod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import xyz.pixelatedw.bizarremod.api.WyDebug;
import xyz.pixelatedw.bizarremod.config.CommonConfig;
import xyz.pixelatedw.bizarremod.events.EventsJoinWorld;
import xyz.pixelatedw.bizarremod.events.EventsStandAbilities;
import xyz.pixelatedw.bizarremod.init.ModCapabilities;
import xyz.pixelatedw.bizarremod.init.ModNetwork;
import xyz.pixelatedw.bizarremod.proxy.ClientProxy;
import xyz.pixelatedw.bizarremod.proxy.IProxy;
import xyz.pixelatedw.bizarremod.proxy.ServerProxy;

@Mod(ModValues.PROJECT_ID)
public class ModMain
{

	public static ModMain instance;
	public static IProxy proxy;
	public static final Logger LOGGER = LogManager.getLogger(ModValues.PROJECT_ID);
	
	public ModMain()
	{
		if(WyDebug.isDebug())
		{
			String basicPath = System.getProperty("java.class.path");
			ModValues.PROJECT_RESOURCES_FOLDER = basicPath.substring(0, basicPath.indexOf("\\bin")).replace("file:/", "").replace("%20", " ") + "/src/main/resources";
		}
		
		instance = this;
		proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());
		
		CommonConfig.init();
		ModNetwork.init();
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(ModMain::commonSetup);	
		MinecraftForge.EVENT_BUS.addListener(this::serverAboutToStart);
	}
	
	private static void commonSetup(FMLCommonSetupEvent event)
	{
		ModCapabilities.init();
		
		MinecraftForge.EVENT_BUS.register(new EventsJoinWorld());
		
		MinecraftForge.EVENT_BUS.register(new EventsStandAbilities());
	}
	
	private void serverAboutToStart(FMLServerAboutToStartEvent event)
	{
		
	}
}
