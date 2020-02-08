package xyz.pixelatedw.wypi.json.models;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;

public abstract class JSONModelItem implements IJSONModel
{

	private String itemName, parentItemName;
	private File template;
	
	public JSONModelItem(String itemName, String template)
	{
		this.itemName = WyHelper.getResourceName(itemName);
		this.parentItemName = "item/generated";
		this.template = new File(APIConfig.getResourceFolderPath() + "/data/" + APIConfig.PROJECT_ID + "/json_templates/models/item/" + template + ".json");
	}
	
	public JSONModelItem(String itemName, String template, String parentItemName)
	{
		this.itemName = WyHelper.getResourceName(itemName);
		this.parentItemName = APIConfig.PROJECT_ID + ":item/" + parentItemName;
		this.template = new File(APIConfig.getResourceFolderPath() + "/data/" + APIConfig.PROJECT_ID + "/json_templates/models/item/" + template + ".json");
	}
	
	public String[] replaceMarkedElements()
	{
		try
		{
			List<String> lines = Files.readAllLines(Paths.get(this.getTemplateFile()), StandardCharsets.UTF_8);
			List<String> formattedList = new ArrayList<String>();
			
			for(String line : lines)
			{
				String formattedLine = line;
				if(line.contains("${modid}"))
					formattedLine = formattedLine.replace("${modid}", APIConfig.PROJECT_ID);
				
				if(line.contains("${texture}"))
					formattedLine = formattedLine.replace("${texture}", this.getItemName());

				if(line.contains("${parentItemName}"))
					formattedLine = formattedLine.replace("${parentItemName}", this.getParentItemName());

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
	
	protected String getParentItemName()
	{
		return this.parentItemName;
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
