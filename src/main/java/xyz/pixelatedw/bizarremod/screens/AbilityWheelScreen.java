package xyz.pixelatedw.bizarremod.screens;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.bizarremod.abilities.Ability;
import xyz.pixelatedw.bizarremod.abilities.PassiveAbility;
import xyz.pixelatedw.bizarremod.api.StandInfo;
import xyz.pixelatedw.bizarremod.api.WyHelper;
import xyz.pixelatedw.bizarremod.api.WyRenderHelper;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.helpers.StandLogicHelper;

@OnlyIn(Dist.CLIENT)
public class AbilityWheelScreen extends Screen
{
	private Minecraft mc;
	private PlayerEntity player;
	private final StandInfo standInfo;
	private final IStandData standProps;
	private String hoveredAbilityName;
	private Ability currentPrimaryAbility, currentSecondaryAbility;

	public AbilityWheelScreen()
	{
		super(new StringTextComponent(""));
		this.mc = Minecraft.getInstance();
		this.player = this.mc.player;

		this.standProps = StandDataCapability.get(this.player);
		this.standInfo = StandLogicHelper.getStandInfo(this.standProps.getStand());
		
		this.currentPrimaryAbility = this.standProps.getPrimaryAbility();
		this.currentSecondaryAbility = this.standProps.getSecondaryAbility();
	}

	@Override
	public void render(int x, int y, float f)
	{
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

		int posX = this.width / 2;
		int posY = this.height / 2;

		if(this.getActiveAbilities().size() > 0)
			WyRenderHelper.drawCenteredString(this.minecraft.fontRenderer, WyHelper.isNullOrEmpty(this.hoveredAbilityName) ? "" : this.hoveredAbilityName, posX, posY - 20, -1);
		else
			WyRenderHelper.drawCenteredString(this.minecraft.fontRenderer, "No active Abilities available", posX, posY - 20, -1);

		super.render(x, y, f);
	}

	@Override
	public void init()
	{
		int posX = (this.width - 256) / 2;
		int posY = (this.height - 256) / 2;

		double phi = 0;
		double radius = 80;
		int abilities = this.getActiveAbilities().size();

		int i = 0;
		while (phi < Math.PI)
		{
			phi += Math.PI / 2;

			for (double theta = 0; theta <= (2 * Math.PI); theta += Math.PI / 3)
			{
				if (i > abilities - 1)
					break;

				int x = (int) (radius * Math.cos(theta) * Math.sin(phi));
				int y = (int) (radius * Math.sin(-theta) * Math.sin(phi));

				if (x == 0 && y == 0)
					continue;

				this.addButton(new AbilityButton((posX + 95) + x, (posY + 95) + y, this.getActiveAbilities().get(i), this.standProps,
						(button, type) ->
						{
							if(type == 0 && !this.standProps.getSecondaryAbility().getName().equalsIgnoreCase(button.ability.getName()))
								this.standProps.setPrimaryAbility(button.ability);
							else if(type == 1 && !this.standProps.getPrimaryAbility().getName().equalsIgnoreCase(button.ability.getName()))
								this.standProps.setSecondaryAbility(button.ability);
						}, 
						(ability) -> 
						{
							this.hoveredAbilityName = ability.getName();
						}));
				i++;
			}

		}
	}

	@Override
	public void tick()
	{
		if(this.currentPrimaryAbility != this.standProps.getPrimaryAbility())
			Minecraft.getInstance().displayGuiScreen((Screen)null);
		
		if(this.currentSecondaryAbility != this.standProps.getSecondaryAbility())
			Minecraft.getInstance().displayGuiScreen((Screen)null);
	}

	@Override
	public boolean isPauseScreen()
	{
		return false;
	}

	private List<Ability> getActiveAbilities()
	{
		return Arrays.stream(this.standInfo.getAbilities()).parallel().filter(ability -> !(ability instanceof PassiveAbility)).collect(Collectors.toList());
	}
}
