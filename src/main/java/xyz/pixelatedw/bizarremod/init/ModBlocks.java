package xyz.pixelatedw.bizarremod.init;

import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.json.loottables.block.JSONLootTableSimpleBlock;
import xyz.pixelatedw.wypi.json.models.item.JSONModelItemBlock;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(APIConfig.PROJECT_ID)
public class ModBlocks
{
	public static final Block METEORITE = null;

	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event)
	{
		Block meteorite = WyRegistry.registerBlock(new Block(Properties.create(Material.ROCK).hardnessAndResistance(3)), "Meteorite");

		event.getRegistry().registerAll(meteorite);
		
		WyRegistry.registerLootTable(meteorite, new JSONLootTableSimpleBlock("Meteorite Fragment", 0, 1));

	}
	
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll
		(
			WyRegistry.registerItem(new BlockItem(METEORITE, new Item.Properties().group(ItemGroup.MATERIALS)), "Meteorite", new JSONModelItemBlock("meteorite"))

				);
	}
}
