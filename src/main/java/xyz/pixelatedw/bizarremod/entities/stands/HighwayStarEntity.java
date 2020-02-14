package xyz.pixelatedw.bizarremod.entities.stands;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import xyz.pixelatedw.bizarremod.Consts;
import xyz.pixelatedw.bizarremod.api.stands.GenericStandEntity;
import xyz.pixelatedw.bizarremod.api.stands.StandInfo;
import xyz.pixelatedw.bizarremod.init.ModAbilities;
import xyz.pixelatedw.bizarremod.init.ModEntities;
import xyz.pixelatedw.wypi.abilities.Ability;

public class HighwayStarEntity extends GenericStandEntity
{
	protected static final DataParameter<Boolean> IS_CHASING = EntityDataManager.createKey(HighwayStarEntity.class, DataSerializers.BOOLEAN);

	public HighwayStarEntity(World world, PlayerEntity owner)
	{
		super(ModEntities.HIGHWAY_STAR, world, owner);
	}
	
	public HighwayStarEntity(World world)
	{
		super(ModEntities.HIGHWAY_STAR, world);
	}
	
	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();
	}
	
	@Override
	protected void registerData()
	{
		super.registerData();
		this.setDestructivePower('C');
		this.setSpeed('B');
		this.setRange('A');
		this.setPersistance('A');
		this.setPrecision('E');
		this.setDevelopmentPotential('C');
		
		this.dataManager.register(IS_CHASING, false);
	}

	@Override
	public String getStandName()
	{
		return "Highway Star";
	}

	public boolean isChasing()
	{
		return this.dataManager.get(IS_CHASING);
	}
	
	public void startChasing()
	{
		this.dataManager.set(IS_CHASING, true);
	}
	
	@Override
	public void onCancel(PlayerEntity owner) {}

	public static class HighwayStarStandInfo extends StandInfo
	{
		private Ability[] abilities = new Ability[] 
				{
					ModAbilities.ROOM_TRAP
				};

		@Override
		public String getStandId()
		{
			return Consts.STAND_ID_HIGHWAY_STAR;
		}
		
		@Override
		public GenericStandEntity getStandEntity(PlayerEntity owner)
		{
			GenericStandEntity stand = new HighwayStarEntity(owner.world, owner);

			return stand;
		}
	
		@Override
		public Ability[] getAbilities()
		{
			return this.abilities;
		}
	
		@Override
		public JoJoPart getStandPart()
		{
			return JoJoPart.DIAMOND_IS_UNBREAKABLE;
		}
	}
}
