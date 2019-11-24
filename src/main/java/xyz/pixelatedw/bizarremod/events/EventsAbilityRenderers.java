package xyz.pixelatedw.bizarremod.events;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import xyz.pixelatedw.bizarremod.ModValues;
import xyz.pixelatedw.bizarremod.init.ModPotionEffects;

public class EventsAbilityRenderers
{

	@SubscribeEvent
	public void onEntityRendered(RenderLivingEvent.Pre event)
	{
		LivingEntity entity = event.getEntity();
		LivingRenderer renderer = event.getRenderer();
		
		if(!entity.isPotionActive(ModPotionEffects.GREEN_DAY_MOLD))
			return;
		
		GlStateManager.pushMatrix();
		{
			GlStateManager.translatef((float) event.getX(), (float) event.getY() + 1.45F, (float) event.getZ());

			Minecraft.getInstance().textureManager.bindTexture(new ResourceLocation(ModValues.PROJECT_ID, "textures/misc/green_mold.png"));

			GlStateManager.rotatef(180.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotatef(180.0F, 0.0F, 1.0F, 0.0F);

			GlStateManager.scaled(1.05, 0.9, 1.05);

			float ageInTicks = entity.ticksExisted + event.getPartialRenderTick();
			float headYawOffset = this.interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, event.getPartialRenderTick());
			float headYaw = this.interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, event.getPartialRenderTick());
			float headPitch = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * event.getPartialRenderTick();

			this.rotateCorpse(entity, ageInTicks, headYawOffset, event.getPartialRenderTick());
			float limbSwingAmount = entity.prevLimbSwingAmount + (entity.limbSwingAmount - entity.prevLimbSwingAmount) * event.getPartialRenderTick();
			float limbSwing = entity.limbSwing - entity.limbSwingAmount * (1.0F - event.getPartialRenderTick());

			renderer.getEntityModel().render(entity, limbSwing, limbSwingAmount, ageInTicks, headYaw - headYawOffset, headPitch, 0.0625F);
		}
		GlStateManager.popMatrix();
	}

    protected float handleRotationFloat(LivingEntity entity, float partialTicks)
    {
        return entity.ticksExisted + partialTicks;
    }
	
	protected void rotateCorpse(LivingEntity entityLiving, float ageInTicks, float headYawOffset, float v)
	{
		GL11.glRotatef(180.0F + headYawOffset, 0.0F, 1.0F, 0.0F);

		if (entityLiving.deathTime > 0)
		{
			float f3 = (entityLiving.deathTime + v - 1.0F) / 20.0F * 1.6F;
			f3 = MathHelper.sqrt(f3);

			if (f3 > 1.0F)
			{
				f3 = 1.0F;
			}
		}
	}

	private float interpolateRotation(float lowerLimit, float upperLimit, float range)
	{
		float f3;

		for (f3 = upperLimit - lowerLimit; f3 < -180.0F; f3 += 360.0F)
		{
			;
		}

		while (f3 >= 180.0F)
		{
			f3 -= 360.0F;
		}

		return lowerLimit + range * f3;
	}

}