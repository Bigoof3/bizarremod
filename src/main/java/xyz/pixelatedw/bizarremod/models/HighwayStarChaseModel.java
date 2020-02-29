package xyz.pixelatedw.bizarremod.models;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import xyz.pixelatedw.bizarremod.api.stands.GenericStandEntity;

public class HighwayStarChaseModel extends BipedModel<GenericStandEntity>
{
	public RendererModel feet1;
	public RendererModel feet2;
	public RendererModel feet3;
	public RendererModel feet4;

	public HighwayStarChaseModel()
	{
		this.bipedBody.showModel = false;
		this.bipedHead.showModel = false;
		this.bipedLeftArm.showModel = false;
		this.bipedLeftLeg.showModel = false;
		this.bipedRightArm.showModel = false;
		this.bipedRightLeg.showModel = false;
		this.bipedHeadwear.showModel = false;
		
		this.textureWidth = 64;
		this.textureHeight = 64;
		
		this.feet2 = new RendererModel(this, 0, 0);
		this.feet2.setRotationPoint(4.0F, 20.0F, 0.0F);
		this.feet2.addBox(-2.0F, 0.0F, -3.0F, 4, 1, 6, 0.0F);
		this.feet3 = new RendererModel(this, 0, 0);
		this.feet3.setRotationPoint(-4.0F, 20.0F, 16.0F);
		this.feet3.addBox(-2.0F, 0.0F, -3.0F, 4, 1, 6, 0.0F);
		this.feet4 = new RendererModel(this, 0, 0);
		this.feet4.setRotationPoint(4.0F, 20.0F, 16.0F);
		this.feet4.addBox(-2.0F, 0.0F, -3.0F, 4, 1, 6, 0.0F);
		this.feet1 = new RendererModel(this, 0, 0);
		this.feet1.setRotationPoint(-4.0F, 20.0F, 0.0F);
		this.feet1.addBox(-2.0F, 0.0F, -3.0F, 4, 1, 6, 0.0F);
	}

	@Override
	public void render(GenericStandEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		this.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

		this.feet2.render(scale);
		this.feet3.render(scale);
		this.feet4.render(scale);
		this.feet1.render(scale);
	}

	@Override
	public void setRotationAngles(GenericStandEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{

	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(RendererModel modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
