package xyz.pixelatedw.bizarremod.particles.effects;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.bizarremod.api.particles.effects.ParticleEffect;
import xyz.pixelatedw.bizarremod.particles.data.GenericParticleData;
import xyz.pixelatedw.wypi.WyHelper;

public class RedBindEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 3; i++)
		{
			posX = posX + WyHelper.randomDouble() / 1.7;
			posZ = posZ + WyHelper.randomDouble() / 1.7;

			motionY += 0.05;

			GenericParticleData data = new GenericParticleData();
			data.setMotion(0, motionY, 0);
			data.setColor(0.5F, 0.0F, 0.5F);
			data.setLife(15);
			data.setSize(3F);
			
			if (world instanceof ServerWorld)
				((ServerWorld) world).spawnParticle(ParticleTypes.FLAME, posX, posY + (i / 10), posZ, 1, 0, 0, 0, 0.0D);
		}

	}
}