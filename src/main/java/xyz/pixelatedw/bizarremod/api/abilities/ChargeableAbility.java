package xyz.pixelatedw.bizarremod.api.abilities;

import java.io.Serializable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.init.ModNetwork;
import xyz.pixelatedw.bizarremod.packets.server.SUpdateAbilityStatePacket;

public abstract class ChargeableAbility extends Ability
{
	private int chargeTime;
	private int maxChargeTime;
	
	// Setting the defaults so that no crash occurs and so they will be null safe.
	protected IOnStartCharging onStartChargingEvent = (player) -> { return true; };
	protected IOnEndCharging onEndChargingEvent = (player) -> { return true; };
	protected IDuringCharging duringChargingEvent = (player, chargeTime) -> {};

	public ChargeableAbility(String name)
	{
		super(name);
	}
	
	/*
	 *  Event Starters
	 */
	@Override
	public void use(PlayerEntity player)
	{
		if(player.world.isRemote)
			return;
		
		if(!this.isOnStandby())
			return;			
		
		if(this.onStartChargingEvent.onStartCharging(player))
		{
			this.startCharging();
			IStandData props = StandDataCapability.get(player);
			ModNetwork.sendTo(new SUpdateAbilityStatePacket(props, props.getAbilityPosition(this.getSavedAbility(player))), (ServerPlayerEntity)player);
		}
	}
	
	/*
	 *  Setters / Getters
	 */
	public void setMaxChargeTime(int time)
	{
		this.maxChargeTime = time * 20;
		this.chargeTime = this.maxChargeTime;
	}
	
	public void startCharging()
	{
		this.setState(State.CHARGING);
	}
	
	public int getMaxChargeTime()
	{
		return this.maxChargeTime;
	}

	
	
	/*
	 *  Methods
	 */
	public void charging(PlayerEntity player)
	{
		if(player.world.isRemote)
			return;
				
		if(this.isCharging() && this.chargeTime > 0)
		{
			this.chargeTime--;

			this.duringChargingEvent.duringCharging(player, this.chargeTime);
		}
		else if(this.isCharging() && this.chargeTime <= 0)
		{
			if(this.onEndChargingEvent.onEndCharging(player))
			{
				this.chargeTime = this.maxChargeTime;				
				this.startCooldown();	
				IStandData props = StandDataCapability.get(player);
				ModNetwork.sendTo(new SUpdateAbilityStatePacket(props, props.getAbilityPosition(this.getSavedAbility(player))), (ServerPlayerEntity)player);
			}
		}
	}
	
	
	/*
	 *	Interfaces
	 */
	public interface IDuringCharging extends Serializable
	{
		void duringCharging(PlayerEntity player, int chargeTime);
	}
	
	public interface IOnStartCharging extends Serializable
	{
		boolean onStartCharging(PlayerEntity player);
	}
	
	public interface IOnEndCharging extends Serializable
	{
		boolean onEndCharging(PlayerEntity player);
	}
}
