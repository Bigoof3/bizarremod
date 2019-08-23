package xyz.pixelatedw.bizarremod.packets.client;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.entities.stands.GenericStandEntity;
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

	public static void handle(CStandControlPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				World world = player.world;
				IStandData props = StandDataCapability.get(player);

				if(!props.hasStandSummoned())
				{
					GenericStandEntity stand = StandLogicHelper.getRegisteredStands().get(message.standName).summonStand(player);
					
					props.setStandSummoned(true);
					stand.onSummon(player);
					world.addEntity(stand);
				}
				else
				{
					double radius = 1.5;
					AxisAlignedBB aabb = new AxisAlignedBB(player.posX, player.posY, player.posZ, player.posX + 1, player.posY + 1, player.posZ + 1).grow(radius, radius, radius);
					GenericStandEntity target = (GenericStandEntity) player.world.getEntitiesWithinAABBExcludingEntity(player, aabb).get(0);
					
					if(target.getOwner() == player)
					{
						props.setStandSummoned(false);
						target.onCancel(player);
						target.remove();
					}
				}
			});			
		}
		ctx.get().setPacketHandled(true);
	}
}
