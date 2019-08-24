package xyz.pixelatedw.bizarremod.items;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import xyz.pixelatedw.bizarremod.ModMain;
import xyz.pixelatedw.bizarremod.ModValues;

public class ItemStandArrow extends Item
{
	public ItemStandArrow()
	{
		super(new Properties().group(ItemGroup.TOOLS).maxStackSize(1));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
    { 
		if(!world.isRemote)
		{
			if(player.isCreative())
				ModMain.proxy.openScreen(ModValues.GUI_CREATIVE_STAND_SELECT, player);
			else
			{
				System.out.println("Random Stand here");
				player.getHeldItem(hand).setCount(0);
			}
		}
		
		return new ActionResult<>(ActionResultType.FAIL, player.getHeldItem(hand));
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) 
	{
		System.out.println("Random Stand here");
		return true;
	}
}
