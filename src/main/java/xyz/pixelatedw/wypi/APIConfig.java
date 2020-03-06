package xyz.pixelatedw.wypi;

import xyz.pixelatedw.wypi.debug.WyDebug;

public class APIConfig
{
	/*
	 * Generic Mod Enviromental Constants
	 */

	public static final String PROJECT_ID = "bizarremod";
	public static final String PROJECT_NAME = "Wynd's Bizarre Mod";
	public static final String PROJECT_VERSION = "0.1.0";
	public static final String PROJECT_MINECRAFT_VERSION = "1.14.4";

	private static String projectResourceFolder;

	/*
	 * Mod Setup Methods
	 */

	public static void setupResourceFolderPath()
	{
		if (!WyDebug.isDebug())
			return;

		String basicPath = System.getProperty("java.class.path");
		projectResourceFolder = basicPath.substring(0, basicPath.indexOf("\\bin")).replace("file:/", "").replace("%20", " ") + "/src/main/resources";
	}

	public static String getResourceFolderPath()
	{
		return projectResourceFolder;
	}

	/*
	 * Generic API Enviromental Constants
	 */

	public static final String API_VERSION = "1.0.0";
	public static final BuildMode BUILD_MODE = BuildMode.EARLY_ACCESS;

	// Build Types
	public static enum BuildMode
	{
		FINAL, DEV, EARLY_ACCESS
	}

	/*
	 * Ability Constants
	 */

	public static final int MAX_SELECTED_ABILITIES = 2;

	// Ability categories
	public static enum AbilityCategory
	{
		ALL
	}

}
