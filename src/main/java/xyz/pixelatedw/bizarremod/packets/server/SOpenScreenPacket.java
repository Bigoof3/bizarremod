package xyz.pixelatedw.bizarremod.packets.server;

import java.util.function.Supplier;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.bizarremod.ModMain;

public class SOpenScreenPacket
{
	private int screenId;
	
	public SOpenScreenPacket() {}
	
	public SOpenScreenPacket openStandSelectScreen()
	{
		this.screenId = 1;
		return this;
	}
	
	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.screenId);
	}

	public static SOpenScreenPacket decode(PacketBuffer buffer)
	{
		SOpenScreenPacket msg = new SOpenScreenPacket();
		msg.screenId = buffer.readInt();
		return msg;
	}
	
	public static void handle(SOpenScreenPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
		{
			ctx.get().enqueueWork(() ->
			{
				ModMain.PROXY.openScreen(message.screenId);
			});
		}
		ctx.get().setPacketHandled(true);
	}
}
