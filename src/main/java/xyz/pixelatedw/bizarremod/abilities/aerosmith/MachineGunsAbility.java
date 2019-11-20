package xyz.pixelatedw.bizarremod.abilities.aerosmith;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.bizarremod.abilities.PassiveAbility;

public class MachineGunsAbility extends PassiveAbility
{

	@Override
	public void tick(LivingEntity user)
	{
		
	}

	@Override
	public void renderDescription(FontRenderer fontObj, int posX, int posY)
	{
		fontObj.drawStringWithShadow("- Machine Guns -", posX + 130, posY + 90, -1);
		fontObj.drawStringWithShadow(TextFormatting.GREEN + " Passive", posX + 155, posY + 100, -1);
		
		fontObj.drawStringWithShadow("Aerosmith is equipped with small machine guns", posX + 70, posY + 115, -1);
		fontObj.drawStringWithShadow("on its wings shooting tracer bullets with", posX + 70, posY + 125, -1);
		fontObj.drawStringWithShadow("infinite ammunition.", posX + 70, posY + 135, -1);
	}


}
