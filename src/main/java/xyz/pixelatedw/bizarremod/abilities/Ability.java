package xyz.pixelatedw.bizarremod.abilities;

import java.io.Serializable;
import java.util.Arrays;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
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
	protected IUse useEvent;
	
	public abstract String getName();
	
	//public abstract void use(PlayerEntity player);
	
	public void use(PlayerEntity player)
	{
		if(player.world.isRemote)
			return;
		
		IStandData props = StandDataCapability.get(player);
		StandInfo info = StandLogicHelper.getStandInfo(props.getStand());
		Ability abl = Arrays.stream(info.getAbilities()).parallel().filter(ability -> ability.getName().equalsIgnoreCase(this.getName())).findFirst().orElse(null);

		if(abl.getState() != Ability.State.STANDBY)
			return;
		
		this.useEvent.onUse(player, abl);
		
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
	
	public void cooldown()
	{	
		if(this.state == State.COOLDOWN && this.cooldown > 0)
			this.cooldown--;
		else
		{
			this.cooldown = this.maxCooldown;
			this.state = State.STANDBY;
		}
	}
	
	public enum State
	{
		STANDBY,
		COOLDOWN
	}
	
	@OnlyIn(Dist.CLIENT)
	public interface IUse extends Serializable
	{
		void onUse(PlayerEntity player, Ability ability);
	}
}
