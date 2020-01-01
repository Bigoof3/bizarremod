package xyz.pixelatedw.bizarremod.events.stands;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.bizarremod.Env;
import xyz.pixelatedw.bizarremod.api.WyRenderHelper;
import xyz.pixelatedw.bizarremod.init.ModPotionEffects;
import xyz.pixelatedw.bizarremod.init.ModResourceLocations;

@Mod.EventBusSubscriber(modid = Env.PROJECT_ID)
public class GreenDayEvents
{

	@SubscribeEvent
	public static void onEntityRendered(RenderLivingEvent.Pre event)
	{
		LivingEntity entity = event.getEntity();
		LivingRenderer renderer = event.getRenderer();
		
		if(!entity.isPotionActive(ModPotionEffects.GREEN_DAY_MOLD))
			return;
		
		if(entity.getActivePotionEffect(ModPotionEffects.GREEN_DAY_MOLD).getDuration() <= 0)
			entity.removePotionEffect(ModPotionEffects.GREEN_DAY_MOLD);
		
		GlStateManager.pushMatrix();
		{
			GlStateManager.translatef((float) event.getX(), (float) event.getY() + 1.45F, (float) event.getZ());

			Minecraft.getInstance().textureManager.bindTexture(ModResourceLocations.GREEN_DAY_MOLD);

			GlStateManager.rotatef(180.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotatef(180.0F, 0.0F, 1.0F, 0.0F);

			GlStateManager.scaled(1.05, 0.9, 1.05);

			float ageInTicks = entity.ticksExisted + event.getPartialRenderTick();
			float headYawOffset = WyRenderHelper.interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, event.getPartialRenderTick());
			float headYaw = WyRenderHelper.interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, event.getPartialRenderTick());
			float headPitch = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * event.getPartialRenderTick();

			WyRenderHelper.rotateCorpse(entity, ageInTicks, headYawOffset, event.getPartialRenderTick());
			float limbSwingAmount = entity.prevLimbSwingAmount + (entity.limbSwingAmount - entity.prevLimbSwingAmount) * event.getPartialRenderTick();
			float limbSwing = entity.limbSwing - entity.limbSwingAmount * (1.0F - event.getPartialRenderTick());

			renderer.getEntityModel().render(entity, limbSwing, limbSwingAmount, ageInTicks, headYaw - headYawOffset, headPitch, 0.0625F);
		}
		GlStateManager.popMatrix();
	}
}