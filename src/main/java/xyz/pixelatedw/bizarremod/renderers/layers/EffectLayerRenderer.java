package xyz.pixelatedw.bizarremod.renderers.layers;

import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.bizarremod.ModValues;

public class EffectLayerRenderer<T extends LivingEntity, M extends BipedModel<T>, A extends BipedModel<T>> extends LayerRenderer<T, EntityModel<T>>
{

	private final ResourceLocation green_mold = new ResourceLocation(ModValues.PROJECT_ID, "textures/misc/green_mold.png");
	private BipedModel model = new BipedModel(0.5F);
	
	public EffectLayerRenderer(IEntityRenderer entityRenderer)
	{
		super(entityRenderer);
	}

	@Override
	public void render(T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		this.bindTexture(this.green_mold);
		this.model.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	}

	@Override
	public boolean shouldCombineTextures()
	{
		return false;
	}

}
