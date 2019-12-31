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
		this.drawLine("- Machine Guns -", posX + 185, posY + 60);
		this.drawLine(TextFormatting.GREEN + " Active", posX + 183, posY + 72);
		
		this.drawLine("Aerosmith is equipped with small machine guns", posX + 190, posY + 95);
		this.drawLine("on its wings shooting tracer bullets with", posX + 190, posY + 110);
		this.drawLine("infinite ammunition.", posX + 190, posY + 125);
	}


}
