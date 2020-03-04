package xyz.pixelatedw.bizarremod.api.stands;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.registries.ForgeRegistryEntry;
import xyz.pixelatedw.wypi.abilities.Ability;

public abstract class StandInfo extends ForgeRegistryEntry<StandInfo>
{

	public abstract String getStandId();
	public abstract void setStandId(String id);
	public abstract String getDefaultStandId();
	public abstract JoJoPart getStandPart();
	public abstract GenericStandEntity getStandEntity(PlayerEntity owner);
	public abstract Ability[] getAbilities();
	public abstract String[][] getStandTextures();
	
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
