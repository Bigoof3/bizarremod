package xyz.pixelatedw.bizarremod.init;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import xyz.pixelatedw.bizarremod.ModMain;
import xyz.pixelatedw.bizarremod.ModValues;

public class ModKeybindings
{

	public static KeyBinding standControl;

	public static void init()
	{
		standControl = new KeyBinding(ModValues.CONTROLS_KEY_STAND_CONTROL, GLFW.GLFW_KEY_Z, ModValues.CONTROLS_CATEGORY);
		ClientRegistry.registerKeyBinding(standControl);
	}

	@SuppressWarnings("resource")
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event)
	{
		PlayerEntity player = ModMain.proxy.getClientPlayer();
		World world = ModMain.proxy.getClientWorld();

		if (player == null)
			return;

		if (standControl.isPressed())
		{
			System.out.println("@@@@@@");
		}
	}
	
	/*@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class Events
	{

		@SuppressWarnings("resource")
		@SubscribeEvent
		public void onKeyInput(KeyInputEvent event)
		{
			PlayerEntity player = ModMain.proxy.getClientPlayer();
			World world = ModMain.proxy.getClientWorld();

			if (player == null)
				return;

			if (standControl.isPressed())
			{
				System.out.println("@@@@@@");
			}
		}
	}*/
}
