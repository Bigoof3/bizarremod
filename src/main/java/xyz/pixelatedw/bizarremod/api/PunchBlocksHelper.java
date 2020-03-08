package xyz.pixelatedw.bizarremod.api;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.minecraft.util.math.BlockPos;

public class PunchBlocksHelper
{
	private static HashMap<BlockPos, Integer> damagedBlocks = new HashMap<BlockPos, Integer>();
	
	public static void addDamagedBlock(BlockPos pos, int damage)
	{
		if(!damagedBlocks.containsKey(pos))
			damagedBlocks.put(pos, damage);
	}
	
	public static void removeDamagedBlock(BlockPos pos)
	{
		if(damagedBlocks.containsKey(pos))
			damagedBlocks.remove(pos);
	}
	
	public static int getDamage(BlockPos pos)
	{
		if(damagedBlocks.containsKey(pos))
			return damagedBlocks.get(pos);
		else
			return 0;
	}
	
	public static void setDamage(BlockPos pos, int damage)
	{
		damagedBlocks.put(pos, damage);
	}
	
	public static void tick()
	{
        for(Iterator<Map.Entry<BlockPos, Integer>> itr = damagedBlocks.entrySet().iterator(); itr.hasNext();)
        {
        	Map.Entry<BlockPos, Integer> pair = itr.next();
        	
			if(pair.getValue() > 0)
				pair.setValue(pair.getValue() - 1);
			else if(pair.getValue() <= 0 || pair.getValue() >= 10)
				itr.remove();
        }
	}
}
