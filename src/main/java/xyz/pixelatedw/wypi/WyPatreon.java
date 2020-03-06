package xyz.pixelatedw.wypi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.wypi.APIConfig.BuildMode;
import xyz.pixelatedw.wypi.debug.WyDebug;

public class WyPatreon
{
	private static String urlConnection = "https://pixelatedw.xyz/api/v1";

	public static boolean isDevBuild()
	{
		return APIConfig.BUILD_MODE == BuildMode.DEV;
	}

	public static boolean isEarlyAccessBuild()
	{
		return APIConfig.BUILD_MODE == BuildMode.EARLY_ACCESS;
	}

	public static boolean isReleaseBuild()
	{
		return APIConfig.BUILD_MODE == BuildMode.FINAL;
	}

	private static int getPatreonLevel(PlayerEntity player)
	{
		boolean flag = false;

		String apiURL = "/patreon/" + player.getUniqueID().toString();
		String result = sendGET(apiURL);

		if (!WyHelper.isNullOrEmpty(result))
		{
			String[] groups = result.split(",");

			int patreonLevel = 0;
			for (String group : groups)
			{
				String formattedGroupName = WyHelper.getResourceName(group);

				if (formattedGroupName.equalsIgnoreCase("patreon_rookie"))
					patreonLevel = 1;

				if (formattedGroupName.equalsIgnoreCase("patreon_supernova"))
					patreonLevel = 2;

				if (formattedGroupName.equalsIgnoreCase("patreon_celestial_dragon"))
					patreonLevel = 3;
			}

			return patreonLevel;
		}

		return 0;
	}

	public static boolean isCelestialDragon(PlayerEntity player)
	{
		return getPatreonLevel(player) >= 3;
	}

	public static boolean isSupernova(PlayerEntity player)
	{
		return getPatreonLevel(player) >= 2;
	}

	public static boolean isRookie(PlayerEntity player)
	{
		return getPatreonLevel(player) >= 1;
	}

	public static boolean hasPatreonAccess(PlayerEntity player)
	{
		int patreon = getPatreonLevel(player);

		if (isDevBuild() && WyDebug.isDebug())
			return true;

		if (isDevBuild() && patreon >= 3)
			return true;
		else if (isEarlyAccessBuild() && patreon >= 2)
			return true;
		else
			return false;
	}

	private static String sendGET(String sendUrl)
	{
		String result = "";

		try
		{
			// Actual URL to the API
			URL url = new URL(urlConnection + "" + sendUrl);

			// Opening a connection
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// Setting the properties
			connection.setRequestMethod("GET");
			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK)
			{
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null)
				{
					response.append(inputLine);
				}

				in.close();

				result = response.toString();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return result;
	}

	@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
	public static class PatreonEvents
	{
		@SubscribeEvent
		public static void onEntityJoinWorld(EntityJoinWorldEvent event)
		{
			if (event.getEntity() instanceof PlayerEntity && !event.getWorld().isRemote)
			{
				PlayerEntity player = (PlayerEntity) event.getEntity();

				if (!WyPatreon.isReleaseBuild() && !hasPatreonAccess(player))
				{
					((ServerPlayerEntity) player).connection.disconnect(new StringTextComponent(TextFormatting.BOLD + "" + TextFormatting.RED + "WARNING! \n\n " + TextFormatting.RESET + "You don't have access to this version yet!"));
					event.setCanceled(true);
					return;
				}
			}
		}
	}
}
