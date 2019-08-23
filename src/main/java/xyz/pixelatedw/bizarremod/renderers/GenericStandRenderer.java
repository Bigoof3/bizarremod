package xyz.pixelatedw.bizarremod.renderers;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.bizarremod.ModValues;
import xyz.pixelatedw.bizarremod.entities.stands.GenericStandEntity;

@OnlyIn(Dist.CLIENT)
public class GenericStandRenderer extends BipedRenderer
{

	private BipedModel model;
	private ResourceLocation texture;
	private float scale;

	public GenericStandRenderer(EntityRendererManager renderManager, BipedModel model, float scale, String texture)
	{
		super(renderManager, model, 0.0F);
		this.model = model;
		this.scale = scale;
		this.texture = new ResourceLocation(ModValues.PROJECT_ID, "textures/models/stands/" + texture + ".png");
	}

	@Override
	public void doRender(MobEntity entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		super.doRender(entity, x, y + 0.4F, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(MobEntity entity)
	{
		return this.texture;
	}

	public static class Factory implements IRenderFactory<GenericStandEntity>
	{
		private BipedModel model;
		private float scale;
		private String texture;

		public Factory(BipedModel model, float scale, String texture)
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
