package xyz.pixelatedw.bizarremod.entities.stands;

import xyz.pixelatedw.bizarremod.api.stands.StandInfo;

public abstract class DefaultStandInfo extends StandInfo
{

	private String standId = "";
	
	public DefaultStandInfo()
	{
		this.standId = this.getDefaultStandId();
	}
	
	@Override
	public String getStandId()
	{
		return this.standId;
	}
	
	@Override
	public String getDefaultStandId()
	{
		return this.getStandTextures()[0][1];
	}

	@Override
	public void setStandId(String id)
	{
		this.standId = id;
	}

}
