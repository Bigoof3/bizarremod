package xyz.pixelatedw.bizarremod.helpers;

import java.util.HashMap;

import xyz.pixelatedw.bizarremod.ModMain;
import xyz.pixelatedw.bizarremod.ModValues;
import xyz.pixelatedw.bizarremod.entities.stands.GreenDayEntity;
import xyz.pixelatedw.bizarremod.entities.stands.StandInfo;

public class StandLogicHelper
{

	private static HashMap<String, StandInfo> stands = new HashMap<String, StandInfo>();
	
	static
	{
		stands.put(ModValues.STAND_ID_GREEN_DAY, new GreenDayEntity.GreenDayStandInfo());
	}
	
	public static StandInfo getStandInfo(String standName)
	{
		if(stands.containsKey(standName))
			return stands.get(standName);
		else
		{
			ModMain.LOGGER.warn(standName + " is not a registered Stand !");
			return null;
		}
	}
	
	public static HashMap<String, StandInfo> getRegisteredStands()
	{
		return stands;
	}
	
	public static byte convertStandStat(char value)
	{
		switch(Character.toUpperCase(value))
		{
			case 'A': return 4;
			case 'B': return 3;
			case 'C': return 2;
			case 'D': return 1;
			case 'E': return 0;
			
			case 'I': return 5;
			case 'N': return 6;
			case '?': return 7;
			
			default: return 7;
		}
	}
	
	public static char convertStandStat(byte value)
	{
		switch(value)
		{
			case 4: return 'A';
			case 3: return 'B';
			case 2: return 'C';
			case 1: return 'D';
			case 0: return 'E';
			
			case 5: return '∞';
			case 6: return 'Ø';
			case 7: return '?';
			
			default: return '?';
		}
	}
	
}
