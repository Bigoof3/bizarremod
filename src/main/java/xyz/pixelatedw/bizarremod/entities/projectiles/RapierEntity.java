package xyz.pixelatedw.bizarremod.entities.projectiles;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.bizarremod.init.ModEntities;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class RapierEntity extends AbilityProjectileEntity
{
	public RapierEntity(World world)
	{
		super(ModEntities.RAPIER, world);
	}

	public RapierEntity(PlayerEntity player, World world)
	{
		super(ModEntities.RAPIER, world, player);
	}
}
