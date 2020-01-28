package xyz.pixelatedw.bizarremod.models;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import xyz.pixelatedw.bizarremod.entities.stands.SilverChariotEntity;

public class SilverChariotModel extends BipedModel<SilverChariotEntity>
{	
	public RendererModel right_arm;
	public RendererModel right_leg;
	public RendererModel head;
	public RendererModel body;
	public RendererModel left_arm;
	public RendererModel left_leg;
	public RendererModel right_arm_armor_top;
	public RendererModel right_arm_armor_base;
	public RendererModel right_arm_armor_spike_0;
	public RendererModel right_arm_armor_spike_1;
	public RendererModel right_arm_armor_spike_3;
	public RendererModel right_arm_armor_spike_2;
	public RendererModel right_arm_armor_spike_4;
	public RendererModel right_leg_armor;
	public RendererModel lower_body;
	public RendererModel mid_body;
	public RendererModel body_armor_front;
	public RendererModel right_arm_support;
	public RendererModel left_arm_support;
	public RendererModel right_leg_protection;
	public RendererModel lower_body_ax;
	public RendererModel left_leg_protection;
	public RendererModel mid_body_armor;
	public RendererModel body_armor_right_fragment;
	public RendererModel body_armor_left_fragment;
	public RendererModel left_arm_armor_top;
	public RendererModel left_arm_armor_base;
	public RendererModel left_arm_armor_spike_0;
	public RendererModel left_arm_armor_spike_1;
	public RendererModel left_arm_armor_spike_3;
	public RendererModel left_arm_armor_spike_2;
	public RendererModel left_arm_armor_spike_4;
	public RendererModel left_leg_armor;

	private RendererModel[] armorModels;
	
	public SilverChariotModel()
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
		
		this.left_arm_armor_top = new RendererModel(this, 26, 5);
		this.left_leg_armor = new RendererModel(this, 0, 32);
		this.right_arm = new RendererModel(this, 40, 16);
		this.right_arm_armor_spike_3 = new RendererModel(this, 23, 1);
		this.right_arm_armor_spike_2 = new RendererModel(this, 23, 1);
		this.right_leg = new RendererModel(this, 0, 16);
		this.right_arm_armor_top = new RendererModel(this, 26, 5);
		this.right_arm_support = new RendererModel(this, 37, 0);
		this.left_arm = new RendererModel(this, 40, 16);
		this.right_leg_protection = new RendererModel(this, 0, 49);
		this.lower_body_ax = new RendererModel(this, 12, 49);
		this.left_leg = new RendererModel(this, 0, 16);
		this.left_arm_armor_spike_3 = new RendererModel(this, 23, 1);
		this.left_leg_protection = new RendererModel(this, 0, 49);
		this.body_armor_left_fragment = new RendererModel(this, 17, 36);
		this.right_arm_armor_spike_1 = new RendererModel(this, 23, 1);
		this.right_leg_armor = new RendererModel(this, 0, 32);
		this.mid_body = new RendererModel(this, 16, 42);
		this.left_arm_armor_spike_0 = new RendererModel(this, 23, 1);
		this.lower_body = new RendererModel(this, 12, 56);
		this.left_arm_armor_spike_4 = new RendererModel(this, 23, 1);
		this.body_armor_front = new RendererModel(this, 16, 28);
		this.left_arm_support = new RendererModel(this, 37, 0);
		this.right_arm_armor_base = new RendererModel(this, 39, 8);
		this.body_armor_right_fragment = new RendererModel(this, 17, 36);
		this.left_arm_armor_spike_1 = new RendererModel(this, 23, 1);
		this.left_arm_armor_spike_2 = new RendererModel(this, 23, 1);
		this.right_arm_armor_spike_4 = new RendererModel(this, 23, 1);
		this.head = new RendererModel(this, 0, 0);
		this.mid_body_armor = new RendererModel(this, 27, 51);
		this.left_arm_armor_base = new RendererModel(this, 39, 8);
		this.body = new RendererModel(this, 16, 16);
		this.right_arm_armor_spike_0 = new RendererModel(this, 23, 1);

