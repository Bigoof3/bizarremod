package xyz.pixelatedw.bizarremod.entities.stands;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import xyz.pixelatedw.bizarremod.Consts;
import xyz.pixelatedw.bizarremod.api.stands.GenericStandEntity;
import xyz.pixelatedw.bizarremod.init.ModAbilities;
import xyz.pixelatedw.bizarremod.init.ModEntities;
import xyz.pixelatedw.wypi.abilities.Ability;

public class AerosmithEntity extends GenericStandEntity
{
	protected static final DataParameter<Boolean> RADAR_ACTIVE = EntityDataManager.createKey(AerosmithEntity.class, DataSerializers.BOOLEAN);

	public AerosmithEntity(World world, PlayerEntity owner)
	{
		super(ModEntities.AEROSMITH, world, owner);
	}
	
	public AerosmithEntity(World world)
	{
		super(ModEntities.AEROSMITH, world);
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
		this.setDestructivePower('B');
		this.setSpeed('B');
		this.setRange('B');
		this.setPersistance('C');
		this.setPrecision('E');
		this.setDevelopmentPotential('C');
		
		this.dataManager.register(RADAR_ACTIVE, false);
	}
	
	@Override
	public String getStandName()
	{
		return "Aerosmith";
	}
	
	@Override
	public void onSummon(PlayerEntity owner)
	{
		super.onSummon(owner);
	}

	@Override
	public void onCancel(PlayerEntity owner)
	{
		
	}
	
	public void triggerRadar(boolean flag)
	{
		this.dataManager.set(RADAR_ACTIVE, flag);
	}
	
	public boolean hasRadarActive()
	{
		return this.dataManager.get(RADAR_ACTIVE);
	}
	
	public static class AerosmithStandInfo extends DefaultStandInfo
	{

		private Ability[] abilities = new Ability[] 
					{
						ModAbilities.MACHINE_GUNS,
						ModAbilities.BOMB,
						ModAbilities.CARBON_DIOXIDE_RADAR
					};
		
		@Override
		public JoJoPart getStandPart()
		{
			return JoJoPart.VENTO_AUREO;
		}

		@Override
		public GenericStandEntity getStandEntity(PlayerEntity owner)
		{
			GenericStandEntity stand = new AerosmithEntity(owner.world, owner);

			return stand;
		}

		@Override
		public Ability[] getAbilities()
		{
			return this.abilities;
		}
		
		@Override
		public String[][] getStandTextures()
		{
			return new String[][]
			{
				{"Standard", Consts.STAND_ID_AEROSMITH },
				{"Alternative", Consts.STAND_ID_AEROSMITH_ALT }
			};
		}
	}
}
