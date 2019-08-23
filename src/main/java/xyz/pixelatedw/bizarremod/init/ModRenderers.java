package xyz.pixelatedw.bizarremod.init;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import xyz.pixelatedw.bizarremod.entities.stands.GreenDayEntity;
import xyz.pixelatedw.bizarremod.models.GreenDayModel;
import xyz.pixelatedw.bizarremod.renderers.GenericStandRenderer;

public class ModRenderers
{

	public static void registerRenderers() 
    {
		RenderingRegistry.registerEntityRenderingHandler(GreenDayEntity.class, new GenericStandRenderer.Factory(new GreenDayModel(), 1, "green_day"));
    }
}
