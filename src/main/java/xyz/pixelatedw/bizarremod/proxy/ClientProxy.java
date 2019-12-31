package xyz.pixelatedw.bizarremod.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.bizarremod.Consts;
import xyz.pixelatedw.bizarremod.screens.StandSelectScreen;

public class ClientProxy implements IProxy
{

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
	
	@Override
	public void openScreen(int guiId, PlayerEntity player)
	{
		switch(guiId)
		{
			case -1:
				Minecraft.getInstance().displayGuiScreen(null);
				break;
			case Consts.GUI_CREATIVE_STAND_SELECT:
				Minecraft.getInstance().displayGuiScreen(new StandSelectScreen(player));
				break;
		}
	}
}
