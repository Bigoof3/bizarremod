package xyz.pixelatedw.bizarremod.init;

import net.minecraft.particles.ParticleType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.bizarremod.particles.data.GenericParticleData;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyRegistry;

public class ModParticleTypes
{
	
    public static final ParticleType<GenericParticleData> GENERIC_PARTICLE = WyRegistry.registerGenericParticleType("generic_particle", GenericParticleData.DESERIALIZER);

    @Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Registration 
    {
    	
        @SubscribeEvent
        public static void registerParticles(RegistryEvent.Register<ParticleType<?>> event) 
        {
            event.getRegistry().registerAll
            (
            	GENERIC_PARTICLE
            );
        }
    }
}
