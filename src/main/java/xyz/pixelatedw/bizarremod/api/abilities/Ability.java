package xyz.pixelatedw.bizarremod.api.abilities;

import java.io.Serializable;
import java.util.Arrays;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import xyz.pixelatedw.bizarremod.api.StandInfo;
import xyz.pixelatedw.bizarremod.api.WyRenderHelper;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.helpers.StandLogicHelper;
import xyz.pixelatedw.bizarremod.init.ModNetwork;
import xyz.pixelatedw.bizarremod.packets.server.SUpdateAbilityStatePacket;

public abstract class Ability implements Serializable
{
	private String name = "";
	private String desc = "";
	protected double cooldown;
	protected double maxCooldown;
	private State state = State.STANDBY;
	
	// Setting the defaults so that no crash occurs and so they will be null safe.
	protected IOnUse onUseEvent = (player) -> { return true; };
	protected IDuringCooldown duringCooldownEvent = (player, cooldown) -> {};
	
	public Ability(String name)
	{
		this.name = name;
	}
	
	/*
	 * 	Event Starters
	 */
	public void use(PlayerEntity player)
	{
		if(player.world.isRemote)
			return;
		
		if(!this.isOnStandby())
			return;			
		
		if(this.onUseEvent.onUse(player))
		{
			this.startCooldown();
			IStandData props = StandDataCapability.get(player);
			ModNetwork.sendTo(new SUpdateAbilityStatePacket(props, props.getAbilityPosition(this.getSavedAbility(player))), (ServerPlayerEntity)player);
		}
	}
	
	
	/*
	 * 	Setters/Getters
	 */
	public boolean isOnStandby()
	{
		return this.state == State.STANDBY;
	}
	
	public boolean isOnCooldown()
	{
		return this.state == State.COOLDOWN;
	}

	public boolean isPassiveActive()
	{
		return this.state == State.PASSIVE;
	}
	
	public boolean isContinuous()
	{
		return this.state == State.CONTINUOUS;
	}
	
	public boolean isCharging()
	{
		return this.state == State.CHARGING;
	}

	public boolean isDisabled()
	{
		return this.state == State.DISABLED;
	}
	
	public void startStandby()
	{
		this.state = State.STANDBY;
	}
	
	public void startCooldown()
	{
		this.state = State.COOLDOWN;
	}
	
	public void setState(State state)
	{
		this.state = state;
	}
	
	public State getState()
	{
		return this.state;
	}
		
	public void setMaxCooldown(double cooldown)
	{
		this.maxCooldown = cooldown * 20;
		this.cooldown = this.maxCooldown;
	}
	
	public void setCooldown(int cooldown)
	{
		this.cooldown = cooldown * 20;
	}
	
	public void setDescription(String desc)
	{
		this.desc = desc;
	}
	
	public String getDescription()
	{
		return this.desc;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	/*
	 * 	Methods
	 */
	public void cooldown(PlayerEntity player)
	{
		if(player.world.isRemote)
			return;
				
		if(this.isOnCooldown() && this.cooldown > 0)
		{
			this.cooldown--;
			this.duringCooldownEvent.duringCooldown(player, (int) this.cooldown);
		}
		else if(this.isOnCooldown() && this.cooldown <= 0)
		{
			this.cooldown = this.maxCooldown;				
			this.state = State.STANDBY;
			IStandData props = StandDataCapability.get(player);
			ModNetwork.sendTo(new SUpdateAbilityStatePacket(props, props.getAbilityPosition(this.getSavedAbility(player))), (ServerPlayerEntity)player);
		}
	}
	
	private Ability getOriginalAbility(PlayerEntity player)
	{
		IStandData props = StandDataCapability.get(player);
		StandInfo info = StandLogicHelper.getStandInfo(props.getStand());		
		return Arrays.stream(info.getAbilities()).parallel().filter(ability -> ability.getName().equalsIgnoreCase(this.getName())).findFirst().orElse(null);
	}
	
	protected Ability getSavedAbility(PlayerEntity player)
	{
		IStandData props = StandDataCapability.get(player);
		Ability abl = props.getAbility(this.getOriginalAbility(player));
		return abl;
	}
	
	@Override
	public boolean equals(Object abl)
	{
		if(!(abl instanceof Ability))
			return false;
		
		return this.getName().equalsIgnoreCase(((Ability) abl).getName());
	}
	
	
	/*
	 *  Menu
	 */
	public abstract void renderDescription(FontRenderer fontObj, int posX, int posY);
	
	protected void drawLine(String text, int x, int y)
	{
		WyRenderHelper.drawCenteredString(Minecraft.getInstance().fontRenderer, text, x, y, -1);
	}
	
	
	/*
	 *	Enums
	 */
	public enum State
	{
		STANDBY,
		COOLDOWN,
		PASSIVE,
		CONTINUOUS,
		CHARGING,
		DISABLED
	}
	
	
	/*
	 *	Interfaces
	 */
	public interface IOnUse extends Serializable
	{
		boolean onUse(PlayerEntity player);
	}
	
	public interface IDuringCooldown extends Serializable
	{
		void duringCooldown(PlayerEntity player, int cooldown);
	}
}
