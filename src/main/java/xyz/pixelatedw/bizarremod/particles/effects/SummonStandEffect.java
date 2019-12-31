package xyz.pixelatedw.bizarremod.particles.effects;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.bizarremod.api.WyHelper;
import xyz.pixelatedw.bizarremod.particles.data.GenericParticleData;

public class SummonStandEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 3; i++)
		{
			posX = posX + WyHelper.randomDouble() / 6;
			posZ = posZ + WyHelper.randomDouble() / 6;

			motionY += 0.05;

			GenericParticleData data = new GenericParticleData();
			data.setMotion(0, motionY, 0);
			data.setColor(0.5F, 0.0F, 0.5F);
			data.setLife(15);
			data.setSize(3F);
			
			if (world instanceof ServerWorld)
				((ServerWorld) world).spawnParticle(data, posX, posY + (i / 10), posZ, 1, 0, 0, 0, 0.0D);
		}

	}

}
