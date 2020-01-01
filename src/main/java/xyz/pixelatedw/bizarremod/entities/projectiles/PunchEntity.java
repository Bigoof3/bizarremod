package xyz.pixelatedw.bizarremod.entities.projectiles;

import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.bizarremod.init.ModEntities;

public class PunchEntity extends StandProjectileEntity
{
	public PunchEntity(World world)
	{
		super(ModEntities.PUNCH, world);
	}

	public PunchEntity(LivingEntity livingEntity, World world)
	{
		super(ModEntities.PUNCH, livingEntity, world);
	}
}
