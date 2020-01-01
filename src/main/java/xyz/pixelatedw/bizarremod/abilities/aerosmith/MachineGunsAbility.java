package xyz.pixelatedw.bizarremod.abilities.aerosmith;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.bizarremod.abilities.Ability;
import xyz.pixelatedw.bizarremod.api.StandInfo;
import xyz.pixelatedw.bizarremod.api.WyHelper;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.entities.projectiles.BulletEntity;
import xyz.pixelatedw.bizarremod.entities.stands.GenericStandEntity;
import xyz.pixelatedw.bizarremod.helpers.StandLogicHelper;

public class MachineGunsAbility extends Ability
{
	public MachineGunsAbility()
	{
		this.setCooldown(200);
	}
	
	@Override
	public String getName()
	{
		return "Machine Guns";
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
		
		BulletEntity bullet = new BulletEntity(stand, player.world);
		
		bullet.setTexture(info.getStandId());
		bullet.setDamage(1 + info.getStandEntity(player).getDestructivePower());
		bullet.setRange(info.getStandEntity(player).getRange() / 2);
		
		if(bullet == null || !props.hasStandSummoned())
			return;

		player.world.addEntity(bullet);
		bullet.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2 + info.getStandEntity(player).getSpeed(), 4 - info.getStandEntity(player).getPrecision());
		this.startCooldown();	
	}

	@Override
	public void renderDescription(FontRenderer fontObj, int posX, int posY)
	{
		this.drawLine("- " + this.getName() + " -", posX + 185, posY + 60);
		this.drawLine(TextFormatting.GREEN + " Active", posX + 183, posY + 72);
		
		this.drawLine("Aerosmith is equipped with small machine guns", posX + 190, posY + 95);
		this.drawLine("on its wings shooting tracer bullets with", posX + 190, posY + 110);
		this.drawLine("infinite ammunition.", posX + 190, posY + 125);
	}

}
