package xyz.pixelatedw.bizarremod.init;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import xyz.pixelatedw.bizarremod.entities.projectiles.AnkhEntity;
import xyz.pixelatedw.bizarremod.entities.projectiles.BulletEntity;
import xyz.pixelatedw.bizarremod.entities.projectiles.PunchEntity;
import xyz.pixelatedw.bizarremod.entities.stands.AerosmithEntity;
import xyz.pixelatedw.bizarremod.entities.stands.GreenDayEntity;
import xyz.pixelatedw.bizarremod.entities.stands.MagiciansRedEntity;
import xyz.pixelatedw.bizarremod.entities.stands.SilverChariotEntity;
import xyz.pixelatedw.bizarremod.models.AerosmithModel;
import xyz.pixelatedw.bizarremod.models.AnkhModel;
import xyz.pixelatedw.bizarremod.models.BulletModel;
import xyz.pixelatedw.bizarremod.models.GreenDayModel;
import xyz.pixelatedw.bizarremod.models.MagiciansRedModel;
import xyz.pixelatedw.bizarremod.models.SilverChariotModel;
import xyz.pixelatedw.bizarremod.renderers.GenericProjectileRenderer;
import xyz.pixelatedw.bizarremod.renderers.GenericStandRenderer;
import xyz.pixelatedw.bizarremod.renderers.PunchRenderer;

public class ModRenderers
{

	public static void registerRenderers() 
    {
		RenderingRegistry.registerEntityRenderingHandler(PunchEntity.class, new PunchRenderer.Factory());
		RenderingRegistry.registerEntityRenderingHandler(BulletEntity.class, new GenericProjectileRenderer.Factory(new BulletModel(), "generic_bullet"));
		RenderingRegistry.registerEntityRenderingHandler(AnkhEntity.class, new GenericProjectileRenderer.Factory(new AnkhModel(), "ankh", 2));

		RenderingRegistry.registerEntityRenderingHandler(GreenDayEntity.class, new GenericStandRenderer.Factory(new GreenDayModel(), 1, "green_day"));
		RenderingRegistry.registerEntityRenderingHandler(AerosmithEntity.class, new GenericStandRenderer.Factory(new AerosmithModel(), 1, "aerosmith"));
		RenderingRegistry.registerEntityRenderingHandler(MagiciansRedEntity.class, new GenericStandRenderer.Factory(new MagiciansRedModel(), 1, "magician_red"));
		RenderingRegistry.registerEntityRenderingHandler(SilverChariotEntity.class, new GenericStandRenderer.Factory(new SilverChariotModel(), 1, "silver_chariot"));
    }
}
