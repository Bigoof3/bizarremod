package xyz.pixelatedw.bizarremod.capabilities.standdata;

public class StandDataBase implements IStandData
{

	private String stand = "";
	private boolean hasStandSummoned = false;
	
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
}
