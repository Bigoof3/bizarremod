package xyz.pixelatedw.bizarremod.config;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class CommonConfig
{

	public static CommonConfig instance;

	private BooleanValue visibleStands;
	private BooleanValue oneStand;
	private BooleanValue shounenScream;
	
	public static void init()
	{
		Pair<CommonConfig, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        ForgeConfigSpec configSpec = pair.getRight();
        CommonConfig.instance = pair.getLeft();
    	ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, configSpec);
	}
	
	public CommonConfig(ForgeConfigSpec.Builder builder)
	{
		this.visibleStands = builder.comment("Regular humans will be able to see Stands\n Default: false").define("Visible Stands", false);
		this.oneStand = builder.comment("Stands are unqiue and only one of each can exist\n Default: true").define("One Stand", true);
		this.shounenScream = builder.comment("Users will scream (audio + text) the name of their Stand or ability used\n Default: false").define("Shounen Scream", false);
	}
	
	public boolean getShounenScream()
	{
		return this.shounenScream.get();
	}
	
	public boolean getOneStand()
	{
		return this.oneStand.get();
	}
	
	public boolean getVisibleStands()
	{
		return this.visibleStands.get();
	}
	
}
