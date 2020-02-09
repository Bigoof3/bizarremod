package xyz.pixelatedw.bizarremod.entities.projectiles;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import xyz.pixelatedw.bizarremod.init.ModEntities;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class PunchEntity extends AbilityProjectileEntity
{
	private static final DataParameter<String> TEXTURE = EntityDataManager.createKey(PunchEntity.class, DataSerializers.STRING);
	
	public PunchEntity(World world)
	{
		super(ModEntities.PUNCH, world);
	}

	public PunchEntity(PlayerEntity player, World world)
	{
		super(ModEntities.PUNCH, world, player);
	}
	
	@Override
	protected void registerData()
	{
		super.registerData();
		this.dataManager.register(TEXTURE, "");
	}
	
	public void setTexture(String texture)
	{
		this.dataManager.set(TEXTURE, texture);
	}

	public String getTexture()
	{
		return this.dataManager.get(TEXTURE);
	}
}
