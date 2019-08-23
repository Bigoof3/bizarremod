package xyz.pixelatedw.bizarremod.proxy;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public interface IProxy
{
	PlayerEntity getClientPlayer();

	World getClientWorld();
	
	void openGUI(int guiId, PlayerEntity player);
}
