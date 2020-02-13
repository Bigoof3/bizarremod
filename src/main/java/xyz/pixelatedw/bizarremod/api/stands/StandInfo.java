package xyz.pixelatedw.bizarremod.api.stands;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.wypi.abilities.Ability;

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
