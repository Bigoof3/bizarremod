package xyz.pixelatedw.wypi.abilities.events;

import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class AbilityProjectileEvents
{
	@Cancelable
	public static class Hit extends Event
	{
		
		private AbilityProjectileEntity projectile;
		private RayTraceResult hit;
		
		public Hit(AbilityProjectileEntity abilityProjectileEntity, RayTraceResult hit)
		{
			this.projectile = abilityProjectileEntity;
			this.hit = hit;
		}
		
		public AbilityProjectileEntity getProjectile()
		{
			return this.projectile;
		}
		
		public RayTraceResult getHit()
		{
			return this.hit;
		}
	}	
}
