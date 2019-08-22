package xyz.pixelatedw.bizarremod.api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import xyz.pixelatedw.bizarremod.ModValues;

public class WyRegistry
{
	public static HashMap<String, String> langMap = new HashMap<String, String>();
	
	
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
