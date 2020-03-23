package xyz.pixelatedw.bizarremod.entities.projectiles;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import xyz.pixelatedw.bizarremod.init.ModEntities;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class LightRayEntity extends AbilityProjectileEntity{

	public LightRayEntity(World world) {
		super(ModEntities.LIGHTRAY, world);
	}

	public LightRayEntity(PlayerEntity player, World world) {
		super(ModEntities.LIGHTRAY, world, player);
		this.setGlowing(true);
		this.onBlockImpactEvent = this::onBlockImpact;
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

	private void onBlockImpact(BlockPos hitpos) {
		if(!this.world.isRemote) {
	         BlockState blockstate1 = ((FireBlock)Blocks.FIRE).getStateForPlacement(world, hitpos);

			world.setBlockState(hitpos, blockstate1);
		}
		}
}
