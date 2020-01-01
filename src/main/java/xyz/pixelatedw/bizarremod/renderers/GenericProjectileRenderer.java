package xyz.pixelatedw.bizarremod.renderers;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.bizarremod.Env;
import xyz.pixelatedw.bizarremod.entities.projectiles.StandProjectileEntity;

@OnlyIn(Dist.CLIENT)
public class GenericProjectileRenderer extends EntityRenderer<Entity>
{
	private final EntityModel model;
	private final String texture;

	public GenericProjectileRenderer(EntityRendererManager renderManager, EntityModel model, String texture)
	{
		super(renderManager);
		this.model = model;
		this.texture = texture;
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		GlStateManager.color4f(1, 1, 1, 1);
		GlStateManager.pushMatrix();
		GlStateManager.disableLighting();
		
		GlStateManager.translatef((float) x, (float) y, (float) z);
		
		GlStateManager.rotated(entity.prevRotationYaw +(entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 180.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotated(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, 1.0F, 0.0F, 0.0F);
		
		this.bindEntityTexture(entity);
		this.model.render(entity, (float) x, (float) y, (float) z, 0.0F, 0.0F, 0.0625F);
		
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
		
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return new ResourceLocation(Env.PROJECT_ID, "textures/models/stands/projectiles/" + this.texture + ".png");
	}

	public static class Factory implements IRenderFactory<StandProjectileEntity>
	{
		private final EntityModel model;
		private final String texture;
		
		public Factory(EntityModel model, String texture)
		{
			this.model = model;
			this.texture = texture;
		}
		
		@Override
		public EntityRenderer<? super StandProjectileEntity> createRenderFor(EntityRendererManager manager)
		{
			return new GenericProjectileRenderer(manager, this.model, this.texture);
		}
	}
}
