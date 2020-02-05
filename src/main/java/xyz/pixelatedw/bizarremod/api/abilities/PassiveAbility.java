package xyz.pixelatedw.bizarremod.api.abilities;

import net.minecraft.entity.player.PlayerEntity;

public abstract class PassiveAbility extends Ability
{
	public PassiveAbility(String name)
	{
		super(name);
	}

	@Override
	public void use(PlayerEntity player) {}
	
	public abstract void tick(PlayerEntity user);
}
