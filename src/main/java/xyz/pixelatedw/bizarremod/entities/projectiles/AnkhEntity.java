package xyz.pixelatedw.bizarremod.entities.projectiles;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;
import xyz.pixelatedw.bizarremod.api.WyHelper;
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
		
		this.onImpactEvent = this::onImpact;
	}

	@Override
	public void tick()
	{
		for(int i = 0; i < 5; i++)
			this.world.addParticle(ParticleTypes.FLAME, this.posX + (WyHelper.randomDouble() / 3), this.posY + (WyHelper.randomDouble() / 3), this.posZ + (WyHelper.randomDouble() / 3), 0, 0.1, 0);
		super.tick();
	}
		
	@Override
	protected void onImpact(RayTraceResult result)
	{
		if (result.getType() == Type.ENTITY)
		{
			EntityRayTraceResult entityHit = (EntityRayTraceResult) result;

			entityHit.getEntity().setFire(200);
		}
		else if (result.getType() == Type.BLOCK)
		{
			BlockRayTraceResult blockHit = (BlockRayTraceResult) result;

			this.world.setBlockState(blockHit.getPos(), Blocks.FIRE.getDefaultState());
		}
	}
}
