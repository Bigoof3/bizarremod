package xyz.pixelatedw.bizarremod.api.abilities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import xyz.pixelatedw.wypi.WyHelper;

public interface IStandAbility
{

	public void renderDescription(FontRenderer fontObj, int posX, int posY);

	public default void drawLine(String text, int x, int y)
	{
		WyHelper.drawCenteredString(Minecraft.getInstance().fontRenderer, text, x, y, -1);
	}

}
