package xyz.pixelatedw.bizarremod.abilities.highwaystar;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.bizarremod.api.abilities.IStandAbility;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class RoomTrapAbility extends Ability implements IStandAbility
{
	public RoomTrapAbility()
	{
		super("Room Trap", AbilityCategory.ALL);
	}
	
	@Override
	public void renderDescription(FontRenderer fontObj, int posX, int posY)
	{
		this.drawLine("- " + this.getName() + " -", posX + 185, posY + 60);
		this.drawLine(TextFormatting.YELLOW + " Active (Continuous)", posX + 183, posY + 72);
		
		this.drawLine("Sets up a trap inside any room.", posX + 190, posY + 95);
		this.drawLine("Anyone who enters the room is targeted by Highway Star", posX + 190, posY + 110);
		this.drawLine("Once it has hold of its victim's scent, it begins to ", posX + 190, posY + 125);
		this.drawLine("relentlessly chase them anywhere until either one dies.", posX + 190, posY + 140);
	}

}
