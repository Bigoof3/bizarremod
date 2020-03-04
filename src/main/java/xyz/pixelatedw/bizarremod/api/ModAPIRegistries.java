package xyz.pixelatedw.bizarremod.api;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryManager;
import xyz.pixelatedw.bizarremod.api.stands.StandInfo;
import xyz.pixelatedw.wypi.APIConfig;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class ModAPIRegistries
{
	public static final IForgeRegistry<StandInfo> STANDS = RegistryManager.ACTIVE.getRegistry(StandInfo.class);
	
	static
	{
		make(new ResourceLocation(APIConfig.PROJECT_ID, "stands"), StandInfo.class);
	}	
	
	public static <T extends IForgeRegistryEntry<T>> void make(ResourceLocation name, Class<T> type)
	{
		new RegistryBuilder<T>().setName(name).setType(type).setMaxID(Integer.MAX_VALUE - 1).create();
	}
}