package xyz.pixelatedw.bizarremod.entities.stands.info;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.bizarremod.ModValues;
import xyz.pixelatedw.bizarremod.abilities.Ability;
import xyz.pixelatedw.bizarremod.abilities.greenday.MoldInfestationAbility;
import xyz.pixelatedw.bizarremod.entities.projectiles.PunchEntity;
import xyz.pixelatedw.bizarremod.entities.stands.GenericStandEntity;
import xyz.pixelatedw.bizarremod.entities.stands.GreenDayEntity;

public class GreenDayStandInfo extends StandInfo
{

	@Override
	public String getStandId()
	{
		return ModValues.STAND_ID_GREEN_DAY;
	}
	
	@Override
	public GenericStandEntity getStandEntity(PlayerEntity owner)
	{
		GenericStandEntity stand = new GreenDayEntity(owner.world, owner);

		return stand;
	}

	@Override
	public Ability[] getAbilities()
	{
		return new Ability[] 
				{ 
					new MoldInfestationAbility() 
				};
	}

	@Override
	public PunchEntity getPunch(PlayerEntity player)
	{
		PunchEntity punch = new PunchEntity(player, player.world);
		
		punch.setTexture(this.getStandId());
		punch.setDamage(2);
		
		return punch;
	}

}
