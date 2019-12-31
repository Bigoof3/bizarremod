package xyz.pixelatedw.bizarremod.abilities.aerosmith;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.bizarremod.abilities.Ability;
import xyz.pixelatedw.bizarremod.api.WyHelper;

public class BombAbility extends Ability
{

	@Override
	public void use(PlayerEntity player)
	{
		
	}

	@Override
	public void renderDescription(FontRenderer fontObj, int posX, int posY)
	{
		WyHelper.drawCenteredString(fontObj, "- Bomb -", posX + 185, posY + 60, -1);
		WyHelper.drawCenteredString(fontObj, TextFormatting.GREEN + " Active", posX + 183, posY + 72, -1);
		
		WyHelper.drawCenteredString(fontObj, "Aerosmith is equipped with small bomb", posX + 190, posY + 95, -1);
		WyHelper.drawCenteredString(fontObj, "that can be dropped on its enemies", posX + 190, posY + 110, -1);
	}

}
