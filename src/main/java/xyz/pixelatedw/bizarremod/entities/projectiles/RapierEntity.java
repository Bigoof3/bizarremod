package xyz.pixelatedw.bizarremod.entities.projectiles;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.VoxelShape;
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
}
