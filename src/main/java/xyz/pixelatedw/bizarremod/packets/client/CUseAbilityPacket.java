package xyz.pixelatedw.bizarremod.packets.client;

import java.io.IOException;
import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.bizarremod.abilities.Ability;
import xyz.pixelatedw.bizarremod.api.WyHelper;

public class CUseAbilityPacket
{
	private Ability ability;
	
	public CUseAbilityPacket() {}
	
	public CUseAbilityPacket(Ability ability)
	{
		this.ability = ability;
	}

	public void encode(PacketBuffer buffer)
	{
		try
		{
			buffer.writeByteArray(WyHelper.serialize(this.ability));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static CUseAbilityPacket decode(PacketBuffer buffer)
	{
		CUseAbilityPacket msg = new CUseAbilityPacket();
		try
		{
			msg.ability = (Ability) WyHelper.deserialize(buffer.readByteArray());
		}
		catch (ClassNotFoundException | IOException e)
		{
			e.printStackTrace();
		}
		return msg;
	}

	public static void handle(CUseAbilityPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();

				message.ability.use(player);
			});			
		}
		ctx.get().setPacketHandled(true);
	}
}
