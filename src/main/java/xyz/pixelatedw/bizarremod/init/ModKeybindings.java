package xyz.pixelatedw.bizarremod.init;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.client.event.InputEvent.MouseInputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.bizarremod.Consts;
import xyz.pixelatedw.bizarremod.Env;
import xyz.pixelatedw.bizarremod.api.WyHelper;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.packets.client.CStandControlPacket;
import xyz.pixelatedw.bizarremod.packets.client.CStandPunchPacket;

@Mod.EventBusSubscriber(modid = Env.PROJECT_ID)
public class ModKeybindings
{

	public static KeyBinding standControl;

	public static void init()
	{
		standControl = new KeyBinding(Consts.CONTROLS_KEY_STAND_CONTROL, GLFW.GLFW_KEY_Z, Consts.CONTROLS_CATEGORY);
		ClientRegistry.registerKeyBinding(standControl);
	}

	@SubscribeEvent
	public static void onKeyInput(KeyInputEvent event)
	{
		PlayerEntity player = Minecraft.getInstance().player;
		
		if (player == null)
			return;
		
		World world = Minecraft.getInstance().world;
		IStandData props = StandDataCapability.get(player);

		if (standControl.isPressed() && !WyHelper.isNullOrEmpty(props.getStand()))
			ModNetwork.sendToServer(new CStandControlPacket(props.getStand()));
	}
	
	@SubscribeEvent
	public static void onMouseInput(MouseInputEvent event)
	{
		PlayerEntity player = Minecraft.getInstance().player;

		if(player == null)
			return;
		
		World world = Minecraft.getInstance().world;
		ItemStack heldItem = player.getHeldItemMainhand();
		IStandData props = StandDataCapability.get(player);

		if(event.getButton() == 0 && event.getAction() == GLFW.GLFW_PRESS && heldItem.isEmpty())
			ModNetwork.sendToServer(new CStandPunchPacket(props.getStand()));
	}
}
