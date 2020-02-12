package xyz.pixelatedw.bizarremod.abilities.silverchariot;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.bizarremod.api.abilities.IStandAbility;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class RapierStabAbility extends Ability implements IStandAbility
{
	public RapierStabAbility()
	{
		super("Rapier Stab", AbilityCategory.ALL);

		this.onUseEvent = this::onUseEvent;
	}

	@Override
	public void renderDescription(FontRenderer fontObj, int posX, int posY)
	{
		this.drawLine("- " + this.getName() + " -", posX + 185, posY + 60);
		this.drawLine(TextFormatting.GREEN + " Active", posX + 183, posY + 72);

		this.drawLine("Allows the Stand to attack with it's rapier,", posX + 190, posY + 95);
		this.drawLine("longer range than that of a punch.", posX + 190, posY + 110);
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		RayTraceResult hit = WyHelper.rayTraceBlocks(player);

		BlockPos pos = new BlockPos(hit.getHitVec()).subtract(new Vec3i(1, 0, 1));
		
		Entity target = WyHelper.getEntitiesNear(pos, player.world, 1.5).stream().findFirst().orElse(null);
		
		if(target == null)
			return false;
		
		// Send Silver Chariot at them and damage the entity
		
		return true;
	}
}