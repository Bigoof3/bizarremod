package xyz.pixelatedw.bizarremod.items;


import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import xyz.pixelatedw.bizarremod.api.stands.StandInfo;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.init.ModEntities;
import xyz.pixelatedw.bizarremod.packets.server.SSyncStandDataPacket;
import xyz.pixelatedw.bizarremod.screens.StandSelectScreen;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.network.WyNetwork;

public class StandArrowItem extends Item
{
	public StandArrowItem()
	{
		super(new Properties().group(ItemGroup.TOOLS).maxStackSize(1));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
    { 
		if(world.isRemote)
		{
			if(player.isCreative())
				Minecraft.getInstance().displayGuiScreen(new StandSelectScreen(player));
		}
		else if(!world.isRemote)
		{
			if(!player.isCreative())
			{
				IStandData props = StandDataCapability.get(player);
				int totalStands = ModEntities.getRegisteredStands().size();
				int randomStand = (int) WyHelper.randomWithRange(0, totalStands - 1);
				StandInfo info = (StandInfo) ModEntities.getRegisteredStands().values().toArray()[randomStand];
				
				if(info == null)
					return new ActionResult<>(ActionResultType.FAIL, player.getHeldItem(hand));
								
				props.setStand(info.getStandId());
				player.getHeldItem(hand).setCount(0);
				
				WyNetwork.sendTo(new SSyncStandDataPacket(props), (ServerPlayerEntity)player);
			}
			
			return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
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
