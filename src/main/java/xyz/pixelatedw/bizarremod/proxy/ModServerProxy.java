package xyz.pixelatedw.bizarremod.proxy;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ModServerProxy implements IModProxy
{

	@Override
	public PlayerEntity getPlayer()
	{
		return null;
	}

	@Override
	public World getWorld()
	{
		return null;
	}

	@Override
	public void openScreen(int id)
	{
		
	}

}
