package xyz.pixelatedw.bizarremod.renderers;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.bizarremod.entities.projectiles.RapierEntity;
import xyz.pixelatedw.bizarremod.init.ModItems;

@OnlyIn(Dist.CLIENT)
public class RapierRenderer extends EntityRenderer<RapierEntity>
{
	private final ItemRenderer itemRenderer;
	private final ItemStack stack;

	public RapierRenderer(EntityRendererManager renderManager)
	{
		super(renderManager);

		this.itemRenderer = Minecraft.getInstance().getItemRenderer();
		this.stack = new ItemStack(ModItems.SILVER_CHARIOTS_RAPIER);
	}

	@Override
	public void doRender(RapierEntity entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		GlStateManager.color4f(1, 1, 1, 1);
		//GlStateManager.translatef((float) x, (float) y, (float) z);
		//GlStateManager.pushMatrix();
		/*GlStateManager.enableRescaleNormal();
		GlStateManager.alphaFunc(516, 0.1F);
		GlStateManager.enableBlend();
		RenderHelper.enableStandardItemLighting();
		GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		GlStateManager.pushMatrix();*/
		//GlStateManager.translatef(0.0F, 0.0F, 0.09375F);
		
		//IBakedModel ibakedmodel = this.itemRenderer.getItemModelWithOverrides(this.stack, entity.world, (LivingEntity) null);
		//this.bindEntityTexture(entity);
		//IBakedModel transformedModel = ForgeHooksClient.handleCameraTransforms(ibakedmodel, ItemCameraTransforms.TransformType.GROUND, false);
		//this.itemRenderer.renderItem(this.stack, transformedModel);

		//GlStateManager.popMatrix();
		//GlStateManager.disableRescaleNormal();
		//GlStateManager.disableBlend();
		
		//GlStateManager.popMatrix();

		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(RapierEntity entity)
	{
		return AtlasTexture.LOCATION_BLOCKS_TEXTURE;
	}

	private int transformModelCount(RapierEntity itemIn, double p_177077_2_, double p_177077_4_, double p_177077_6_, float p_177077_8_, IBakedModel p_177077_9_)
	{
		Item item = this.stack.getItem();
		if (item == null)
		{
			return 0;
		}
		else
		{
			boolean flag = p_177077_9_.isGui3d();
			int i = 1;
			float f = 0.25F;
			float f1 = MathHelper.sin((itemIn.getLife() + p_177077_8_) / 10.0F) * 0.1F + 0.1F;
			float f2 = p_177077_9_.getItemCameraTransforms().getTransform(ItemCameraTransforms.TransformType.GROUND).scale.getY();
			GlStateManager.translatef((float) p_177077_2_, (float) p_177077_4_ + f1 + 0.25F * f2, (float) p_177077_6_);
			if (flag || this.renderManager.options != null)
			{
				float f3 = ((itemIn.getLife() + p_177077_8_) / 20.0F) * (180F / (float) Math.PI);
				GlStateManager.rotatef(f3, 0.0F, 1.0F, 0.0F);
			}

			GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
			return i;
		}
	}

	public static class Factory implements IRenderFactory<RapierEntity>
	{
		@Override
		public EntityRenderer<? super RapierEntity> createRenderFor(EntityRendererManager manager)
		{
			return new RapierRenderer(manager);
		}
	}
}
