package xyz.pixelatedw.bizarremod.particles.effects;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import xyz.pixelatedw.bizarremod.api.WyHelper;
import xyz.pixelatedw.bizarremod.init.ModParticles;
import xyz.pixelatedw.bizarremod.particles.SimpleParticle;

public class GoParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		posX += 0.5;
		posZ += 0.5;

		for (int i = 0; i < 3; i++)
		{

			posX = posX + WyHelper.randomDouble() / 6;
			posZ = posZ + WyHelper.randomDouble() / 6;

			double middlePoint = 0.5;
			middlePoint *= WyHelper.randomDouble() + 0.3F;

			motionY += middlePoint / 6;
			motionY += 0.05;

			SimpleParticle particle = new SimpleParticle(world, ModParticles.GO, posX, posY + (i / 10), posZ, 0, motionY, 0);
			particle.setColor(0.5F, 0.0F, 0.5F);
			particle.setParticleAge(10);
			particle.setParticleScale(3F);

			Minecraft.getInstance().particles.addEffect(particle);
		}

	}

}
