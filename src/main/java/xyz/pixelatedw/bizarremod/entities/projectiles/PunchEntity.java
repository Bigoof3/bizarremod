package xyz.pixelatedw.bizarremod.entities.projectiles;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import xyz.pixelatedw.bizarremod.init.ModEntities;

public class PunchEntity extends ThrowableEntity
{
	
	private String texture = "";
	private double damage = 1;
	
	public PunchEntity(World world)
	{
		super(ModEntities.PUNCH, world);
	}
	
	public PunchEntity(LivingEntity livingEntity, World world)
	{
		super(ModEntities.PUNCH, livingEntity, world);
	}

	@Override
	protected void onImpact(RayTraceResult result)
	{
		
	}
	
	@Override
	public void tick()
	{
		super.tick();
	}

	@Override
	protected void registerData() {}

	public void setDamage(double damage)
	{
		this.damage = damage;
	}
	
	public double getDamage()
	{
		return this.damage;
	}
	
	public void setTexture(String texture)
	{
		this.texture = texture;
	}
	
	public String getTexture()
	{
		return this.texture;
	}
	
	@Override
    public IPacket<?> createSpawnPacket() 
	{
		return NetworkHooks.getEntitySpawningPacket(this);
    }
}
