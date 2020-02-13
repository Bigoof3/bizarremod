package xyz.pixelatedw.bizarremod.api;

import xyz.pixelatedw.bizarremod.ModMain;
import xyz.pixelatedw.bizarremod.api.stands.StandInfo;
import xyz.pixelatedw.bizarremod.init.ModEntities;

public class StandLogicHelper
{
	public static StandInfo getStandInfo(String standName)
	{
		if(ModEntities.getRegisteredStands().containsKey(standName))
			return ModEntities.getRegisteredStands().get(standName);
		else
		{
			ModMain.LOGGER.warn(standName + " is not a registered Stand !");
			return null;
		}
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
			
			case 'I': return 127;
			case 'N': return -2;
			case '?': return -1;
			
			default: return -1;
		}
	}
	
	public static String convertStandStat(byte value)
	{
		switch(value)
		{
			case 4: return "A";
			case 3: return "B";
			case 2: return "C";
			case 1: return "D";
			case 0: return "E";
			
			case 5: return "∞";
			case 6: return "Ø";
			case 7: return "?";
			
			default: return "?";
		}
	}
	
}
