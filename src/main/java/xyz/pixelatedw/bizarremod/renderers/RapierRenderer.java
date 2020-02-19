package xyz.pixelatedw.bizarremod.renderers;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.bizarremod.entities.projectiles.RapierEntity;
import xyz.pixelatedw.bizarremod.init.ModItems;

@OnlyIn(Dist.CLIENT)
public class RapierRenderer extends EntityRenderer<RapierEntity>
{
	private final ItemRenderer itemRenderer;
	private final ItemStack stack;
	private long timer = 0;

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
		GlStateManager.pushMatrix();
		GlStateManager.translatef((float) x, (float) y + 0.75F, (float) z);
		GlStateManager.scaled(3, 3, 3);
		
		GlStateManager.rotated(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotated(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks - 180.0F, 0.0F, 0.0F, 1.0F);	
		
		if(!entity.isStuckInGround())
			this.timer = entity.world.getGameTime() * 10 % 360;	
		GlStateManager.rotatef(this.timer, 0.0F, 0.0F, 1.0F);
				
		IBakedModel ibakedmodel = this.itemRenderer.getItemModelWithOverrides(this.stack, entity.world, (LivingEntity) null);
		this.bindEntityTexture(entity);
		IBakedModel transformedModel = ForgeHooksClient.handleCameraTransforms(ibakedmodel, TransformType.GROUND, false);
		this.itemRenderer.renderItem(this.stack, transformedModel);

		GlStateManager.popMatrix();

		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(RapierEntity entity)
	{
		return AtlasTexture.LOCATION_BLOCKS_TEXTURE;
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
