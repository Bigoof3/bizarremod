package xyz.pixelatedw.wypi.abilities;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;

public abstract class PassiveAbility extends Ability
{
	
	public PassiveAbility(String name, AbilityCategory category)
	{
		super(name, category);
	}

	@Override
	public void use(PlayerEntity player) {}
	
	public abstract void tick(PlayerEntity user);
}
