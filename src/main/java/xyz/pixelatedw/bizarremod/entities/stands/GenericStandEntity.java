package xyz.pixelatedw.bizarremod.entities.stands;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public abstract class GenericStandEntity extends Entity
{

	protected GenericStandEntity(EntityType type, World worldIn)
	{
		super(type, worldIn);
	}
	
	public void onSummon(PlayerEntity owner)
	{
		owner.sendMessage(this.getName());
	}
	
	
	abstract void onCancel(PlayerEntity owner);
}
