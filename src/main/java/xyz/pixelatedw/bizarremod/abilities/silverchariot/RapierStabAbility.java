package xyz.pixelatedw.bizarremod.abilities.silverchariot;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.bizarremod.abilities.Ability;

public class RapierStabAbility extends Ability
{
	public RapierStabAbility()
	{
		
	}
	
	@Override
	public String getName()
	{
		return "Rapier Stab";
	}

	@Override
	public void renderDescription(FontRenderer fontObj, int posX, int posY)
	{
		this.drawLine("- " + this.getName() + " -", posX + 185, posY + 60);
		this.drawLine(TextFormatting.GREEN + " Active", posX + 183, posY + 72);
		
		this.drawLine("Allows the Stand to attack with it's rapier,", posX + 190, posY + 95);
		this.drawLine("longer range than that of a punch.", posX + 190, posY + 110);
	}

}