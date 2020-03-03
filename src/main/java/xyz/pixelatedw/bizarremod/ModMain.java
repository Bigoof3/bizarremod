package xyz.pixelatedw.bizarremod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.bizarremod.config.CommonConfig;
import xyz.pixelatedw.bizarremod.proxy.IModProxy;
import xyz.pixelatedw.bizarremod.proxy.ModClientProxy;
import xyz.pixelatedw.bizarremod.proxy.ModServerProxy;
import xyz.pixelatedw.wypi.APIConfig;

@Mod(APIConfig.PROJECT_ID)
public class ModMain
{

	public static ModMain instance;
	public static final Logger LOGGER = LogManager.getLogger(APIConfig.PROJECT_ID);
	
	public static final IModProxy PROXY = DistExecutor.runForDist(() -> () -> new ModClientProxy(), () -> () -> new ModServerProxy());
	
	public ModMain()
	{
		APIConfig.setupResourceFolderPath();
		
		instance = this;
		
		CommonConfig.init();
	}
}
