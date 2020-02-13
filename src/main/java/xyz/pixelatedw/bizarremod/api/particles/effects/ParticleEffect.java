package xyz.pixelatedw.bizarremod.api.particles.effects;

import net.minecraft.world.World;

public abstract class ParticleEffect
{

	public abstract void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ);
	
}
