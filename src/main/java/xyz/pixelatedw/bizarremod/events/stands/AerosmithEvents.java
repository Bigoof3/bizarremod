package xyz.pixelatedw.bizarremod.events.stands;

import java.util.List;
import java.util.stream.Collectors;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.bizarremod.Consts;
import xyz.pixelatedw.bizarremod.api.StandLogicHelper;
import xyz.pixelatedw.bizarremod.api.stands.GenericStandEntity;
import xyz.pixelatedw.bizarremod.api.stands.StandInfo;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.init.ModResourceLocations;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class AerosmithEvents
{
	@SubscribeEvent
	public static void onRenderUI(RenderGameOverlayEvent event)
	{
		PlayerEntity player = Minecraft.getInstance().player;
		
		IStandData props = StandDataCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);
		StandInfo info = StandLogicHelper.getStandInfo(props.getStand());

		if(!props.getStand().equalsIgnoreCase(Consts.STAND_ID_AEROSMITH) || !props.hasStandSummoned())
			return;

		List<LivingEntity> nearbyEnemies = WyHelper.getEntitiesNear(player.getPosition(), player.world, 15);
		nearbyEnemies = nearbyEnemies.parallelStream().filter(entity -> !(entity instanceof GenericStandEntity)).collect(Collectors.toList());
		nearbyEnemies.remove(player);
		
		int posX = (Minecraft.getInstance().mainWindow.getScaledWidth() - 1480) / 13;
		int posY = (Minecraft.getInstance().mainWindow.getScaledHeight() - 256) / 2;
		
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.enableAlphaTest();
		
		GlStateManager.translated(posX, posY, 0);
		
		GlStateManager.translated(128, 128, 128);
		GlStateManager.scaled(0.4, 0.4, 0);
		
		GlStateManager.translated(-128, -128, -128);
		
		Minecraft.getInstance().getTextureManager().bindTexture(ModResourceLocations.CO_RADAR);
		GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 0);
		
		for (LivingEntity target : nearbyEnemies)
		{
			int targetPosX = target.getPosition().getX();
			int targetPosY = target.getPosition().getY();
			int targetPosZ = target.getPosition().getZ();
			
			GlStateManager.pushMatrix();

			int xDiff = (player.getPosition().getX() - targetPosX) * 5;
			double yDiff = player.getPosition().getY() - targetPosY;
			int zDiff = (player.getPosition().getZ() - targetPosZ) * 5;

			double size = 0.1;
			if(yDiff >= 5)
				size = 0.05;
			else if((yDiff > 0 && yDiff < 5) || (yDiff < 0 && yDiff > -5))
				size = 0.1;
			else if(yDiff <= -5)
				size = 0.15;
			
			GlStateManager.translated(115 + xDiff, 115 + zDiff, 100);
			GlStateManager.scaled(size, size, 0);
						
			Minecraft.getInstance().getTextureManager().bindTexture(ModResourceLocations.CO_TARGET);
			GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 0);
			
			GlStateManager.popMatrix();
		}

		GlStateManager.translated(128, 128, 0);
		
		GlStateManager.scaled(0.15, 0.15, 0);
		float angle = player.rotationYaw;	
		GlStateManager.rotated(angle % 360, 0, 0, 1);
		Minecraft.getInstance().getTextureManager().bindTexture(ModResourceLocations.CO_USER);	
		
		GlStateManager.translated(-128, -128, 0);
		GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 0);

		GlStateManager.popMatrix();
	}

}
