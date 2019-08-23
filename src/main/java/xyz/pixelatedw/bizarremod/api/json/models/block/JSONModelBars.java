package xyz.pixelatedw.bizarremod.api.json.models.block;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import xyz.pixelatedw.bizarremod.ModValues;
import xyz.pixelatedw.bizarremod.api.json.models.JSONModelBlock;

public class JSONModelBars extends JSONModelBlock
{
	private File paneComponentTemplate;
	private String[] paneComponents = new String[] { "_cap",  "_cap_alt", "_post", "_post_ends", "_side", "_side_alt" };
	
	public JSONModelBars(String blockName)
	{
		super(blockName, "", "pane");
	}

	@Override
	public String[] getModel()
	{
		for(String component : paneComponents)
		{
			this.paneComponentTemplate = new File(ModValues.PROJECT_RESOURCES_FOLDER + "/data/" + ModValues.PROJECT_ID + "/json_templates/models/block/pane" + component + ".json");

			File jsonModel = new File(ModValues.PROJECT_RESOURCES_FOLDER + "/assets/" + ModValues.PROJECT_ID + "/models/block/" + this.getBlockName() + "" + component + ".json");
			if (jsonModel.exists())
				continue;
			
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(jsonModel), "UTF-8")))
			{
				String[] model = this.replaceMarkedElements(this.paneComponentTemplate.toURI());
				
				if(model == null)
					continue;
				
				for(String line : model)
				{
					writer.write(line + "\n");
				}
				writer.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
			
		return null;
	}

	@Override
	public String[] getBlockStateModel()
	{
		return this.replaceMarkedElements(this.getBlockStateTemplateFile());
	}

}
