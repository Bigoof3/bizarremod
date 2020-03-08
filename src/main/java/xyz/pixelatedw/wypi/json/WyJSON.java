package xyz.pixelatedw.wypi.json;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.debug.WyDebug;

public class WyJSON
{
	/*
	 *  Seriously fuck these forced JSONs shoved down our throats only to make script kiddies happy with their datapacks
	 *  30 lines+ of JSON for a block to drop itself is not more useful or better looking than a few Java variables and methods...
	 */
	
	public static void runGenerators(boolean override)
	{
		if (!WyDebug.isDebug())
			return;
		
		WyJSON.generateJSONLangs();
		WyJSON.generateJSONModels(override);
		WyJSON.generateJSONLootTables(override);
	}
	
	public static void generateJSONLootTables(boolean override)
	{		
		Iterator i = WyRegistry.getLootTables().keySet().iterator();
		File lootTablesFolder = new File(APIConfig.getResourceFolderPath() + "/data/" + APIConfig.PROJECT_ID + "/loot_tables/");
		
		if (!lootTablesFolder.exists())
			lootTablesFolder.mkdirs();
		
		while (i.hasNext())
		{
			Object next = i.next();
			File jsonModel = null;

			if(next instanceof Block)
			{
				Block nextBlock = (Block) next;
				String name = WyHelper.getResourceName(nextBlock.getRegistryName().getPath());

				jsonModel = new File(APIConfig.getResourceFolderPath() + "/data/" + APIConfig.PROJECT_ID + "/loot_tables/blocks/" + name + ".json");			
			}
			
			if (jsonModel != null && jsonModel.exists() && !override)
				continue;
			
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(jsonModel), "UTF-8")))
			{
				String[] model = WyRegistry.getLootTables().get(next).getLootTable();
				
				if(model == null)
				{
					writer.close();
					Files.delete(jsonModel.toPath());
					continue;
				}
				
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
	}
	
	public static void generateJSONLangs()
	{
		Map<String, String> sorted = WyHelper.sortAlphabetically(WyRegistry.getLangMap());
		Set set = sorted.entrySet();
		Iterator i = set.iterator();

		Map.Entry prevEntry = null;

		File langFolder = new File(APIConfig.getResourceFolderPath() + "/assets/" + APIConfig.PROJECT_ID + "/lang/");
		langFolder.mkdirs();

		if (langFolder.exists())
		{
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(APIConfig.getResourceFolderPath() + "/assets/" + APIConfig.PROJECT_ID + "/lang/en_us.json"), "UTF-8")))
			{
				writer.write("{\n");
				while (i.hasNext())
				{
					Map.Entry entry = (Map.Entry) i.next();

					if (prevEntry != null)
					{
						if (!((String) prevEntry.getKey()).substring(0, 2).equals(((String) entry.getKey()).substring(0, 2)))
						{
							writer.write("\n");
						}
					}

					if (i.hasNext())
						writer.write("\t\"" + entry.getKey() + "\": \"" + entry.getValue() + "\",\n");
					else
						writer.write("\t\"" + entry.getKey() + "\": \"" + entry.getValue() + "\"\n");

					prevEntry = entry;
				}
				writer.write("}\n");
				writer.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void generateJSONModels(boolean override)
	{
		Iterator i = WyRegistry.getItems().keySet().iterator();
		File itemFolder = new File(APIConfig.getResourceFolderPath() + "/assets/" + APIConfig.PROJECT_ID + "/models/item/");

		if (!itemFolder.exists())
			itemFolder.mkdirs();
		
		while (i.hasNext())
		{
			Item item = (Item) i.next();
			String itemName = WyHelper.getResourceName(item.getRegistryName().getPath());

			File jsonModel = new File(APIConfig.getResourceFolderPath() + "/assets/" + APIConfig.PROJECT_ID + "/models/item/" + itemName + ".json");
			if (jsonModel.exists() && !override)
				continue;
			
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(jsonModel), "UTF-8")))
			{
				String[] model = WyRegistry.getItems().get(item).getModel();
				
				if(model == null)
				{
					writer.close();
					Files.delete(jsonModel.toPath());
					continue;
				}
				
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
		
		i = WyRegistry.getBlocks().keySet().iterator();
		
		File blockFolder = new File(APIConfig.getResourceFolderPath() + "/assets/" + APIConfig.PROJECT_ID + "/models/block/");
		if (!blockFolder.exists())
			blockFolder.mkdirs();
		
		File blockStatesFolder = new File(APIConfig.getResourceFolderPath() + "/assets/" + APIConfig.PROJECT_ID + "/blockstates/");
		if (!blockStatesFolder.exists())
			blockStatesFolder.mkdirs();
		
		while (i.hasNext())
		{
			Block block = (Block) i.next();
			String blockName = WyHelper.getResourceName(block.getRegistryName().getPath());

			File jsonModel = new File(APIConfig.getResourceFolderPath() + "/assets/" + APIConfig.PROJECT_ID + "/models/block/" + blockName + ".json");
			if (!jsonModel.exists() || override)
			{
				try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(jsonModel), "UTF-8")))
				{
					String[] model = WyRegistry.getBlocks().get(block).getModel();
					
					if(model == null)
					{
						writer.close();
						Files.delete(jsonModel.toPath());
					}
					else
					{
						for(String line : model)
						{
							writer.write(line + "\n");
						}
						writer.close();
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			
			jsonModel = new File(APIConfig.getResourceFolderPath() + "/assets/" + APIConfig.PROJECT_ID + "/blockstates/" + blockName + ".json");
			if (!jsonModel.exists() || override)
			{
				try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(jsonModel), "UTF-8")))
				{
					String[] model = WyRegistry.getBlocks().get(block).getBlockStateModel();
					
					if(model == null)
					{
						writer.close();
						Files.delete(jsonModel.toPath());
						continue;
					}
					else
					{
						for(String line : model)
						{
							writer.write(line + "\n");
						}
						writer.close();
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}

}
