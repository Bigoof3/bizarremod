package xyz.pixelatedw.bizarremod.init;

import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;
import xyz.pixelatedw.bizarremod.items.StandArrowItem;
import xyz.pixelatedw.bizarremod.items.weapons.SilverChariotsRapierItem;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.json.models.item.JSONModelSimple3DItem;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(APIConfig.PROJECT_ID)
public class ModItems
{
	
	public static final Item SILVER_CHARIOTS_RAPIER = null;

	public static final Item STAND_ARROW = null;
	
	public static final Item METEORITE_FRAGMENT = null;

	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event)
	{
		if (!event.getName().equals(ForgeRegistries.ITEMS.getRegistryName()))
			return;

		event.getRegistry().registerAll
		(
			WyRegistry.registerItem(new SilverChariotsRapierItem(), "Silver Chariot's Rapier", new JSONModelSimple3DItem("silver_chariots_rapier").setThirdPersonScales(1.20, 1.20, 1.20).setThirdPersonTranslations(0, 7.5, 0.5)),
			
			WyRegistry.registerItem(new StandArrowItem(), "Stand Arrow", new JSONModelSimple3DItem("stand_arrow").setThirdPersonScales(0.6, 0.6, 0.6).setFirstPersonScales(0.6, 0.6, 0.6)),
			
			WyRegistry.registerItem(new Item(new Properties().group(ItemGroup.MATERIALS)), "Meteorite Fragment")
		);
	}

}
