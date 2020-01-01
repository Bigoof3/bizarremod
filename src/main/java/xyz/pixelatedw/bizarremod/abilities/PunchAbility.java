package xyz.pixelatedw.bizarremod.abilities;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.bizarremod.api.StandInfo;
import xyz.pixelatedw.bizarremod.api.WyHelper;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.entities.projectiles.PunchEntity;
import xyz.pixelatedw.bizarremod.entities.stands.GenericStandEntity;
import xyz.pixelatedw.bizarremod.helpers.StandLogicHelper;

public class PunchAbility extends Ability
{
	@Override
	public String getName()
	{
		return "Punch";
	}
	
	@Override
	public void use(PlayerEntity player)
	{
		if(player.world.isRemote)
			return;
		
		IStandData props = StandDataCapability.get(player);
		StandInfo info = StandLogicHelper.getStandInfo(props.getStand());
		GenericStandEntity stand = WyHelper.getNearbyEntities(player.getPosition(), player.world, 2, GenericStandEntity.class).parallelStream().filter(std -> std.getOwner() == player).findFirst().orElse(null);
		
		if(stand == null)
			return;
		
		PunchEntity punch = new PunchEntity(stand, player.world);
		
		punch.setTexture(info.getStandId());
		punch.setDamage(1 + info.getStandEntity(player).getDestructivePower());
		punch.setRange(info.getStandEntity(player).getRange() / 2);
		
		if(punch == null || !props.hasStandSummoned())
			return;

		player.world.addEntity(punch);
		punch.shoot(player, player.rotationPitch, player.rotationYaw, 0, 1 + (info.getStandEntity(player).getSpeed() / 3), 4 - info.getStandEntity(player).getPrecision());
		this.startCooldown();
	}

	@Override
	public void renderDescription(FontRenderer fontObj, int posX, int posY)
	{
		this.drawLine("- " + this.getName() + " -", posX + 185, posY + 60);
		this.drawLine(TextFormatting.GREEN + " Active", posX + 183, posY + 72);
		
		this.drawLine("Allows the Stand to attack with it's punch", posX + 190, posY + 95);
	}
}