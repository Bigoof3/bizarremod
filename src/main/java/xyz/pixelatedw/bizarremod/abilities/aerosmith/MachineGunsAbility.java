package xyz.pixelatedw.bizarremod.abilities.aerosmith;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.bizarremod.abilities.Ability;
import xyz.pixelatedw.bizarremod.api.StandInfo;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.entities.projectiles.BulletEntity;
import xyz.pixelatedw.bizarremod.helpers.StandLogicHelper;

public class MachineGunsAbility extends Ability
{
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
		
		BulletEntity punch = new BulletEntity(player, player.world);
		
		punch.setTexture(info.getStandId());
		punch.setDamage(1 + (info.getStandEntity(player).getDestructivePower() / 1.5));
		punch.setRange(info.getStandEntity(player).getRange() * 2.5);
		
		if(punch == null || !props.hasStandSummoned())
			return;

		player.world.addEntity(punch);
		punch.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);		
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
