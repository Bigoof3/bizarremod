package xyz.pixelatedw.bizarremod.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import xyz.pixelatedw.bizarremod.init.ModKeybindings;
import xyz.pixelatedw.bizarremod.init.ModRenderers;

public class ClientProxy implements IProxy
{
	
	public ClientProxy()
	{
		FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientProxy::clientSetup);
		
		MinecraftForge.EVENT_BUS.register(new ModKeybindings());
		ModKeybindings.init();
	}

	public static void clientSetup(FMLClientSetupEvent event)
	{
		ModRenderers.registerRenderers();
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
