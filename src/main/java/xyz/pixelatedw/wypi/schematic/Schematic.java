package xyz.pixelatedw.wypi.schematic;

import java.util.HashMap;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.TileEntity;

public class Schematic
{
	private ListNBT tileEntitiesNBT;
	private TileEntity[] tileEtities = new TileEntity[0];
	private short width, height, length;
	private HashMap<Integer, BlockState> blocks;
	private byte[] blocksData, data;
	private String name = "N/A";

	public Schematic(String name, ListNBT tilesNBT, short width, short height, short length, byte[] blockData, HashMap<Integer, BlockState> blocks, byte[] data)
	{
		this.name = name;
		this.tileEntitiesNBT = tilesNBT;
		this.width = width;
		this.height = height;
		this.length = length;
		this.blocks = blocks;
		this.blocksData = blockData;
		this.data = data;
	}	 

	public int getWidth()
	{
		return this.width;
	}
	
	public int getLength()
	{
		return this.length;
	}
	
	public int getHeight()
	{
		return this.height;
	}
	
	public String getName()
	{
		return this.name;
	}

	public ListNBT getTilesNBT() 
	{
		return this.tileEntitiesNBT;
	}
	
	public TileEntity[] getTiles()
	{
		return this.tileEtities;
	}
	
	public void setTiles(TileEntity[] tiles)
	{
		this.tileEtities = tiles;
	}
	
	public HashMap<Integer, BlockState> getBlocks() 
	{
		return this.blocks;
	}

	public byte[] getData() 
	{
		return this.data;
	}
	
	public byte[] getBlockData() 
	{
		return this.blocksData;
	}
}
