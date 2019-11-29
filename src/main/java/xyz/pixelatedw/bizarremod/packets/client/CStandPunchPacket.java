package xyz.pixelatedw.bizarremod.packets.client;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.bizarremod.api.StandInfo;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.entities.projectiles.PunchEntity;
import xyz.pixelatedw.bizarremod.helpers.StandLogicHelper;

public class CStandPunchPacket
{
	private String standName = "";
	
	public CStandPunchPacket() {}
	
	public CStandPunchPacket(String standName)
	{
		this.standName = standName;
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeString(this.standName);
	}
	
	public static CStandPunchPacket decode(PacketBuffer buffer)
	{
		CStandPunchPacket msg = new CStandPunchPacket();
		msg.standName = buffer.readString();
		return msg;
	}

	public static void handle(CStandPunchPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				World world = player.world;
				IStandData props = StandDataCapability.get(player);

				if(props.hasStandSummoned())
				{
					StandInfo standInfo = StandLogicHelper.getRegisteredStands().get(message.standName);
					
					PunchEntity punch = standInfo.getPunch(player);
					
					if(punch == null)
						return;
					
					world.addEntity(punch);
					punch.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
				}
			});			
		}
		ctx.get().setPacketHandled(true);
	}
}
