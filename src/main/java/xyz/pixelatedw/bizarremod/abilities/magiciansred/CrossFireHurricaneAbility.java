package xyz.pixelatedw.bizarremod.abilities.magiciansred;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.bizarremod.abilities.Ability;

public class CrossFireHurricaneAbility extends Ability
{
	public CrossFireHurricaneAbility()
	{
		this.setMaxCooldown(20);
	}
	
	@Override
	public String getName()
	{
		return "Cross Fire Hurricane";
	}

	@Override
	public void renderDescription(FontRenderer fontObj, int posX, int posY)
	{
		this.drawLine("- " + this.getName() + " -", posX + 185, posY + 60);
		this.drawLine(TextFormatting.GREEN + " Active", posX + 183, posY + 72);
		
		this.drawLine("Magician's Red can fire a few ankhs of flame", posX + 190, posY + 95);
		this.drawLine("from its mouth.", posX + 190, posY + 110);
	}

}
