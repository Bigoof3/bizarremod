package xyz.pixelatedw.bizarremod.init;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.bizarremod.ModValues;
import xyz.pixelatedw.bizarremod.api.WyRegistry;
import xyz.pixelatedw.bizarremod.items.ItemStandArrow;

public class ModItems
{

	public static Item STAND_ARROW = new ItemStandArrow();

	@Mod.EventBusSubscriber(modid = ModValues.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class Registry
	{
		
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event)
		{
			if (!event.getName().equals(ForgeRegistries.ITEMS.getRegistryName()))
				return;
			
			event.getRegistry().registerAll
	        (
	        	WyRegistry.registerItem(STAND_ARROW, "Stand Arrow")
	        );
		}
		
	}
}
