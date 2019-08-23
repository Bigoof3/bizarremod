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

public abstract class JSONModelBlock implements IJSONModel
{
	private String blockName;
	private File blockTemplate, blockStateTemplate;

	public JSONModelBlock(String blockName, String blockTemplate, String blockStateTemplate)
	{
		this.blockName = WyHelper.getFancyName(blockName);
		this.blockTemplate = new File(ModValues.PROJECT_RESOURCES_FOLDER + "/data/" + ModValues.PROJECT_ID + "/json_templates/models/block/" + blockTemplate + ".json");
		this.blockStateTemplate = new File(ModValues.PROJECT_RESOURCES_FOLDER + "/data/" + ModValues.PROJECT_ID + "/json_templates/models/blockstate/" + blockStateTemplate + ".json");
	}

	public abstract String[] getBlockStateModel();
	
	protected String[] replaceMarkedElements(URI file)
	{
		try
		{
			List<String> lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
			List<String> formattedList = new ArrayList<String>();
			
			for(String line : lines)
			{
				String formattedLine = line;
				if(line.contains("${modid}"))
					formattedLine = formattedLine.replace("${modid}", ModValues.PROJECT_ID);
				
				if(line.contains("${texture}"))
					formattedLine = formattedLine.replace("${texture}", this.getBlockName());
								
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
	
	protected String getBlockName()
	{
		return this.blockName;
	}
	
	protected URI getBlockTemplateFile()
	{
		return this.blockTemplate.toURI();
	}
	
	protected URI getBlockStateTemplateFile()
	{
		return this.blockStateTemplate.toURI();
	}
}
