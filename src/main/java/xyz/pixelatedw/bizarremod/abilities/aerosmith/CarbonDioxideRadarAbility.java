package xyz.pixelatedw.bizarremod.abilities.aerosmith;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.bizarremod.api.abilities.IStandAbility;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.PassiveAbility;

public class CarbonDioxideRadarAbility extends PassiveAbility implements IStandAbility
{
	public CarbonDioxideRadarAbility()
	{
		super("Carbon Dioxide Radar", AbilityCategory.ALL);
	}

	@Override
	public void renderDescription(FontRenderer fontObj, int posX, int posY)
	{
		this.drawLine("- " + this.getName() + " -", posX + 185, posY + 60);
		this.drawLine(TextFormatting.AQUA + " Passive", posX + 183, posY + 72);
		
		this.drawLine("Used to find an enemy's position through their", posX + 190, posY + 95);
		this.drawLine("breath when they exhale.", posX + 190, posY + 110);
		this.drawLine("While active, a small mechanical radar will appear", posX + 190, posY + 125);
		this.drawLine("on screen showing all targets.", posX + 190, posY + 140);
	}
}
