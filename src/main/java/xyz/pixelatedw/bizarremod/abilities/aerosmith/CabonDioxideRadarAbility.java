package xyz.pixelatedw.bizarremod.abilities.aerosmith;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.bizarremod.abilities.PassiveAbility;

public class CabonDioxideRadarAbility extends PassiveAbility
{

	@Override
	public void tick(LivingEntity user)
	{
		
	}

	@Override
	public void renderDescription(FontRenderer fontObj, int posX, int posY)
	{
		fontObj.drawStringWithShadow("- Carbon Dioxide Radio -", posX + 130, posY + 90, -1);
		fontObj.drawStringWithShadow(TextFormatting.AQUA + " Passive", posX + 155, posY + 100, -1);
		
		fontObj.drawStringWithShadow("Used to find an enemy's position through their breath", posX + 70, posY + 115, -1);
		fontObj.drawStringWithShadow("when they exhale.", posX + 70, posY + 125, -1);
		fontObj.drawStringWithShadow("While active, a small mechanical radar will appear", posX + 70, posY + 135, -1);
		fontObj.drawStringWithShadow("on screen showing all targets.", posX + 70, posY + 145, -1);
	}

}
