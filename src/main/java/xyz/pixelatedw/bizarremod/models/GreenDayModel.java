package xyz.pixelatedw.bizarremod.models;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import xyz.pixelatedw.bizarremod.api.stands.GenericStandEntity;

public class GreenDayModel extends BipedModel<GenericStandEntity>
{
	public RendererModel right_arm;
	public RendererModel right_leg;
	public RendererModel head;
	public RendererModel body;
	public RendererModel left_arm;
	public RendererModel left_leg;
	public RendererModel right_arm_plate;
	public RendererModel rght_arm_plate_pump_1;
	public RendererModel rght_arm_plate_pump_2;
	public RendererModel rght_arm_plate_pump_3;
	public RendererModel rght_arm_plate_pump_4;
	public RendererModel head_deco;
	public RendererModel head_pump_1;
	public RendererModel head_pump_2;
	public RendererModel head_pump_3;
	public RendererModel head_pump_4;
	public RendererModel head_pump_5;
	public RendererModel head_pump_6;
	public RendererModel head_pump_7;
	public RendererModel head_pump_8;
	public RendererModel head_deco_pump_1;
	public RendererModel head_deco_pump_2;
	public RendererModel head_deco_pump_3;
	public RendererModel head_deco_pump_4;
	public RendererModel left_arm_plate;
	public RendererModel left_arm_plate_pump_1;
	public RendererModel left_arm_plate_pump_2;
	public RendererModel left_arm_plate_pump_3;
	public RendererModel left_arm_plate_pump_4;

