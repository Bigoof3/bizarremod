package xyz.pixelatedw.bizarremod.abilities.greenday;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.Vec3i;
import xyz.pixelatedw.bizarremod.abilities.PassiveAbility;
import xyz.pixelatedw.bizarremod.api.WyHelper;
import xyz.pixelatedw.bizarremod.entities.stands.GenericStandEntity;

public class MoldInfestationAbility extends PassiveAbility
{

	@Override
	public void tick(LivingEntity user)
	{
		for(LivingEntity entity : WyHelper.<LivingEntity>getNearbyEntities(user, 50, LivingEntity.class))
		{
			if(entity instanceof GenericStandEntity)
				continue;
			
			if(entity.getPosition().compareTo(new Vec3i(entity.posX, entity.prevPosY, entity.posZ)) < 0.0)
			{
				entity.addPotionEffect(new EffectInstance(Effects.POISON, 200, 1));
			}
		}
	}

}
