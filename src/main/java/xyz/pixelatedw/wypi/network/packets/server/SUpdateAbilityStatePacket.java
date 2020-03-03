package xyz.pixelatedw.wypi.network.packets.server;

import java.util.function.Supplier;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

public class SUpdateAbilityStatePacket
{
	private INBT data;
	private int slotNumber;

	public SUpdateAbilityStatePacket()
	{
	}

	public SUpdateAbilityStatePacket(IAbilityData props, int slotNumber)
	{
		this.data = new CompoundNBT();
		this.data = AbilityDataCapability.INSTANCE.getStorage().writeNBT(AbilityDataCapability.INSTANCE, props, null);

		this.slotNumber = slotNumber;
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeCompoundTag((CompoundNBT) this.data);
		buffer.writeInt(this.slotNumber);
	}

	public static SUpdateAbilityStatePacket decode(PacketBuffer buffer)
	{
		SUpdateAbilityStatePacket msg = new SUpdateAbilityStatePacket();
		msg.data = buffer.readCompoundTag();
		msg.slotNumber = buffer.readInt();
		return msg;
	}

	public static void handle(SUpdateAbilityStatePacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
		{
			ctx.get().enqueueWork(() ->
			{
				//PlayerEntity player = Minecraft.getInstance().player;
				//IAbilityData props = AbilityDataCapability.get(player);
				/*Ability sAbl = null;

				try
				{
					sAbl = (Ability) WyHelper.deserialize(((CompoundNBT) message.data).getByteArray("ability_" + message.slotNumber));
				}
				catch (ClassNotFoundException | IOException e)
				{
					e.printStackTrace();
				}

				if (sAbl == null)
					return;

				for (int i = 0; i < APIConfig.MAX_SELECTED_ABILITIES; i++)
				{
					if (props.getEquippedAbilities()[i] == null)
						continue;

					Ability cAbl = props.getUnlockedAbility(props.getEquippedAbilities()[i]);

					if (cAbl == null || !cAbl.equals(sAbl))
						continue;

					cAbl.setState(sAbl.getState());
				}*/
			});
		}

		ctx.get().setPacketHandled(true);
	}
}
