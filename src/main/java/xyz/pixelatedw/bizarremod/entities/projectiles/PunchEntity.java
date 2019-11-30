package xyz.pixelatedw.bizarremod.entities.projectiles;

import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import xyz.pixelatedw.bizarremod.init.ModEntities;

public class PunchEntity extends ThrowableEntity
{
	private static final DataParameter<String> TEXTURE = EntityDataManager.createKey(PunchEntity.class, DataSerializers.STRING);
	private double damage = 1;
	private int ticks, maxticks;
	
	public PunchEntity(World world)
	{
		super(ModEntities.PUNCH, world);
		this.ticks = 5;
		this.maxticks = this.ticks;
	}

	public PunchEntity(LivingEntity livingEntity, World world)
	{
		super(ModEntities.PUNCH, livingEntity, world);
		this.ticks = 5;
		this.maxticks = this.ticks;
	}

	@Override
	public void tick()
	{	
		super.tick();
		if (this.ticks <= 0)
		{
			this.ticks = this.maxticks;
			this.remove();
		}
		else
			this.ticks--;
	}
	
	@Override
	protected void onImpact(RayTraceResult result)
	{
		if (this.world.isRemote)
		{
			this.remove();
			return;
		}

		if (result.getType() == Type.ENTITY)
		{
			EntityRayTraceResult entityHit = (EntityRayTraceResult) result;

			if (entityHit.getEntity() instanceof LivingEntity)
			{
				LivingEntity hitEntity = (LivingEntity) entityHit.getEntity();
				hitEntity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 4);
				this.remove();
			}
		}
		else if (result.getType() == Type.BLOCK)
		{
			BlockRayTraceResult blockHit = (BlockRayTraceResult) result;

			Material hitMat = this.world.getBlockState(new BlockPos(blockHit.getHitVec().getX(), blockHit.getHitVec().getY(), blockHit.getHitVec().getZ())).getMaterial();

			if (hitMat.isSolid())
				this.remove();
		}
	}

	@Override
	protected void registerData()
	{
		this.dataManager.register(TEXTURE, "");
	}

	public void setDamage(double damage)
	{
		this.damage = damage;
	}

	public double getDamage()
	{
		return this.damage;
	}

	public void setTexture(String texture)
	{
		this.dataManager.set(TEXTURE, texture);
		// this.texture = texture;
	}

	public String getTexture()
	{
		return this.dataManager.get(TEXTURE);
		// return this.texture;
	}

	@Override
	protected float getGravityVelocity()
	{
		return 0.001F;
	}
	
	@Override
	public IPacket<?> createSpawnPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
