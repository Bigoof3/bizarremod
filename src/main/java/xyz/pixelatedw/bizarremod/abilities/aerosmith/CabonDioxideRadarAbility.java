package xyz.pixelatedw.bizarremod.abilities.aerosmith;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.bizarremod.abilities.PassiveAbility;
import xyz.pixelatedw.bizarremod.api.WyHelper;

public class CabonDioxideRadarAbility extends PassiveAbility
{

	@Override
	public void tick(LivingEntity user)
	{
		
	}

	@Override
	public void renderDescription(FontRenderer fontObj, int posX, int posY)
	{
		WyHelper.drawCenteredString(fontObj, "- Carbon Dioxide Radio -", posX + 185, posY + 60, -1);
		WyHelper.drawCenteredString(fontObj, TextFormatting.AQUA + " Passive", posX + 183, posY + 72, -1);
		
		WyHelper.drawCenteredString(fontObj, "Used to find an enemy's position through their", posX + 190, posY + 95, -1);
		WyHelper.drawCenteredString(fontObj, "breath when they exhale.", posX + 190, posY + 110, -1);
		WyHelper.drawCenteredString(fontObj, "While active, a small mechanical radar will appear", posX + 190, posY + 125, -1);
		WyHelper.drawCenteredString(fontObj, "on screen showing all targets.", posX + 190, posY + 140, -1);
	}

}
