package xyz.pixelatedw.bizarremod.abilities.magiciansred;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.bizarremod.abilities.Ability;
import xyz.pixelatedw.bizarremod.api.WyHelper;
import xyz.pixelatedw.bizarremod.entities.stands.GenericStandEntity;

public class RedBindAbility extends Ability
{
	public RedBindAbility()
	{
		this.setMaxCooldown(60);
		
		this.onUseEvent = this::onUseEvent;
	}
	
	@Override
	public String getName()
	{
		return "Red Bind";
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
	
	protected void onUseEvent(PlayerEntity player, Ability ability)
	{
		WyHelper.getNearbyEntities(player.getPosition(), player.world, 10, LivingEntity.class).parallelStream().filter(entity -> entity.isAlive() && entity != player && !(entity instanceof GenericStandEntity)).forEach(entity ->
		{
			entity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 5));
			for(int i = 0; i < 5; i++)
				((ServerWorld) player.world).spawnParticle(ParticleTypes.FLAME, entity.posX + (WyHelper.randomDouble() / 3), entity.posY + (WyHelper.randomDouble() / 2), entity.posZ + (WyHelper.randomDouble() / 3), 5, 0, 0, 0, 0.1D);
		});
	}

}
