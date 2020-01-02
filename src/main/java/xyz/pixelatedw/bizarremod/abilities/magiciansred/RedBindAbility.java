package xyz.pixelatedw.bizarremod.abilities.magiciansred;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.bizarremod.abilities.Ability;

public class RedBindAbility extends Ability
{
	public RedBindAbility()
	{
		this.setMaxCooldown(30);
	}
	
	@Override
	public String getName()
	{
		return "Red Bind";
	}

	@Override
	public void renderDescription(FontRenderer fontObj, int posX, int posY)
	{
		this.drawLine("- " + this.getName() + " -", posX + 185, posY + 60);
		this.drawLine(TextFormatting.GREEN + " Active", posX + 183, posY + 72);
		
		this.drawLine("Magician's Red can use his flame to tie his opponent.", posX + 190, posY + 95);
		this.drawLine("By putting a flame too close to the opponent's face,", posX + 190, posY + 110);
		this.drawLine("it eventually burns the oxygen around them to the point", posX + 190, posY + 125);
		this.drawLine("of suffocating them.", posX + 190, posY + 140);
	}

}
