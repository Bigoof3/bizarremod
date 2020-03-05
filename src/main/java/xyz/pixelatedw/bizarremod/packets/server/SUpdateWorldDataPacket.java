package xyz.pixelatedw.bizarremod.packets.server;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.bizarremod.ModMain;
import xyz.pixelatedw.bizarremod.data.world.ExtendedWorldData;

public class SUpdateWorldDataPacket
{
	private CompoundNBT data;

	public SUpdateWorldDataPacket() {}
	
	public SUpdateWorldDataPacket(World world, ExtendedWorldData props)
	{
		this.data = new CompoundNBT();
		this.data = ExtendedWorldData.get(world).write(this.data);
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeCompoundTag(data);
	}
	
	public static SUpdateWorldDataPacket decode(PacketBuffer buffer)
	{
		SUpdateWorldDataPacket msg = new SUpdateWorldDataPacket();
		msg.data = buffer.readCompoundTag();
		return msg;
	}

	public static void handle(SUpdateWorldDataPacket message, Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ModMain.PROXY.getPlayer();
				World world = player.world;

				ExtendedWorldData.get(world).read(message.data);
			});			
		}
		ctx.get().setPacketHandled(true);
	}
}
