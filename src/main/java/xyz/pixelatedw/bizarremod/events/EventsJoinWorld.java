package xyz.pixelatedw.bizarremod.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.init.ModNetwork;
import xyz.pixelatedw.bizarremod.packets.server.SSyncStandDataPacket;

public class EventsJoinWorld
{
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event)
	{		
		if(event.getEntity() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getEntity();
			IStandData props = StandDataCapability.get(player);

			if(!player.world.isRemote)
				ModNetwork.sendTo(new SSyncStandDataPacket(props), (ServerPlayerEntity)player);
		}
	}
}
