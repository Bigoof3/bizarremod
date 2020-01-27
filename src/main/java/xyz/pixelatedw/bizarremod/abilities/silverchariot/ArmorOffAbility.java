package xyz.pixelatedw.bizarremod.abilities.silverchariot;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.bizarremod.abilities.Ability;

public class ArmorOffAbility extends Ability
{
	public ArmorOffAbility()
	{
		
	}
	
	@Override
	public String getName()
	{
		return "Armor Off";
	}

	@Override
	public void renderDescription(FontRenderer fontObj, int posX, int posY)
	{
		this.drawLine("- " + this.getName() + " -", posX + 185, posY + 60);
		this.drawLine(TextFormatting.GREEN + " Active", posX + 183, posY + 72);
		
		this.drawLine("Increases Silver Chariot's speed and precision", posX + 190, posY + 95);
		this.drawLine("but grealy lowers it's defense.", posX + 190, posY + 110);
	}

}
