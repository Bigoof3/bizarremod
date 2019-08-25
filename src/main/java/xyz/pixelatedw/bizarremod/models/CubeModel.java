package xyz.pixelatedw.bizarremod.models;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;

public class CubeModel extends EntityModel
{
	RendererModel cube;

	public CubeModel()
	{
		textureWidth = 64;
		textureHeight = 64;

		cube = new RendererModel(this, 0, 0);
		cube.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8);
		cube.setRotationPoint(0F, 0F, 0F);
		cube.setTextureSize(64, 64);
		cube.mirror = true;
		setRotation(cube, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(entity, f, f1, f2, f3, f4, f5);
		cube.render(f5);
	}

	private void setRotation(RendererModel model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(Entity ent, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.setRotationAngles(ent, f1, f2, f3, f4, f5, f);
	}
}