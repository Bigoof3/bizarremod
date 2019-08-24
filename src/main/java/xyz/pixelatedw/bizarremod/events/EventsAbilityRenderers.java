package xyz.pixelatedw.bizarremod.events;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import xyz.pixelatedw.bizarremod.ModValues;

public class EventsAbilityRenderers
{

	@SubscribeEvent
	public void onEntityRendered(RenderLivingEvent.Pre event)
	{
		if(event.getEntity() instanceof PlayerEntity)
		{
			LivingEntity entity = event.getEntity();
			LivingRenderer renderer = event.getRenderer();

			GlStateManager.pushMatrix();
			{
				GlStateManager.translatef((float) event.getX(), (float) event.getY() + 1.3F, (float) event.getZ());
				
				GlStateManager.rotatef(180.0F, 1.0F, 0.0F, 0.0F);
				GlStateManager.rotatef(180.0F, 0.0F, 1.0F, 0.0F);
				
				Minecraft.getInstance().textureManager.bindTexture(new ResourceLocation(ModValues.PROJECT_ID, "textures/misc/green_mold.png"));
				BipedModel model = new BipedModel(0.5F);
				model.render(entity, (float)event.getX(), (float)event.getY(), (float)event.getZ(), 0, 0, 0.0625F);	
			}
			GlStateManager.popMatrix();
		}
	}
	
}