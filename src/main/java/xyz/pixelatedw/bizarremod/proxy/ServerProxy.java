package xyz.pixelatedw.bizarremod.proxy;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ServerProxy implements IProxy
{

	@Override
	public PlayerEntity getClientPlayer()
	{
		return null;
	}

	@Override
	public World getClientWorld()
	{
		return null;
	}

}
