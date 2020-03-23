package xyz.pixelatedw.bizarremod.entities.stands;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.bizarremod.Consts;
import xyz.pixelatedw.bizarremod.api.stands.GenericStandEntity;
import xyz.pixelatedw.bizarremod.api.stands.StandInfo.JoJoPart;
import xyz.pixelatedw.bizarremod.init.ModAbilities;
import xyz.pixelatedw.bizarremod.init.ModBlocks;
import xyz.pixelatedw.bizarremod.init.ModEntities;
import xyz.pixelatedw.wypi.abilities.Ability;

public class SunEntity extends GenericStandEntity{
	private PlayerEntity owner;
	public SunEntity(World world, PlayerEntity owner)
	{
		super(ModEntities.SUN, world, owner);
		this.owner = owner;
		this.setGlowing(true);
	}
	
	public SunEntity(World world)
	{
		super(ModEntities.SUN, world);
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
		this.setSpeed('E');
		this.setRange('A');
		this.setPersistance('A');
		this.setPrecision('E');
		this.setDevelopmentPotential('E');
	}

	@Override
	public String getStandName()
	{
		return "Sun";
	}
	
	@Override
	public void onSummon(PlayerEntity owner)
	{
		this.setPosition(owner.posX + 70, owner.posY + 60, owner.posZ);
		super.onSummon(owner);
	}

	@Override
	public void onCancel(PlayerEntity owner)
	{
		
	}
	
	@Override
	public void tick()
	{
		this.noClip = true;
		super.tick();
		this.noClip = false;
		this.setNoGravity(true);

		if (!this.world.isRemote)
		{
			
			if(this.owner == null) {
				this.remove();
				return;
			}
			if (this.getHealth() <= 0)
				this.owner.attackEntityFrom(DamageSource.MAGIC, Float.MAX_VALUE);

			if (this.getHealth() != this.owner.getHealth())
				this.setHealth(this.owner.getHealth());
			
			double distance = Math.sqrt((this.owner.posX - this.posX) *(this.owner.posX - this.posX));

			double motion = (this.owner.posX - this.posX) / (distance / 4);
			double distanceZ = Math.sqrt((this.owner.posZ - this.posZ) *(this.owner.posZ - this.posZ));

			double motionZ = (this.owner.posZ - this.posZ)  / (distanceZ / 4);
			
			double distanceY = Math.sqrt(((this.owner.posY + 60) - this.posY) *((this.owner.posY + 60) - this.posY));

			double motionY = ((this.owner.posY + 60) - this.posY) / (distanceY / 2);


			if (distance > 70)
				this.setMotion(motion, motionY, motionZ);
			
			if (distanceZ > 70)
				this.setMotion(motion, motionY, motionZ);

			if(distanceY > 35)
			    this.setMotion(motion, motionY, motionZ);
			

			if(distance < 50)
			    this.setMotion(0, this.getMotion().y, this.getMotion().z);


			if(distanceZ < 50)
			    this.setMotion(this.getMotion().x, this.getMotion().y, 0);

			if(distanceY < 20)
			    this.setMotion(this.getMotion().x, 0, this.getMotion().z);



			if(this.posY > 255) {
				this.setMotion(this.getMotion().x, 0, this.getMotion().z);
				this.setPosition(this.posX, this.posY - 2, this.posZ);
			}
			this.setRotation(this.owner.rotationYaw, this.owner.rotationPitch);
			
			
		}
		
		this.world.setDayTime(1300);
	}
	
	public static class SunStandInfo extends DefaultStandInfo
	{
		private Ability[] abilities = new Ability[] 
					{ 
							ModAbilities.HEAT,
							ModAbilities.LIGHT_RAYS
					};

		@Override
		public GenericStandEntity getStandEntity(PlayerEntity owner)
		{
			GenericStandEntity stand = new SunEntity(owner.world, owner);

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
				{ "Default", Consts.STAND_ID_SUN }
			};
		}
	}
}
