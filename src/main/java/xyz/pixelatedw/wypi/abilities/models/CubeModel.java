package xyz.pixelatedw.wypi.abilities.models;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;

public class CubeModel extends EntityModel
{
	public RendererModel Shape1;

	public CubeModel()
	{
		this.textureWidth = 64;
		this.textureHeight = 64;
		
		this.Shape1 = new RendererModel(this, 0, 0);
		this.Shape1.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8);
		this.Shape1.setRotationPoint(-5.3F, 7F, -0.1F);
		this.Shape1.setTextureSize(64, 64);
		this.Shape1.mirror = true;
		this.setRotation(this.Shape1, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float scale)
	{
		super.render(entity, f, f1, f2, f3, f4, scale);
		this.setRotationAngles(f, f1, f2, f3, f4, scale);
		this.Shape1.render(scale);
	}

	private void setRotation(RendererModel model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.setRotationAngles(null, f1, f2, f3, f4, f5, f);
	}
}
