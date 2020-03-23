package xyz.pixelatedw.bizarremod.abilities.sun;

import java.util.List;

import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.bizarremod.api.StandLogicHelper;
import xyz.pixelatedw.bizarremod.api.abilities.IStandAbility;
import xyz.pixelatedw.bizarremod.api.stands.GenericStandEntity;
import xyz.pixelatedw.bizarremod.data.entity.standdata.IStandData;
import xyz.pixelatedw.bizarremod.data.entity.standdata.StandDataCapability;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.PassiveAbility;

public class HeatPassiveAbility extends PassiveAbility implements IStandAbility {

	public int tick;

	public HeatPassiveAbility() {
		super("Heat", AbilityCategory.ALL);
		// TODO Auto-generated constructor stub
		this.duringPassive = this::duringPassive;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void renderDescription(FontRenderer fontObj, int posX, int posY) {
		// TODO Auto-generated method stub
		this.drawLine("- " + this.getName() + " -", posX + 185, posY + 60);
		this.drawLine(TextFormatting.AQUA + " Passive", posX + 183, posY + 72);

		this.drawLine("Emits heat that passively ", posX + 190, posY + 95);
		this.drawLine("burns all entities around it.", posX + 190, posY + 110);
	}

	private void duringPassive(PlayerEntity user) {

		IStandData standProps = StandDataCapability.get(user);

		tick++;
		if (!standProps.hasStandSummoned())
			return;
		if (!user.world.isRemote) {
			GenericStandEntity sun = StandLogicHelper.getStandEntityOf(user);

			List<LivingEntity> targets = WyHelper.getEntitiesNear(sun.getPosition(), user.world, 100,
					LivingEntity.class);

			targets.removeIf(entity -> entity instanceof GenericStandEntity);

			for (LivingEntity e : targets) {
				BlockPos pos = new BlockPos(e.getPosition().getX(), e.getPosition().getY() + 2, e.getPosition().getZ());
				Block block = e.world.getBlockState(pos).getBlock();
				BlockPos pos2 = new BlockPos(e.getPosition().getX(), e.getPosition().getY() + 3,
						e.getPosition().getZ());
				Block block2 = e.world.getBlockState(pos2).getBlock();
				BlockPos pos3 = new BlockPos(e.getPosition().getX(), e.getPosition().getY() + 4,
						e.getPosition().getZ());
				Block block3 = e.world.getBlockState(pos3).getBlock();
				BlockPos pos4 = new BlockPos(e.getPosition().getX(), e.getPosition().getY() + 5,
						e.getPosition().getZ());
				Block block4 = e.world.getBlockState(pos4).getBlock();

				if (sun != null) {
					if (!(block instanceof AirBlock) && !(block2 instanceof AirBlock) && !(block3 instanceof AirBlock)
							&& !(block4 instanceof AirBlock)) {

					} else if (!(block instanceof AirBlock) || !(block2 instanceof AirBlock)
							|| !(block3 instanceof AirBlock)) {
						if (tick % 200 == 0) {
							e.attackEntityFrom(DamageSource.ON_FIRE, 1);
						}
					} else {
						e.setFire(10);
					}
				}
			}
		}
	}

}
