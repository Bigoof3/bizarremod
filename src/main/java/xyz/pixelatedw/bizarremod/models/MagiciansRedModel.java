package xyz.pixelatedw.bizarremod.models;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import xyz.pixelatedw.bizarremod.entities.stands.GenericStandEntity;

public class MagiciansRedModel extends BipedModel<GenericStandEntity>
{
	public RendererModel right_arm;
	public RendererModel right_leg;
	public RendererModel head;
	public RendererModel body;
	public RendererModel left_arm;
	public RendererModel left_leg;
	public RendererModel lower_beak_1;
	public RendererModel head_hair_1;
	public RendererModel head_hair_2;
	public RendererModel nose_beak_1;
	public RendererModel lower_beak_2;
	public RendererModel head_hair_1_end;
	public RendererModel head_hair_2_end;
	public RendererModel upper_beak_2;

	public MagiciansRedModel()
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
		this.body = new RendererModel(this, 16, 16);
		this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
		this.left_leg = new RendererModel(this, 16, 48);
		this.left_leg.setRotationPoint(1.9F, 12.0F, 0.0F);
		this.left_leg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.upper_beak_2 = new RendererModel(this, 38, 0);
		this.upper_beak_2.setRotationPoint(1.5F, 0.4F, 0.2F);
		this.upper_beak_2.addBox(-1.0F, -0.5F, -2.0F, 2, 1, 2, 0.0F);
		this.setRotateAngle(upper_beak_2, 0.5235987755982988F, 0.0F, 0.0F);
		this.head_hair_1_end = new RendererModel(this, 0, 43);
		this.head_hair_1_end.setRotationPoint(0.0F, -5.1F, 0.3F);
		this.head_hair_1_end.addBox(-1.0F, -2.5F, -1.0F, 2, 2, 3, 0.0F);
		this.setRotateAngle(head_hair_1_end, -0.6981317007977318F, 0.0F, 0.0F);
		this.right_arm = new RendererModel(this, 40, 16);
		this.right_arm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		this.right_arm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
		this.head = new RendererModel(this, 0, 0);
		this.head.setRotationPoint(0.0F, 0.0F, 0.5F);
		this.head.addBox(-3.5F, -8.0F, -4.0F, 7, 8, 7, 0.0F);
		this.right_leg = new RendererModel(this, 0, 16);
		this.right_leg.setRotationPoint(-1.9F, 12.0F, 0.0F);
		this.right_leg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.head_hair_2_end = new RendererModel(this, 0, 43);
		this.head_hair_2_end.setRotationPoint(0.0F, -5.1F, 0.3F);
		this.head_hair_2_end.addBox(-1.0F, -2.5F, -1.0F, 2, 2, 3, 0.0F);
		this.setRotateAngle(head_hair_2_end, -0.6981317007977318F, 0.0F, 0.0F);
		this.head_hair_2 = new RendererModel(this, 7, 35);
		this.head_hair_2.setRotationPoint(0.0F, -5.2F, 0.6F);
		this.head_hair_2.addBox(-0.5F, -6.0F, 0.0F, 1, 4, 2, 0.0F);
		this.setRotateAngle(head_hair_2, -1.3962634015954636F, 0.0F, 0.0F);
		this.nose_beak_1 = new RendererModel(this, 23, 0);
		this.nose_beak_1.setRotationPoint(-1.5F, -2.8F, -7.5F);
		this.nose_beak_1.addBox(0.0F, 0.0F, 0.0F, 3, 2, 4, 0.0F);
		this.setRotateAngle(nose_beak_1, 0.40980330836826856F, 0.0F, 0.0F);
		this.lower_beak_2 = new RendererModel(this, 42, 5);
		this.lower_beak_2.setRotationPoint(0.5F, 0.0F, -2.0F);
		this.lower_beak_2.addBox(-1.5F, 0.0F, -3.0F, 2, 1, 2, 0.0F);
		this.head_hair_1 = new RendererModel(this, 0, 35);
		this.head_hair_1.setRotationPoint(0.0F, -7.4F, -0.5F);
		this.head_hair_1.addBox(-0.5F, -6.0F, 0.0F, 1, 5, 2, 0.0F);
		this.setRotateAngle(head_hair_1, -1.0471975511965976F, 0.0F, 0.0F);
		this.left_arm = new RendererModel(this, 32, 48);
		this.left_arm.setRotationPoint(5.0F, 2.0F, 0.0F);
		this.left_arm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
		this.lower_beak_1 = new RendererModel(this, 30, 7);
		this.lower_beak_1.setRotationPoint(0.0F, -2.0F, -3.0F);
		this.lower_beak_1.addBox(-1.5F, 0.0F, -3.0F, 3, 1, 3, 0.0F);
		this.nose_beak_1.addChild(this.upper_beak_2);
		this.head_hair_1.addChild(this.head_hair_1_end);
		this.head_hair_2.addChild(this.head_hair_2_end);
		this.head.addChild(this.head_hair_2);
		this.head.addChild(this.nose_beak_1);
		this.lower_beak_1.addChild(this.lower_beak_2);
		this.head.addChild(this.head_hair_1);
		this.head.addChild(this.lower_beak_1);
	}

	@Override
	public void render(GenericStandEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		this.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		
		this.body.render(scale);
		this.left_leg.render(scale);
		this.right_arm.render(scale);
		this.head.render(scale);
		this.right_leg.render(scale);
		this.left_arm.render(scale);
	}
	
	@Override
	public void setRotationAngles(GenericStandEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		this.bipedLeftArm = this.left_arm;
		this.bipedRightArm = this.right_arm;
		this.bipedLeftLeg = this.left_leg;
		this.bipedRightLeg = this.right_leg;
		
		super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		
		this.head.rotateAngleY = (netHeadYaw / 60F);
		this.head.rotateAngleX = (headPitch / 60F);
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
