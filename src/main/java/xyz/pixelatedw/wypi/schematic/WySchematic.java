package xyz.pixelatedw.wypi.schematic;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.common.primitives.UnsignedBytes;
import com.mojang.brigadier.StringReader;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.command.arguments.BlockStateParser;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.ListNBT;
import net.minecraft.state.IProperty;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import xyz.pixelatedw.wypi.APIConfig;

public class WySchematic
{
	public static Schematic load(String name)
	{
		try
		{
			InputStream is = WySchematic.class.getClassLoader().getResourceAsStream("assets/" + APIConfig.PROJECT_ID + "/schematics/" + name + ".schem");
			CompoundNBT nbt = CompressedStreamTools.readCompressed(is);

			short width = nbt.getShort("Width");
			short height = nbt.getShort("Height");
			short length = nbt.getShort("Length");

			byte[] blockData = nbt.getByteArray("BlockData");
			byte[] data = nbt.getByteArray("Data");
		
			ListNBT tiles = nbt.getList("TileEntities", 10);
			CompoundNBT palette = (CompoundNBT) nbt.get("Palette");
			
			HashMap<Integer, BlockState> blocks = new HashMap<Integer, BlockState>();
			
			for(String key : palette.keySet())
			{
				StringReader sr = new StringReader(key);
				BlockStateParser parser = new BlockStateParser(sr, false).parse(true);

				blocks.put(palette.getInt(key), parser.getState());
			}

			is.close();

			return new Schematic(name, tiles, width, height, length, blockData, blocks, data);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public static void build(Schematic sch, IWorld world, BlockPos pos)
	{
		build(sch, world, pos.getX(), pos.getY(), pos.getZ(), null);
	}
	
	public static void build(Schematic sch, IWorld world, int posX, int posY, int posZ)
	{
		build(sch, world, posX, posY, posZ, null);
	}

	public static void build(Schematic sch, IWorld world, BlockPos pos, Block airReplacement)
	{
		build(sch, world, pos.getX(), pos.getY(), pos.getZ(), airReplacement);
	}
	
	public static void build(Schematic sch, IWorld world, int posX, int posY, int posZ, Block airReplacement)
	{
		try
		{
			int i = 0;
			List<TileEntity> tiles = new ArrayList<TileEntity>();
									
			for (int sy = 0; sy < sch.getHeight(); sy++)
			{
				for (int sz = 0; sz < sch.getLength(); sz++)
				{
					for (int sx = 0; sx < sch.getWidth(); sx++)
					{
						int bd = UnsignedBytes.toInt(sch.getBlockData()[i]);
						BlockState b = sch.getBlocks().get(bd);
						
						if (b != Blocks.AIR.getDefaultState())
						{
							if (world.getBlockState(new BlockPos(posX + sx, posY + sy, posZ + sz)) != b)
							{
								BlockPos pos = new BlockPos(posX + sx, posY + sy, posZ + sz);
								if(airReplacement == null || (airReplacement != null && b != airReplacement.getDefaultState()))
									world.setBlockState(pos, b, 3);			
								else
								{
									if(world.getBlockState(pos.up()).getBlock() == Blocks.WATER)
										world.setBlockState(pos, Blocks.WATER.getDefaultState(), 2);
									else
										world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
								}
								world.getChunk(pos).markBlockForPostprocessing(pos);
							}
						}
						i++;
					}
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("[ERROR] The .schematic could not be built due to : " + e.toString());
			e.printStackTrace();
		}
	}
	
	private static class PropertyData
	{
		private String name;
		private IProperty prop;
		
		public PropertyData(String name, IProperty prop)
		{
			this.name = name;
			this.prop = prop;
		}

		public String getName()
		{
			return this.name;
		}

		public IProperty getProperty()
		{
			return this.prop;
		}
	}

}