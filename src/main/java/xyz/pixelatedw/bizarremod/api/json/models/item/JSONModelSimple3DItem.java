package xyz.pixelatedw.bizarremod.api.json.models.item;

import java.util.ArrayList;
import java.util.List;

import xyz.pixelatedw.bizarremod.api.json.models.JSONModelItem;

public class JSONModelSimple3DItem extends JSONModelItem
{
	private double[] thirdPersonRotations, firstPersonRotations;
	private double[] thirdPersonTranslations, firstPersonTranslations;
	private double[] thirdPersonScales, firstPersonScales;

	public JSONModelSimple3DItem(String itemName)
	{
		super(itemName, "simple_3d_item");
		
		this.thirdPersonRotations = new double[] {  0, -90, 55 };
		this.thirdPersonTranslations = new double[] {  0, 2.0, -0.1 };
		this.thirdPersonScales = new double[] { 0.70, 0.70, 0.70 };
		
		this.firstPersonRotations = new double[] {  0, -90, 40 };
		this.firstPersonTranslations = new double[] { 1.13, 4.5, 1.13 };
		this.firstPersonScales = new double[] { 0.7, 0.7, 0.7 };
	}

	@Override
	public String[] getModel()
	{		
		String[] simpleItem = this.replaceMarkedElements();
		List<String> formattedList = new ArrayList<String>();
		
		for(String line : simpleItem)
		{
			String formattedLine = line;
			
			if(line.contains("\"${thirdperson_rotations}\""))
				formattedLine = formattedLine.replace("\"${thirdperson_rotations}\"", this.thirdPersonRotations[0] + ", " + this.thirdPersonRotations[1] + ", " + this.thirdPersonRotations[2]);
			if(line.contains("\"${thirdperson_translations}\""))
				formattedLine = formattedLine.replace("\"${thirdperson_translations}\"", this.thirdPersonTranslations[0] + ", " + this.thirdPersonTranslations[1] + ", " + this.thirdPersonTranslations[2]);
			if(line.contains("\"${thirdperson_scales}\""))
				formattedLine = formattedLine.replace("\"${thirdperson_scales}\"", this.thirdPersonScales[0] + ", " + this.thirdPersonScales[1] + ", " + this.thirdPersonScales[2]);
			
			if(line.contains("\"${firstperson_rotations}\""))
				formattedLine = formattedLine.replace("\"${firstperson_rotations}\"", this.firstPersonRotations[0] + ", " + this.firstPersonRotations[1] + ", " + this.firstPersonRotations[2]);
			if(line.contains("\"${firstperson_translations}\""))
				formattedLine = formattedLine.replace("\"${firstperson_translations}\"", this.firstPersonTranslations[0] + ", " + this.firstPersonTranslations[1] + ", " + this.firstPersonTranslations[2]);
			if(line.contains("\"${firstperson_scales}\""))
				formattedLine = formattedLine.replace("\"${firstperson_scales}\"", this.firstPersonScales[0] + ", " + this.firstPersonScales[1] + ", " + this.firstPersonScales[2]);		
			
			formattedList.add(formattedLine);
		}
		
		String[] formattedLines = new String[formattedList.size()];
		return formattedList.toArray(formattedLines);
	}
	
	public JSONModelSimple3DItem setFirstPersonRotations(double x, double y, double z)
	{
		this.firstPersonRotations = new double[] { x, y, z };
		return this;
	}
	
	public JSONModelSimple3DItem setFirstPersonTranslations(double x, double y, double z)
	{
		this.firstPersonTranslations = new double[] { x, y, z };
		return this;
	}
	
	public JSONModelSimple3DItem setFirstPersonScales(double x, double y, double z)
	{
		this.firstPersonScales = new double[] { x, y, z };
		return this;
	}
	
	public JSONModelSimple3DItem setThirdPersonRotations(double x, double y, double z)
	{
		this.thirdPersonRotations = new double[] { x, y, z };
		return this;
	}
	
	public JSONModelSimple3DItem setThirdPersonTranslations(double x, double y, double z)
	{
		this.thirdPersonTranslations = new double[] { x, y, z };
		return this;
	}
	
	public JSONModelSimple3DItem setThirdPersonScales(double x, double y, double z)
	{
		this.thirdPersonScales = new double[] { x, y, z };
		return this;
	}
}