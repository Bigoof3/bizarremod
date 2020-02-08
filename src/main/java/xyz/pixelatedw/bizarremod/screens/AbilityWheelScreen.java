package xyz.pixelatedw.bizarremod.screens;

import java.util.List;
import java.util.stream.Collectors;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.bizarremod.api.StandInfo;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.helpers.StandLogicHelper;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.PassiveAbility;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.client.CSyncAbilityDataPacket;

@OnlyIn(Dist.CLIENT)
public class AbilityWheelScreen extends Screen
{
	private Minecraft mc;
	private PlayerEntity player;
	private final StandInfo standInfo;
	private final IStandData standProps;
	private final IAbilityData abilityProps;
	private String hoveredAbilityName;
	private Ability currentPrimaryAbility, currentSecondaryAbility;

	public AbilityWheelScreen()
	{
		super(new StringTextComponent(""));
		this.mc = Minecraft.getInstance();
		this.player = this.mc.player;

		this.standProps = StandDataCapability.get(this.player);
		this.standInfo = StandLogicHelper.getStandInfo(this.standProps.getStand());

		this.abilityProps = AbilityDataCapability.get(this.player);		
		
		this.currentPrimaryAbility = this.abilityProps.getEquippedAbility(0);
		this.currentSecondaryAbility = this.abilityProps.getEquippedAbility(1);	
	}

	@Override
	public void render(int x, int y, float f)
	{
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

		int posX = this.width / 2;
		int posY = this.height / 2;

		if(this.getActiveAbilities().size() > 0)
			WyHelper.drawCenteredString(this.minecraft.fontRenderer, WyHelper.isNullOrEmpty(this.hoveredAbilityName) ? "" : this.hoveredAbilityName, posX, posY - 20, -1);
		else
			WyHelper.drawCenteredString(this.minecraft.fontRenderer, "No active Abilities available", posX, posY - 20, -1);

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

				this.addButton(new AbilityButton((posX + 95) + x, (posY + 95) + y, this.getActiveAbilities().get(i), this.abilityProps,
						(button, type) ->
						{
							Ability first = this.abilityProps.getEquippedAbility(0);
							Ability second = this.abilityProps.getEquippedAbility(1);
							
							if(type == 0 && (second == null || !second.equals(button.ability)))
								this.abilityProps.setEquippedAbility(0, button.ability);
							else if(type == 1 && (first == null || !first.equals(button.ability)))
								this.abilityProps.setEquippedAbility(1, button.ability);

							WyNetwork.sendToServer(new CSyncAbilityDataPacket(this.abilityProps));
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
		if(this.currentPrimaryAbility != this.abilityProps.getEquippedAbility(0))
			Minecraft.getInstance().displayGuiScreen((Screen)null);
		
		if(this.currentSecondaryAbility != this.abilityProps.getEquippedAbility(1))
			Minecraft.getInstance().displayGuiScreen((Screen)null);
	}

	@Override
	public boolean isPauseScreen()
	{
		return false;
	}

	private List<Ability> getActiveAbilities()
	{
		return this.abilityProps.getUnlockedAbilities(AbilityCategory.ALL).parallelStream().filter(ability -> !(ability instanceof PassiveAbility)).collect(Collectors.toList());
	}
}
