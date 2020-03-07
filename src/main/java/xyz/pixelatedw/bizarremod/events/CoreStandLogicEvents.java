package xyz.pixelatedw.bizarremod.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.WorldTickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.bizarremod.api.PunchBlocksHelper;
import xyz.pixelatedw.bizarremod.api.StandLogicHelper;
import xyz.pixelatedw.bizarremod.api.stands.StandInfo;
import xyz.pixelatedw.bizarremod.data.entity.standdata.IStandData;
import xyz.pixelatedw.bizarremod.data.entity.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.data.world.ExtendedWorldData;
import xyz.pixelatedw.bizarremod.packets.server.SSyncStandDataPacket;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.server.SSyncAbilityDataPacket;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class CoreStandLogicEvents
{
	@SubscribeEvent
	public static void onWorldTick(WorldTickEvent event)
	{
		if(event.world.getGameTime() % 100 == 0 && event.phase == Phase.START)
		{
			PunchBlocksHelper.tick(event.world);
		}
	}
	
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
	
	@SubscribeEvent
	public static void onStandOwnerDies(LivingDeathEvent event)
	{
		if(event.getEntityLiving() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getEntity();
			IStandData standProps = StandDataCapability.get(player);
			
			if (WyHelper.isNullOrEmpty(standProps.getStand()))
				return;
			
			standProps.setStandSummoned(false);
			StandInfo info = StandLogicHelper.getStandInfo(standProps.getStand());
			ExtendedWorldData.get(player.world).removeUsedStand(info);
			standProps.setStand("");
		}
	}
}
