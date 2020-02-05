package xyz.pixelatedw.bizarremod.entities.stands;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import xyz.pixelatedw.bizarremod.Consts;
import xyz.pixelatedw.bizarremod.abilities.silverchariot.ArmorOffAbility;
import xyz.pixelatedw.bizarremod.abilities.silverchariot.RapierStabAbility;
import xyz.pixelatedw.bizarremod.abilities.silverchariot.ShootingSwordAbility;
import xyz.pixelatedw.bizarremod.api.StandInfo;
import xyz.pixelatedw.bizarremod.api.abilities.Ability;
import xyz.pixelatedw.bizarremod.init.ModEntities;

public class SilverChariotEntity extends GenericStandEntity
{
	protected static final DataParameter<Boolean> ARMOR_STATE = EntityDataManager.createKey(SilverChariotEntity.class, DataSerializers.BOOLEAN);

	public SilverChariotEntity(World world, PlayerEntity owner)
	{
		super(ModEntities.SILVER_CHARIOT, world, owner);
	}
	
	public SilverChariotEntity(World world)
	{
		super(ModEntities.SILVER_CHARIOT, world);
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
		this.setSpeed('A');
		this.setRange('C');
		this.setPersistance('B');
		this.setPrecision('B');
		this.setDevelopmentPotential('C');
		
		this.dataManager.register(ARMOR_STATE, true);
	}

	@Override
	public String getStandName()
	{
		return "Silver Chariot";
	}

	public boolean hasArmor()
	{
		return this.dataManager.get(ARMOR_STATE);
	}
	
	public void removeArmor()
	{
		this.dataManager.set(ARMOR_STATE, false);
	}
	
	@Override
	public void onCancel(PlayerEntity owner) {}
	
	public static class SilverChariotStandInfo extends StandInfo
	{
		private Ability[] abilities = new Ability[] 
				{
					new RapierStabAbility(),
					new ArmorOffAbility(),
					new ShootingSwordAbility()
				};

		@Override
		public String getStandId()
		{
			return Consts.STAND_ID_SILVER_CHARIOT;
		}
		
		@Override
		public GenericStandEntity getStandEntity(PlayerEntity owner)
		{
			GenericStandEntity stand = new SilverChariotEntity(owner.world, owner);
	
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
			return JoJoPart.STARDUST_CRUSADERS;
		}
	}
}
