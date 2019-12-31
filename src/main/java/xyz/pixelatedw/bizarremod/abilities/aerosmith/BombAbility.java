package xyz.pixelatedw.bizarremod.abilities.aerosmith;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.bizarremod.abilities.Ability;

public class BombAbility extends Ability
{
	@Override
	public String getName()
	{
		return "Bomb";
	}
	
	@Override
	public void use(PlayerEntity player)
	{
		
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
