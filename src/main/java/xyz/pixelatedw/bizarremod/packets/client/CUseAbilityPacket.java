package xyz.pixelatedw.bizarremod.packets.client;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

public class CUseAbilityPacket
{
	private int slot;
	
	public CUseAbilityPacket() {}
	
	public CUseAbilityPacket(int slot)
	{
		this.slot = slot;
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.slot);
	}
	
	public static CUseAbilityPacket decode(PacketBuffer buffer)
	{
		CUseAbilityPacket msg = new CUseAbilityPacket();
		msg.slot = buffer.readInt();
		return msg;
	}

	public static void handle(CUseAbilityPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				IAbilityData abilityProps = AbilityDataCapability.get(player);

				Ability abl = abilityProps.getEquippedAbility(message.slot);
				
				if(abl != null)
					abl.use(player);
			});			
		}
		ctx.get().setPacketHandled(true);
	}
}
