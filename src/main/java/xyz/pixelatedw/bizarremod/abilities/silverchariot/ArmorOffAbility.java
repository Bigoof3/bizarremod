package xyz.pixelatedw.bizarremod.abilities.silverchariot;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.bizarremod.abilities.Ability;
import xyz.pixelatedw.bizarremod.api.StandInfo;
import xyz.pixelatedw.bizarremod.api.WyHelper;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.entities.stands.SilverChariotEntity;
import xyz.pixelatedw.bizarremod.helpers.StandLogicHelper;

public class ArmorOffAbility extends Ability
{
	public ArmorOffAbility()
	{
		this.onUseEvent = this::onUseEvent;
	}
	
	@Override
	public String getName()
	{
		return "Armor Off";
	}

	@Override
	public void renderDescription(FontRenderer fontObj, int posX, int posY)
	{
		this.drawLine("- " + this.getName() + " -", posX + 185, posY + 60);
		this.drawLine(TextFormatting.GREEN + " Active", posX + 183, posY + 72);
		
		this.drawLine("Increases Silver Chariot's speed and precision", posX + 190, posY + 95);
		this.drawLine("but grealy lowers it's defense.", posX + 190, posY + 110);
	}

	protected void onUseEvent(PlayerEntity player, Ability ability)
	{
		IStandData props = StandDataCapability.get(player);
		StandInfo info = StandLogicHelper.getStandInfo(props.getStand());
		
		for(SilverChariotEntity entity : WyHelper.getNearbyEntities(player.getPosition(), player.world, 20, SilverChariotEntity.class))
		{
			if(entity.getOwner() == player)
			{
				entity.removeArmor();
				break;
			}
		}
	}
}
