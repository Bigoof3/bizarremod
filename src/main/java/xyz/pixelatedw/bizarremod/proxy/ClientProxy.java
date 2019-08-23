package xyz.pixelatedw.bizarremod.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import xyz.pixelatedw.bizarremod.ModValues;
import xyz.pixelatedw.bizarremod.api.json.WyJSONHelper;
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
		
		WyJSONHelper.generateJSONLangs();
		WyJSONHelper.generateJSONModels(false);
		WyJSONHelper.generateJSONLootTables(false);
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
	
	@Override
	public void openGUI(int guiId, PlayerEntity player)
	{
		switch(guiId)
		{
			case ModValues.GUI_CREATIVE_STAND_SELECT:
				break;
		}
	}

}
