package xyz.pixelatedw.bizarremod.abilities;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.bizarremod.api.StandInfo;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.entities.projectiles.PunchEntity;
import xyz.pixelatedw.bizarremod.helpers.StandLogicHelper;

public class PunchAbility extends Ability
{
	public PunchAbility()
	{
		this.onUseEvent = this::onUse;
	}
	
	@Override
	public String getName()
	{
		return "Punch";
	}

	@Override
	public void renderDescription(FontRenderer fontObj, int posX, int posY)
	{
		this.drawLine("- " + this.getName() + " -", posX + 185, posY + 60);
		this.drawLine(TextFormatting.GREEN + " Active", posX + 183, posY + 72);
		
		this.drawLine("Allows the Stand to attack with it's punch", posX + 190, posY + 95);
	}
	
	protected void onUse(PlayerEntity player, Ability ability)
	{
		IStandData props = StandDataCapability.get(player);
		StandInfo info = StandLogicHelper.getStandInfo(props.getStand());

		PunchEntity punch = new PunchEntity(player, player.world);
		
		punch.setTexture(info.getStandId());
		punch.setDamage(1 + info.getStandEntity(player).getDestructivePower());
		punch.setRange(info.getStandEntity(player).getRange() / 2);
		
		if(punch == null || !props.hasStandSummoned())
			return;

		player.world.addEntity(punch);
		punch.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2 + (info.getStandEntity(player).getSpeed() / 3), 4 - info.getStandEntity(player).getPrecision());
	}
}