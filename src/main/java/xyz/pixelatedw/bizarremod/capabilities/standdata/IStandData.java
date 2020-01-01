package xyz.pixelatedw.bizarremod.capabilities.standdata;

import xyz.pixelatedw.bizarremod.abilities.Ability;

public interface IStandData
{

	String getStand();
	void setStand(String standName);
	
	boolean hasStandSummoned();
	void setStandSummoned(boolean value);
	
	Ability getPrimaryAbility();
	void setPrimaryAbility(Ability ability);

	Ability getSecondaryAbility();
	void setSecondaryAbility(Ability ability);
}
