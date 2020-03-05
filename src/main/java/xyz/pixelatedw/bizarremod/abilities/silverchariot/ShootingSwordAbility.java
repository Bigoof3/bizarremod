package xyz.pixelatedw.bizarremod.abilities.silverchariot;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.bizarremod.api.StandLogicHelper;
import xyz.pixelatedw.bizarremod.api.abilities.IStandAbility;
import xyz.pixelatedw.bizarremod.entities.projectiles.RapierEntity;
import xyz.pixelatedw.bizarremod.entities.stands.SilverChariotEntity;
import xyz.pixelatedw.bizarremod.init.ModItems;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class ShootingSwordAbility extends ContinuousAbility implements IStandAbility
{
	public ShootingSwordAbility()
	{
		super("Shooting Sword", AbilityCategory.ALL);
		
		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.duringContinuity = this::duringContinuity;
	}
	
	@Override
	public void renderDescription(FontRenderer fontObj, int posX, int posY)
	{
		this.drawLine("- " + this.getName() + " -", posX + 185, posY + 60);
		this.drawLine(TextFormatting.GREEN + " Active", posX + 183, posY + 72);
		
		this.drawLine("Silver Chariot's only ranged ability", posX + 190, posY + 95);
		this.drawLine("This launches his sword with great force and precision", posX + 190, posY + 110);
		this.drawLine("striking with enough force to pierce a human body.", posX + 190, posY + 125);
		this.drawLine("Silver Chariot can only materialize one rapier.", posX + 190, posY + 140);
	}
	
	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		SilverChariotEntity stand = (SilverChariotEntity) StandLogicHelper.getStandEntityOf(player);

		if (!stand.hasRapier())
			return false;		
		
		RapierEntity rapier = new RapierEntity(player, player.world);
		rapier.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2 + stand.getSpeed(), 4 - stand.getPrecision());

		rapier.setCanGetStuckInGround();
		rapier.setDamage(6 + stand.getDestructivePower());
		rapier.setMaxLife(Integer.MAX_VALUE);
		//rapier.setGravity(0.05F);

		player.world.addEntity(rapier);
		
		stand.removeRapier();
		stand.setItemStackToSlot(EquipmentSlotType.MAINHAND, ItemStack.EMPTY);
		
		return true;
	}

	private void duringContinuity(PlayerEntity player, int timer)
	{
		SilverChariotEntity stand = (SilverChariotEntity) StandLogicHelper.getStandEntityOf(player);
		
		if(stand == null)
			return;
		
		RapierEntity rapier = WyHelper.getEntitiesNear(player.getPosition(), player.world, stand.hasArmor() ? 2 : 5, RapierEntity.class).parallelStream().findFirst().orElse(null);

		if(rapier == null || !rapier.isStuckInGround())
			return;
		
		stand.setPositionAndUpdate(rapier.posX, rapier.posY, rapier.posZ);
		rapier.remove();
		
		stand.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ModItems.SILVER_CHARIOTS_RAPIER));
		stand.equipRapier();
		
		this.stopContinuity(player);
	}
}