		this.left_arm.addChild(this.left_arm_armor_top);
		this.left_leg.addChild(this.left_leg_armor);
		this.right_arm_armor_top.addChild(this.right_arm_armor_spike_3);
		this.right_arm_armor_top.addChild(this.right_arm_armor_spike_2);
		this.right_arm.addChild(this.right_arm_armor_top);
		this.body.addChild(this.right_arm_support);
		this.lower_body.addChild(this.right_leg_protection);
		this.lower_body.addChild(this.lower_body_ax);
		this.left_arm_armor_top.addChild(this.left_arm_armor_spike_3);
		this.lower_body.addChild(this.left_leg_protection);
		this.body_armor_front.addChild(this.body_armor_left_fragment);
		this.right_arm_armor_top.addChild(this.right_arm_armor_spike_1);
		this.right_leg.addChild(this.right_leg_armor);
		this.body.addChild(this.mid_body);
		this.left_arm_armor_top.addChild(this.left_arm_armor_spike_0);
		this.body.addChild(this.lower_body);
		this.left_arm_armor_top.addChild(this.left_arm_armor_spike_4);
		this.body.addChild(this.body_armor_front);
		this.body.addChild(this.left_arm_support);
		this.right_arm_armor_top.addChild(this.right_arm_armor_base);
		this.body_armor_front.addChild(this.body_armor_right_fragment);
		this.left_arm_armor_top.addChild(this.left_arm_armor_spike_1);
		this.left_arm_armor_top.addChild(this.left_arm_armor_spike_2);
		this.right_arm_armor_top.addChild(this.right_arm_armor_spike_4);
		this.mid_body.addChild(this.mid_body_armor);
		this.left_arm_armor_top.addChild(this.left_arm_armor_base);
		this.right_arm_armor_top.addChild(this.right_arm_armor_spike_0);
		
		this.loadModels();
		
