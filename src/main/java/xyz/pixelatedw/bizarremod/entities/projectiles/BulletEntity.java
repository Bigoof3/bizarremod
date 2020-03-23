package xyz.pixelatedw.bizarremod.entities.projectiles;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xyz.pixelatedw.bizarremod.init.ModEntities;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class BulletEntity extends AbilityProjectileEntity {
	public BulletEntity(World world) {
		super(ModEntities.BULLET, world);
	}

	public BulletEntity(PlayerEntity player, World world) {
		super(ModEntities.BULLET, world, player);
	}

	@Override
	public void tick() {
		if(!this.world.isRemote) {
		if(this.getThrower() == null) {
			this.remove();
		}
		}
		super.tick();
	}

}
