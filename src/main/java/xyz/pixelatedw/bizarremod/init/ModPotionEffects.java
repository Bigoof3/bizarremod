package xyz.pixelatedw.bizarremod.init;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.bizarremod.api.StandEffect;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyRegistry;

public class ModPotionEffects
{

	public static final Effect GREEN_DAY_MOLD = new StandEffect(EffectType.HARMFUL, 5149489);
	
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
				WyRegistry.registerEffect("Green Day Mold", GREEN_DAY_MOLD)
			);
		}
	}
}
