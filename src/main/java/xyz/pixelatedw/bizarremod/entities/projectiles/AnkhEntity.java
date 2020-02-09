package xyz.pixelatedw.bizarremod.entities.projectiles;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.bizarremod.init.ModEntities;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class AnkhEntity extends AbilityProjectileEntity
{
	public AnkhEntity(World world)
	{
		super(ModEntities.ANKH, world);
	}

	public AnkhEntity(PlayerEntity player, World world)
	{
		super(ModEntities.ANKH, world, player);

		this.onBlockImpactEvent = this::onBlockImpactEvent;
		this.onEntityImpactEvent = this::onEntityImpactEvent;
	}

	@Override
	public void tick()
	{
		for (int i = 0; i < 5; i++)
			this.world.addParticle(ParticleTypes.FLAME, this.posX + (WyHelper.randomDouble() / 3), this.posY + (WyHelper.randomDouble() / 3), this.posZ + (WyHelper.randomDouble() / 3), 0, 0.1, 0);
		super.tick();
	}

	private void onBlockImpactEvent(BlockPos hitPos)
	{
		BlockState state = Blocks.FIRE.getDefaultState();
		BlockPos pos = hitPos.up();

		if (this.world.getBlockState(pos).getBlock() == Blocks.AIR && state.isValidPosition(this.world, pos))
			this.world.setBlockState(pos, state);
	}
	
	private void onEntityImpactEvent(LivingEntity hitEntity)
	{
		hitEntity.getEntity().setFire(200);
	}
}
