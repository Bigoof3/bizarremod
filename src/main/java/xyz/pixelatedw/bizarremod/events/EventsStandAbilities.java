package xyz.pixelatedw.bizarremod.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import xyz.pixelatedw.bizarremod.abilities.Ability;
import xyz.pixelatedw.bizarremod.abilities.PassiveAbility;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.entities.projectiles.PunchEntity;
import xyz.pixelatedw.bizarremod.entities.stands.StandInfo;
import xyz.pixelatedw.bizarremod.helpers.StandLogicHelper;
import xyz.pixelatedw.bizarremod.init.ModNetwork;
import xyz.pixelatedw.bizarremod.packets.client.CRequestSyncStandDataPacket;

public class EventsStandAbilities
{

	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event)
	{
		if(event.getEntityLiving() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getEntityLiving();
			IStandData props = StandDataCapability.get(player);
			
			if(!props.hasStandSummoned() || player.world.isRemote)
				return;

			StandInfo info = StandLogicHelper.getStandInfo(props.getStand());
			
			for(Ability ability : info.getAbilities())
			{
				if(ability instanceof PassiveAbility)
					((PassiveAbility) ability).tick(player);
			}
		}
	}
	
	@SubscribeEvent
	public void onPunch(PlayerInteractEvent.LeftClickEmpty event)
	{
		PlayerEntity player = event.getPlayer();
		IStandData props = StandDataCapability.get(player);
		StandInfo info = StandLogicHelper.getStandInfo(props.getStand());

		PunchEntity punch = info.getPunch(player);
		if(punch == null)
			return;

		ModNetwork.sendToServer(new CRequestSyncStandDataPacket());
		
		if(!props.hasStandSummoned())
			return;
		
		player.world.addEntity(punch);
		punch.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
	}
	
}
