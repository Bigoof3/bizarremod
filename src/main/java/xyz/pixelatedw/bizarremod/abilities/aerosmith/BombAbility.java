package xyz.pixelatedw.bizarremod.abilities.aerosmith;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.bizarremod.api.StandLogicHelper;
import xyz.pixelatedw.bizarremod.api.abilities.IStandAbility;
import xyz.pixelatedw.bizarremod.api.stands.StandInfo;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.entities.projectiles.BombEntity;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class BombAbility extends Ability implements IStandAbility
{
	public BombAbility()
	{
		super("Bomb", AbilityCategory.ALL);
		this.setMaxCooldown(10);

		this.onUseEvent = this::onUseEvent;
	}

	@Override
	public void renderDescription(FontRenderer fontObj, int posX, int posY)
	{
		this.drawLine("- " + this.getName() + " -", posX + 185, posY + 60);
		this.drawLine(TextFormatting.GREEN + " Active", posX + 183, posY + 72);
		
		this.drawLine("Aerosmith is equipped with small bomb", posX + 190, posY + 95);
		this.drawLine("that can be dropped on its enemies", posX + 190, posY + 110);
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		IStandData props = StandDataCapability.get(player);
		StandInfo info = StandLogicHelper.getStandInfo(props.getStand());
		
		BombEntity bomb = new BombEntity(player, player.world);
		bomb.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2 + info.getStandEntity(player).getSpeed(), 1);
			
		bomb.setDamage(5 + info.getStandEntity(player).getDestructivePower());
		bomb.setMaxLife(10 + (info.getStandEntity(player).getRange() * 2));
		bomb.setGravity(0.05F);
		
		player.world.addEntity(bomb);
		
		return true;
	}
	

}
