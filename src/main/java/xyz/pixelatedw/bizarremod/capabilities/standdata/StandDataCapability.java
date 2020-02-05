package xyz.pixelatedw.bizarremod.capabilities.standdata;

import java.io.IOException;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import xyz.pixelatedw.bizarremod.api.WyHelper;
import xyz.pixelatedw.bizarremod.api.abilities.Ability;

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
					if (instance.getPreviouslyUsedAbility() != null)
						props.putByteArray("previouslyUsedAbility", WyHelper.serialize(instance.getPreviouslyUsedAbility()));

					for (int i = 0; i < instance.getHotbarAbilities().length; i++)
					{
						Ability ability = instance.getHotbarAbilities()[i];
						if (ability != null)
							props.putString("hotbar_ability_" + i, ability.getName());
					}

					int i = 0;
					for (Ability abl : instance.getAbilities())
					{
						props.putByteArray("ability_" + i, WyHelper.serialize(abl));
						i++;
					}
					props.putInt("abilitiesOwned", i);
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
					instance.setPreviouslyUsedAbility((Ability) WyHelper.deserialize(props.getByteArray("previouslyUsedAbility")));

					int total = props.getInt("abilitiesOwned");
					
					instance.clearAbilities();
					for (int i = 0; i < total; i++)
						instance.addAbility((Ability) WyHelper.deserialize(props.getByteArray("ability_" + i)));
					
					for (int i = 0; i < instance.getHotbarAbilities().length; i++)
					{
						instance.setAbilityInHotbar(i, instance.getAbility(props.getString("hotbar_ability_" + i)));
					}
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
