package xyz.pixelatedw.bizarremod.api;

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
import xyz.pixelatedw.bizarremod.Env;

public class WyPatreon
{
	private static String urlConnection = "https://pixelatedw.xyz/api";

	private static int getPatreonLevel(PlayerEntity player)
	{
		boolean flag = false;

		String apiURL = "/patreon?uuid=" + player.getUniqueID().toString();
		String result = sendGET(apiURL);

		if (!WyHelper.isNullOrEmpty(result))
		{
			int patreonLevel = Integer.parseInt(result);

			return patreonLevel;
		}

		return 1;
	}
	
	public static boolean isCelestialDragon(PlayerEntity player)
	{
		return getPatreonLevel(player) == 4;
	}

	public static boolean isSupernova(PlayerEntity player)
	{
		return getPatreonLevel(player) == 3;
	}

	public static boolean isRookie(PlayerEntity player)
	{
		return getPatreonLevel(player) == 2;
	}

	public static boolean hasPatreonAccess(PlayerEntity player)
	{
		int patreon = getPatreonLevel(player);

		if (WyHelper.isDevBuild() && WyHelper.isDebug())
			return true;

		if (WyHelper.isDevBuild() && patreon >= 4)
			return true;
		else if (WyHelper.isEarlyAccessBuild() && patreon >= 3)
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
	
	@Mod.EventBusSubscriber(modid = Env.PROJECT_ID)
	public static class PatreonEvents
	{
		@SubscribeEvent
		public static void onEntityJoinWorld(EntityJoinWorldEvent event)
		{
			if (event.getEntity() instanceof PlayerEntity && !event.getWorld().isRemote)
			{
				PlayerEntity player = (PlayerEntity) event.getEntity();

				if(!WyHelper.isReleaseBuild() && !hasPatreonAccess(player))
				{
					((ServerPlayerEntity)player).connection.disconnect(new StringTextComponent(TextFormatting.BOLD + "" + TextFormatting.RED + "WARNING! \n\n " + TextFormatting.RESET + "You don't have access to this version yet!"));
					event.setCanceled(true);
					return;
				}
			}
		}
	}
}
