package xyz.pixelatedw.bizarremod.data.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;
import xyz.pixelatedw.bizarremod.api.stands.StandInfo;
import xyz.pixelatedw.bizarremod.config.CommonConfig;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;

public class ExtendedWorldData extends WorldSavedData
{
	private static final String IDENTIFIER = APIConfig.PROJECT_ID;
	private static Map<World, ExtendedWorldData> loadedExtWorlds = new HashMap<>();
	
	private List<String> usedStands = new ArrayList<String>();
	
	public ExtendedWorldData()
	{
		super(IDENTIFIER);
	}

	public ExtendedWorldData(String identifier)
	{
		super(identifier);
	}
	
	public static ExtendedWorldData get(World world)
	{
		if (world == null)
			return null;
		
		ExtendedWorldData worldExt;

		if (loadedExtWorlds.containsKey(world))
		{
			worldExt = loadedExtWorlds.get(world);
			return worldExt;
		}

		if (world instanceof ServerWorld)
		{
			ServerWorld serverWorld = (ServerWorld) world;
			world.increaseMaxEntityRadius(50);
			ExtendedWorldData worldSavedData = serverWorld.getSavedData().get(ExtendedWorldData::new, IDENTIFIER);
			if (worldSavedData != null)
			{
				worldExt = worldSavedData;
			}
			else
			{
				worldExt = new ExtendedWorldData();
				serverWorld.getSavedData().set(worldExt);
			}
		}
		else
		{
			worldExt = new ExtendedWorldData();
		}

		loadedExtWorlds.put(world, worldExt);
		return worldExt;
	}
	
	@Override
	public void read(CompoundNBT nbt)
	{
		CompoundNBT stands = nbt.getCompound("usedStands");
		this.usedStands.clear();
		stands.keySet().stream().forEach(x ->
		{
			this.usedStands.add(x);
		});
	}
	
	@Override
	public CompoundNBT write(CompoundNBT nbt)
	{
		CompoundNBT stands = new CompoundNBT();
		if (this.usedStands.size() > 0)
		{
			this.usedStands.stream().forEach(x ->
			{
				stands.putBoolean(x, true);
			});
		}
		nbt.put("usedStands", stands);
		
		return nbt;
	}
	
	public void addUsedStand(StandInfo info)
	{
		if(!CommonConfig.instance.isOneStandEnabled())
			return;
		
		String resourceName = WyHelper.getResourceName(info.getDefaultStandId());
		
		if (!this.usedStands.contains(resourceName))
		{
			this.usedStands.add(resourceName);
			this.markDirty();
		}
	}
	
	public void removeUsedStand(StandInfo info)
	{
		if(!CommonConfig.instance.isOneStandEnabled())
			return;
		
		String resourceName = WyHelper.getResourceName(info.getDefaultStandId());
		
		if (this.usedStands.contains(resourceName))
		{
			this.usedStands.remove(resourceName);
			this.markDirty();
		}
	}
	
	public boolean isStandUsed(StandInfo info)
	{
		if(!CommonConfig.instance.isOneStandEnabled())
			return false;
		
		String resourceName = WyHelper.getResourceName(info.getDefaultStandId());

		return this.usedStands.contains(resourceName);
	}
}
