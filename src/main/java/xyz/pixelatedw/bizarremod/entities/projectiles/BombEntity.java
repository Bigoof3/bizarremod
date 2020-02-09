package xyz.pixelatedw.bizarremod.entities.projectiles;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion.Mode;
import net.minecraft.world.World;
import xyz.pixelatedw.bizarremod.init.ModEntities;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class BombEntity extends AbilityProjectileEntity
{
	public BombEntity(World world)
	{
		super(ModEntities.BOMB, world);
	}

	public BombEntity(PlayerEntity player, World world)
	{
		super(ModEntities.BOMB, world, player);
		
		this.onBlockImpactEvent = this::onBlockImpactEvent;
	}
	
	private void onBlockImpactEvent(BlockPos hitPos)
	{
		this.world.createExplosion(this, hitPos.getX(),hitPos.getY(), hitPos.getZ(), 3, Mode.DESTROY);
	}
}
