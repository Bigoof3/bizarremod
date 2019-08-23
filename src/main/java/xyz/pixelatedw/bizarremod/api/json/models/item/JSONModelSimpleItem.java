package xyz.pixelatedw.bizarremod.api.json.models.item;

import xyz.pixelatedw.bizarremod.api.json.models.JSONModelItem;

public class JSONModelSimpleItem extends JSONModelItem
{
	public JSONModelSimpleItem(String itemName)
	{
		super(itemName, "simple_item");
	}

	@Override
	public String[] getModel()
	{		
		return this.replaceMarkedElements();
	}
}
