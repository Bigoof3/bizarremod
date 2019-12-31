package xyz.pixelatedw.bizarremod.abilities.greenday;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.bizarremod.abilities.PassiveAbility;
import xyz.pixelatedw.bizarremod.api.WyHelper;
import xyz.pixelatedw.bizarremod.entities.stands.GenericStandEntity;
import xyz.pixelatedw.bizarremod.init.ModPotionEffects;

public class MoldInfestationAbility extends PassiveAbility
{

	@Override
	public void tick(LivingEntity user)
	{
		for(LivingEntity entity : WyHelper.getNearbyEntities(user.getPosition(), user.world, 50, LivingEntity.class))
		{
			if(entity instanceof GenericStandEntity || entity == user)
				continue;

			if(entity.getPosition().compareTo(new Vec3i(entity.posX, entity.prevPosY, entity.posZ)) < 0.0)
			{
				entity.addPotionEffect(new EffectInstance(ModPotionEffects.GREEN_DAY_MOLD, 200, 1));
			}
		}
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void renderDescription(FontRenderer fontObj, int posX, int posY)
	{
		WyHelper.drawCenteredString(fontObj, "- Mold Infestation -", posX + 185, posY + 60, -1);
		WyHelper.drawCenteredString(fontObj, TextFormatting.AQUA + " Passive", posX + 183, posY + 72, -1);
		
		WyHelper.drawCenteredString(fontObj, "Produces a potent mold that rots and destroys", posX + 190, posY + 95, -1);
		WyHelper.drawCenteredString(fontObj, "the flesh of those it infects.", posX + 190, posY + 110, -1);
		WyHelper.drawCenteredString(fontObj, "The mold's growth is triggered when the potential", posX + 190, posY + 125, -1);
		WyHelper.drawCenteredString(fontObj, "victims lowers their current altitude.", posX + 190, posY + 140, -1);
	}

}
