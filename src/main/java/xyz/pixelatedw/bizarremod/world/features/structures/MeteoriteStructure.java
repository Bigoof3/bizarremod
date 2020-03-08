package xyz.pixelatedw.bizarremod.world.features.structures;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import xyz.pixelatedw.bizarremod.config.CommonConfig;
import xyz.pixelatedw.bizarremod.init.ModBlocks;
import xyz.pixelatedw.bizarremod.init.ModFeatures;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;

public class MeteoriteStructure extends Feature<NoFeatureConfig>
{
	public MeteoriteStructure()
	{
		super(NoFeatureConfig::deserialize);
		this.setRegistryName(APIConfig.PROJECT_ID, "meteorite");
	}

	@Override
	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config)
	{
		BlockState blockstate = ModBlocks.METEORITE.getDefaultState();

		boolean flag = false;
		if (worldIn.isAirBlock(pos) && blockstate.isValidPosition(worldIn, pos))
		{
			int i = (int) (1 + WyHelper.randomWithRange(0, 2));
			int j = (int) (1 + WyHelper.randomWithRange(0, 2));

			for (BlockPos blockpos : BlockPos.getAllInBoxMutable(pos.add(-i, 0, -j), pos.add(i, 1, j)))
			{
				int k = pos.getX() - blockpos.getX();
				int l = pos.getZ() - blockpos.getZ();
				if (k * k + l * l <= rand.nextFloat() * 10.0F - rand.nextFloat() * 6.0F)
					this.tryPlace(worldIn, blockpos, rand, blockstate);
				else if (rand.nextFloat() < 0.031D)
					this.tryPlace(worldIn, blockpos, rand, blockstate);
			}
			System.out.println(pos);
			flag = true;
		}

		return flag;
	}

	private void tryPlace(IWorld worldIn, BlockPos pos, Random random, BlockState state)
	{
		if (worldIn.isAirBlock(pos) && this.canPlaceOn(worldIn, pos, random))
			worldIn.setBlockState(pos, state, 4);
	}

	private boolean canPlaceOn(IWorld worldIn, BlockPos pos, Random random)
	{
		BlockPos blockpos = pos.down();
		BlockState blockstate = worldIn.getBlockState(blockpos);
		return blockstate.getBlock() == Blocks.GRASS_PATH ? random.nextBoolean() : blockstate.func_224755_d(worldIn, blockpos, Direction.UP);
	}

	public static void register(Biome biome)
	{
		int rarity = CommonConfig.instance.getMeteoriteRarity();
		rarity = rarity == 0 ? 0 : rarity / 1000;
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModFeatures.METEORITE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, rarity, 1)));
	}
}
