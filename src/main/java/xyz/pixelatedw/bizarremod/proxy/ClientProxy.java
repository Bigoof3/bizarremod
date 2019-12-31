package xyz.pixelatedw.bizarremod.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import xyz.pixelatedw.bizarremod.Consts;
import xyz.pixelatedw.bizarremod.api.json.WyJSONHelper;
import xyz.pixelatedw.bizarremod.events.EventsAbilityRenderers;
import xyz.pixelatedw.bizarremod.init.ModKeybindings;
import xyz.pixelatedw.bizarremod.init.ModRenderers;
import xyz.pixelatedw.bizarremod.particles.effects.ParticleEffect;
import xyz.pixelatedw.bizarremod.screens.StandSelectScreen;

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
		
		MinecraftForge.EVENT_BUS.register(new EventsAbilityRenderers());
		
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
	
	public void spawnParticleEffect(ParticleEffect effect, World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		effect.spawn(world, posX, posY, posZ, motionX, motionY, motionZ);
	}

}
