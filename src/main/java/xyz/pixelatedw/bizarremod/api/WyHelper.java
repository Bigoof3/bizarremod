package xyz.pixelatedw.bizarremod.api;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xyz.pixelatedw.bizarremod.Env;

public class WyHelper
{

	public static float handleRotationFloat(LivingEntity entity, float partialTicks)
    {
        return entity.ticksExisted + partialTicks;
    }
	
    public static void rotateCorpse(LivingEntity entityLiving, float ageInTicks, float headYawOffset, float v)
	{
		GL11.glRotatef(180.0F + headYawOffset, 0.0F, 1.0F, 0.0F);

		if (entityLiving.deathTime > 0)
		{
			float f3 = (entityLiving.deathTime + v - 1.0F) / 20.0F * 1.6F;
			f3 = MathHelper.sqrt(f3);

			if (f3 > 1.0F)
			{
				f3 = 1.0F;
			}
		}
	}

	public static float interpolateRotation(float lowerLimit, float upperLimit, float range)
	{
		float f3;

		for (f3 = upperLimit - lowerLimit; f3 < -180.0F; f3 += 360.0F)
		{
			;
		}

		while (f3 >= 180.0F)
		{
			f3 -= 360.0F;
		}

		return lowerLimit + range * f3;
	}
	
	public static boolean isDevBuild()
	{
		return Env.BUILD_MODE.equalsIgnoreCase("DEV");
	}

	public static boolean isEarlyAccessBuild()
	{
		return Env.BUILD_MODE.equalsIgnoreCase("EARLY_ACCESS");
	}

	public static boolean isReleaseBuild()
	{
		return Env.BUILD_MODE.equalsIgnoreCase("RELEASE");
	}

	public static boolean isDebug()
	{
		return ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;
	}
	
	public static byte[] serialize(Object obj) throws IOException
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(out);
		os.writeObject(obj);
		return out.toByteArray();
	}

	public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException
	{
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		ObjectInputStream is = new ObjectInputStream(in);
		return is.readObject();
	}
	
	public static <T> List<T> shuffle(List<T> ar)
	{
		Random rnd = new Random();
		
		for (int i = ar.size() - 1; i > 0; i--)
		{
			int index = rnd.nextInt(i + 1);
			// Simple swap
			T a = ar.get(index);
			ar.set(index, ar.get(i));
			ar.set(i, a);
		}
		
		return ar;
	}
	
	public static double percentage(double i, double j)
	{
		return (i / 100) * j;
	}

	public static double randomWithRange(int min, int max)
	{
		return new Random().nextInt(max + 1 - min) + min;
	}

	public static double randomDouble()
	{
		return new Random().nextDouble() * 2 - 1;
	}
	
	public static <T extends Entity> List<T> getNearbyEntities(BlockPos pos, World world, double radius, Class<? extends T>... classEntities)
	{
		AxisAlignedBB aabb = new AxisAlignedBB(pos.add(1, 1, 1)).grow(radius, radius, radius);
		List<T> list = new ArrayList<T>();
		for(Class<? extends T> clzz : classEntities)
		{
			list.addAll(world.getEntitiesWithinAABB(clzz, aabb));
		}
		return list;
	}
	
	public static Color hexToRGB(String hexColor)
	{
		if (hexColor.startsWith("#"))
			return Color.decode(hexColor);
		else
			return Color.decode("#" + hexColor);
	}

	public static <K extends Comparable, V extends Comparable> Map<K, V> sortAlphabetically(Map<K, V> map)
	{
		List<Map.Entry<K, V>> entries = new LinkedList<Map.Entry<K, V>>(map.entrySet());

		Collections.sort(entries, new Comparator<Map.Entry<K, V>>()
		{
			@Override
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2)
			{
				return o1.getKey().compareTo(o2.getKey());
			}
		});

		Map<K, V> sortedMap = new LinkedHashMap<K, V>();

		for (Map.Entry<K, V> entry : entries)
		{
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}
	
	public static boolean isNullOrEmpty(String str)
	{
		if (str != null && !str.isEmpty() && !str.equalsIgnoreCase("n/a"))
			return false;
		return true;
	}
	
	public static String upperCaseFirst(String text)
	{
		return Character.toUpperCase(text.charAt(0)) + text.substring(1) + " "; 
	}
	
	public static String getResourceName(String text)
	{
		return text.replaceAll("[ \\t]+$", "").replaceAll("\\s+", "_").replaceAll("[\\'\\:\\-\\,\\#]", "").toLowerCase();
	}
}
