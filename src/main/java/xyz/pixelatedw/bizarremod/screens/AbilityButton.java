package xyz.pixelatedw.bizarremod.screens;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.gui.widget.button.Button;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.bizarremod.api.WyRenderHelper;
import xyz.pixelatedw.bizarremod.init.ModResourceLocations;

@OnlyIn(Dist.CLIENT)
public class AbilityButton extends Button
{
	private int smoothScaling = 0;
	private int translate = 0;
	
	public AbilityButton(int x, int y, int width, int height, Button.IPressable onPress)
	{
		super(x, y, 64, 64, "", onPress);
	}

	@Override
	public void renderButton(int x, int y, float f)
	{
		GlStateManager.disableDepthTest();
		GlStateManager.enableBlend();
		int size = 64;		
		
		if (this.isHovered())
		{
			if(size + this.smoothScaling < 74)
				this.smoothScaling++;
			if(this.translate > -5)
				this.translate--;
			size += this.smoothScaling;
		}
		else 
		{
			if(size + this.smoothScaling > 64)
				this.smoothScaling--;
			if(this.translate <= 0)
				this.translate++;
			size += this.smoothScaling;
		}

		GlStateManager.translated(this.translate / 1.5, (this.translate * 2), 0);
		
		WyRenderHelper.drawIcon(ModResourceLocations.ABILITY_SLOT, this.x, this.y, size, size);
		
		GlStateManager.translated(-this.translate / 1.5, -(this.translate * 2), 0);
		
		GlStateManager.disableBlend();
		GlStateManager.enableDepthTest();
	}
}