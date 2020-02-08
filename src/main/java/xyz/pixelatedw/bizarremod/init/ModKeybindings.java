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
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.client.CSyncAbilityDataPacket;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class ModKeybindings
{

	public static KeyBinding standControl;
	public static KeyBinding abilityWheel;

	public static void init()
	{
		standControl = new KeyBinding(Consts.CONTROLS_KEY_STAND_CONTROL, GLFW.GLFW_KEY_Z, Consts.CONTROLS_CATEGORY);
		ClientRegistry.registerKeyBinding(standControl);
		
		abilityWheel = new KeyBinding(Consts.CONTROLS_KEY_ABILITY_WHEEL, GLFW.GLFW_KEY_X, Consts.CONTROLS_CATEGORY);
		ClientRegistry.registerKeyBinding(abilityWheel);
	}

	@SubscribeEvent
	public static void onKeyInput(KeyInputEvent event)
	{
		PlayerEntity player = Minecraft.getInstance().player;
		
		if (player == null)
			return;
		
		World world = Minecraft.getInstance().world;
		IStandData props = StandDataCapability.get(player);
		
		if (standControl.isPressed())
			WyNetwork.sendToServer(new CStandControlPacket());
				
		if (abilityWheel.isPressed() && props.hasStandSummoned())
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
		
		if(event.getAction() == GLFW.GLFW_PRESS && heldItem.isEmpty() && !Minecraft.getInstance().isGamePaused() && Minecraft.getInstance().currentScreen == null)
		{
			Ability first = abilityProps.getEquippedAbility(0);
			Ability second = abilityProps.getEquippedAbility(1);
			
			System.out.println(first);
			System.out.println(abilityProps.getUnlockedAbilities(AbilityCategory.ALL).size());
			WyNetwork.sendToServer(new CSyncAbilityDataPacket(abilityProps));
			
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
