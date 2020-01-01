package xyz.pixelatedw.bizarremod.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.bizarremod.Env;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.init.ModNetwork;
import xyz.pixelatedw.bizarremod.packets.server.SSyncStandDataPacket;

@Mod.EventBusSubscriber(modid = Env.PROJECT_ID)
public class JoinWorldEvents
{
	@SubscribeEvent
	public static void onEntityJoinWorld(EntityJoinWorldEvent event)
	{		
		if(event.getEntity() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getEntity();
			IStandData props = StandDataCapability.get(player);

			props.setStandSummoned(false);
			
			System.out.println(props.getPrimaryAbility() + " / " + props.getSecondaryAbility());
			
			if(!player.world.isRemote)
				ModNetwork.sendTo(new SSyncStandDataPacket(props), (ServerPlayerEntity)player);
		}
	}
}
