package xyz.pixelatedw.bizarremod.packets.server;

import java.util.UUID;
import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.bizarremod.ModMain;
import xyz.pixelatedw.bizarremod.api.stands.GenericStandEntity;
import xyz.pixelatedw.bizarremod.data.entity.standdata.IStandData;
import xyz.pixelatedw.bizarremod.data.entity.standdata.StandDataCapability;
import xyz.pixelatedw.wypi.WyHelper;

public class SStandExistencePacket
{
	private UUID ownerUUID;
	private int standId;

	public SStandExistencePacket() {}
	
	public SStandExistencePacket(UUID uuid, int standId)
	{
		this.ownerUUID = uuid;
		this.standId = standId;
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeUniqueId(this.ownerUUID);
		buffer.writeInt(this.standId);
	}

	public static SStandExistencePacket decode(PacketBuffer buffer)
	{
		SStandExistencePacket msg = new SStandExistencePacket();
		msg.ownerUUID = buffer.readUniqueId();
		msg.standId = buffer.readInt();
		return msg;
	}

	public static void handle(SStandExistencePacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ModMain.PROXY.getPlayer();
				World world = player.world;
				IStandData observerProps = StandDataCapability.get(player);			
				IStandData summonerProps = StandDataCapability.get(world.getPlayerByUuid(message.ownerUUID));
				GenericStandEntity stand = (GenericStandEntity) world.getEntityByID(message.standId);
				
				if(!WyHelper.isNullOrEmpty(observerProps.getStand()) || player.getUniqueID().equals(message.ownerUUID))
					return;
				
				stand.remove();
			});
		}
		ctx.get().setPacketHandled(true);
	}
}
