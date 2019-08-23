package xyz.pixelatedw.bizarremod.renderers;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.bizarremod.ModValues;
import xyz.pixelatedw.bizarremod.entities.stands.GenericStandEntity;

@OnlyIn(Dist.CLIENT)
public class GenericStandRenderer extends EntityRenderer<GenericStandEntity>
{

	private EntityModel<GenericStandEntity> model;
	private ResourceLocation texture;
	private float scale;

	public GenericStandRenderer(EntityRendererManager renderManager, EntityModel model, float scale, String texture)
	{
		super(renderManager);
		this.model = model;
		this.scale = scale;
		this.texture = new ResourceLocation(ModValues.PROJECT_ID, "textures/models/stands/" + texture + ".png");
	}

	@Override
	public void doRender(GenericStandEntity entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		GlStateManager.pushMatrix();
		GlStateManager.translatef((float)x, (float)y + 1.7F, (float)z);
		this.bindEntityTexture(entity);
		if (this.renderOutlines)
		{
			GlStateManager.enableColorMaterial();
			GlStateManager.setupSolidRenderingTextureCombine(this.getTeamColor(entity));
		}
		
		GlStateManager.rotatef(180, 0, 0, 1);
		GlStateManager.scaled(this.scale, this.scale, this.scale);
		
		this.model.render(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		if (this.renderOutlines)
		{
			GlStateManager.tearDownSolidRenderingTextureCombine();
			GlStateManager.disableColorMaterial();
		}

		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(GenericStandEntity entity)
	{
		return this.texture;
	}

	public static class Factory implements IRenderFactory<GenericStandEntity>
	{
		private EntityModel model;
		private float scale;
		private String texture;

		public Factory(EntityModel model, float scale, String texture)
		{
			this.model = model;
			this.scale = scale;
			this.texture = texture;

			if (this.scale == 0)
				this.scale = 1;			
		}

		@Override
		public EntityRenderer<? super GenericStandEntity> createRenderFor(EntityRendererManager manager)
		{
			return new GenericStandRenderer(manager, this.model, this.scale, this.texture);
		}
	}
}
