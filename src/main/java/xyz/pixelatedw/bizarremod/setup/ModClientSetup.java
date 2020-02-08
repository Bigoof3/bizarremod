package xyz.pixelatedw.bizarremod.setup;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.pixelatedw.bizarremod.init.ModKeybindings;
import xyz.pixelatedw.bizarremod.init.ModParticleTypes;
import xyz.pixelatedw.bizarremod.init.ModRenderers;
import xyz.pixelatedw.bizarremod.particles.SimpleParticle;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.APIDefaults;
import xyz.pixelatedw.wypi.json.WyJSON;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientSetup
{
	
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event)
	{
		APIDefaults.initI18n();
		ModKeybindings.init();
		
		ModRenderers.registerRenderers();
				
		WyJSON.runGenerators(false);
	}

	@SubscribeEvent
	public static void registerParticleFactories(ParticleFactoryRegisterEvent event)
	{
		Minecraft.getInstance().particles.registerFactory(ModParticleTypes.GENERIC_PARTICLE, new SimpleParticle.Factory());
	}
	
}