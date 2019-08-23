package xyz.pixelatedw.bizarremod.entities.stands.info;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.bizarremod.entities.stands.GenericStandEntity;
import xyz.pixelatedw.bizarremod.entities.stands.GreenDayEntity;

public class GreenDayStandInfo extends StandInfo
{

	@Override
	public GenericStandEntity summonStand(PlayerEntity owner)
	{
		GenericStandEntity stand = new GreenDayEntity(owner.world, owner);

		return stand;
	}

}
