package xyz.pixelatedw.bizarremod.init;

import net.minecraft.potion.Effect;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;
import xyz.pixelatedw.bizarremod.effects.GreenDayMoldEffect;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyRegistry;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(APIConfig.PROJECT_ID)
public class ModEffects
{

	public static final Effect GREEN_DAY_MOLD = null;
	
	@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class Registry
	{	
		@SubscribeEvent
		public static void registerEffects(final RegistryEvent.Register<Effect> event)
		{
			if (!event.getName().equals(ForgeRegistries.POTIONS.getRegistryName()))
				return;
			
			event.getRegistry().registerAll
			(
				WyRegistry.registerEffect("Green Day Mold", new GreenDayMoldEffect())
			);
		}
	}
}
