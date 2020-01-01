package xyz.pixelatedw.bizarremod.models;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;

public class BulletModel extends EntityModel
{
	public RendererModel body;
	public RendererModel head;
	public RendererModel base;
	public RendererModel bottom;

	public BulletModel()
	{
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.head = new RendererModel(this, 0, 0);
		this.head.setRotationPoint(0.0F, 0.0F, -1.0F);
		this.head.addBox(-1.5F, -1.5F, -4.0F, 3, 3, 1, 0.0F);
		this.base = new RendererModel(this, 0, 0);
		this.base.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.base.addBox(-1.5F, -1.5F, 4.0F, 3, 3, 1, 0.0F);
		this.body = new RendererModel(this, 2, 0);
		this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body.addBox(-2.0F, -2.0F, -4.0F, 4, 4, 8, 0.0F);
		this.bottom = new RendererModel(this, 0, 16);
		this.bottom.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bottom.addBox(-2.0F, -2.0F, 5.0F, 4, 4, 1, 0.0F);
		this.body.addChild(this.head);
		this.body.addChild(this.base);
		this.base.addChild(this.bottom);
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.body.render(f5);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(RendererModel RendererModel, float x, float y, float z)
	{
		RendererModel.rotateAngleX = x;
		RendererModel.rotateAngleY = y;
		RendererModel.rotateAngleZ = z;
	}
}
