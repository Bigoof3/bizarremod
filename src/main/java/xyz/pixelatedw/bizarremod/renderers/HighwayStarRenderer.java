package xyz.pixelatedw.bizarremod.renderers;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.bizarremod.entities.stands.HighwayStarEntity;
import xyz.pixelatedw.bizarremod.models.HighwayStarChaseModel;
import xyz.pixelatedw.bizarremod.models.HighwayStarModel;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;

@OnlyIn(Dist.CLIENT)
public class HighwayStarRenderer extends BipedRenderer<HighwayStarEntity, BipedModel<HighwayStarEntity>>
{
	private static final BipedModel HUMANOID_MODEL = new HighwayStarModel();
	private static final BipedModel CHASE_MODEL = new HighwayStarChaseModel();
	private static final ResourceLocation HUMANOID_TEXTURE = new ResourceLocation(APIConfig.PROJECT_ID, "textures/models/stands/highway_star.png");;
	private static final ResourceLocation CHASE_TEXTURE = new ResourceLocation(APIConfig.PROJECT_ID, "textures/models/stands/highway_star_chase.png");;

	public HighwayStarRenderer(EntityRendererManager renderManager)
	{
		super(renderManager, HUMANOID_MODEL, 0.0F);
	}
	
	@Override
	public void doRender(HighwayStarEntity entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		GlStateManager.color4f(1, 1, 1, 1);
		GlStateManager.pushMatrix();
		GlStateManager.translated(x, y + 1.90, z);
		
		Minecraft.getInstance().textureManager.bindTexture(this.getEntityTexture(entity));

		GlStateManager.rotatef(180.0F, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotatef(180.0F, 0.0F, 1.0F, 0.0F);
		
		float ageInTicks = entity.ticksExisted + partialTicks;
		float headYawOffset = WyHelper.interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, partialTicks);
		float headYaw = WyHelper.interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, partialTicks);
		float headPitch = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;

		WyHelper.rotateCorpse(entity, ageInTicks, headYawOffset, partialTicks);
		float limbSwingAmount = entity.prevLimbSwingAmount + (entity.limbSwingAmount - entity.prevLimbSwingAmount) * partialTicks;
		float limbSwing = entity.limbSwing - entity.limbSwingAmount * (1.0F - partialTicks);
				
		if(entity.isChasing())
		{
			GlStateManager.translated(0, -0.45, 0);
			if(!entity.isInvisible())
				CHASE_MODEL.render(entity, limbSwing, limbSwingAmount, ageInTicks, headYaw - headYawOffset, headPitch, 0.0625F);
		}
		else
		{
			HUMANOID_MODEL.render(entity, limbSwing, limbSwingAmount, ageInTicks, headYaw - headYawOffset, headPitch, 0.0625F);
			//super.doRender(entity, x, y + 0.4F, z, entityYaw, partialTicks);
		}

		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(HighwayStarEntity entity)
	{
		if(entity.isChasing())
			return CHASE_TEXTURE;
		else
			return HUMANOID_TEXTURE;
	}
	
	public static class Factory implements IRenderFactory<HighwayStarEntity>
	{
		@Override
		public EntityRenderer<? super HighwayStarEntity> createRenderFor(EntityRendererManager manager)
		{
			return new HighwayStarRenderer(manager);
		}
	}
}
