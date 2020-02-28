package xyz.pixelatedw.bizarremod.abilities.aerosmith;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.bizarremod.api.StandLogicHelper;
import xyz.pixelatedw.bizarremod.api.abilities.IStandAbility;
import xyz.pixelatedw.bizarremod.api.stands.StandInfo;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.entities.stands.AerosmithEntity;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class CarbonDioxideRadarAbility extends ContinuousAbility implements IStandAbility
{
	public CarbonDioxideRadarAbility()
	{
		super("Carbon Dioxide Radar", AbilityCategory.ALL);
		
		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}

	@Override
	public void renderDescription(FontRenderer fontObj, int posX, int posY)
	{
		this.drawLine("- " + this.getName() + " -", posX + 185, posY + 60);
		this.drawLine(TextFormatting.YELLOW + " Active (Continuous)", posX + 183, posY + 72);
		
		this.drawLine("Used to find an enemy's position through their", posX + 190, posY + 95);
		this.drawLine("breath when they exhale.", posX + 190, posY + 110);
		this.drawLine("While active, a small mechanical radar will appear", posX + 190, posY + 125);
		this.drawLine("on screen showing all targets.", posX + 190, posY + 140);
	}

	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		IStandData props = StandDataCapability.get(player);
		StandInfo info = StandLogicHelper.getStandInfo(props.getStand());
		
		AerosmithEntity stand = StandLogicHelper.getStandEntity(player);

		stand.triggerRadar(true);
		
		return true;
	}
	
	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		IStandData props = StandDataCapability.get(player);
		StandInfo info = StandLogicHelper.getStandInfo(props.getStand());
		
		AerosmithEntity stand = StandLogicHelper.getStandEntity(player);

		stand.triggerRadar(false);
		
		return true;
	}
}
