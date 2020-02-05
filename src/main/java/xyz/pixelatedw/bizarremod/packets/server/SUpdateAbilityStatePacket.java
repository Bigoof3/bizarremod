package xyz.pixelatedw.bizarremod.packets.server;

import java.io.IOException;
import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.bizarremod.api.WyHelper;
import xyz.pixelatedw.bizarremod.api.abilities.Ability;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.init.ModNetwork;
import xyz.pixelatedw.bizarremod.packets.client.CRequestSyncStandDataPacket;

public class SUpdateAbilityStatePacket
{
	private INBT data;
	private int slotNumber;

	public SUpdateAbilityStatePacket()
	{
	}

	public SUpdateAbilityStatePacket(IStandData props, int slotNumber)
	{
		this.data = new CompoundNBT();
		this.data = StandDataCapability.INSTANCE.getStorage().writeNBT(StandDataCapability.INSTANCE, props, null);
		
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
				ModNetwork.sendToServer(new CRequestSyncStandDataPacket());
				PlayerEntity player = Minecraft.getInstance().player;
				IStandData props = StandDataCapability.get(player);
				Ability sAbl = null;
				
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

				for (int i = 0; i < 2; i++)
				{
					if (props.getHotbarAbilities()[i] == null)
						continue;

					Ability cAbl = props.getAbility(props.getHotbarAbilities()[i]);
		
					if (cAbl == null || !cAbl.equals(sAbl))
						continue;

					System.out.println(sAbl);
					System.out.println(cAbl);
					
					cAbl.setState(sAbl.getState());
				}
			});
		}

		ctx.get().setPacketHandled(true);
	}
}
