package xyz.pixelatedw.bizarremod.entities.stands;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.bizarremod.abilities.Ability;
import xyz.pixelatedw.bizarremod.entities.projectiles.PunchEntity;

public abstract class StandInfo
{

	public abstract String getStandId();
	public abstract GenericStandEntity getStandEntity(PlayerEntity owner);
	public abstract Ability[] getAbilities();
	public abstract PunchEntity getPunch(PlayerEntity player);
	
}
