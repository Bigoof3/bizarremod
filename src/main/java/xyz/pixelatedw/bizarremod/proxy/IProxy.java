package xyz.pixelatedw.bizarremod.proxy;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.bizarremod.particles.effects.ParticleEffect;

public interface IProxy
{
	PlayerEntity getClientPlayer();

	World getClientWorld();
	
	void openScreen(int guiId, PlayerEntity player);
	
	public void spawnParticleEffect(ParticleEffect effect, World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ);

}
