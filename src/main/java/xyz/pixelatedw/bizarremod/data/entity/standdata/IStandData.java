package xyz.pixelatedw.bizarremod.data.entity.standdata;

public interface IStandData
{

	String getStand();
	void setStand(String standName);
	
	boolean hasStandSummoned();
	void setStandSummoned(boolean value);
}
