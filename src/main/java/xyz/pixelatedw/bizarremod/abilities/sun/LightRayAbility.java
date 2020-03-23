package xyz.pixelatedw.bizarremod.abilities.sun;

import net.minecraft.block.BlockState;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import xyz.pixelatedw.bizarremod.api.StandLogicHelper;
import xyz.pixelatedw.bizarremod.api.abilities.IStandAbility;
import xyz.pixelatedw.bizarremod.api.stands.GenericStandEntity;
import xyz.pixelatedw.bizarremod.api.stands.StandInfo;
import xyz.pixelatedw.bizarremod.data.entity.standdata.IStandData;
import xyz.pixelatedw.bizarremod.data.entity.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.entities.projectiles.BulletEntity;
import xyz.pixelatedw.bizarremod.entities.projectiles.LightRayEntity;
import xyz.pixelatedw.bizarremod.init.ModEntities;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.RepeaterAbility;

public class LightRayAbility extends RepeaterAbility implements IStandAbility {

	public LightRayAbility() {
		super("Light Rays", AbilityCategory.ALL);
		// TODO Auto-generated constructor stub
		this.setMaxRepearCount(8, 10);
		this.setMaxCooldown(10);
		this.onUseEvent = this::onUseEvent;
	}

	@Override
	public void renderDescription(FontRenderer fontObj, int posX, int posY) {
		// TODO Auto-generated method stub

		this.drawLine("- " + this.getName() + " -", posX + 185, posY + 60);
		this.drawLine(TextFormatting.LIGHT_PURPLE + " Active (Repeater)", posX + 183, posY + 72);
		
		this.drawLine("The Sun shoots light rays capable of ", posX + 190, posY + 95);
		this.drawLine("melting anything it touches.", posX + 190, posY + 110);

	}

	private boolean onUseEvent(PlayerEntity p) {
		
		World world = p.world;
		if(!world.isRemote) {
		BlockRayTraceResult ray = WyHelper.rayTraceBlocks(p);
		EntityRayTraceResult rayEntity = WyHelper.rayTraceEntities(p, 100);
		IStandData props = StandDataCapability.get(p);
		GenericStandEntity stand = StandLogicHelper.getStandEntityOf(p);
		StandInfo info = StandLogicHelper.getStandInfo(props.getStand());

        LightRayEntity lightray = new LightRayEntity(p, world);
        
        
        
        lightray.setPosition(stand.posX , stand.posY, stand.posZ);
        if(rayEntity != null) {
        	Entity target = rayEntity.getEntity();
         Vec3i ePos = new Vec3i(target.posX, target.posY, target.posZ);
        double endPosX = WyHelper.randomWithRange(ePos.getX() -2, ePos.getX() + 2);
        double endPosZ = WyHelper.randomWithRange(ePos.getZ() -2, ePos.getZ() + 2);

        
        
        double motionX = (endPosX - stand.posX) / 20;
        double motionY =  (ePos.getY() - stand.posY)  / 20;
        double motionZ = (endPosZ - stand.posZ) / 20;

        
        lightray.setMotion(motionX, motionY, motionZ);
      
		lightray.setDamage(4 + info.getStandEntity(p).getDestructivePower());

        world.addEntity(lightray);
        return true;
        } else {
        	BlockPos pos = ray.getPos();
        	 double endPosX = WyHelper.randomWithRange(pos.getX() -2, pos.getX() + 2);
             double endPosZ = WyHelper.randomWithRange(pos.getZ() -2, pos.getZ() + 2);

             
             
             double motionX = (endPosX - stand.posX) / 20;
             double motionY =  (pos.getY() - stand.posY)  / 20;
             double motionZ = (endPosZ - stand.posZ) / 20;

             
             lightray.setMotion(motionX, motionY, motionZ);
           
     		lightray.setDamage(4 + info.getStandEntity(p).getDestructivePower());

             world.addEntity(lightray);
             return true;
        }
	}
		return false;
	}
}
