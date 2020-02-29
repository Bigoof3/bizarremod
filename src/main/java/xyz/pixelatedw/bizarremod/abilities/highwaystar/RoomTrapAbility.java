package xyz.pixelatedw.bizarremod.abilities.highwaystar;

import java.util.Optional;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.village.PointOfInterestManager;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.bizarremod.api.StandLogicHelper;
import xyz.pixelatedw.bizarremod.api.abilities.IStandAbility;
import xyz.pixelatedw.bizarremod.api.stands.StandInfo;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.entities.stands.HighwayStarEntity;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class RoomTrapAbility extends Ability implements IStandAbility
{
	public RoomTrapAbility()
	{
		super("Room Trap", AbilityCategory.ALL);

		this.onUseEvent = this::onUseEvent;
	}

	@Override
	public void renderDescription(FontRenderer fontObj, int posX, int posY)
	{
		this.drawLine("- " + this.getName() + " -", posX + 185, posY + 60);
		this.drawLine(TextFormatting.YELLOW + " Active (Continuous)", posX + 183, posY + 72);

		this.drawLine("Sets up a trap inside any room.", posX + 190, posY + 95);
		this.drawLine("Anyone who enters the room is targeted by Highway Star", posX + 190, posY + 110);
		this.drawLine("Once it has hold of its victim's scent, it begins to ", posX + 190, posY + 125);
		this.drawLine("relentlessly chase them anywhere until either one dies.", posX + 190, posY + 140);
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		Optional<BlockPos> optional = ((ServerWorld) player.world).getPointOfInterestManager().func_219147_b(PointOfInterestType.HOME.func_221045_c(), (pos) ->
		{
			return true;
		}, new BlockPos(player), 48, PointOfInterestManager.Status.ANY);

		if(optional.isPresent())
		{
			IStandData props = StandDataCapability.get(player);
			StandInfo info = StandLogicHelper.getStandInfo(props.getStand());
			
			HighwayStarEntity stand = StandLogicHelper.getStandEntity(player);
		
			if(player.getPosition().withinDistance(optional.get(), 5) && stand.getTrapPosition() == null)
			{
				stand.setTrapPosition(optional.get());
				player.sendMessage(new StringTextComponent("Trap set"));
			}
			
			return true;
		}
		else
		{
			return false;
		}
	}
}
