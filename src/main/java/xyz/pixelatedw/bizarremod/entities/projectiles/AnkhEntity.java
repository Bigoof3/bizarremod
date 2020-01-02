package xyz.pixelatedw.bizarremod.entities.projectiles;

import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.bizarremod.init.ModEntities;

public class AnkhEntity extends StandProjectileEntity
{
	public AnkhEntity(World world)
	{
		super(ModEntities.ANKH, world);
	}

	public AnkhEntity(LivingEntity livingEntity, World world)
	{
		super(ModEntities.ANKH, livingEntity, world);
	}
}
