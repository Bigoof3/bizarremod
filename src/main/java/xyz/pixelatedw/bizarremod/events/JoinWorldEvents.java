package xyz.pixelatedw.bizarremod.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.packets.server.SSyncStandDataPacket;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.server.SSyncAbilityDataPacket;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class JoinWorldEvents
{
	@SubscribeEvent
	public static void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		if (event.getEntity() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getEntity();
			IStandData standProps = StandDataCapability.get(player);
			IAbilityData abilityProps = AbilityDataCapability.get(player);

			if (WyHelper.isNullOrEmpty(standProps.getStand()))
				return;

			standProps.setStandSummoned(false);

			if (!player.world.isRemote)
			{
				WyNetwork.sendTo(new SSyncStandDataPacket(standProps), (ServerPlayerEntity) player);
				WyNetwork.sendTo(new SSyncAbilityDataPacket(abilityProps), (ServerPlayerEntity) player);
			}

		}
	}
}
