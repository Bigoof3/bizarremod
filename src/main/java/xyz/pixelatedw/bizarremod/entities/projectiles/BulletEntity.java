package xyz.pixelatedw.bizarremod.entities.projectiles;

import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.bizarremod.init.ModEntities;

public class BulletEntity extends StandProjectileEntity
{
	public BulletEntity(World world)
	{
		super(ModEntities.BULLET, world);
	}

	public BulletEntity(LivingEntity livingEntity, World world)
	{
		super(ModEntities.BULLET, livingEntity, world);
	}
}
