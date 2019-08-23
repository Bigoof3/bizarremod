package xyz.pixelatedw.bizarremod.packets.client;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.bizarremod.helpers.StandLogicHelper;

public class CStandControlPacket
{
	private String standName = "";
	
	public CStandControlPacket() {}
	
	public CStandControlPacket(String standName)
	{
		this.standName = standName;
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeString(this.standName);
	}
	
	public static CStandControlPacket decode(PacketBuffer buffer)
	{
		CStandControlPacket msg = new CStandControlPacket();
		msg.standName = buffer.readString();
		return msg;
	}

	@SuppressWarnings("resource")
	public static void handle(CStandControlPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();

				StandLogicHelper.getRegisteredStands().get(message.standName).summonStand(player);		
			});			
		}
		ctx.get().setPacketHandled(true);
	}
}
