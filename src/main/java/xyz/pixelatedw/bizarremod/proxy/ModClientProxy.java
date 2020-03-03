package xyz.pixelatedw.bizarremod.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.bizarremod.screens.StandSelectScreen;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ModClientProxy implements IModProxy
{

	@Override
	public PlayerEntity getPlayer()
	{
		return Minecraft.getInstance().player;
	}

	@Override
	public World getWorld()
	{
		return Minecraft.getInstance().world;
	}

	@Override
	public void openScreen(int id)
	{
		switch(id)
		{
			case 1:
				Minecraft.getInstance().displayGuiScreen(new StandSelectScreen(this.getPlayer()));
				break;
		}
	}

}
