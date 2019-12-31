package xyz.pixelatedw.bizarremod.api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.particles.IParticleData.IDeserializer;
import net.minecraft.particles.ParticleType;
import net.minecraft.potion.Effect;
import net.minecraft.world.World;
import xyz.pixelatedw.bizarremod.Env;
import xyz.pixelatedw.bizarremod.api.json.loottables.IJSONLootTable;
import xyz.pixelatedw.bizarremod.api.json.models.JSONModelBlock;
import xyz.pixelatedw.bizarremod.api.json.models.JSONModelItem;
import xyz.pixelatedw.bizarremod.api.json.models.item.JSONModelSimpleItem;
import xyz.pixelatedw.bizarremod.particles.data.GenericParticleData;

public class WyRegistry
{
	public static HashMap<Item, JSONModelItem> items = new HashMap<Item, JSONModelItem>();
	public static HashMap<Block, JSONModelBlock> blocks = new HashMap<Block, JSONModelBlock>();
	public static HashMap<Object, IJSONLootTable> lootTables = new HashMap<Object, IJSONLootTable>();
	public static HashMap<String, String> langMap = new HashMap<String, String>();
	
	public static ParticleType registerGenericParticleType(String id)
	{
		return registerGenericParticleType(id, GenericParticleData.DESERIALIZER);
	}
	
	public static ParticleType registerGenericParticleType(String id, IDeserializer<?> deserializer)
	{
		return new ParticleType<>(true, deserializer).setRegistryName(Env.PROJECT_ID, id);
	}
	
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
		String truename = WyHelper.getResourceName(localizedName);
		langMap.put("item." + Env.PROJECT_ID + "." + truename, localizedName);
		item.setRegistryName(Env.PROJECT_ID, truename);
		
		items.put(item, jsonType);

		return item;
	}
	
	public static Effect registerEffect(String localizedName, Effect effect)
	{
		String truename = WyHelper.getResourceName(localizedName);
		langMap.put("effect." + Env.PROJECT_ID + "." + truename, localizedName);
		effect.setRegistryName(Env.PROJECT_ID, truename);

		return effect;
	}
	
	public static <T extends Entity> EntityType<T> registerEntityType(String id, Function<World, T> func)
	{
		return registerEntityType(id, func, 0.6F, 1.8F);
	}
	
	public static <T extends Entity> EntityType<T> registerEntityType(String id, Function<World, T> func, float width, float height)
	{
		String name = WyHelper.getResourceName(id);
		
		EntityType type = EntityType.Builder.create((entityType, world) -> func.apply(world), EntityClassification.MISC)
			.setTrackingRange(128)
			.setShouldReceiveVelocityUpdates(true)
			.setUpdateInterval(1)
			.setCustomClientFactory((entity, world) -> func.apply(world))
			.size(width, height)
			.build(name)
			.setRegistryName(Env.PROJECT_ID, name);
		
		StringBuilder builder = new StringBuilder();
		String[] strs = name.split("_");
		Arrays.stream(strs).forEach(x -> builder.append(WyHelper.upperCaseFirst(x)));	
		
		langMap.put("entity." + Env.PROJECT_ID + "." + name, builder.toString().trim());
		
		return type;
	}
	
}
