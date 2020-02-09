package xyz.pixelatedw.wypi.abilities.renderers;

import java.awt.Color;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.models.CubeModel;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

@OnlyIn(Dist.CLIENT)
public class AbilityProjectileRenderer extends EntityRenderer<AbilityProjectileEntity>
{
	private double scaleX = 1, scaleY = 1, scaleZ = 1;
	private double offsetX = 0, offsetY = 0, offsetZ = 0;
	private double red, blue, green, alpha;
	private EntityModel model;
	private ResourceLocation texture;

	public AbilityProjectileRenderer(EntityRendererManager renderManager, EntityModel model)
	{
		super(renderManager);
		this.model = model;
	}

	public void setTexture(ResourceLocation res)
	{
		this.texture = res;
	}

	public void setColor(double red, double green, double blue, double alpha)
	{
		this.red = red / 255;
		this.green = green / 255;
		this.blue = blue / 255;
		this.alpha = alpha / 255;
	}

	public void setScale(double scaleX, double scaleY, double scaleZ)
	{
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.scaleZ = scaleZ;
	}

	public void setOffset(double offsetX, double offsetY, double offsetZ)
	{
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.offsetZ = offsetZ;
	}
	
	@Override
	public void doRender(AbilityProjectileEntity entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		GlStateManager.color4f(1, 1, 1, 1);
		GlStateManager.pushMatrix();
		{
			GlStateManager.translated(x, y + 0.25, z);
			
			if (this.texture == null)
				GlStateManager.disableTexture();
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);

			GlStateManager.rotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 180.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotatef(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, 1.0F, 0.0F, 0.0F);

			GlStateManager.rotatef(180, 0, 0, 1);

			GlStateManager.color4f((float) this.red, (float) this.green, (float) this.blue, (float) this.alpha);
			GlStateManager.scaled(this.scaleX, this.scaleY, this.scaleZ);
			GlStateManager.translated(0, -(this.scaleY / 10), 0);

			if (this.texture != null)
				Minecraft.getInstance().textureManager.bindTexture(this.getEntityTexture(entity));

			if (this.model != null)
				this.model.render(entity, (float) x, (float) y, (float) z, 0.0F, 0.0F, 0.0625F);

			GlStateManager.disableBlend();
			if (this.texture == null)
				GlStateManager.enableTexture();
		}
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(AbilityProjectileEntity entity)
	{
		return this.texture;
	}

	public static class Factory implements IRenderFactory<AbilityProjectileEntity>
	{
		private EntityModel model = new CubeModel();
		private double scaleX = 1, scaleY = 1, scaleZ = 1;
		private double offsetX = 0, offsetY = 0, offsetZ = 0;
		private double red = 255, green = 255, blue = 255, alpha = 255;
		private ResourceLocation texture;

		public Factory(EntityModel model)
		{
			this.model = model;
		}

		public Factory setTexture(String textureName)
		{
			this.texture = new ResourceLocation(APIConfig.PROJECT_ID, "textures/models/projectiles/" + textureName + ".png");
			return this;
		}

		public Factory setColor(double red, double green, double blue, double alpha)
		{
			this.red = red;
			this.green = green;
			this.blue = blue;
			this.alpha = alpha;
			return this;
		}

		public Factory setColor(String hex)
		{
			Color color = WyHelper.hexToRGB(hex);
			this.red = color.getRed();
			this.green = color.getGreen();
			this.blue = color.getBlue();
			this.alpha = color.getAlpha();
			return this;
		}

		public Factory setScale(double scale)
		{
			this.scaleX = this.scaleY = this.scaleZ = scale;
			return this;
		}

		public Factory setScale(double scaleX, double scaleY, double scaleZ)
		{
			this.scaleX = scaleX;
			this.scaleY = scaleY;
			this.scaleZ = scaleZ;
			return this;
		}
		
		public Factory setOffset(double offsetX, double offsetY, double offsetZ)
		{
			this.offsetX = offsetX;
			this.offsetY = offsetY;
			this.offsetZ = offsetZ;
			return this;
		}

		@Override
		public EntityRenderer<? super AbilityProjectileEntity> createRenderFor(EntityRendererManager manager)
		{
			AbilityProjectileRenderer renderer = new AbilityProjectileRenderer(manager, this.model);
			renderer.setTexture(this.texture);
			renderer.setScale(this.scaleX, this.scaleY, this.scaleZ);
			renderer.setOffset(this.offsetX, this.offsetY, this.offsetZ);
			renderer.setColor(this.red, this.green, this.blue, this.alpha);
			return renderer;
		}
	}

}
