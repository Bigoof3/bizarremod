package xyz.pixelatedw.bizarremod.proxy;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.bizarremod.ModMain;
import xyz.pixelatedw.bizarremod.particles.effects.ParticleEffect;

public class ServerProxy implements IProxy
{

	@Override
	public PlayerEntity getClientPlayer()
	{
		return null;
	}

	@Override
	public World getClientWorld()
	{
		return null;
	}

	@Override
	public void openScreen(int guiId, PlayerEntity player) {}

	@Override
	public void spawnParticleEffect(ParticleEffect effect, World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		ModMain.LOGGER.warn("Can't spawn particles on server side !");
	}
}
