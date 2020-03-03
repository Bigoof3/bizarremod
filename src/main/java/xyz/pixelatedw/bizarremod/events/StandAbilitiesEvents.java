package xyz.pixelatedw.bizarremod.events;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.wypi.APIConfig;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, value = Dist.CLIENT)
public class StandAbilitiesEvents
{
	@SuppressWarnings("resource")
	@SubscribeEvent
	public static void onRenderUI(RenderGameOverlayEvent.Pre event)
	{
		/*if (event.getType() == ElementType.CROSSHAIRS)
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
		}*/
	}
}
