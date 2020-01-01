package xyz.pixelatedw.bizarremod.api;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.bizarremod.abilities.Ability;
import xyz.pixelatedw.bizarremod.entities.stands.GenericStandEntity;

public abstract class StandInfo
{

	public abstract String getStandId();
	public abstract JoJoPart getStandPart();
	public abstract GenericStandEntity getStandEntity(PlayerEntity owner);
	public abstract Ability[] getAbilities();

	public enum JoJoPart
	{
		STARDUST_CRUSADERS,
		DIAMOND_IS_UNBREAKABLE,
		VENTO_AUREO,
		STONE_OCEAN,
		STEEL_BALL_RUN,
		JOJOLION
	}
	
}
