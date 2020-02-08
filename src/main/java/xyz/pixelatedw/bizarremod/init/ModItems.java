package xyz.pixelatedw.bizarremod.init;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.bizarremod.items.ItemStandArrow;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.json.models.item.JSONModelSimple3DItem;

public class ModItems
{

	public static Item STAND_ARROW = new ItemStandArrow();

	@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class Registry
	{
		
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event)
		{
			if (!event.getName().equals(ForgeRegistries.ITEMS.getRegistryName()))
				return;
			
			event.getRegistry().registerAll
	        (
	        	WyRegistry.registerItem(STAND_ARROW, "Stand Arrow", new JSONModelSimple3DItem("standarrow"))
	        );
		}
		
	}
}
