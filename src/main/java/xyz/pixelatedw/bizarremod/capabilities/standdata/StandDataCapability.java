package xyz.pixelatedw.bizarremod.capabilities.standdata;

import java.io.IOException;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import xyz.pixelatedw.bizarremod.abilities.Ability;
import xyz.pixelatedw.bizarremod.api.WyHelper;

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

				try
				{
					if(instance.getPrimaryAbility() != null)
						props.putByteArray("primaryAbility", WyHelper.serialize(instance.getPrimaryAbility()));
					if(instance.getSecondaryAbility() != null)
						props.putByteArray("secondaryAbility", WyHelper.serialize(instance.getSecondaryAbility()));
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				
				return props;
			}

			@Override
			public void readNBT(Capability<IStandData> capability, IStandData instance, Direction side, INBT nbt)
			{
				CompoundNBT props = (CompoundNBT) nbt;

				instance.setStand(props.getString("stand"));
				
				instance.setStandSummoned(props.getBoolean("hasStandSummoned"));
				
				try
				{
					if(props.getByteArray("primaryAbility").length > 5)
						instance.setPrimaryAbility((Ability) WyHelper.deserialize(props.getByteArray("primaryAbility")));
					if(props.getByteArray("secondaryAbility").length > 5)
						instance.setSecondaryAbility((Ability) WyHelper.deserialize(props.getByteArray("secondaryAbility")));
				}
				catch (ClassNotFoundException | IOException e)
				{
					e.printStackTrace();
				}
			}

		}, StandDataBase::new);

	}

	public static IStandData get(final LivingEntity entity)
	{
		return entity.getCapability(INSTANCE, null).orElse(new StandDataBase());
	}
}
