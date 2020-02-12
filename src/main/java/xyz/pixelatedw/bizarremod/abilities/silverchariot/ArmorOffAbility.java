package xyz.pixelatedw.bizarremod.abilities.silverchariot;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.bizarremod.api.StandInfo;
import xyz.pixelatedw.bizarremod.api.abilities.IStandAbility;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.entities.stands.SilverChariotEntity;
import xyz.pixelatedw.bizarremod.helpers.StandLogicHelper;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class ArmorOffAbility extends ContinuousAbility implements IStandAbility
{
	public ArmorOffAbility()
	{
		super("Armor Off", AbilityCategory.ALL);
		
		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.duringContinuity = this::duringContinuity;
		this.onEndContinuityEvent = this::onEndContinuityEvent;

	}
	
	@Override
	public void renderDescription(FontRenderer fontObj, int posX, int posY)
	{
		this.drawLine("- " + this.getName() + " -", posX + 185, posY + 60);
		this.drawLine(TextFormatting.YELLOW + " Active (Continuous)", posX + 183, posY + 72);
		
		this.drawLine("Increases Silver Chariot's speed and precision", posX + 190, posY + 95);
		this.drawLine("but grealy lowers it's defense.", posX + 190, posY + 110);
	}

	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		IStandData props = StandDataCapability.get(player);
		StandInfo info = StandLogicHelper.getStandInfo(props.getStand());
				
		for(SilverChariotEntity entity : WyHelper.getEntitiesNear(player.getPosition(), player.world, 20, SilverChariotEntity.class))
		{
			if(entity.getOwner() == player)
			{
				entity.removeArmor();
				break;
			}
		}
		
		return true;
	}
	
	private void duringContinuity(PlayerEntity player, int passiveTimer)
	{
		for(SilverChariotEntity entity : WyHelper.getEntitiesNear(player.getPosition(), player.world, 20, SilverChariotEntity.class))
		{
			if(entity.getOwner() == player)
			{
				entity.setDestructivePower('B');
				entity.setRange('B');
				entity.setPrecision('A');
				
				break;
			}
		}
	}
	
	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		for(SilverChariotEntity entity : WyHelper.getEntitiesNear(player.getPosition(), player.world, 20, SilverChariotEntity.class))
		{
			if(entity.getOwner() == player)
			{			
				entity.setDestructivePower('C');
				entity.setRange('C');
				entity.setPrecision('B');
				
				break;
			}
		}
		
		return true;
	}
}
