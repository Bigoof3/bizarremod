package xyz.pixelatedw.wypi.json.models.item;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.json.models.JSONModelItem;
import xyz.pixelatedw.wypi.json.models.JSONPredicateObject;

public class JSONModelPredicates extends JSONModelItem
{
	protected JSONPredicateObject[] predicates;

	public JSONModelPredicates(String itemName, String template, JSONPredicateObject... predicate)
	{
		super(itemName, template, itemName);
		this.predicates = predicate;
	}
	
	@Override
	public String[] getModel()
	{
		for(JSONPredicateObject predicateObject : this.predicates)
		{
			JSONModelSimpleItem predicateItem = new JSONModelSimpleItem(this.getItemName() + "_" + predicateObject.getName(), this.getItemName());

			File jsonModel = new File(APIConfig.getResourceFolderPath() + "/assets/" + APIConfig.PROJECT_ID + "/models/item/" + this.getItemName() + "_" + predicateObject.getName() + ".json");
			if (jsonModel.exists())
				continue;

			try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(jsonModel), "UTF-8")))
			{
				String[] model = predicateItem.replaceMarkedElements();

				if (model == null)
					continue;

				for (String line : model)
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
		
		String[] model = this.replaceMarkedElements();
		
		return this.replacePredicateOverrides(model);
	}
	
	protected String[] replacePredicateOverrides(String[] model)
	{
		List<String> formattedList = new ArrayList<String>();
		
		for(String line : model)
		{
			String formattedLine = line;
			if(line.contains("\"${overrides}\""))
				formattedLine = formattedLine.replace("\"${overrides}\"", this.getPredicates());

			formattedList.add(formattedLine);
		}
		
		String[] formattedLines = new String[formattedList.size()];
		return formattedList.toArray(formattedLines);
	}
	
	protected String getPredicates()
	{
		StringBuilder sb = new StringBuilder();

		int i = 0;
		for(JSONPredicateObject predicateObject : this.predicates)
		{
			sb.append("{\n");
				sb.append("\t\t\t\"predicate\": \n");
				sb.append("\t\t\t{\n");
				int j = 0;
				for(Pair<String, Double> predicate : predicateObject.getPredicates())
				{
					sb.append("\t\t\t\t\"" + predicate.getKey() + "\": " + predicate.getValue() + "" + (predicateObject.getPredicates().length > j + 1 ? "," : "") + "\n");
					j++;
				}
				sb.append("\t\t\t},\n");
				sb.append("\t\t\t\"model\": \"" + APIConfig.PROJECT_ID + ":item/" + this.getItemName() + "_" + predicateObject.getName() + "\"\n");
			sb.append("\t\t}" + (this.predicates.length > i + 1 ? "," : ""));
			i++;
		}

		return sb.toString();
	}	
}
