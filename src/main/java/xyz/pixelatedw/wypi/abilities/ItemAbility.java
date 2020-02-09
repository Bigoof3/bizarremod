package xyz.pixelatedw.wypi.abilities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;

public abstract class ItemAbility extends ContinuousAbility
{	
	public ItemAbility(String name, AbilityCategory category)
	{
		super(name, category);
		
		this.onStartContinuityEvent = this::onStartContinuityEvent;
	}

	/*
	 *  Event Consumers
	 */
	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		if (player.getHeldItemMainhand().isEmpty() && !this.getItemStack().isEmpty())
		{
			player.inventory.setInventorySlotContents(player.inventory.currentItem, this.getItemStack());
			return true;
		}
		else
		{
			if(this.getItemStack().isEmpty())
				player.sendMessage(new TranslationTextComponent("ability.item.empty_stack"));
			else
				player.sendMessage(new TranslationTextComponent("ability.item.another_item_in_hand"));
			return false;
		}
	}
	
	/*
	 * 	Methods
	 */
	public abstract ItemStack getItemStack();
	public abstract boolean canBeActive(PlayerEntity player);
}