		this.armorModels = new RendererModel[] { this.body_armor_front, this.right_arm_armor_top, this.left_arm_armor_top, this.mid_body_armor, this.left_leg_armor, this.right_leg_armor };
	}

	private void loadModels()
	{
		this.left_arm_armor_top.setRotationPoint(1.0F, -0.5F, 0.0F);
		this.left_arm_armor_top.addBox(0.0F, -2.0F, -2.0F, 2, 4, 4, 0.0F);

		this.left_leg_armor.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.left_leg_armor.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);

		this.right_arm.setRotationPoint(-5.0F, 3.0F, 0.0F);
		this.right_arm.addBox(-2.0F, -2.0F, -1.5F, 3, 11, 3, 0.0F);

		this.right_arm_armor_spike_3.setRotationPoint(0.5F, -0.6F, -2.0F);
		this.right_arm_armor_spike_3.addBox(0.0F, 0.0F, -2.0F, 1, 1, 2, 0.0F);

		this.right_arm_armor_spike_2.setRotationPoint(0.5F, -1.8F, -1.3F);
		this.right_arm_armor_spike_2.addBox(0.0F, 0.0F, -2.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(right_arm_armor_spike_2, -0.7853981633974483F, 0.0F, 0.0F);

		this.right_leg.setRotationPoint(-2.0F, 12.0F, 0.0F);
		this.right_leg.addBox(-1.5F, 0.0F, -1.5F, 3, 12, 3, 0.0F);

		this.right_arm_armor_top.setRotationPoint(-3.8F, -0.5F, 0.0F);
		this.right_arm_armor_top.addBox(0.0F, -2.0F, -2.0F, 2, 4, 4, 0.0F);

		this.right_arm_support.setRotationPoint(-5.0F, 0.0F, -1.5F);
		this.right_arm_support.addBox(0.0F, 0.0F, 0.0F, 1, 3, 3, 0.0F);

		this.left_arm.setRotationPoint(5.0F, 3.0F, 0.0F);
		this.left_arm.addBox(-1.0F, -2.0F, -1.5F, 3, 11, 3, 0.0F);
		
		this.right_leg_protection.setRotationPoint(-4.8F, 0.5F, 0.0F);
		this.right_leg_protection.addBox(0.0F, -2.5F, -2.5F, 2, 5, 5, 0.0F);

		this.lower_body_ax.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.lower_body_ax.addBox(-3.0F, -1.0F, -1.0F, 6, 2, 2, 0.0F);

		this.left_leg.setRotationPoint(2.0F, 12.0F, 0.0F);
		this.left_leg.addBox(-1.5F, 0.0F, -1.5F, 3, 12, 3, 0.0F);

		this.left_arm_armor_spike_3.setRotationPoint(0.5F, -0.6F, -2.0F);
		this.left_arm_armor_spike_3.addBox(0.0F, 0.0F, -2.0F, 1, 1, 2, 0.0F);

		this.left_leg_protection.setRotationPoint(4.8F, 0.5F, 0.0F);
		this.left_leg_protection.addBox(-2.0F, -2.5F, -2.5F, 2, 5, 5, 0.0F);

		this.body_armor_left_fragment.setRotationPoint(1.5F, 5.0F, -2.0F);
		this.body_armor_left_fragment.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);

		this.right_arm_armor_spike_1.setRotationPoint(0.5F, -0.6F, 2.0F);
		this.right_arm_armor_spike_1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);

		this.right_leg_armor.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.right_leg_armor.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);

		this.mid_body.setRotationPoint(-2.0F, 6.5F, -1.5F);
		this.mid_body.addBox(0.0F, 0.0F, 0.0F, 4, 3, 3, 0.0F);

		this.left_arm_armor_spike_0.setRotationPoint(0.5F, -4.0F, -0.5F);
		this.left_arm_armor_spike_0.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);

		this.lower_body.setRotationPoint(0.0F, 11.3F, 0.0F);
		this.lower_body.addBox(-2.0F, -2.0F, -2.0F, 4, 4, 4, 0.0F);

		this.left_arm_armor_spike_4.setRotationPoint(0.5F, -1.8F, 1.3F);
		this.left_arm_armor_spike_4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(left_arm_armor_spike_4, 0.7853981633974483F, 0.0F, 0.0F);

		this.body_armor_front.setRotationPoint(0.5F, 0.0F, -0.8F);
		this.body_armor_front.addBox(-4.0F, 0.0F, -2.0F, 7, 6, 1, 0.0F);

		this.left_arm_support.setRotationPoint(4.0F, 0.0F, -1.5F);
		this.left_arm_support.addBox(0.0F, 0.0F, 0.0F, 1, 3, 3, 0.0F);

		this.right_arm_armor_base.setRotationPoint(0.0F, 4.0F, 0.0F);
		this.right_arm_armor_base.addBox(0.0F, -2.0F, -1.0F, 2, 3, 2, 0.0F);

		this.body_armor_right_fragment.setRotationPoint(-3.5F, 5.0F, -2.0F);
		this.body_armor_right_fragment.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);

		this.left_arm_armor_spike_1.setRotationPoint(0.5F, -0.6F, 2.0F);
		this.left_arm_armor_spike_1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);

		this.left_arm_armor_spike_2.setRotationPoint(0.5F, -1.8F, -1.3F);
		this.left_arm_armor_spike_2.addBox(0.0F, 0.0F, -2.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(left_arm_armor_spike_2, -0.7853981633974483F, 0.0F, 0.0F);

		this.right_arm_armor_spike_4.setRotationPoint(0.5F, -1.8F, 1.3F);
		this.right_arm_armor_spike_4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(right_arm_armor_spike_4, 0.7853981633974483F, 0.0F, 0.0F);

		this.head.setRotationPoint(0.0F, 0.0F, 0.5F);
		this.head.addBox(-3.0F, -7.0F, -3.5F, 6, 7, 6, 0.0F);

		this.mid_body_armor.setRotationPoint(-0.5F, -0.5F, -0.5F);
		this.mid_body_armor.addBox(0.0F, 0.0F, 0.0F, 5, 4, 4, 0.0F);

		this.left_arm_armor_base.setRotationPoint(0.0F, 4.0F, 0.0F);
		this.left_arm_armor_base.addBox(0.0F, -2.0F, -1.0F, 2, 3, 2, 0.0F);

		this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 7, 4, 0.0F);

		this.right_arm_armor_spike_0.setRotationPoint(0.5F, -4.0F, -0.5F);
		this.right_arm_armor_spike_0.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
	}
	
	@Override
	public void render(SilverChariotEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		this.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		
		this.right_arm.render(scale);
		this.right_leg.render(scale);
		this.left_arm.render(scale);
		this.left_leg.render(scale);
		this.head.render(scale);
		this.body.render(scale);
		
		for(int i = 0; i < this.armorModels.length; i++)
		{
			RendererModel model = this.armorModels[i];

			GlStateManager.color4f(0F, 1F, 1F, 0.5F);
			model.render(scale);
			/*if(entity.hasArmor())
			{
				model.isHidden = false;
			}
			else
			{
				model.isHidden = true;
			}*/
		}
	}

	@Override
	public void setRotationAngles(SilverChariotEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{	
		if(entity.hasArmor())
			this.loadModels();
		
		this.bipedLeftArm = this.left_arm;
		this.bipedRightArm = this.right_arm;
		this.bipedLeftLeg = this.left_leg;
		this.bipedRightLeg = this.right_leg;
		
		super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		
		this.head.rotateAngleY = (netHeadYaw / 60F);
		this.head.rotateAngleX = (headPitch / 60F);

		for(int i = 0; i < this.armorModels.length; i++)
		{
			RendererModel model = this.armorModels[i];
			
			if(!entity.hasArmor())
			{
				model.rotationPointY += 0.15F;
				switch(i % 3)
				{
					case 0:
						model.rotateAngleZ += 0.007F;
						model.rotateAngleX -= 0.007F;
						break;
					case 1:
						model.rotateAngleZ += 0.007F;
						model.rotateAngleX += 0.007F;
						break;
					case 2:
						model.rotateAngleZ -= 0.007F;
						model.rotateAngleX -= 0.007F;
						break;
				}
			}
			else
			{
				model.rotateAngleZ = 0F;
				model.rotateAngleX = 0F;
			}
		}
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
