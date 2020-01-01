package xyz.pixelatedw.bizarremod.capabilities.standdata;

import xyz.pixelatedw.bizarremod.abilities.Ability;

public class StandDataBase implements IStandData
{

	private String stand = "";
	private boolean hasStandSummoned = false;
	private Ability primaryAbility, secondaryAbility;
	
	@Override
	public boolean hasStandSummoned()
	{
		return this.hasStandSummoned;
	}

	@Override
	public void setStandSummoned(boolean value)
	{
		this.hasStandSummoned = value;
	}
	
	@Override
	public String getStand()
	{
		return this.stand;
	}

	@Override
	public void setStand(String standName)
	{
		this.stand = standName;
	}

	@Override
	public Ability getPrimaryAbility()
	{
		return this.primaryAbility;
	}

	@Override
	public void setPrimaryAbility(Ability ability)
	{
		this.primaryAbility = ability;
	}

	@Override
	public Ability getSecondaryAbility()
	{
		return this.secondaryAbility;
	}

	@Override
	public void setSecondaryAbility(Ability ability)
	{
		this.secondaryAbility = ability;
	}

}
