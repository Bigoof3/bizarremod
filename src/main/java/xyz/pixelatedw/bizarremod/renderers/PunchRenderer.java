package xyz.pixelatedw.bizarremod.renderers;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.bizarremod.Env;
import xyz.pixelatedw.bizarremod.entities.projectiles.StandProjectileEntity;
import xyz.pixelatedw.bizarremod.models.FistModel;

@OnlyIn(Dist.CLIENT)
public class PunchRenderer extends EntityRenderer<StandProjectileEntity>
{
	private FistModel punch = new FistModel();

	public PunchRenderer(EntityRendererManager renderManager)
	{
		super(renderManager);
	}

	@Override
	public void doRender(StandProjectileEntity entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		GlStateManager.color4f(1, 1, 1, 1);
		GlStateManager.pushMatrix();
		GlStateManager.disableLighting();
		
		GlStateManager.translatef((float) x + 0.1F, (float) y, (float) z);
		
		GlStateManager.rotated(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotated(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks - 180.0F, 0.0F, 0.0F, 1.0F);
			
		GlStateManager.rotatef(90.0F, 0.0F, 1.0F, 0.0F);
		
		this.bindEntityTexture(entity);
		this.punch.render(entity, (float) x, (float) y, (float) z, 0.0F, 0.0F, 0.0625F);
		
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
		
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(StandProjectileEntity entity)
	{
		return new ResourceLocation(Env.PROJECT_ID, "textures/models/stands/punches/" + entity.getTexture() + ".png");
	}

	public static class Factory implements IRenderFactory<StandProjectileEntity>
	{
		@Override
		public EntityRenderer<? super StandProjectileEntity> createRenderFor(EntityRendererManager manager)
		{
			return new PunchRenderer(manager);
		}
	}
}
