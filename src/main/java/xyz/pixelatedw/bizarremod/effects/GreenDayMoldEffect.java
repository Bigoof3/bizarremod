package xyz.pixelatedw.bizarremod.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3i;
import xyz.pixelatedw.bizarremod.entities.stands.GenericStandEntity;
import xyz.pixelatedw.bizarremod.init.ModEffects;
import xyz.pixelatedw.wypi.WyHelper;

public class GreenDayMoldEffect extends Effect
{

	public GreenDayMoldEffect()
	{
		super(EffectType.HARMFUL, 5149489);
	}
	
	@Override
	public boolean isReady(int duration, int amplifier)
	{
		return duration % 25 == 0;
	}

	@Override
	public void performEffect(LivingEntity entity, int amplifier) 
	{
		entity.attackEntityFrom(DamageSource.MAGIC, 1);

		for(LivingEntity target : WyHelper.getEntitiesNear(entity.getPosition(), entity.world, 20, LivingEntity.class))
		{
			if(target instanceof GenericStandEntity || target == entity)
				continue;

			if(target.getPosition().compareTo(new Vec3i(target.posX, target.prevPosY, target.posZ)) < 0.0)
				target.addPotionEffect(new EffectInstance(ModEffects.GREEN_DAY_MOLD, 300, 0));
		}
	}
}
