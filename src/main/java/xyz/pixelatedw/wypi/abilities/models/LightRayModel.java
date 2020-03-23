package xyz.pixelatedw.wypi.abilities.models;


import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class LightRayModel extends EntityModel {
	private final RendererModel bone;

	public LightRayModel() {
		textureWidth = 64;
		textureHeight = 64;

		bone = new RendererModel(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 0, -4.0F, -10.0F, -6.0F, 6, 6, 7, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -4.0F, -10.0F, -19.0F, 6, 5, 13, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -4.0F, -10.0F, -23.0F, 6, 4, 4, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5);
	}
	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}