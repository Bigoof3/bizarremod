package xyz.pixelatedw.bizarremod.config;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.EnumValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class CommonConfig
{

	public static CommonConfig instance;

	private BooleanValue visibleStands;
	private BooleanValue visibleStandsInFirstPerson;
	private BooleanValue oneStand;
	private BooleanValue shounenScream;
	
	private IntValue chanceOfDying;
	private IntValue meteoriteRarity;

	// Stand Config
	private EnumValue co2RadarPosition;

	public enum CO2RadarPosition
	{
		MIDDLE_LEFT, MIDDLE_RIGHT
	}
	
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
		//this.visibleStandsInFirstPerson = builder.comment("Stand Owners are able to see their stand in first person mode\n Default: true").define("Visible Stands in First Person", false);
		this.oneStand = builder.comment("Stands are unqiue and only one of each can exist\n Default: true").define("One Stand", true);
		//this.shounenScream = builder.comment("Users will scream (audio + text) the name of their Stand or ability used\n Default: false").define("Shounen Scream", false);
		
		this.chanceOfDying = builder.comment("Chance of dying when using the Stand Arrow on yourself or another player\n Default: 0%").defineInRange("Chance of Dying", 0, 0, 100);
		this.meteoriteRarity = builder.comment("Defines how rare Meteorites are\n Default: 1").defineInRange("Meteorite's Rarity", 1, 0, 100);
		
		this.co2RadarPosition = builder.comment("Defines the position where Aerosmith's CO2 Radar will be placed on screen\n Default: MIDDLE_LEFT").defineEnum("CO2 Radar Position", CO2RadarPosition.MIDDLE_LEFT, CO2RadarPosition.values());
	}

	public int getMeteoriteRarity()
	{
		return this.meteoriteRarity.get();
	}
	
	public int getChanceOfDying()
	{
		return this.chanceOfDying.get();
	}
	
	public boolean isVisibleStandsInFirstPersonEnabled()
	{
		return this.visibleStandsInFirstPerson.get();
	}

	public boolean isShounenScreamEnabled()
	{
		return this.shounenScream.get();
	}

	public boolean isOneStandEnabled()
	{
		return this.oneStand.get();
	}

	public boolean isVisibleStandsEnabled()
	{
		return this.visibleStands.get();
	}

	public CO2RadarPosition getCO2RadarPosition()
	{
		return (CO2RadarPosition) this.co2RadarPosition.get();
	}
}
