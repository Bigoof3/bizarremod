package xyz.pixelatedw.bizarremod.entities.stands;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.bizarremod.Consts;
import xyz.pixelatedw.bizarremod.api.stands.GenericStandEntity;
import xyz.pixelatedw.bizarremod.init.ModAbilities;
import xyz.pixelatedw.bizarremod.init.ModEntities;
import xyz.pixelatedw.wypi.abilities.Ability;

public class HighwayStarEntity extends GenericStandEntity
{
	protected static final DataParameter<Boolean> IS_CHASING = EntityDataManager.createKey(HighwayStarEntity.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Integer> CHASING_TARGET = EntityDataManager.createKey(HighwayStarEntity.class, DataSerializers.VARINT);
	protected static final DataParameter<BlockPos> TRAP_POSITION = EntityDataManager.createKey(HighwayStarEntity.class, DataSerializers.BLOCK_POS);

	public HighwayStarEntity(World world, PlayerEntity owner)
	{
		super(ModEntities.HIGHWAY_STAR, world, owner);
	}
	
	public HighwayStarEntity(World world)
	{
		super(ModEntities.HIGHWAY_STAR, world);
	}
	
	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();
	}
	
	@Override
	protected void registerData()
	{
		super.registerData();
		this.setDestructivePower('C');
		this.setSpeed('B');
		this.setRange('A');
		this.setPersistance('A');
		this.setPrecision('E');
		this.setDevelopmentPotential('C');
		
		this.dataManager.register(IS_CHASING, false);
		this.dataManager.register(CHASING_TARGET, -1);
		this.dataManager.register(TRAP_POSITION, null);
	}

	@Override
	public String getStandName()
	{
		return "Highway Star";
	}

	public boolean isChasing()
	{
		return this.dataManager.get(IS_CHASING);
	}
	
	public void setIsChasing(boolean flag)
	{
		this.dataManager.set(IS_CHASING, flag);
	}
	
	public void setTrapPosition(BlockPos pos)
	{
		this.dataManager.set(TRAP_POSITION, pos);
	}
	
	public BlockPos getTrapPosition()
	{
		return this.dataManager.get(TRAP_POSITION);
	}
	
	public void setChaseTarget(LivingEntity target)
	{
		this.dataManager.set(CHASING_TARGET, target != null ? target.getEntityId() : -1);
	}
	
	public int getChaseTarget()
	{
		return this.dataManager.get(CHASING_TARGET);
	}
	
	@Override
	public void onCancel(PlayerEntity owner) {}

	@Override
	public void tick()
	{
		super.tick();
		if (!this.world.isRemote)
		{
			LivingEntity target = null;
			if(this.isChasing())
				target = (LivingEntity) this.world.getEntityByID(this.getChaseTarget());
						
			if(target == null || !target.isAlive())
			{
				target = null;
				this.setInvisible(true);
				this.setChaseTarget(null);
				this.setIsChasing(false);
				return;
			}
			
			if(this.getTrapPosition() != null && (!this.isChasing() || target == null))
			{
				this.setPosition(this.getTrapPosition().getX(), this.getTrapPosition().getY() + 0.6, this.getTrapPosition().getZ());
				this.setInvisible(true);
			}
			
			if(target != null)
			{
				this.setInvisible(false);
				this.setPosition(target.posX, target.posY, target.posZ);
				
				if(this.ticksExisted % 50 == 0)
				{
					target.attackEntityFrom(DamageSource.MAGIC, 1);
					this.getOwner().heal(1);
				}
			}
		}
	}
	
	public static class HighwayStarStandInfo extends DefaultStandInfo
	{
		private Ability[] abilities = new Ability[] 
				{
					ModAbilities.ROOM_TRAP
				};


		@Override
		public GenericStandEntity getStandEntity(PlayerEntity owner)
		{
			GenericStandEntity stand = new HighwayStarEntity(owner.world, owner);

			return stand;
		}
	
		@Override
		public Ability[] getAbilities()
		{
			return this.abilities;
		}
	
		@Override
		public JoJoPart getStandPart()
		{
			return JoJoPart.DIAMOND_IS_UNBREAKABLE;
		}
		
		@Override
		public String[][] getStandTextures()
		{
			return new String[][]
			{
				{ "Default", Consts.STAND_ID_HIGHWAY_STAR }
			};
		}
	}
}
