package xyz.pixelatedw.wypi.json.models.block;

import java.io.File;

import xyz.pixelatedw.wypi.json.models.JSONModelBlock;

public class JSONModelThinBlock extends JSONModelBlock
{
	private File paneComponentTemplate;
	
	public JSONModelThinBlock(String blockName)
	{
		super(blockName, "thin_block", "simple_blockstate");
	}

	@Override
	public String[] getModel()
	{			
		return this.replaceMarkedElements(this.getBlockTemplateFile());
	}

	@Override
	public String[] getBlockStateModel()
	{
		return this.replaceMarkedElements(this.getBlockStateTemplateFile());
	}

}
