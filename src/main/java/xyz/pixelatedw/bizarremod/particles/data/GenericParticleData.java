package xyz.pixelatedw.bizarremod.particles.data;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.bizarremod.init.ModParticleTextures;
import xyz.pixelatedw.bizarremod.init.ModParticleTypes;

public class GenericParticleData implements IParticleData
{

	public static final IDeserializer<GenericParticleData> DESERIALIZER = new IDeserializer<GenericParticleData>()
	{
		@Override
		public GenericParticleData deserialize(ParticleType<GenericParticleData> particleType, StringReader stringReader) throws CommandSyntaxException
		{
			stringReader.expect(' ');
			float red = stringReader.readFloat();
			float green = stringReader.readFloat();
			float blue = stringReader.readFloat();
			float alpha = stringReader.readFloat();
			float size = stringReader.readFloat();
			int life = stringReader.readInt();
			
			double motionX = stringReader.readDouble();
			double motionY = stringReader.readDouble();
			double motionZ = stringReader.readDouble();
				
			GenericParticleData data = new GenericParticleData();
			data.setColor(red, green, blue, alpha);
			data.setMotion(motionX, motionY, motionZ);
			data.setSize(size);
			data.setLife(life);
			//data.setTexture(texture);
			
			return data;
		}

		@Override
		public GenericParticleData read(ParticleType<GenericParticleData> particleType, PacketBuffer packetBuffer)
		{
			float red = packetBuffer.readFloat();
			float green = packetBuffer.readFloat();
			float blue = packetBuffer.readFloat();
			float alpha = packetBuffer.readFloat();
			float size = packetBuffer.readFloat();
			int life = packetBuffer.readInt();
			
			double motionX = packetBuffer.readDouble();
			double motionY = packetBuffer.readDouble();
			double motionZ = packetBuffer.readDouble();
			
			ResourceLocation texture = packetBuffer.readResourceLocation();
			
			GenericParticleData data = new GenericParticleData();
			data.setColor(red, green, blue, alpha);
			data.setMotion(motionX, motionY, motionZ);
			data.setSize(size);
			data.setLife(life);
			data.setTexture(texture);
			
			return data;
		}
	};

	private float red, green, blue;
	private double motionX, motionY, motionZ;
	private float alpha = 1.0F;
	private float size = 1;
	private int life = 10;
	private ResourceLocation texture = ModParticleTextures.GO;
	
	public GenericParticleData() {}

	@Override
	public ParticleType<?> getType()
	{
		return ModParticleTypes.GENERIC_PARTICLE;
	}

	@Override
	public void write(PacketBuffer buffer)
	{
		buffer.writeFloat(this.red);
		buffer.writeFloat(this.green);
		buffer.writeFloat(this.blue);
		buffer.writeFloat(this.alpha);
		buffer.writeFloat(this.size);
		buffer.writeInt(this.life);
		
		buffer.writeDouble(this.motionX);
		buffer.writeDouble(this.motionY);
		buffer.writeDouble(this.motionZ);
		
		buffer.writeResourceLocation(this.texture);
	}

	public void setMotion(double motionX, double motionY, double motionZ)
	{
		this.motionX = motionX;
		this.motionY = motionY;
		this.motionZ = motionZ;
	}
	
	public void setColor(float red, float green, float blue)
	{
		this.setColor(red, green, blue, 1.0F);
	}
	
	public void setColor(float red, float green, float blue, float alpha)
	{
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
	}
	
	public void setSize(float size)
	{
		this.size = size;
	}

	public void setLife(int life)
	{
		this.life = life;
	}
	
	public void setTexture(ResourceLocation texture)
	{
		this.texture = texture;
	}
	
	@Override
	public String getParameters()
	{
		return this.getType().getRegistryName().toString();
	}

	public float getRed()
	{
		return this.red;
	}
	
	public float getGreen()
	{
		return this.green;
	}
	
	public float getBlue()
	{
		return this.blue;
	}
	
	public float getAlpha()
	{
		return this.alpha;
	}
	
	public float getSize()
	{
		return this.size;
	}
	
	public int getLife()
	{
		return this.life;
	}
	
	public double getMotionX()
	{
		return this.motionX;
	}
	
	public double getMotionY()
	{
		return this.motionY;
	}
	
	public double getMotionZ()
	{
		return this.motionZ;
	}
	
	public ResourceLocation getTexture()
	{
		return this.texture;
	}

}