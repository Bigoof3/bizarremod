package xyz.pixelatedw.bizarremod.api.stands;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import xyz.pixelatedw.bizarremod.api.StandLogicHelper;

public abstract class GenericStandEntity extends CreatureEntity
{
	// 0-4 -> E-A;
	// 127 -> Inifinity;
	// -2 -> Null;
	// -1 -> ?
	protected static final DataParameter<Byte> DESTRUCTIVE_POWER = EntityDataManager.createKey(GenericStandEntity.class, DataSerializers.BYTE);
	protected static final DataParameter<Byte> SPEED = EntityDataManager.createKey(GenericStandEntity.class, DataSerializers.BYTE);
	protected static final DataParameter<Byte> RANGE = EntityDataManager.createKey(GenericStandEntity.class, DataSerializers.BYTE);
	protected static final DataParameter<Byte> PERSISTANCE = EntityDataManager.createKey(GenericStandEntity.class, DataSerializers.BYTE);
	protected static final DataParameter<Byte> PRECISION = EntityDataManager.createKey(GenericStandEntity.class, DataSerializers.BYTE);
	protected static final DataParameter<Byte> DEVELOPMENT_POTENTIAL = EntityDataManager.createKey(GenericStandEntity.class, DataSerializers.BYTE);

	private PlayerEntity owner;

	public GenericStandEntity(EntityType type, World world)
	{
		super(type, world);
	}

	public GenericStandEntity(EntityType<? extends GenericStandEntity> type, World world, PlayerEntity owner)
	{
		super(type, world);

		if (owner == null)
			this.remove();

		this.owner = owner;
		this.setPosition(owner.getPosition().getX(), owner.getPosition().getY(), owner.getPosition().getZ());
		this.setMotion(Vec3d.ZERO);
		this.prevPosX = owner.getPosition().getX();
		this.prevPosY = owner.getPosition().getY();
		this.prevPosZ = owner.getPosition().getZ();

		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(this.owner.getMaxHealth());
	}

	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23F);
		this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(200.0D);
	}

	@Override
	protected void registerData()
	{
		super.registerData();
		this.dataManager.register(DESTRUCTIVE_POWER, (byte) 0);
		this.dataManager.register(SPEED, (byte) 0);
		this.dataManager.register(RANGE, (byte) 0);
		this.dataManager.register(PERSISTANCE, (byte) 0);
		this.dataManager.register(PRECISION, (byte) 0);
		this.dataManager.register(DEVELOPMENT_POTENTIAL, (byte) 0);
	}

	@Override
	public boolean isNotColliding(IWorldReader world)
	{
		return true;
	}

	@Override
	public void tick()
	{
		if (!this.world.isRemote)
		{
			if (this.owner == null)
			{
				this.remove();
				return;
			}

			if (this.getHealth() <= 0)
				this.owner.attackEntityFrom(DamageSource.MAGIC, Float.MAX_VALUE);

			if (this.getHealth() != this.owner.getHealth())
				this.setHealth(this.owner.getHealth());

			double distance = this.getDistanceSq(this.owner);
			if (distance > 1)
				this.moveStand(distance, this.owner);

			this.setRotation(this.owner.rotationYaw, this.owner.rotationPitch);
		}

		this.noClip = true;
		super.tick();
		this.noClip = false;
		this.setNoGravity(true);
	}

	public void moveStand(double distance, Entity entity)
	{
		double distX = this.posX - entity.posX;
		double distY = this.posY - entity.posY;
		double distZ = this.posZ - entity.posZ;
		float speed = 0.4F;

		if (distY > -1 && distY < 1)
			distY = 0;
		else
		{
			if (distY > 1)
				distY -= 1;
			else if (distY < -1)
				distY += 1;
		}

		double powX = 0, powY = 0, powZ = 0;

		if (distX > 2.0D)
			powX -= speed / 2;
		if (distX < -2.0D)
			powX += speed / 2;

		if (distZ > 2.0D)
			powZ -= speed / 2;
		if (distZ < -2.0D)
			powZ += speed / 2;

		this.setMotion(this.getMotion().add(powX, 0, powZ));
		this.setMotion(this.getMotion().getX(), 0, this.getMotion().getZ());

		if (distY != 0)
			this.setPosition(this.posX, entity.posY + 0.3, this.posZ);
	}

	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float damageValue)
	{
		if (this.world.isRemote)
			return false;

		if (damageSource.getTrueSource() != null && damageSource.getTrueSource() instanceof PlayerEntity && damageSource.getTrueSource() == this.owner)
			return false;

		this.owner.attackEntityFrom(damageSource, damageValue);
		return super.attackEntityFrom(damageSource, damageValue);
	}

	public void onSummon(PlayerEntity owner)
	{
		owner.sendMessage(this.getName());
	}

	public abstract void onCancel(PlayerEntity owner);

	public abstract String getStandName();

	public void setOwner(PlayerEntity player)
	{
		this.owner = player;
	}

	public PlayerEntity getOwner()
	{
		return this.owner;
	}

	// Stand's stats getters/setters

	public byte getDevelopmentPotential()
	{
		return this.dataManager.get(DEVELOPMENT_POTENTIAL);
	}

	public void setDevelopmentPotential(char value)
	{
		byte dev = StandLogicHelper.convertStandStat(value);
		this.dataManager.set(DEVELOPMENT_POTENTIAL, dev);
	}

	public byte getPrecision()
	{
		return this.dataManager.get(PRECISION);
	}

	public void setPrecision(char value)
	{
		byte pre = StandLogicHelper.convertStandStat(value);
		this.dataManager.set(PRECISION, pre);
	}

	public byte getPersistance()
	{
		return this.dataManager.get(PERSISTANCE);
	}

	public void setPersistance(char value)
	{
		byte per = StandLogicHelper.convertStandStat(value);
		this.dataManager.set(PERSISTANCE, per);
	}

	public byte getRange()
	{
		return this.dataManager.get(RANGE);
	}

	public void setRange(char value)
	{
		byte rng = StandLogicHelper.convertStandStat(value);
		this.dataManager.set(RANGE, rng);
	}

	public byte getSpeed()
	{
		return this.dataManager.get(SPEED);
	}

	public void setSpeed(char value)
	{
		byte spd = StandLogicHelper.convertStandStat(value);
		this.dataManager.set(SPEED, spd);
	}

	public byte getDestructivePower()
	{
		return this.dataManager.get(DESTRUCTIVE_POWER);
	}

	public void setDestructivePower(char value)
	{
		byte dp = StandLogicHelper.convertStandStat(value);
		this.dataManager.set(DESTRUCTIVE_POWER, dp);
	}
}
