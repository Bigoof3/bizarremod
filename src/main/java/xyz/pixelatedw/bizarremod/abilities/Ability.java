package xyz.pixelatedw.bizarremod.abilities;

import java.io.Serializable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.bizarremod.api.WyRenderHelper;

public abstract class Ability implements Serializable
{
	public abstract String getName();
	
	public abstract void use(PlayerEntity player);
	
	public abstract void renderDescription(FontRenderer fontObj, int posX, int posY);
	
	protected void drawLine(String text, int x, int y)
	{
		WyRenderHelper.drawCenteredString(Minecraft.getInstance().fontRenderer, text, x, y, -1);
	}
}
