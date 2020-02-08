package xyz.pixelatedw.bizarremod.events;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.wypi.APIConfig;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class StandAbilitiesEvents
{
	@SuppressWarnings("resource")
	@SubscribeEvent
	public static void onRenderUI(RenderGameOverlayEvent.Pre event)
	{
		if (event.getType() == ElementType.CROSSHAIRS)
		{
			Minecraft mc = Minecraft.getInstance();
			PlayerEntity player = mc.player;
			IStandData standProps = StandDataCapability.get(player);

			if (standProps == null || player == null)
				return;

			int posX = mc.mainWindow.getScaledWidth();
			int posY = mc.mainWindow.getScaledHeight();

			GlStateManager.enableAlphaTest();
			GlStateManager.enableBlend();

			GlStateManager.color4f(1, 1, 1, 0.5F);

			// System.out.println(standProps.getSecondaryAbility().getState());

			// WyRenderHelper.drawIcon(ModResourceLocations.LEFT_MOUSE_USED, posX - 40, posY - 40, 32, 32);

			GlStateManager.disableBlend();
			GlStateManager.disableAlphaTest();
		}
	}
}
