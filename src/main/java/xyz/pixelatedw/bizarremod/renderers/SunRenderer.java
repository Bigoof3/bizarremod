package xyz.pixelatedw.bizarremod.renderers;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.bizarremod.entities.stands.SunEntity;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.abilities.models.SphereModel;

public class SunRenderer extends EntityRenderer<SunEntity> {

	private static ResourceLocation TEXTURE = new ResourceLocation(APIConfig.PROJECT_ID,
			"textures/models/projectiles/generifc_bullet.png");
	private SphereModel model;

	@Override
	public boolean shouldRender(SunEntity livingEntity, ICamera camera, double camX, double camY, double camZ) {
		
		return true;
	}
	protected SunRenderer(EntityRendererManager renderManager) {
		super(renderManager);
		// TODO Auto-generated constructor stub
		this.model = new SphereModel();
	}

	@Override
	protected ResourceLocation getEntityTexture(SunEntity entity) {
		// TODO Auto-generated method stub
		return TEXTURE;
	}

	@Override
	public void doRender(SunEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {

		GlStateManager.pushMatrix();
		GlStateManager.disableTexture();
		GlStateManager.color4f(1f, 0.4f, 0, 0.5f);
		
		GlStateManager.translatef((float) x, (float) y, (float) z);
		
		
		this.model.render(entity, (float) x, (float) y, (float) z, 0.0F, 0.0F, 5.0625F);
		
		GlStateManager.enableTexture();
		GlStateManager.popMatrix();
		
		super.doRender(entity, x, y, z, entityYaw, partialTicks);

	}
	public static class Factory implements IRenderFactory<SunEntity>
	{
		@Override
		public EntityRenderer<? super SunEntity> createRenderFor(EntityRendererManager manager)
		{
			return new SunRenderer(manager);
		}
	}
}









