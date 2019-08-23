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
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.packets.client.CStandControlPacket;

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
		IStandData props = StandDataCapability.get(player);
		
		if (player == null)
			return;

		if (standControl.isPressed())
		{
			ModNetwork.sendToServer(new CStandControlPacket(ModValues.STAND_ID_GREEN_DAY));
		}
	}
}
