package xyz.pixelatedw.bizarremod.init;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;
import xyz.pixelatedw.bizarremod.world.features.structures.MeteoriteStructure;
import xyz.pixelatedw.wypi.APIConfig;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(APIConfig.PROJECT_ID)
public class ModFeatures
{
	public static final Feature<NoFeatureConfig> METEORITE = null;

	public static void init()
	{
		for (Biome biome : ForgeRegistries.BIOMES)
		{
			MeteoriteStructure.register(biome);
		}
	}

	@SubscribeEvent
	public static void registerEntityTypes(RegistryEvent.Register<Feature<?>> event)
	{
		event.getRegistry().register(new MeteoriteStructure());
	}
}
