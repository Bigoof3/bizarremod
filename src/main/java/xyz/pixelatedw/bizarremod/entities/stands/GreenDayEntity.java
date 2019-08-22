package xyz.pixelatedw.bizarremod.entities.stands;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import xyz.pixelatedw.bizarremod.init.ModEntities;

public class GreenDayEntity extends GenericStandEntity
{

	public GreenDayEntity(World world)
	{
		super(ModEntities.GREEN_DAY, world);
	}

	@Override
	protected void registerData()
	{
		
	}
	
	@Override
	public ITextComponent getName()
	{
		return new StringTextComponent("Green Day".toUpperCase());
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
		return new SSpawnObjectPacket(this);
	}

}
