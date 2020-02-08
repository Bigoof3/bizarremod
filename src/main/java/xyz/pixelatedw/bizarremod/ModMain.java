package xyz.pixelatedw.bizarremod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.bizarremod.config.CommonConfig;
import xyz.pixelatedw.bizarremod.init.ModNetwork;
import xyz.pixelatedw.bizarremod.proxy.ClientProxy;
import xyz.pixelatedw.bizarremod.proxy.IProxy;
import xyz.pixelatedw.bizarremod.proxy.ServerProxy;
import xyz.pixelatedw.wypi.APIConfig;

@Mod(APIConfig.PROJECT_ID)
public class ModMain
{

	public static ModMain instance;
	public static IProxy proxy;
	public static final Logger LOGGER = LogManager.getLogger(APIConfig.PROJECT_ID);
	
	public ModMain()
	{
		APIConfig.setupResourceFolderPath();
		
		instance = this;
		proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());
		
		CommonConfig.init();
		ModNetwork.init();		
	}
}
