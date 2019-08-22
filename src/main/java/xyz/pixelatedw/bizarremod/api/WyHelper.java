package xyz.pixelatedw.bizarremod.api;

public class WyHelper
{

	public static String upperCaseFirst(String text)
	{
		return Character.toUpperCase(text.charAt(0)) + text.substring(1) + " "; 
	}
	
	public static String getFancyName(String text)
	{
		return text.replaceAll("\\s+", "").toLowerCase().replaceAll("'", "").replaceAll("-", "").replaceAll(":", "").replaceAll("#", "").replace(",", "");
	}
}
