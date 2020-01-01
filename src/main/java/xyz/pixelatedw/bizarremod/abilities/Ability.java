package xyz.pixelatedw.bizarremod.abilities;

import java.io.Serializable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.bizarremod.api.WyRenderHelper;

public abstract class Ability implements Serializable
{
	protected State state = State.STANDBY;
	protected int cooldown;
	protected int maxCooldown;
	
	public abstract String getName();
	
	public abstract void use(PlayerEntity player);
	
	public abstract void renderDescription(FontRenderer fontObj, int posX, int posY);
	
	protected void drawLine(String text, int x, int y)
	{
		WyRenderHelper.drawCenteredString(Minecraft.getInstance().fontRenderer, text, x, y, -1);
	}
	
	public void setCooldown(int cooldown)
	{
		this.cooldown = cooldown;
		this.maxCooldown = this.cooldown;
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
		//System.out.println(this);
		if(this.state == State.COOLDOWN && this.cooldown > 0)
		{
			this.cooldown--;
		}
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
}
