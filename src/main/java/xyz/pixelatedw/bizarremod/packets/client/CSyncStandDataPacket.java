package xyz.pixelatedw.bizarremod.packets.client;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.bizarremod.data.entity.standdata.IStandData;
import xyz.pixelatedw.bizarremod.data.entity.standdata.StandDataCapability;

public class CSyncStandDataPacket
{
	private INBT data;

	public CSyncStandDataPacket() {}
	
	public CSyncStandDataPacket(IStandData props)
	{
		this.data = new CompoundNBT();
		this.data = StandDataCapability.INSTANCE.getStorage().writeNBT(StandDataCapability.INSTANCE, props, null);
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeCompoundTag((CompoundNBT) data);
	}
	
	public static CSyncStandDataPacket decode(PacketBuffer buffer)
	{
		CSyncStandDataPacket msg = new CSyncStandDataPacket();
		msg.data = buffer.readCompoundTag();
		return msg;
	}

	public static void handle(CSyncStandDataPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				World world = player.world;
				IStandData props = StandDataCapability.get(player);
				
				StandDataCapability.INSTANCE.getStorage().readNBT(StandDataCapability.INSTANCE, props, null, message.data);
			});			
		}
		ctx.get().setPacketHandled(true);
	}
}
