package xyz.pixelatedw.bizarremod.abilities.greenday;

import java.util.List;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.bizarremod.api.abilities.IStandAbility;
import xyz.pixelatedw.bizarremod.api.stands.GenericStandEntity;
import xyz.pixelatedw.bizarremod.data.entity.standdata.IStandData;
import xyz.pixelatedw.bizarremod.data.entity.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.init.ModEffects;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.PassiveAbility;

public class MoldInfestationAbility extends PassiveAbility implements IStandAbility
{

	public MoldInfestationAbility()
	{
		super("Mold Infestation", AbilityCategory.ALL);
		
		this.duringPassive = this::duringPassive;
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void renderDescription(FontRenderer fontObj, int posX, int posY)
	{
		this.drawLine("- " + this.getName() + " -", posX + 185, posY + 60);
		this.drawLine(TextFormatting.AQUA + " Passive", posX + 183, posY + 72);
		
		this.drawLine("Produces a potent mold that rots and destroys", posX + 190, posY + 95);
		this.drawLine("the flesh of those it infects.", posX + 190, posY + 110);
		this.drawLine("The mold's growth is triggered when the potential", posX + 190, posY + 125);
		this.drawLine("victims lowers their current altitude.", posX + 190, posY + 140);
	}
	
	private void duringPassive(PlayerEntity user)
	{
		IStandData standProps = StandDataCapability.get(user);
		
		if(!standProps.hasStandSummoned())
			return;
		
		List<LivingEntity> targets = WyHelper.getEntitiesNear(user.getPosition(), user.world, 50, LivingEntity.class);
		targets.remove(user);
		targets.removeIf(entity -> entity instanceof GenericStandEntity);
		
		for(LivingEntity entity : targets)
		{
			if(entity.getPosition().compareTo(new Vec3i(entity.posX, entity.prevPosY, entity.posZ)) < 0.0)
				entity.addPotionEffect(new EffectInstance(ModEffects.GREEN_DAY_MOLD, 300, 0));
		}
	}

}
