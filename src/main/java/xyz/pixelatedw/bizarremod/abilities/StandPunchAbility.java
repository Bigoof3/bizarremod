package xyz.pixelatedw.bizarremod.abilities;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.bizarremod.api.StandLogicHelper;
import xyz.pixelatedw.bizarremod.api.abilities.IStandAbility;
import xyz.pixelatedw.bizarremod.api.stands.GenericStandEntity;
import xyz.pixelatedw.bizarremod.api.stands.StandInfo;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.entities.projectiles.PunchEntity;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class StandPunchAbility extends Ability implements IStandAbility
{
	private long lastTimePunched = 0;
	private int consecutivePunches = 0;
	private boolean isSneaking = false;

	public StandPunchAbility()
	{
		super("Punch", AbilityCategory.ALL);
		this.onUseEvent = this::onUseEvent;
		this.duringCooldownEvent = this::duringCooldownEvent;
		this.onEndCooldown = this::onEndCooldown;
	}

	@Override
	public void renderDescription(FontRenderer fontObj, int posX, int posY)
	{
		this.drawLine("- " + this.getName() + " -", posX + 185, posY + 60);
		this.drawLine(TextFormatting.GREEN + " Active", posX + 183, posY + 72);

		this.drawLine("Allows the Stand to attack with it's punch", posX + 190, posY + 95);
	}

	protected boolean onUseEvent(PlayerEntity player)
	{
		IStandData props = StandDataCapability.get(player);
		StandInfo info = StandLogicHelper.getStandInfo(props.getStand());

		int damage = 1 + info.getStandEntity(player).getDestructivePower();
		int range = info.getStandEntity(player).getRange() / 2;

		if (player.world.getGameTime() - this.lastTimePunched <= 5)
			this.consecutivePunches++;
		else
			this.consecutivePunches = 0;

		this.lastTimePunched = player.world.getGameTime();

		if (!player.isSneaking())
		{
			if (this.consecutivePunches >= 5)
			{
				this.setMaxCooldown(2);
				this.consecutivePunches = 0;
				this.isSneaking = false;
				return true;
			}

			PunchEntity punch = new PunchEntity(player, player.world);
			punch.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2 + (info.getStandEntity(player).getSpeed() / 3), 4 - info.getStandEntity(player).getPrecision());

			punch.setTexture(info.getStandId());
			punch.setDamage(damage);
			punch.setMaxLife(range);

			player.world.addEntity(punch);
		}
		else
		{
			GenericStandEntity stand = StandLogicHelper.getStandEntity(player);
			EntityRayTraceResult hit = WyHelper.rayTraceEntities(player, 5 + range);
			Entity target = hit != null ? hit.getEntity() : null;

			if (target == null)
				return false;

			if (this.consecutivePunches >= 5)
			{
				this.setMaxCooldown(2);
				this.consecutivePunches = 0;
				this.isSneaking = true;
				return true;
			}
			
			stand.setPositionAndUpdate(target.posX, target.posY, target.posZ);

			target.attackEntityFrom(DamageSource.MAGIC, damage);
		}

		return true;
	}

	private void duringCooldownEvent(PlayerEntity player, int timer)
	{
		IStandData props = StandDataCapability.get(player);
		StandInfo info = StandLogicHelper.getStandInfo(props.getStand());

		int damage = 1 + info.getStandEntity(player).getDestructivePower();
		int range = info.getStandEntity(player).getRange() / 2;

		if (timer <= 10 || timer % 2 != 0)
			return;

		if (!this.isSneaking)
		{
			double offsetX = WyHelper.randomDouble() / 1.25;
			double offsetY = WyHelper.randomDouble() / 1.25;
			double offsetZ = WyHelper.randomDouble() / 1.25;

			PunchEntity punch = new PunchEntity(player, player.world);
			punch.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2 + (info.getStandEntity(player).getSpeed() / 3), 7 - info.getStandEntity(player).getPrecision());

			punch.setTexture(info.getStandId());
			punch.setDamage(1 + damage);
			punch.setMaxLife(range);

			player.world.addEntity(punch);
		}
		else
		{
			GenericStandEntity stand = StandLogicHelper.getStandEntity(player);
			EntityRayTraceResult hit = WyHelper.rayTraceEntities(player, 5 + range);
			Entity target = hit != null ? hit.getEntity() : null;

			if (target == null)
				return;
			
			stand.setPositionAndUpdate(target.posX, target.posY, target.posZ);

			target.attackEntityFrom(DamageSource.MAGIC, damage);
		}
	}

	private void onEndCooldown(PlayerEntity player)
	{
		this.setMaxCooldown(0);
	}
}