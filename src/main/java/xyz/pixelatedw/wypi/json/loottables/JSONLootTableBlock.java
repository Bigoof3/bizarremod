package xyz.pixelatedw.wypi.json.loottables;

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

public abstract class JSONLootTableBlock implements IJSONLootTable
{
	private File template;
	private int min, max;
	private String itemName;

	public JSONLootTableBlock(String itemName, int min, int max, String templateName)
	{
		this.itemName = WyHelper.getResourceName(itemName);
		this.min = min;
		this.max = max;
		this.template = new File(APIConfig.getResourceFolderPath() + "/data/" + APIConfig.PROJECT_ID + "/json_templates/loot_tables/block/" + templateName + ".json");
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
					formattedLine = formattedLine.replace("${modid}", APIConfig.PROJECT_ID);
				
				if(line.contains("${item}"))
					formattedLine = formattedLine.replace("${item}", this.getItemName());
				
				if(line.contains("\"${min}\""))
					formattedLine = formattedLine.replace("\"${min}\"", this.getMin() + "");
	
				if(line.contains("\"${max}\""))
					formattedLine = formattedLine.replace("\"${max}\"", this.getMax() + "");
							
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
	
	protected int getMin()
	{
		return this.min;
	}
	
	protected int getMax()
	{
		return this.max;
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
