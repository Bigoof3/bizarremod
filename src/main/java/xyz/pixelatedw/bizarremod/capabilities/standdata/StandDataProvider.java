package xyz.pixelatedw.bizarremod.capabilities.standdata;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class StandDataProvider implements ICapabilitySerializable<CompoundNBT>
{

	IStandData instance = StandDataCapability.INSTANCE.getDefaultInstance();

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side)
	{
		return StandDataCapability.INSTANCE.orEmpty(cap, LazyOptional.of(() -> instance));
	}

	@Override
	public CompoundNBT serializeNBT()
	{
		return (CompoundNBT) StandDataCapability.INSTANCE.getStorage().writeNBT(StandDataCapability.INSTANCE, instance, null);
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt)
	{
		StandDataCapability.INSTANCE.getStorage().readNBT(StandDataCapability.INSTANCE, instance, null, nbt);
	}
	
}
