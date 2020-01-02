package xyz.pixelatedw.bizarremod.abilities;

import java.io.Serializable;
import java.util.Arrays;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.bizarremod.api.StandInfo;
import xyz.pixelatedw.bizarremod.api.WyRenderHelper;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.helpers.StandLogicHelper;

public abstract class Ability implements Serializable
{
	protected State state = State.STANDBY;
	protected int cooldown;
	protected int maxCooldown;
	protected IOnUse onUseEvent;
	protected IDuringCooldown duringCooldownEvent;
		
	public abstract String getName();

	public void use(PlayerEntity player)
	{
		if(player.world.isRemote)
			return;
		
		Ability abl = this.getOriginalAbility(player);
		
		if(abl.getState() != Ability.State.STANDBY)
			return;
		
		if(this.onUseEvent != null)
			this.onUseEvent.onUse(player, abl);
		
		abl.startCooldown();
	}
	
	
	public abstract void renderDescription(FontRenderer fontObj, int posX, int posY);
	
	protected void drawLine(String text, int x, int y)
	{
		WyRenderHelper.drawCenteredString(Minecraft.getInstance().fontRenderer, text, x, y, -1);
	}
	
	public void setMaxCooldown(int cooldown)
	{
		this.maxCooldown = cooldown;
	}
	
	public void setCooldown(int cooldown)
	{
		this.cooldown = cooldown;
	}
	
	public State getState()
	{
		return this.state;
	}
	
	public void startCooldown()
	{
		this.state = State.COOLDOWN;
	}
	
	public void cooldown(PlayerEntity player)
	{
		if(this.state == State.COOLDOWN && this.cooldown > 0)
		{
			this.cooldown--;
			if(this.duringCooldownEvent != null)
				this.duringCooldownEvent.duringCooldown(player, this.getOriginalAbility(player), this.cooldown);
		}
		else
		{
			this.cooldown = this.maxCooldown;
			this.state = State.STANDBY;
		}
	}
	
	private Ability getOriginalAbility(PlayerEntity player)
	{
		IStandData props = StandDataCapability.get(player);
		StandInfo info = StandLogicHelper.getStandInfo(props.getStand());		
		return Arrays.stream(info.getAbilities()).parallel().filter(ability -> ability.getName().equalsIgnoreCase(this.getName())).findFirst().orElse(null);
	}
	
	public enum State
	{
		STANDBY,
		COOLDOWN
	}
	
	public interface IOnUse extends Serializable
	{
		void onUse(PlayerEntity player, Ability ability);
	}
	
	public interface IDuringCooldown extends Serializable
	{
		void duringCooldown(PlayerEntity player, Ability ability, int cooldown);
	}
}
