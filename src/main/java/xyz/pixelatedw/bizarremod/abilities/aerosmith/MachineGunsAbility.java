package xyz.pixelatedw.bizarremod.abilities.aerosmith;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.bizarremod.abilities.PassiveAbility;
import xyz.pixelatedw.bizarremod.api.WyHelper;

public class MachineGunsAbility extends PassiveAbility
{

	@Override
	public void tick(LivingEntity user)
	{
		
	}

	@Override
	public void renderDescription(FontRenderer fontObj, int posX, int posY)
	{
		WyHelper.drawCenteredString(fontObj, "- Machine Guns -", posX + 185, posY + 60, -1);
		WyHelper.drawCenteredString(fontObj, TextFormatting.GREEN + " Active", posX + 183, posY + 72, -1);
		
		WyHelper.drawCenteredString(fontObj, "Aerosmith is equipped with small machine guns", posX + 190, posY + 95, -1);
		WyHelper.drawCenteredString(fontObj, "on its wings shooting tracer bullets with", posX + 190, posY + 110, -1);
		WyHelper.drawCenteredString(fontObj, "infinite ammunition.", posX + 190, posY + 125, -1);
	}


}
