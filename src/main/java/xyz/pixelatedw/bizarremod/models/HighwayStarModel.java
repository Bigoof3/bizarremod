package xyz.pixelatedw.bizarremod.models;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import xyz.pixelatedw.bizarremod.api.stands.GenericStandEntity;

public class HighwayStarModel extends BipedModel<GenericStandEntity>
{
	public RendererModel head;
	public RendererModel body;
	public RendererModel left_arm;
	public RendererModel left_leg;
	public RendererModel right_arm;
	public RendererModel right_leg;
	public RendererModel chin_ornament;
	public RendererModel left_arm_upper_part_1;
	public RendererModel left_arm_upper_part_2;
	public RendererModel left_arm_upper_part_3;
	public RendererModel left_arm_middle;
	public RendererModel left_arm_lower_part_1;
	public RendererModel left_arm_lower_part_2;
	public RendererModel left_arm_fist;
	public RendererModel left_leg_upper_part_1;
	public RendererModel left_leg_upper_part_2;
	public RendererModel left_leg_upper_part_3;
	public RendererModel left_leg_middle;
	public RendererModel left_leg_lower_part_1;
	public RendererModel left_leg_lower_part_2;
	public RendererModel left_leg_feet;
	public RendererModel right_arm_upper_part_1;
	public RendererModel right_arm_upper_part_2;
	public RendererModel right_arm_upper_part_3;
	public RendererModel right_arm_middle;
	public RendererModel right_arm_lower_part_1;
	public RendererModel right_arm_lower_part_2;
	public RendererModel right_arm_fist;
	public RendererModel right_leg_upper_part_1;
	public RendererModel right_leg_upper_part_2;
	public RendererModel right_leg_upper_part_3;
	public RendererModel right_leg_middle;
	public RendererModel right_leg_lower_part_1;
	public RendererModel right_leg_lower_part_2;
	public RendererModel right_leg_feet;

	public HighwayStarModel()
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
		
