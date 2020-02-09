package xyz.pixelatedw.bizarremod.entities.stands;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.bizarremod.Consts;
import xyz.pixelatedw.bizarremod.api.StandInfo;
import xyz.pixelatedw.bizarremod.init.ModAbilities;
import xyz.pixelatedw.bizarremod.init.ModEntities;
import xyz.pixelatedw.wypi.abilities.Ability;

public class AerosmithEntity extends GenericStandEntity
{

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
	
	public static class AerosmithStandInfo extends StandInfo
	{

		private Ability[] abilities = new Ability[] 
					{
						ModAbilities.MACHINE_GUNS,
						ModAbilities.BOMB,
						ModAbilities.CARBON_DIOXIDE_RADAR
					};
		
		@Override
		public String getStandId()
		{
			return Consts.STAND_ID_AEROSMITH;
		}

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
	}
}
