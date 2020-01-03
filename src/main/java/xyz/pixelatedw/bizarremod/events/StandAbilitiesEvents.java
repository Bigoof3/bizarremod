package xyz.pixelatedw.bizarremod.events;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.bizarremod.Env;
import xyz.pixelatedw.bizarremod.abilities.Ability;
import xyz.pixelatedw.bizarremod.abilities.PassiveAbility;
import xyz.pixelatedw.bizarremod.api.StandInfo;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.helpers.StandLogicHelper;

@Mod.EventBusSubscriber(modid = Env.PROJECT_ID)
public class StandAbilitiesEvents
{

	@SubscribeEvent
	public static void onLivingUpdate(LivingUpdateEvent event)
	{
		if (event.getEntityLiving() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getEntityLiving();
			IStandData props = StandDataCapability.get(player);

			if (!props.hasStandSummoned())
				return;

			StandInfo info = StandLogicHelper.getStandInfo(props.getStand());

			if (info.getAbilities() == null || info.getAbilities().length <= 0)
				return;

			for (Ability ability : info.getAbilities())
			{
				if (ability instanceof PassiveAbility)
					((PassiveAbility) ability).tick(player);
				else
					ability.cooldown(player);
			}
		}
	}

	@SuppressWarnings("resource")
	@SubscribeEvent
	public static void onRenderUI(RenderGameOverlayEvent.Pre event)
	{
		if(event.getType() == ElementType.CROSSHAIRS)
		{
			Minecraft mc = Minecraft.getInstance();
			PlayerEntity player = mc.player;
			IStandData standProps = StandDataCapability.get(player);
			
			if(standProps == null || player == null)
				return;
			
			int posX = mc.mainWindow.getScaledWidth();
			int posY = mc.mainWindow.getScaledHeight();
			
			GlStateManager.enableAlphaTest();
			GlStateManager.enableBlend();
			
			GlStateManager.color4f(1, 1, 1, 0.5F);
			
			//System.out.println(standProps.getSecondaryAbility().getState());
			
			//WyRenderHelper.drawIcon(ModResourceLocations.LEFT_MOUSE_USED, posX - 40, posY - 40, 32, 32);
						
			GlStateManager.disableBlend();
			GlStateManager.disableAlphaTest();
		}
	}
}
