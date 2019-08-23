package xyz.pixelatedw.bizarremod.capabilities.standdata;

public class StandDataBase implements IStandData
{

	private String stand = "";
	
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

}
