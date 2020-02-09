package xyz.pixelatedw.bizarremod.items.weapons;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemTier;
import net.minecraft.item.SwordItem;

public class SilverChariotsRapierItem extends SwordItem
{

	public SilverChariotsRapierItem()
	{
		super(ItemTier.DIAMOND, 3, -2.4F, new Item.Properties().group(ItemGroup.COMBAT));
	}

}
