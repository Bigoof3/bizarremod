package xyz.pixelatedw.bizarremod.entities.projectiles;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;
import xyz.pixelatedw.bizarremod.api.PunchBlocksHelper;
import xyz.pixelatedw.bizarremod.api.StandLogicHelper;
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
		
		this.onBlockImpactEvent = this::onBlockImpactEvent;
	}
	
	private void onBlockImpactEvent(BlockPos hit)
	{
		BlockState state = this.world.getBlockState(hit);
		
		if(!(this.getThrower() instanceof PlayerEntity))
			return;
		
		double blockHardness = state.getPlayerRelativeBlockHardness((PlayerEntity) this.getThrower(), this.world, hit);
		int standDamage = StandLogicHelper.getStandEntityOf((PlayerEntity) this.getThrower()).getDestructivePower();
		double blockDamage = ((blockHardness * standDamage) * 10);
		int damage = (int) (PunchBlocksHelper.getDamage(hit) + Math.round(blockDamage));
		
		if(damage == 0 && blockDamage >= 1)
		{
			PunchBlocksHelper.addDamagedBlock(hit, 1);
			this.world.sendBlockBreakProgress(this.getEntityId(), hit, 0);
		}
		else if(damage > 0 && damage < 10)
		{
			this.world.sendBlockBreakProgress(this.getEntityId(), hit, -1);
			damage++;
			PunchBlocksHelper.setDamage(hit, damage);
			this.world.sendBlockBreakProgress(this.getEntityId(), hit, damage - 1);
		}
		else if(damage >= 10)
		{
			PunchBlocksHelper.removeDamagedBlock(hit);
			TileEntity tileentity = state.hasTileEntity() ? this.world.getTileEntity(hit) : null;
            LootContext.Builder loot = (new LootContext.Builder((ServerWorld)this.world)).withRandom(this.world.rand).withParameter(LootParameters.POSITION, hit).withParameter(LootParameters.TOOL, ItemStack.EMPTY).withNullableParameter(LootParameters.BLOCK_ENTITY, tileentity);
			Block.spawnDrops(state, loot);
			this.world.removeBlock(hit, false);
		}
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
