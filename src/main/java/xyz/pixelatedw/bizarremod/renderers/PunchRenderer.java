package xyz.pixelatedw.bizarremod.renderers;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.bizarremod.ModValues;
import xyz.pixelatedw.bizarremod.entities.projectiles.PunchEntity;
import xyz.pixelatedw.bizarremod.models.CubeModel;

@OnlyIn(Dist.CLIENT)
public class PunchRenderer extends EntityRenderer<PunchEntity>
{
	private CubeModel punch = new CubeModel();

	public PunchRenderer(EntityRendererManager renderManager)
	{
		super(renderManager);
	}

	@Override
	public void doRender(PunchEntity entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		GlStateManager.pushMatrix();
		GlStateManager.translatef((float) x, (float) y + 0.375F, (float) z);
		
		GlStateManager.rotatef(MathHelper.lerp(partialTicks, entity.prevRotationYaw, entity.rotationYaw) - 90.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotatef(MathHelper.lerp(partialTicks, entity.prevRotationPitch, entity.rotationPitch), 0.0F, 0.0F, 1.0F);	
		
		this.bindEntityTexture(entity);
		GL11.glColor4f(1, 1, 1, 1);
		this.punch.render(entity, (float) x, (float) y, (float) z, 0.0F, 0.0F, 0.0625F);
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(PunchEntity entity)
	{
		return new ResourceLocation(ModValues.PROJECT_ID, "textures/models/stands/" + entity.getTexture() + ".png");
	}

	public static class Factory implements IRenderFactory<PunchEntity>
	{
		@Override
		public EntityRenderer<? super PunchEntity> createRenderFor(EntityRendererManager manager)
		{
			return new PunchRenderer(manager);
		}
	}
}
