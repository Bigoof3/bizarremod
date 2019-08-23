package xyz.pixelatedw.bizarremod.entities.stands.info;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.bizarremod.entities.stands.GenericStandEntity;

public abstract class StandInfo
{

	public abstract GenericStandEntity summonStand(PlayerEntity owner);
	
}
