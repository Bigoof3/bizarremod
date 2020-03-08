package xyz.pixelatedw.wypi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.particles.IParticleData.IDeserializer;
import net.minecraft.particles.ParticleType;
import net.minecraft.potion.Effect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.json.loottables.IJSONLootTable;
import xyz.pixelatedw.wypi.json.models.JSONModelBlock;
import xyz.pixelatedw.wypi.json.models.JSONModelItem;
import xyz.pixelatedw.wypi.json.models.block.JSONModelSimpleBlock;
import xyz.pixelatedw.wypi.json.models.item.JSONModelSimpleItem;
import xyz.pixelatedw.wypi.json.models.item.JSONModelSpawnEgg;

public class WyRegistry
{
	/*
	 * Maps
	 */

	private static HashMap<Item, JSONModelItem> items = new HashMap<Item, JSONModelItem>();
	private static HashMap<Block, JSONModelBlock> blocks = new HashMap<Block, JSONModelBlock>();
	private static HashMap<Object, IJSONLootTable> lootTables = new HashMap<Object, IJSONLootTable>();
	private static HashMap<String, String> langMap = new HashMap<String, String>();

	/*
	 * Getters
	 */

	public static HashMap<Item, JSONModelItem> getItems()
	{
		return items;
	}

	public static HashMap<Block, JSONModelBlock> getBlocks()
	{
		return blocks;
	}

	public static HashMap<String, String> getLangMap()
	{
		return langMap;
	}

	public static HashMap<Object, IJSONLootTable> getLootTables()
	{
		return lootTables;
	}

	/*
	 * Register Helpers
	 */

	public static Effect registerEffect(String localizedName, Effect effect)
	{
		String truename = WyHelper.getResourceName(localizedName);
		langMap.put("effect." + APIConfig.PROJECT_ID + "." + truename, localizedName);
		effect.setRegistryName(APIConfig.PROJECT_ID, truename);

		return effect;
	}

	public static ParticleType registerGenericParticleType(String id, IDeserializer<?> deserializer)
	{
		return new ParticleType<>(true, deserializer).setRegistryName(APIConfig.PROJECT_ID, id);
	}

	public static void registerLootTable(Object obj, IJSONLootTable json)
	{
		lootTables.put(obj, json);
	}

	public static void registerName(String key, String localizedName)
	{
		langMap.put(key, localizedName);
	}

	public static void registerAbilities(RegistryEvent.Register<Ability> event, Ability... abilities)
	{
		for(Ability ability : abilities)
		{
			event.getRegistry().register(registerAbility(ability));
		}
	}
	
	public static Ability registerAbility(Ability ability)
	{
		String truename = WyHelper.getResourceName(ability.getName());
		ability.setRegistryName(APIConfig.PROJECT_ID, truename);

		langMap.put("ability." + APIConfig.PROJECT_ID + "." + truename, ability.getName());

		return ability;
	}

	public static Item registerItem(Item item, String localizedName)
	{
		return registerItem(item, localizedName, new JSONModelSimpleItem(localizedName));
	}

	public static Item registerItem(Item item, String localizedName, JSONModelItem jsonType)
	{
		String truename = WyHelper.getResourceName(localizedName);
		item.setRegistryName(APIConfig.PROJECT_ID, truename);

		langMap.put("item." + APIConfig.PROJECT_ID + "." + truename, localizedName);
		items.put(item, jsonType);

		return item;
	}

	public static Item registerSpawnEggItem(EntityType type, int backgroundColor, int foregroundColor)
	{
		SpawnEggItem egg = new SpawnEggItem(type, backgroundColor, foregroundColor, (new Item.Properties()).group(ItemGroup.MISC));

		StringBuilder builder = new StringBuilder();
		String[] strs = type.getRegistryName().getPath().split("_");
		Arrays.stream(strs).forEach(x -> builder.append(WyHelper.upperCaseFirst(x)));

		String langKey = type.getRegistryName().getPath() + "_spawn_egg";
		String localizedName = "Spawn " + builder.toString().trim();

		langMap.put("item." + APIConfig.PROJECT_ID + "." + langKey, localizedName);
		egg.setRegistryName(APIConfig.PROJECT_ID, langKey);

		items.put(egg, new JSONModelSpawnEgg(langKey));

		return egg;
	}

	public static Block registerBlock(Block block, String localizedName)
	{
		return registerBlock(block, localizedName, new JSONModelSimpleBlock(localizedName));
	}

	public static Block registerBlock(Block block, String localizedName, JSONModelBlock jsonType)
	{
		String truename = WyHelper.getResourceName(localizedName);
		block.setRegistryName(new ResourceLocation(APIConfig.PROJECT_ID, truename));
		
		langMap.put("block." + APIConfig.PROJECT_ID + "." + truename, localizedName);
		blocks.put(block, jsonType);

		return block;
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
				.size(width, height).build(name)
				.setRegistryName(APIConfig.PROJECT_ID, name);

		StringBuilder builder = new StringBuilder();
		String[] strs = name.split("_");
		Arrays.stream(strs).forEach(x -> builder.append(WyHelper.upperCaseFirst(x)));

		langMap.put("entity." + APIConfig.PROJECT_ID + "." + name, builder.toString().trim());

		return type;
	}
}
