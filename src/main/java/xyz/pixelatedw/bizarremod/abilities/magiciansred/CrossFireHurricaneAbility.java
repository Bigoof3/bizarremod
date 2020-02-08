package xyz.pixelatedw.bizarremod.abilities.magiciansred;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.bizarremod.api.StandInfo;
import xyz.pixelatedw.bizarremod.api.abilities.IStandAbility;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.entities.projectiles.AnkhEntity;
import xyz.pixelatedw.bizarremod.helpers.StandLogicHelper;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.RepeaterAbility;

public class CrossFireHurricaneAbility extends RepeaterAbility implements IStandAbility
{	
	public CrossFireHurricaneAbility()
	{
		super("Cross Fire Hurricane", AbilityCategory.ALL);
		this.setMaxCooldown(3);
		this.setMaxRepearCount(3, 5);
		
		this.onUseEvent = this::onUseEvent;
	}

	@Override
	public void renderDescription(FontRenderer fontObj, int posX, int posY)
	{
		this.drawLine("- " + this.getName() + " -", posX + 185, posY + 60);
		this.drawLine(TextFormatting.LIGHT_PURPLE + " Active (Repeater)", posX + 183, posY + 72);
		
		this.drawLine("Magician's Red fires a few ankhs of flame", posX + 190, posY + 95);
		this.drawLine("from its mouth.", posX + 190, posY + 110);
	}

	protected boolean onUseEvent(PlayerEntity player)
	{
		IStandData props = StandDataCapability.get(player);
		StandInfo info = StandLogicHelper.getStandInfo(props.getStand());
		
		AnkhEntity ankh = new AnkhEntity(player, player.world);
			
		ankh.setDamage(2 + info.getStandEntity(player).getDestructivePower());
		ankh.setRange(3 + info.getStandEntity(player).getRange());

		player.world.addEntity(ankh);
		ankh.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2 + info.getStandEntity(player).getSpeed(), 4 - info.getStandEntity(player).getPrecision());
		
		return true;
	}
}
