package xyz.pixelatedw.bizarremod.init;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import xyz.pixelatedw.bizarremod.Env;
import xyz.pixelatedw.bizarremod.packets.client.CRequestSyncStandDataPacket;
import xyz.pixelatedw.bizarremod.packets.client.CStandControlPacket;
import xyz.pixelatedw.bizarremod.packets.client.CStandPunchPacket;
import xyz.pixelatedw.bizarremod.packets.client.CUseAbilityPacket;
import xyz.pixelatedw.bizarremod.packets.server.SSyncStandDataPacket;

public class ModNetwork
{
	private static final String PROTOCOL_VERSION = Integer.toString(1);
	private static SimpleChannel channel = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(Env.PROJECT_ID, "main_channel")).clientAcceptedVersions(PROTOCOL_VERSION::equals).serverAcceptedVersions(PROTOCOL_VERSION::equals).networkProtocolVersion(() -> PROTOCOL_VERSION).simpleChannel();

	public static void init()
	{
		int packet = 0;

		// Client
		channel.registerMessage(packet++, CStandControlPacket.class, CStandControlPacket::encode, CStandControlPacket::decode, CStandControlPacket::handle);
		channel.registerMessage(packet++, CRequestSyncStandDataPacket.class, CRequestSyncStandDataPacket::encode, CRequestSyncStandDataPacket::decode, CRequestSyncStandDataPacket::handle);
		channel.registerMessage(packet++, CStandPunchPacket.class, CStandPunchPacket::encode, CStandPunchPacket::decode, CStandPunchPacket::handle);
		channel.registerMessage(packet++, CUseAbilityPacket.class, CUseAbilityPacket::encode, CUseAbilityPacket::decode, CUseAbilityPacket::handle);

		// Server
		channel.registerMessage(packet++, SSyncStandDataPacket.class, SSyncStandDataPacket::encode, SSyncStandDataPacket::decode, SSyncStandDataPacket::handle);
	}

	public static <MSG> void sendToServer(MSG msg)
	{
		channel.sendToServer(msg);
	}

	public static <MSG> void sendTo(MSG msg, ServerPlayerEntity player)
	{
		if (!(player instanceof FakePlayer))
		{
			channel.sendTo(msg, player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		}
	}

	public static <MSG> void sendToAll(MSG msg)
	{
		channel.send(PacketDistributor.ALL.noArg(), msg);
	}

	public static <MSG> void sendToAllAround(MSG msg, LivingEntity sender)
	{
		try
		{
			Chunk chunk = sender.world.getChunk(sender.getPosition().getX(), sender.getPosition().getZ());
			channel.send(PacketDistributor.TRACKING_CHUNK.with(() -> chunk), msg);
			if (sender instanceof ServerPlayerEntity)
				sendTo(msg, (ServerPlayerEntity) sender);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return;
		}
	}
}
