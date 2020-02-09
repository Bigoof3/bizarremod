package xyz.pixelatedw.bizarremod.capabilities.standdata;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class StandDataCapability
{

	@CapabilityInject(IStandData.class)
	public static final Capability<IStandData> INSTANCE = null;

	public static void register()
	{
		CapabilityManager.INSTANCE.register(IStandData.class, new Capability.IStorage<IStandData>()
		{

			@Override
			public INBT writeNBT(Capability<IStandData> capability, IStandData instance, Direction side)
			{
				CompoundNBT props = new CompoundNBT();

				props.putString("stand", instance.getStand());
				
				props.putBoolean("hasStandSummoned", instance.hasStandSummoned());
				
				return props;
			}

			@Override
			public void readNBT(Capability<IStandData> capability, IStandData instance, Direction side, INBT nbt)
			{
				CompoundNBT props = (CompoundNBT) nbt;

				instance.setStand(props.getString("stand"));
				
				instance.setStandSummoned(props.getBoolean("hasStandSummoned"));
			}

		}, StandDataBase::new);

	}

	public static IStandData get(final LivingEntity entity)
	{
		return entity.getCapability(INSTANCE, null).orElse(new StandDataBase());
	}
	
}
