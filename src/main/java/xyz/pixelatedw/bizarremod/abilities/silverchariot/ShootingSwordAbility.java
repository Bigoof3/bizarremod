package xyz.pixelatedw.bizarremod.abilities.silverchariot;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.bizarremod.api.abilities.IStandAbility;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class ShootingSwordAbility extends Ability implements IStandAbility
{
	public ShootingSwordAbility()
	{
		super("Shooting Sword", AbilityCategory.ALL);
	}
	
	@Override
	public void renderDescription(FontRenderer fontObj, int posX, int posY)
	{
		this.drawLine("- " + this.getName() + " -", posX + 185, posY + 60);
		this.drawLine(TextFormatting.GREEN + " Active", posX + 183, posY + 72);
		
		this.drawLine("Silver Chariot's only ranged ability", posX + 190, posY + 95);
		this.drawLine("This launches his sword with great force and precision", posX + 190, posY + 110);
		this.drawLine("striking with enough force to pierce a human body.", posX + 190, posY + 125);
		this.drawLine("Silver Chariot can only materialize one rapier.", posX + 190, posY + 140);
	}

}
