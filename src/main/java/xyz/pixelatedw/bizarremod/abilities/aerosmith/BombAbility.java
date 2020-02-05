package xyz.pixelatedw.bizarremod.abilities.aerosmith;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.bizarremod.api.abilities.Ability;

public class BombAbility extends Ability
{
	public BombAbility()
	{
		super("Bomb");
	}

	@Override
	public void renderDescription(FontRenderer fontObj, int posX, int posY)
	{
		this.drawLine("- " + this.getName() + " -", posX + 185, posY + 60);
		this.drawLine(TextFormatting.GREEN + " Active", posX + 183, posY + 72);
		
		this.drawLine("Aerosmith is equipped with small bomb", posX + 190, posY + 95);
		this.drawLine("that can be dropped on its enemies", posX + 190, posY + 110);
	}

}
