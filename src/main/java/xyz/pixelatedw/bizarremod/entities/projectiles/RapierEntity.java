package xyz.pixelatedw.bizarremod.entities.projectiles;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import xyz.pixelatedw.bizarremod.api.stands.GenericStandEntity;
import xyz.pixelatedw.bizarremod.init.ModEntities;
import xyz.pixelatedw.wypi.abilities.events.AbilityProjectileEvents;
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

	@Override
	public void tick()
	{
		BlockPos blockpos = new BlockPos(this.posX, this.posY, this.posZ);
		BlockState blockstate = this.world.getBlockState(blockpos);
		if (!blockstate.isAir(this.world, blockpos))
		{
			VoxelShape voxelshape = blockstate.getCollisionShape(this.world, blockpos);
			if (!voxelshape.isEmpty())
			{
				for (AxisAlignedBB axisalignedbb : voxelshape.toBoundingBoxList())
				{
					if (axisalignedbb.offset(blockpos).contains(new Vec3d(this.posX, this.posY, this.posZ)))
					{
						this.inGround = true;
						break;
					}
				}
			}
		}

		float f1 = 0.99F;
		Vec3d vec3d = this.getMotion();
		this.setMotion(vec3d.scale(f1));
		if (!this.inGround)
		{
			Vec3d vec3d3 = this.getMotion();
			this.setMotion(vec3d3.x, vec3d3.y - 0.05F, vec3d3.z);
		}
		
		if(this.inGround)
			this.stuckInGround = true;

		super.tick();
	}
	
	@Override
	protected void onImpact(RayTraceResult hit)
	{
		if(!this.world.isRemote && !this.stuckInGround)
		{
			if(hit.getType() == RayTraceResult.Type.ENTITY)
			{
				EntityRayTraceResult entityHit = (EntityRayTraceResult) hit;

				if(entityHit.getEntity() instanceof LivingEntity && this.getThrower() != null)
				{
					LivingEntity hitEntity = (LivingEntity) entityHit.getEntity();
					
					if(hitEntity instanceof GenericStandEntity && ((GenericStandEntity)hitEntity).getOwner() == this.getThrower())
						return;
					
					AbilityProjectileEvents.Hit event = new AbilityProjectileEvents.Hit(this, hit);
					if(MinecraftForge.EVENT_BUS.post(event))
						return;
	
					hitEntity.attackEntityFrom(DamageSource.causeMobDamage(this.getThrower()), 6);
						
					if(this.withEffects.getEffects().length > 0)
					{
						for(EffectInstance instance : this.withEffects.getEffects())
						{
							hitEntity.addPotionEffect(instance);
						}
					}
							
					this.onEntityImpactEvent.onImpact(hitEntity);
				}
			}			
		}
	}
}
