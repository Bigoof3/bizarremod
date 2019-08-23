package xyz.pixelatedw.bizarremod.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import xyz.pixelatedw.bizarremod.init.ModKeybindings;

public class ClientProxy implements IProxy
{
	
	public ClientProxy()
	{
		MinecraftForge.EVENT_BUS.register(new ModKeybindings());
		ModKeybindings.init();
	}

	@Override
	public PlayerEntity getClientPlayer()
	{
		return Minecraft.getInstance().player;
	}

	@Override
	public World getClientWorld()
	{
		return Minecraft.getInstance().world;
	}

}
