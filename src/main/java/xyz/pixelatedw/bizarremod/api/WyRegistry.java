package xyz.pixelatedw.bizarremod.api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import xyz.pixelatedw.bizarremod.ModValues;
import xyz.pixelatedw.bizarremod.api.json.loottables.IJSONLootTable;
import xyz.pixelatedw.bizarremod.api.json.models.JSONModelBlock;
import xyz.pixelatedw.bizarremod.api.json.models.JSONModelItem;
import xyz.pixelatedw.bizarremod.api.json.models.item.JSONModelSimpleItem;

public class WyRegistry
{
	public static HashMap<Item, JSONModelItem> items = new HashMap<Item, JSONModelItem>();
	public static HashMap<Block, JSONModelBlock> blocks = new HashMap<Block, JSONModelBlock>();
	public static HashMap<Object, IJSONLootTable> lootTables = new HashMap<Object, IJSONLootTable>();
	public static HashMap<String, String> langMap = new HashMap<String, String>();
	
	public static void registerLootTable(Object obj, IJSONLootTable json)
	{
		lootTables.put(obj, json);
	}
	
	public static void registerName(String key, String localizedName)
	{
		langMap.put(key, localizedName);
	}
	
	public static Item registerItem(Item item, String localizedName)
	{
		return registerItem(item, localizedName, new JSONModelSimpleItem(localizedName));
	}
	
	public static Item registerItem(Item item, String localizedName, JSONModelItem jsonType)
	{
		String truename = WyHelper.getFancyName(localizedName);
		langMap.put("item." + ModValues.PROJECT_ID + "." + truename, localizedName);
		item.setRegistryName(ModValues.PROJECT_ID, truename);
		
		items.put(item, jsonType);

		return item;
	}
	
	public static <T extends Entity> EntityType<T> registerEntityType(String id, Function<World, T> func)
	{
		return registerEntityType(id, func, 0.6F, 1.8F);
	}
	
	public static <T extends Entity> EntityType<T> registerEntityType(String id, Function<World, T> func, float width, float height)
	{
		String name = WyHelper.getFancyName(id);
		
		EntityType type = EntityType.Builder.create((entityType, world) -> func.apply(world), EntityClassification.MISC)
			.setTrackingRange(128)
			.setShouldReceiveVelocityUpdates(true)
			.setUpdateInterval(3)
			.setCustomClientFactory((entity, world) -> func.apply(world))
			.size(width, height)
			.build(name)
			.setRegistryName(ModValues.PROJECT_ID, name);
		
		StringBuilder builder = new StringBuilder();
		String[] strs = name.split("_");
		Arrays.stream(strs).forEach(x -> builder.append(WyHelper.upperCaseFirst(x)));	
		
		langMap.put("entity." + ModValues.PROJECT_ID + "." + name, builder.toString().trim());
		
		return type;
	}
	
}