		this.left_arm_fist = new RendererModel(this, 40, 54);
		this.left_arm_fist.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.left_arm_fist.addBox(-1.0F, 8.0F, -1.5F, 3, 2, 3, 0.0F);
		this.left_leg_upper_part_3 = new RendererModel(this, 0, 32);
		this.left_leg_upper_part_3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.left_leg_upper_part_3.addBox(-2.0F, 4.0F, -2.0F, 4, 1, 4, 0.0F);
		this.left_leg_lower_part_1 = new RendererModel(this, 0, 44);
		this.left_leg_lower_part_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.left_leg_lower_part_1.addBox(-2.0F, 8.0F, -2.0F, 4, 1, 4, 0.0F);
		this.right_arm_fist = new RendererModel(this, 40, 54);
		this.right_arm_fist.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.right_arm_fist.addBox(-2.0F, 8.0F, -1.5F, 3, 2, 3, 0.0F);
		this.body = new RendererModel(this, 16, 16);
		this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
		this.right_arm_middle = new RendererModel(this, 40, 37);
		this.right_arm_middle.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.right_arm_middle.addBox(-2.0F, 3.0F, -1.5F, 3, 3, 3, 0.0F);
		this.right_arm_lower_part_1 = new RendererModel(this, 40, 44);
		this.right_arm_lower_part_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.right_arm_lower_part_1.addBox(-2.0F, 6.0F, -1.5F, 3, 1, 3, 0.0F);
		this.left_leg_upper_part_1 = new RendererModel(this, 0, 22);
		this.left_leg_upper_part_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.left_leg_upper_part_1.addBox(-2.0F, 2.0F, -2.0F, 4, 1, 4, 0.0F);
		this.right_leg_upper_part_1 = new RendererModel(this, 0, 22);
		this.right_leg_upper_part_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.right_leg_upper_part_1.addBox(-2.0F, 2.0F, -2.0F, 4, 1, 4, 0.0F);
		this.right_leg_lower_part_2 = new RendererModel(this, 0, 49);
		this.right_leg_lower_part_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.right_leg_lower_part_2.addBox(-2.0F, 9.0F, -2.0F, 4, 1, 4, 0.0F);
		this.right_leg_middle = new RendererModel(this, 0, 37);
		this.right_leg_middle.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.right_leg_middle.addBox(-2.0F, 5.0F, -2.0F, 4, 3, 4, 0.0F);
		this.right_arm_lower_part_2 = new RendererModel(this, 40, 49);
		this.right_arm_lower_part_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.right_arm_lower_part_2.addBox(-2.0F, 7.0F, -1.5F, 3, 1, 3, 0.0F);
		this.right_leg_lower_part_1 = new RendererModel(this, 0, 44);
		this.right_leg_lower_part_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.right_leg_lower_part_1.addBox(-2.0F, 8.0F, -2.0F, 4, 1, 4, 0.0F);
		this.left_leg = new RendererModel(this, 0, 16);
		this.left_leg.setRotationPoint(1.9F, 12.0F, 0.0F);
		this.left_leg.addBox(-2.0F, 0.0F, -2.0F, 4, 2, 4, 0.0F);
		this.left_arm_upper_part_3 = new RendererModel(this, 40, 32);
		this.left_arm_upper_part_3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.left_arm_upper_part_3.addBox(-1.0F, 2.0F, -1.5F, 3, 1, 3, 0.0F);
		this.left_arm_middle = new RendererModel(this, 40, 37);
		this.left_arm_middle.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.left_arm_middle.addBox(-1.0F, 3.0F, -1.5F, 3, 3, 3, 0.0F);
		this.left_arm_upper_part_1 = new RendererModel(this, 40, 22);
		this.left_arm_upper_part_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.left_arm_upper_part_1.addBox(-1.0F, 0.0F, -1.5F, 3, 1, 3, 0.0F);
		this.right_arm_upper_part_1 = new RendererModel(this, 40, 22);
		this.right_arm_upper_part_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.right_arm_upper_part_1.addBox(-2.0F, 0.0F, -1.5F, 3, 1, 3, 0.0F);
		this.left_leg_lower_part_2 = new RendererModel(this, 0, 49);
		this.left_leg_lower_part_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.left_leg_lower_part_2.addBox(-2.0F, 9.0F, -2.0F, 4, 1, 4, 0.0F);
		this.left_arm_upper_part_2 = new RendererModel(this, 40, 27);
		this.left_arm_upper_part_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.left_arm_upper_part_2.addBox(-1.0F, 1.0F, -1.5F, 3, 1, 3, 0.0F);
		this.right_arm_upper_part_2 = new RendererModel(this, 40, 27);
		this.right_arm_upper_part_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.right_arm_upper_part_2.addBox(-2.0F, 1.0F, -1.5F, 3, 1, 3, 0.0F);
		this.left_arm_lower_part_2 = new RendererModel(this, 40, 49);
		this.left_arm_lower_part_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.left_arm_lower_part_2.addBox(-1.0F, 7.0F, -1.5F, 3, 1, 3, 0.0F);
		this.head = new RendererModel(this, 0, 0);
		this.head.setRotationPoint(0.5F, 0.0F, 0.5F);
		this.head.addBox(-4.0F, -8.0F, -4.0F, 7, 8, 7, 0.0F);
		this.left_arm_lower_part_1 = new RendererModel(this, 40, 44);
		this.left_arm_lower_part_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.left_arm_lower_part_1.addBox(-1.0F, 6.0F, -1.5F, 3, 1, 3, 0.0F);
		this.left_leg_middle = new RendererModel(this, 0, 37);
		this.left_leg_middle.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.left_leg_middle.addBox(-2.0F, 5.0F, -2.0F, 4, 3, 4, 0.0F);
		this.right_leg_feet = new RendererModel(this, 0, 54);
		this.right_leg_feet.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.right_leg_feet.addBox(-2.0F, 10.0F, -2.0F, 4, 2, 4, 0.0F);
		this.right_arm_upper_part_3 = new RendererModel(this, 40, 32);
		this.right_arm_upper_part_3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.right_arm_upper_part_3.addBox(-2.0F, 2.0F, -1.5F, 3, 1, 3, 0.0F);
		this.left_leg_upper_part_2 = new RendererModel(this, 0, 27);
		this.left_leg_upper_part_2.setRotationPoint(0.0F, 2.0F, 0.0F);
		this.left_leg_upper_part_2.addBox(-2.0F, 1.0F, -2.0F, 4, 1, 4, 0.0F);
		this.right_leg_upper_part_2 = new RendererModel(this, 0, 27);
		this.right_leg_upper_part_2.setRotationPoint(0.0F, 2.0F, 0.0F);
		this.right_leg_upper_part_2.addBox(-2.0F, 1.0F, -2.0F, 4, 1, 4, 0.0F);
		this.right_leg = new RendererModel(this, 0, 16);
		this.right_leg.setRotationPoint(-1.9F, 12.0F, 0.0F);
		this.right_leg.addBox(-2.0F, 0.0F, -2.0F, 4, 2, 4, 0.0F);
		this.chin_ornament = new RendererModel(this, 0, 0);
		this.chin_ornament.setRotationPoint(-0.5F, 0.0F, -3.5F);
		this.chin_ornament.addBox(-1.0F, 0.0F, 0.0F, 2, 3, 0, 0.0F);
		this.setRotateAngle(chin_ornament, -0.091106186954104F, 0.0F, 0.0F);
		this.left_leg_feet = new RendererModel(this, 0, 54);
		this.left_leg_feet.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.left_leg_feet.addBox(-2.0F, 10.0F, -2.0F, 4, 2, 4, 0.0F);
		this.right_leg_upper_part_3 = new RendererModel(this, 0, 32);
		this.right_leg_upper_part_3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.right_leg_upper_part_3.addBox(-2.0F, 4.0F, -2.0F, 4, 1, 4, 0.0F);
		this.left_arm = new RendererModel(this, 40, 16);
		this.left_arm.setRotationPoint(5.0F, 2.0F, 0.0F);
		this.left_arm.addBox(-1.0F, -2.0F, -1.5F, 3, 2, 3, 0.0F);
		this.right_arm = new RendererModel(this, 40, 16);
		this.right_arm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		this.right_arm.addBox(-2.0F, -2.0F, -1.5F, 3, 2, 3, 0.0F);
		this.left_arm.addChild(this.left_arm_fist);
		this.left_leg.addChild(this.left_leg_upper_part_3);
		this.left_leg.addChild(this.left_leg_lower_part_1);
		this.right_arm.addChild(this.right_arm_fist);
		this.right_arm.addChild(this.right_arm_middle);
		this.right_arm.addChild(this.right_arm_lower_part_1);
		this.left_leg.addChild(this.left_leg_upper_part_1);
		this.right_leg.addChild(this.right_leg_upper_part_1);
		this.right_leg.addChild(this.right_leg_lower_part_2);
		this.right_leg.addChild(this.right_leg_middle);
		this.right_arm.addChild(this.right_arm_lower_part_2);
		this.right_leg.addChild(this.right_leg_lower_part_1);
		this.left_arm.addChild(this.left_arm_upper_part_3);
		this.left_arm.addChild(this.left_arm_middle);
		this.left_arm.addChild(this.left_arm_upper_part_1);
		this.right_arm.addChild(this.right_arm_upper_part_1);
		this.left_leg.addChild(this.left_leg_lower_part_2);
		this.left_arm.addChild(this.left_arm_upper_part_2);
		this.right_arm.addChild(this.right_arm_upper_part_2);
		this.left_arm.addChild(this.left_arm_lower_part_2);
		this.left_arm.addChild(this.left_arm_lower_part_1);
		this.left_leg.addChild(this.left_leg_middle);
		this.right_leg.addChild(this.right_leg_feet);
		this.right_arm.addChild(this.right_arm_upper_part_3);
		this.left_leg.addChild(this.left_leg_upper_part_2);
		this.right_leg.addChild(this.right_leg_upper_part_2);
		this.head.addChild(this.chin_ornament);
		this.left_leg.addChild(this.left_leg_feet);
		this.right_leg.addChild(this.right_leg_upper_part_3);
	}

	@Override
	public void render(GenericStandEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		this.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		
		this.body.render(scale);
		this.left_leg.render(scale);
		this.head.render(scale);
		this.right_leg.render(scale);
		this.left_arm.render(scale);
		this.right_arm.render(scale);
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

	public void setRotateAngle(RendererModel modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
