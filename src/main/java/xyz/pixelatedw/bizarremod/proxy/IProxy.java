package xyz.pixelatedw.bizarremod.proxy;

import net.minecraft.entity.player.PlayerEntity;

public interface IProxy
{
	void openScreen(int guiId, PlayerEntity player);
}
