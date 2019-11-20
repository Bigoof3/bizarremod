package xyz.pixelatedw.bizarremod.abilities.aerosmith;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.bizarremod.abilities.Ability;

public class BombAbility extends Ability
{

	@Override
	public void use(PlayerEntity player)
	{
		
	}

	@Override
	public void renderDescription(FontRenderer fontObj, int posX, int posY)
	{
		fontObj.drawStringWithShadow("- Bomb -", posX + 130, posY + 90, -1);
		fontObj.drawStringWithShadow(TextFormatting.GREEN + " Active", posX + 155, posY + 100, -1);
		
		fontObj.drawStringWithShadow("Aerosmith is equipped with small bomb", posX + 70, posY + 115, -1);
		fontObj.drawStringWithShadow("that can be dropped on its enemies", posX + 70, posY + 125, -1);
	}

}
