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
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.packets.client.CStandControlPacket;
import xyz.pixelatedw.bizarremod.packets.client.CUseAbilityPacket;
import xyz.pixelatedw.bizarremod.screens.AbilityWheelScreen;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class ModKeybindings
{
	private static ModKeybindings instance;
	
	public static final KeyBinding STAND_CONTROL = new KeyBinding(Consts.CONTROLS_KEY_STAND_CONTROL, GLFW.GLFW_KEY_Z, Consts.CONTROLS_CATEGORY);
	public static final KeyBinding ABILITY_WHEEL = new KeyBinding(Consts.CONTROLS_KEY_ABILITY_WHEEL, GLFW.GLFW_KEY_R, Consts.CONTROLS_CATEGORY);

	public static void init()
	{
		ClientRegistry.registerKeyBinding(STAND_CONTROL);	
		ClientRegistry.registerKeyBinding(ABILITY_WHEEL);
	}

	@SubscribeEvent
	public static void onKeyInput(KeyInputEvent event)
	{
		PlayerEntity player = Minecraft.getInstance().player;
		
		if (player == null)
			return;
		
		World world = Minecraft.getInstance().world;
		IStandData props = StandDataCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);
		
		if (STAND_CONTROL.isPressed())
			WyNetwork.sendToServer(new CStandControlPacket());
				
		if (ABILITY_WHEEL.isPressed() && props.hasStandSummoned())
			Minecraft.getInstance().displayGuiScreen(new AbilityWheelScreen());
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
		IAbilityData abilityProps = AbilityDataCapability.get(player);
		
		if(event.getAction() == GLFW.GLFW_PRESS && heldItem.isEmpty() && !Minecraft.getInstance().isGamePaused() && Minecraft.getInstance().currentScreen == null && props.hasStandSummoned())
		{
			Ability first = abilityProps.getEquippedAbility(0);
			Ability second = abilityProps.getEquippedAbility(1);
			
			if(event.getButton() == 0 && first != null)
			{
				WyNetwork.sendToServer(new CUseAbilityPacket(0));
				first.use(player);
			}
			else if(event.getButton() == 1 && second != null)
			{
				WyNetwork.sendToServer(new CUseAbilityPacket(1));
				second.use(player);
			}
		}
	}
}
