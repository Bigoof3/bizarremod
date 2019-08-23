package xyz.pixelatedw.bizarremod.entities.stands.info;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.bizarremod.entities.stands.GenericStandEntity;
import xyz.pixelatedw.bizarremod.entities.stands.GreenDayEntity;

public class GreenDayStandInfo extends StandInfo
{

	@Override
	public void summonStand(PlayerEntity owner)
	{
		World world = owner.world;

		GenericStandEntity stand = new GreenDayEntity(world, owner);
		world.addEntity(stand);
		stand.onSummon(owner);
	}

}
