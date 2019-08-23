package xyz.pixelatedw.bizarremod.api.json.models.item;

import xyz.pixelatedw.bizarremod.api.json.models.JSONModelItem;

public class JSONModelRod extends JSONModelItem
{
	public JSONModelRod(String itemName)
	{
		super(itemName, "rod");
	}

	@Override
	public String[] getModel()
	{		
		return this.replaceMarkedElements();
	}
}
