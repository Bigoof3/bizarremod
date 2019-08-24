package xyz.pixelatedw.bizarremod.abilities;

import net.minecraft.entity.LivingEntity;

public abstract class PassiveAbility extends Ability
{
	public abstract void tick(LivingEntity user);
}
