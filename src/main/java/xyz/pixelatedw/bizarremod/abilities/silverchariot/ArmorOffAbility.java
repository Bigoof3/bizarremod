package xyz.pixelatedw.bizarremod.abilities.silverchariot;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.bizarremod.api.StandLogicHelper;
import xyz.pixelatedw.bizarremod.api.abilities.IStandAbility;
import xyz.pixelatedw.bizarremod.api.stands.StandInfo;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.entities.stands.SilverChariotEntity;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.server.SSyncAbilityDataPacket;

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
		
		SilverChariotEntity stand = StandLogicHelper.getStandEntity(player);

		stand.removeArmor();
		
		return true;
	}
	
	private void duringContinuity(PlayerEntity player, int passiveTimer)
	{
		SilverChariotEntity stand = StandLogicHelper.getStandEntity(player);

		if(stand == null)
		{
			this.continueTime = 0;
			this.startCooldown();
			IAbilityData props = AbilityDataCapability.get(player);
			WyNetwork.sendTo(new SSyncAbilityDataPacket(props), (ServerPlayerEntity) player);
			return;
		}
		
		stand.setDestructivePower('B');
		stand.setRange('B');
		stand.setPrecision('A');
	}
	
	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		SilverChariotEntity stand = StandLogicHelper.getStandEntity(player);
	
		if(stand != null)
		{
			stand.setDestructivePower('C');
			stand.setRange('C');
			stand.setPrecision('B');
		}

		return true;
	}
}
