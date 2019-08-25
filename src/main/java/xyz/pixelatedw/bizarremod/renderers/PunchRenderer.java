package xyz.pixelatedw.bizarremod.renderers;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.bizarremod.entities.projectiles.PunchEntity;
import xyz.pixelatedw.bizarremod.models.CubeModel;

@OnlyIn(Dist.CLIENT)
public class PunchRenderer extends EntityRenderer<PunchEntity>
{
	private CubeModel punch = new CubeModel();
	
	public PunchRenderer(EntityRendererManager renderManager)
	{
		super(renderManager);
	}

	@Override
	public void doRender(PunchEntity entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		this.punch.render(entity, (float)x, (float)y, (float)z, 0.0F, 0.0F, 0.0625F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(PunchEntity entity)
	{
		return null;
	}
	
	public static class Factory implements IRenderFactory<PunchEntity>
	{
		@Override
		public EntityRenderer<? super PunchEntity> createRenderFor(EntityRendererManager manager)
		{
			return new PunchRenderer(manager);
		}
	}
}
