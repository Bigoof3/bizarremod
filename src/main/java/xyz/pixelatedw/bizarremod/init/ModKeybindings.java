package xyz.pixelatedw.bizarremod.init;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.client.event.InputEvent.MouseInputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import xyz.pixelatedw.bizarremod.ModMain;
import xyz.pixelatedw.bizarremod.Consts;
import xyz.pixelatedw.bizarremod.api.WyHelper;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.packets.client.CStandControlPacket;
import xyz.pixelatedw.bizarremod.packets.client.CStandPunchPacket;

public class ModKeybindings
{

	public static KeyBinding standControl;

	public static void init()
	{
		standControl = new KeyBinding(Consts.CONTROLS_KEY_STAND_CONTROL, GLFW.GLFW_KEY_Z, Consts.CONTROLS_CATEGORY);
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

		if (standControl.isPressed() && !WyHelper.isNullOrEmpty(props.getStand()))
			ModNetwork.sendToServer(new CStandControlPacket(props.getStand()));
	}
	
	@SuppressWarnings("resource")
	@SubscribeEvent
	public void onMouseInput(MouseInputEvent event)
	{
		PlayerEntity player = ModMain.proxy.getClientPlayer();

		if(player == null)
			return;
		
		World world = ModMain.proxy.getClientWorld();
		ItemStack heldItem = player.getHeldItemMainhand();
		IStandData props = StandDataCapability.get(player);

		if(event.getButton() == 0 && event.getAction() == GLFW.GLFW_PRESS && heldItem.isEmpty())
			ModNetwork.sendToServer(new CStandPunchPacket(props.getStand()));
	}
}
