package xyz.pixelatedw.bizarremod.entities.stands;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import xyz.pixelatedw.bizarremod.init.ModEntities;

public class GreenDayEntity extends GenericStandEntity
{

	public GreenDayEntity(World world, PlayerEntity owner)
	{
		super(ModEntities.GREEN_DAY, world, owner);
	}
	
	public GreenDayEntity(World world)
	{
		super(ModEntities.GREEN_DAY, world);
	}

	@Override
	protected void registerData()
	{
		super.registerData();
		this.setDestructivePower('A');
		this.setSpeed('C');
		this.setRange('A');
		this.setPersistance('A');
		this.setPrecision('E');
		this.setDevelopmentPotential('A');
	}
	
	@Override
	public void tick()
	{
		super.tick();	
	}
	
	@Override
	public String getStandName()
	{
		return "Green Day";
	}
	
	@Override
	public void onSummon(PlayerEntity owner)
	{
		super.onSummon(owner);
	}

	@Override
	public void onCancel(PlayerEntity owner)
	{
		
	}

	@Override
	protected void readAdditional(CompoundNBT compound) {}

	@Override
	protected void writeAdditional(CompoundNBT compound) {}

	@Override
	public IPacket<?> createSpawnPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

}
