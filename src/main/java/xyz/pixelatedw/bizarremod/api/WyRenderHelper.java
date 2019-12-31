package xyz.pixelatedw.bizarremod.api;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class WyRenderHelper
{
	public static void drawIcon(ResourceLocation res, int x, int y, int u, int v)
	{
		Minecraft.getInstance().getTextureManager().bindTexture(res);
		BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(x, y + v, 1).tex(0.0, 1.0).endVertex();
		bufferbuilder.pos(x + u, y + v, 1).tex(1.0, 1.0).endVertex();
		bufferbuilder.pos(x + u, y, 1).tex(1.0, 0.0).endVertex();
		bufferbuilder.pos(x, y, 1).tex(0.0, 0.0).endVertex();
		Tessellator.getInstance().draw();
	}
	
	public static void drawCenteredString(FontRenderer fontObj, String text, int x, int y, int z)
	{
		fontObj.drawStringWithShadow(text, x - fontObj.getStringWidth(text) / 2, y, z);
	}

	public static float handleRotationFloat(LivingEntity entity, float partialTicks)
    {
        return entity.ticksExisted + partialTicks;
    }
	
    public static void rotateCorpse(LivingEntity entityLiving, float ageInTicks, float headYawOffset, float v)
	{
		GL11.glRotatef(180.0F + headYawOffset, 0.0F, 1.0F, 0.0F);

		if (entityLiving.deathTime > 0)
		{
			float f3 = (entityLiving.deathTime + v - 1.0F) / 20.0F * 1.6F;
			f3 = MathHelper.sqrt(f3);

			if (f3 > 1.0F)
			{
				f3 = 1.0F;
			}
		}
	}

	public static float interpolateRotation(float lowerLimit, float upperLimit, float range)
	{
		float f3;

		for (f3 = upperLimit - lowerLimit; f3 < -180.0F; f3 += 360.0F)
		{
			;
		}

		while (f3 >= 180.0F)
		{
			f3 -= 360.0F;
		}

		return lowerLimit + range * f3;
	}
	
}
