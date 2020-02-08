package xyz.pixelatedw.wypi.network;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.PacketDistributor.TargetPoint;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import xyz.pixelatedw.wypi.APIConfig;

public class WyNetwork
{
	private static int packet = 0;
	private static final String PROTOCOL_VERSION = Integer.toString(1);
	public static SimpleChannel channel = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(APIConfig.PROJECT_ID, "main_channel")).clientAcceptedVersions(PROTOCOL_VERSION::equals).serverAcceptedVersions(PROTOCOL_VERSION::equals).networkProtocolVersion(() -> PROTOCOL_VERSION).simpleChannel();

	public static <MSG> void registerPacket(Class<MSG> messageType, BiConsumer<MSG, PacketBuffer> encoder, Function<PacketBuffer, MSG> decoder, BiConsumer<MSG, Supplier<NetworkEvent.Context>> messageConsumer)
	{
		channel.registerMessage(packet++, messageType, encoder, decoder, messageConsumer);
	}
	
	public static <MSG> void sendToServer(MSG msg)
	{
		channel.sendToServer(msg);
	}

	public static <MSG> void sendTo(MSG msg, ServerPlayerEntity player)
	{
		if (!(player instanceof FakePlayer))
			channel.sendTo(msg, player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
	}

	public static <MSG> void sendToAll(MSG msg)
	{
		channel.send(PacketDistributor.ALL.noArg(), msg);
	}

	public static <MSG> void sendToAllAround(MSG msg, LivingEntity sender)
	{
		try
		{
			channel.send(PacketDistributor.NEAR.with(() -> new TargetPoint(sender.posX, sender.posY, sender.posZ, 50, sender.dimension)), msg);
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
