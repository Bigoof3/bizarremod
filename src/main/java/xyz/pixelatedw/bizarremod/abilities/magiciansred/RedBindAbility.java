package xyz.pixelatedw.bizarremod.abilities.magiciansred;

import java.util.List;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.bizarremod.api.StandInfo;
import xyz.pixelatedw.bizarremod.api.WyHelper;
import xyz.pixelatedw.bizarremod.api.abilities.ContinuousAbility;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.entities.stands.GenericStandEntity;
import xyz.pixelatedw.bizarremod.helpers.StandLogicHelper;
import xyz.pixelatedw.bizarremod.particles.effects.RedBindEffect;

public class RedBindAbility extends ContinuousAbility
{
	public static final RedBindAbility INSTANCE = new RedBindAbility();

	private static final RedBindEffect RED_BIND_EFFECT = new RedBindEffect();
	
	public RedBindAbility()
	{
		super("Red Bind");
		this.setMaxCooldown(30);
		this.setThreshold(20);

		this.duringContinuity = this::duringContinuity;
	}

	@Override
	public void renderDescription(FontRenderer fontObj, int posX, int posY)
	{
		this.drawLine("- " + this.getName() + " -", posX + 185, posY + 60);
		this.drawLine(TextFormatting.GREEN + " Active", posX + 183, posY + 72);

		this.drawLine("Magician's Red can use his flame to tie his opponent.", posX + 190, posY + 95);
		this.drawLine("By putting a flame too close to the opponent's face,", posX + 190, posY + 110);
		this.drawLine("it eventually burns the oxygen around them to the point", posX + 190, posY + 125);
		this.drawLine("of suffocating them.", posX + 190, posY + 140);
	}

	protected void duringContinuity(PlayerEntity player, int passiveTimer)
	{
		IStandData props = StandDataCapability.get(player);
		StandInfo info = StandLogicHelper.getStandInfo(props.getStand());

		List<LivingEntity> list = WyHelper.getNearbyEntities(player.getPosition(), player.world, 10, LivingEntity.class);
		list.remove(player);
		list.removeIf(entity -> entity instanceof GenericStandEntity);

		for (LivingEntity target : list)
		{
			System.out.println(target.getPosition().getY());
			target.setMotion(0, 0.1, 0);
			target.fallDistance = 0;
		}
	}
}
