package xyz.pixelatedw.bizarremod.entities.stands;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import xyz.pixelatedw.bizarremod.Consts;
import xyz.pixelatedw.bizarremod.api.stands.GenericStandEntity;
import xyz.pixelatedw.bizarremod.init.ModAbilities;
import xyz.pixelatedw.bizarremod.init.ModEntities;
import xyz.pixelatedw.bizarremod.init.ModItems;
import xyz.pixelatedw.wypi.abilities.Ability;

public class SilverChariotEntity extends GenericStandEntity
{
	protected static final DataParameter<Boolean> HAS_ARMOR = EntityDataManager.createKey(SilverChariotEntity.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> HAS_RAPIER = EntityDataManager.createKey(SilverChariotEntity.class, DataSerializers.BOOLEAN);

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
		
		this.dataManager.register(HAS_ARMOR, true);
		this.dataManager.register(HAS_RAPIER, true);
	}
	
	@Override
	public String getStandName()
	{
		return "Silver Chariot";
	}

	public boolean hasArmor()
	{
		return this.dataManager.get(HAS_ARMOR);
	}
	
	public void removeArmor()
	{
		this.dataManager.set(HAS_ARMOR, false);
	}
	
	public void equipArmor()
	{
		this.dataManager.set(HAS_ARMOR, true);
	}
	
	public boolean hasRapier()
	{
		return this.dataManager.get(HAS_RAPIER);
	}
	
	public void removeRapier()
	{
		this.dataManager.set(HAS_RAPIER, false);
	}
	
	public void equipRapier()
	{
		this.dataManager.set(HAS_RAPIER, true);
	}
	
	@Override
	public void onSummon(PlayerEntity owner)
	{
		this.setDestructivePower('C');
		this.setRange('C');
		this.setPrecision('B');
		
		super.onSummon(owner);
	}
	
	@Override
	public void onCancel(PlayerEntity owner) {}

	public static class SilverChariotStandInfo extends DefaultStandInfo
	{
		private Ability[] abilities = new Ability[] 
				{
					ModAbilities.RAPIER_STAB,
					ModAbilities.ARMOR_OFF,
					ModAbilities.SHOOTING_SWORD
				};

		@Override
		public GenericStandEntity getStandEntity(PlayerEntity owner)
		{
			SilverChariotEntity stand = new SilverChariotEntity(owner.world, owner);
	
			stand.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ModItems.SILVER_CHARIOTS_RAPIER));
			stand.equipArmor();
			stand.equipRapier();
			
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
		
		@Override
		public String[][] getStandTextures()
		{
			return new String[][]
			{
				{ "Default", Consts.STAND_ID_SILVER_CHARIOT }
			};
		}
	}
}
