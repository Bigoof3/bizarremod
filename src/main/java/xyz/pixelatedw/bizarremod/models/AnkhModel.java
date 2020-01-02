package xyz.pixelatedw.bizarremod.models;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;

public class AnkhModel extends EntityModel
{
	public RendererModel bottom;
	public RendererModel middle;
	public RendererModel top_1;
	public RendererModel top_2;
	public RendererModel top_3;
	public RendererModel top_4;
	public RendererModel top_5;
	public RendererModel top_6;
	public RendererModel top_7;

	public AnkhModel()
	{
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.top_2 = new RendererModel(this, 0, 0);
		this.top_2.setRotationPoint(0.1F, -6.6F, 0.0F);
		this.top_2.addBox(0.0F, 0.0F, 0.0F, 4, 2, 2, 0.0F);
		this.setRotateAngle(top_2, 0.0F, 0.0F, 1.5707963267948966F);
		this.top_3 = new RendererModel(this, 0, 0);
		this.top_3.setRotationPoint(1.6F, -7.3F, 0.0F);
		this.top_3.addBox(0.0F, 0.0F, 0.0F, 3, 2, 2, 0.0F);
		this.setRotateAngle(top_3, 0.0F, 0.0F, 2.356194490192345F);
		this.bottom = new RendererModel(this, 0, 0);
		this.bottom.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bottom.addBox(0.0F, 0.0F, 0.0F, 2, 6, 2, 0.0F);
		this.top_1 = new RendererModel(this, 0, 0);
		this.top_1.setRotationPoint(-0.5F, -4.0F, 0.0F);
		this.top_1.addBox(0.0F, 0.0F, 0.0F, 3, 2, 2, 0.0F);
		this.setRotateAngle(top_1, 0.0F, 0.0F, 0.7853981633974483F);
		this.top_4 = new RendererModel(this, 0, 0);
		this.top_4.setRotationPoint(0.5F, -2.0F, 0.0F);
		this.top_4.addBox(0.0F, 0.0F, 0.0F, 3, 2, 2, 0.0F);
		this.setRotateAngle(top_4, 0.0F, 0.0F, -0.7853981633974483F);
		this.middle = new RendererModel(this, 0, 0);
		this.middle.setRotationPoint(-3.0F, -2.0F, 0.0F);
		this.middle.addBox(0.0F, 0.0F, 0.0F, 8, 2, 2, 0.0F);
		this.top_6 = new RendererModel(this, 0, 0);
		this.top_6.setRotationPoint(2.6F, -5.2F, 0.0F);
		this.top_6.addBox(0.0F, 0.0F, 0.0F, 3, 2, 2, 0.0F);
		this.setRotateAngle(top_6, 0.0F, 0.0F, -2.356194490192345F);
		this.top_7 = new RendererModel(this, 0, 0);
		this.top_7.setRotationPoint(0.0F, -8.65F, 0.0F);
		this.top_7.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
		this.top_5 = new RendererModel(this, 0, 0);
		this.top_5.setRotationPoint(4.0F, -6.6F, 0.0F);
		this.top_5.addBox(0.0F, 0.0F, 0.0F, 4, 2, 2, 0.0F);
		this.setRotateAngle(top_5, 0.0F, 0.0F, 1.5707963267948966F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.top_2.render(f5);
		this.top_3.render(f5);
		this.bottom.render(f5);
		this.top_1.render(f5);
		this.top_4.render(f5);
		this.middle.render(f5);
		this.top_6.render(f5);
		this.top_7.render(f5);
		this.top_5.render(f5);
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
