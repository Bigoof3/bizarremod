package xyz.pixelatedw.bizarremod.entities.stands;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import xyz.pixelatedw.bizarremod.helpers.StandLogicHelper;

public abstract class GenericStandEntity extends Entity
{
	// 4-0 -> A-E; 
	// 5 -> Inifinity; 
	// 6 -> Null; 
	// 7 -> ? 
	protected static final DataParameter<Byte> DESTRUCTIVE_POWER = EntityDataManager.createKey(GenericStandEntity.class, DataSerializers.BYTE);
	protected static final DataParameter<Byte> SPEED = EntityDataManager.createKey(GenericStandEntity.class, DataSerializers.BYTE);
	protected static final DataParameter<Byte> RANGE = EntityDataManager.createKey(GenericStandEntity.class, DataSerializers.BYTE);
	protected static final DataParameter<Byte> PERSISTANCE = EntityDataManager.createKey(GenericStandEntity.class, DataSerializers.BYTE);
	protected static final DataParameter<Byte> PRECISION = EntityDataManager.createKey(GenericStandEntity.class, DataSerializers.BYTE);
	protected static final DataParameter<Byte> DEVELOPMENT_POTENTIAL = EntityDataManager.createKey(GenericStandEntity.class, DataSerializers.BYTE);

	@Override
	protected void registerData()
	{
		this.dataManager.register(DESTRUCTIVE_POWER, (byte) 0);
		this.dataManager.register(SPEED, (byte) 0);
		this.dataManager.register(RANGE, (byte) 0);
		this.dataManager.register(PERSISTANCE, (byte) 0);
		this.dataManager.register(PRECISION, (byte) 0);
		this.dataManager.register(DEVELOPMENT_POTENTIAL, (byte) 0);
	}
	
	protected GenericStandEntity(EntityType type, World worldIn)
	{
		super(type, worldIn);
	}
	
	public void onSummon(PlayerEntity owner)
	{
		owner.sendMessage(this.getName());
	}
		
	abstract void onCancel(PlayerEntity owner);

	abstract String getStandName();
	
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
