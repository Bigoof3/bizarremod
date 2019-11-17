package xyz.pixelatedw.bizarremod.abilities;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.PlayerEntity;

public abstract class Ability
{
	
	public abstract void use(PlayerEntity player);
	
	public abstract void renderDescription(FontRenderer fontObj, int posX, int posY);
}
