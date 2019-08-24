package xyz.pixelatedw.bizarremod.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public abstract class PassiveAbility extends Ability
{
	public abstract void tick(LivingEntity user);
	
	@Override
	public void use(PlayerEntity player) {}
}
