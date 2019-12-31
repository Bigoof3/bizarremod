package xyz.pixelatedw.bizarremod;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.pixelatedw.bizarremod.api.json.WyJSONHelper;
import xyz.pixelatedw.bizarremod.events.EventsAbilityRenderers;
import xyz.pixelatedw.bizarremod.init.ModKeybindings;
import xyz.pixelatedw.bizarremod.init.ModParticleTypes;
import xyz.pixelatedw.bizarremod.init.ModRenderers;
import xyz.pixelatedw.bizarremod.particles.SimpleParticle;

@Mod.EventBusSubscriber(modid = Env.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup
{
	
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new ModKeybindings());
		ModKeybindings.init();
		
		ModRenderers.registerRenderers();
				
		WyJSONHelper.generateJSONLangs();
		WyJSONHelper.generateJSONModels(false);
		WyJSONHelper.generateJSONLootTables(false);
	}

	@SubscribeEvent
	public static void registerParticleFactories(ParticleFactoryRegisterEvent event)
	{
		Minecraft.getInstance().particles.registerFactory(ModParticleTypes.GENERIC_PARTICLE, new SimpleParticle.Factory());
	}
	
}