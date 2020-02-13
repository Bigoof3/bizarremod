package xyz.pixelatedw.bizarremod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.bizarremod.config.CommonConfig;
import xyz.pixelatedw.bizarremod.init.ModNetwork;
import xyz.pixelatedw.wypi.APIConfig;

@Mod(APIConfig.PROJECT_ID)
public class ModMain
{

	public static ModMain instance;
	public static final Logger LOGGER = LogManager.getLogger(APIConfig.PROJECT_ID);
	
	public ModMain()
	{
		APIConfig.setupResourceFolderPath();
		
		instance = this;
		
		CommonConfig.init();
		ModNetwork.init();		
	}
}
