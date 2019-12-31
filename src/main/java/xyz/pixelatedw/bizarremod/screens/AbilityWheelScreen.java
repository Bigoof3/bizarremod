package xyz.pixelatedw.bizarremod.screens;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AbilityWheelScreen extends Screen
{
	private Minecraft mc;
	private PlayerEntity player;

	public AbilityWheelScreen()
	{
		super(new StringTextComponent(""));
		this.mc = Minecraft.getInstance();
		this.player = this.mc.player;
	}

	@Override
	public void render(int x, int y, float f)
	{
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

		int posX = (this.width - 256) / 2;
		int posY = (this.height - 256) / 2;

		super.render(x, y, f);
	}

	@Override
	public void init()
	{
		int posX = (this.width - 256) / 2;
		int posY = (this.height - 256) / 2;

		double phi = 0;
		double radius = 80;
		int abilities = 6;
		
		int i = 0;
		while(phi < Math.PI)
		{
			phi += Math.PI / 2;
			
			for(double theta = 0; theta <= (2 * Math.PI); theta += Math.PI / 3)
			{
				if(i > abilities - 1)
					break;
				
				int x = (int) (radius * Math.cos(theta) * Math.sin(phi));
				int y = (int) (radius * Math.sin(theta) * Math.sin(phi));

				if(x == 0 && y == 0)
					continue;
				
				this.addButton(new AbilityButton((posX + 95) + x, (posY + 95) + y, 98, 20, (button) ->
				{
					System.out.println("Press Callback");
				}));
				i++;
			}
			
		}
		//System.out.println(i);
		
	}

	@Override
	public boolean isPauseScreen()
	{
		return false;
	}
}
