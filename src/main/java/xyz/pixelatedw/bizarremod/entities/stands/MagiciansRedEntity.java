package xyz.pixelatedw.bizarremod.entities.stands;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.bizarremod.Consts;
import xyz.pixelatedw.bizarremod.api.StandInfo;
import xyz.pixelatedw.bizarremod.init.ModAbilities;
import xyz.pixelatedw.bizarremod.init.ModEntities;
import xyz.pixelatedw.wypi.abilities.Ability;

public class MagiciansRedEntity extends GenericStandEntity
{

	public MagiciansRedEntity(World world, PlayerEntity owner)
	{
		super(ModEntities.MAGICIAN_RED, world, owner);
	}
	
	public MagiciansRedEntity(World world)
	{
		super(ModEntities.MAGICIAN_RED, world);
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
		this.setRange('C');
		this.setPersistance('B');
		this.setPrecision('C');
		this.setDevelopmentPotential('D');
	}

	@Override
	public String getStandName()
	{
		return "Magician's Red";
	}

	@Override
	public void onCancel(PlayerEntity owner) {}
	
	public static class MagiciansRedStandInfo extends StandInfo
	{
		private Ability[] abilities = new Ability[] 
				{
					ModAbilities.PUNCH,
					ModAbilities.CROSS_FIRE_HURRICANE,
					ModAbilities.RED_BIND
				};

		@Override
		public String getStandId()
		{
			return Consts.STAND_ID_MAGICIANS_RED;
		}
		
		@Override
		public GenericStandEntity getStandEntity(PlayerEntity owner)
		{
			GenericStandEntity stand = new MagiciansRedEntity(owner.world, owner);
	
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
