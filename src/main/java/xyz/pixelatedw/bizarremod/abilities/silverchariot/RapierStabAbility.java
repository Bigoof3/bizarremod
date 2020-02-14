package xyz.pixelatedw.bizarremod.abilities.silverchariot;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.bizarremod.api.StandLogicHelper;
import xyz.pixelatedw.bizarremod.api.abilities.IStandAbility;
import xyz.pixelatedw.bizarremod.api.stands.GenericStandEntity;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class RapierStabAbility extends Ability implements IStandAbility
{
	public RapierStabAbility()
	{
		super("Rapier Stab", AbilityCategory.ALL);

		this.setMaxCooldown(1);

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
		EntityRayTraceResult hit = WyHelper.rayTraceEntities(player, 10);
		Entity target = hit != null ? hit.getEntity() : null;

		if (target == null)
			return false;

		GenericStandEntity stand = StandLogicHelper.getStandEntity(player);

		stand.setPositionAndUpdate(target.posX, target.posY, target.posZ);
		
		target.attackEntityFrom(DamageSource.MAGIC, 2);

		return true;
	}
}