	public GreenDayModel()
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
		this.left_arm_plate = new RendererModel(this, 40, 0);
		this.left_arm_plate.setRotationPoint(1.5F, -0.5F, -0.5F);
		this.left_arm_plate.addBox(-3.0F, -2.0F, -2.0F, 5, 5, 5, 0.0F);
		this.rght_arm_plate_pump_3 = new RendererModel(this, 30, 0);
		this.rght_arm_plate_pump_3.setRotationPoint(0.8F, -0.9F, 2.0F);
		this.rght_arm_plate_pump_3.addBox(-0.6F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(rght_arm_plate_pump_3, 0.6981317007977318F, 3.141592653589793F, 0.0F);
		this.head_deco_pump_1 = new RendererModel(this, 30, 0);
		this.head_deco_pump_1.setRotationPoint(0.0F, -2.9F, -1.9F);
		this.head_deco_pump_1.addBox(-0.6F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(head_deco_pump_1, 0.6981317007977318F, 0.0F, 0.0F);
		this.head_pump_5 = new RendererModel(this, 30, 0);
		this.head_pump_5.setRotationPoint(-2.8F, -6.8F, 2.7F);
		this.head_pump_5.addBox(-0.6F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(head_pump_5, 0.3490658503988659F, -3.6651914291880923F, 0.0F);
		this.head_pump_3 = new RendererModel(this, 30, 0);
		this.head_pump_3.setRotationPoint(3.0F, -6.8F, -2.8F);
		this.head_pump_3.addBox(-0.6F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(head_pump_3, 0.3490658503988659F, -0.6981317007977318F, 0.0F);
		this.left_arm_plate_pump_3 = new RendererModel(this, 30, 0);
		this.left_arm_plate_pump_3.setRotationPoint(0.8F, -0.9F, 2.0F);
		this.left_arm_plate_pump_3.addBox(-0.6F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(left_arm_plate_pump_3, 0.6981317007977318F, 3.141592653589793F, 0.0F);
		this.right_leg = new RendererModel(this, 0, 16);
		this.right_leg.setRotationPoint(-1.9F, 12.0F, 0.0F);
		this.right_leg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.body = new RendererModel(this, 16, 16);
		this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
		this.head_deco_pump_2 = new RendererModel(this, 30, 0);
		this.head_deco_pump_2.setRotationPoint(-2.0F, -2.9F, 0.0F);
		this.head_deco_pump_2.addBox(-0.6F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(head_deco_pump_2, 0.6981317007977318F, 1.5707963267948966F, 0.0F);
		this.left_leg = new RendererModel(this, 0, 16);
		this.left_leg.setRotationPoint(1.9F, 12.0F, 0.0F);
		this.left_leg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.head = new RendererModel(this, 0, 0);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-4.0F, -7.0F, -4.0F, 8, 7, 8, 0.0F);
		this.rght_arm_plate_pump_4 = new RendererModel(this, 30, 0);
		this.rght_arm_plate_pump_4.setRotationPoint(-1.7F, -1.0F, 2.0F);
		this.rght_arm_plate_pump_4.addBox(-0.6F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(rght_arm_plate_pump_4, 0.6981317007977318F, 3.141592653589793F, 0.0F);
		this.head_pump_6 = new RendererModel(this, 30, 0);
		this.head_pump_6.setRotationPoint(3.0F, -6.8F, 0.0F);
		this.head_pump_6.addBox(-0.6F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(head_pump_6, 0.3490658503988659F, -1.5707963267948966F, 0.0F);
		this.rght_arm_plate_pump_2 = new RendererModel(this, 30, 0);
		this.rght_arm_plate_pump_2.setRotationPoint(-1.7F, -1.0F, -1.3F);
		this.rght_arm_plate_pump_2.addBox(-0.6F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(rght_arm_plate_pump_2, 0.6981317007977318F, 0.0F, 0.0F);
		this.left_arm_plate_pump_1 = new RendererModel(this, 30, 0);
		this.left_arm_plate_pump_1.setRotationPoint(0.8F, -1.0F, -1.3F);
		this.left_arm_plate_pump_1.addBox(-0.6F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(left_arm_plate_pump_1, 0.6981317007977318F, 0.0F, 0.0F);
		this.head_pump_7 = new RendererModel(this, 30, 0);
		this.head_pump_7.setRotationPoint(0.0F, -6.8F, 3.0F);
		this.head_pump_7.addBox(-0.6F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(head_pump_7, 0.3490658503988659F, -3.141592653589793F, 0.0F);
		this.right_arm = new RendererModel(this, 40, 16);
		this.right_arm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		this.right_arm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
		this.head_deco = new RendererModel(this, 0, 34);
		this.head_deco.setRotationPoint(0.0F, -7.0F, 0.0F);
		this.head_deco.addBox(-2.5F, -5.0F, -2.5F, 5, 5, 5, 0.0F);
		this.head_deco_pump_3 = new RendererModel(this, 30, 0);
		this.head_deco_pump_3.setRotationPoint(2.0F, -2.9F, 0.0F);
		this.head_deco_pump_3.addBox(-0.6F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(head_deco_pump_3, 0.6981317007977318F, 4.71238898038469F, 0.0F);
		this.rght_arm_plate_pump_1 = new RendererModel(this, 30, 0);
		this.rght_arm_plate_pump_1.setRotationPoint(0.8F, -1.0F, -1.3F);
		this.rght_arm_plate_pump_1.addBox(-0.6F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(rght_arm_plate_pump_1, 0.6981317007977318F, 0.0F, 0.0F);
		this.left_arm_plate_pump_4 = new RendererModel(this, 30, 0);
		this.left_arm_plate_pump_4.setRotationPoint(-1.7F, -1.0F, 2.0F);
		this.left_arm_plate_pump_4.addBox(-0.6F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(left_arm_plate_pump_4, 0.6981317007977318F, 3.141592653589793F, 0.0F);
		this.head_pump_2 = new RendererModel(this, 30, 0);
		this.head_pump_2.setRotationPoint(-2.7F, -6.8F, -2.8F);
		this.head_pump_2.addBox(-0.6F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(head_pump_2, 0.3490658503988659F, 0.6981317007977318F, 0.0F);
		this.head_pump_1 = new RendererModel(this, 30, 0);
		this.head_pump_1.setRotationPoint(0.0F, -6.8F, -3.0F);
		this.head_pump_1.addBox(-0.6F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(head_pump_1, 0.3490658503988659F, 0.0F, 0.0F);
		this.head_pump_8 = new RendererModel(this, 30, 0);
		this.head_pump_8.setRotationPoint(-3.0F, -6.8F, 0.0F);
		this.head_pump_8.addBox(-0.6F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(head_pump_8, 0.3490658503988659F, -4.71238898038469F, 0.0F);
		this.left_arm_plate_pump_2 = new RendererModel(this, 30, 0);
		this.left_arm_plate_pump_2.setRotationPoint(-1.7F, -1.0F, -1.3F);
		this.left_arm_plate_pump_2.addBox(-0.6F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(left_arm_plate_pump_2, 0.6981317007977318F, 0.0F, 0.0F);
		this.right_arm_plate = new RendererModel(this, 40, 0);
		this.right_arm_plate.setRotationPoint(-0.5F, -0.5F, -0.5F);
		this.right_arm_plate.addBox(-3.0F, -2.0F, -2.0F, 5, 5, 5, 0.0F);
		this.left_arm = new RendererModel(this, 40, 16);
		this.left_arm.setRotationPoint(5.0F, 2.0F, 0.0F);
		this.left_arm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
		this.head_pump_4 = new RendererModel(this, 30, 0);
		this.head_pump_4.setRotationPoint(2.9F, -6.8F, 2.9F);
		this.head_pump_4.addBox(-0.6F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(head_pump_4, 0.3490658503988659F, -2.0943951023931953F, 0.0F);
		this.head_deco_pump_4 = new RendererModel(this, 30, 0);
		this.head_deco_pump_4.setRotationPoint(0.0F, -2.9F, 2.0F);
		this.head_deco_pump_4.addBox(-0.6F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(head_deco_pump_4, 0.6981317007977318F, 3.141592653589793F, 0.0F);
		this.left_arm.addChild(this.left_arm_plate);
		this.right_arm_plate.addChild(this.rght_arm_plate_pump_3);
		this.head_deco.addChild(this.head_deco_pump_1);
		this.head.addChild(this.head_pump_5);
		this.head.addChild(this.head_pump_3);
		this.left_arm_plate.addChild(this.left_arm_plate_pump_3);
		this.head_deco.addChild(this.head_deco_pump_2);
		this.right_arm_plate.addChild(this.rght_arm_plate_pump_4);
		this.head.addChild(this.head_pump_6);
		this.right_arm_plate.addChild(this.rght_arm_plate_pump_2);
		this.left_arm_plate.addChild(this.left_arm_plate_pump_1);
		this.head.addChild(this.head_pump_7);
		this.head.addChild(this.head_deco);
		this.head_deco.addChild(this.head_deco_pump_3);
		this.right_arm_plate.addChild(this.rght_arm_plate_pump_1);
		this.left_arm_plate.addChild(this.left_arm_plate_pump_4);
		this.head.addChild(this.head_pump_2);
		this.head.addChild(this.head_pump_1);
		this.head.addChild(this.head_pump_8);
		this.left_arm_plate.addChild(this.left_arm_plate_pump_2);
		this.right_arm.addChild(this.right_arm_plate);
		this.head.addChild(this.head_pump_4);
		this.head_deco.addChild(this.head_deco_pump_4);
	}

	@Override
	public void render(GenericStandEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		this.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

		this.right_leg.render(scale);
		this.body.render(scale);
		this.left_leg.render(scale);
		this.head.render(scale);
		this.right_arm.render(scale);
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

	public void setRotateAngle(RendererModel RendererModel, float x, float y, float z)
	{
		RendererModel.rotateAngleX = x;
		RendererModel.rotateAngleY = y;
		RendererModel.rotateAngleZ = z;
	}
}
