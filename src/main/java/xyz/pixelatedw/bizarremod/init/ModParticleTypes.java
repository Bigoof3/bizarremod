package xyz.pixelatedw.bizarremod.init;

import net.minecraft.particles.ParticleType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.bizarremod.Env;
import xyz.pixelatedw.bizarremod.api.WyRegistry;
import xyz.pixelatedw.bizarremod.particles.data.GenericParticleData;

public class ModParticleTypes
{
	
    public static final ParticleType<GenericParticleData> GENERIC_PARTICLE = WyRegistry.registerGenericParticleType("generic_particle");

    @Mod.EventBusSubscriber(modid = Env.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
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
