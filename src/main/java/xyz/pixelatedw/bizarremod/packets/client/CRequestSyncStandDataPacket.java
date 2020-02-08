package xyz.pixelatedw.bizarremod.packets.client;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.packets.server.SSyncStandDataPacket;
import xyz.pixelatedw.wypi.network.WyNetwork;

public class CRequestSyncStandDataPacket
{
	private int entityId = 0;
	private INBT data;

	public CRequestSyncStandDataPacket() {}
	
	public void encode(PacketBuffer buffer)
	{

	}
	
	public static CRequestSyncStandDataPacket decode(PacketBuffer buffer)
	{
		CRequestSyncStandDataPacket msg = new CRequestSyncStandDataPacket();
		return msg;
	}

	public static void handle(CRequestSyncStandDataPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				World world = player.world;
				IStandData props = StandDataCapability.get(player);

				WyNetwork.sendTo(new SSyncStandDataPacket(props), (ServerPlayerEntity) player);
			});			
		}
		ctx.get().setPacketHandled(true);
	}
}
