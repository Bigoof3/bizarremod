package xyz.pixelatedw.bizarremod.api.json.models;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import xyz.pixelatedw.bizarremod.ModValues;
import xyz.pixelatedw.bizarremod.api.WyHelper;

public abstract class JSONModelItem implements IJSONModel
{

	private String itemName;
	private File template;
	
	public JSONModelItem(String itemName, String template)
	{
		this.itemName = WyHelper.getFancyName(itemName);
		this.template = new File(ModValues.PROJECT_RESOURCES_FOLDER + "/data/" + ModValues.PROJECT_ID + "/json_templates/models/item/" + template + ".json");
	}
	
	protected String[] replaceMarkedElements()
	{
		try
		{
			List<String> lines = Files.readAllLines(Paths.get(this.getTemplateFile()), StandardCharsets.UTF_8);
			List<String> formattedList = new ArrayList<String>();
			
			for(String line : lines)
			{
				String formattedLine = line;
				if(line.contains("${modid}"))
					formattedLine = formattedLine.replace("${modid}", ModValues.PROJECT_ID);
				
				if(line.contains("${texture}"))
					formattedLine = formattedLine.replace("${texture}", this.getItemName());

				formattedList.add(formattedLine);
			}
			
			String[] formattedLines = new String[formattedList.size()];
			return formattedList.toArray(formattedLines);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	protected String getItemName()
	{
		return this.itemName;
	}
	
	protected URI getTemplateFile()
	{
		return this.template.toURI();
	}
}
