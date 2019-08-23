package xyz.pixelatedw.bizarremod.api.json.models.item;

import xyz.pixelatedw.bizarremod.api.json.models.JSONModelItem;

public class JSONModelSword extends JSONModelItem
{
	public JSONModelSword(String itemName)
	{
		super(itemName, "sword");
	}

	@Override
	public String[] getModel()
	{		
		return this.replaceMarkedElements();
	}
}
