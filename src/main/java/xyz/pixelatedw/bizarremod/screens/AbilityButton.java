package xyz.pixelatedw.bizarremod.screens;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.bizarremod.init.ModResourceLocations;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

@OnlyIn(Dist.CLIENT)
public class AbilityButton extends Button
{
	protected final ISelectable onSelect;
	protected final IHoverable onHover;
	protected final Ability ability;
	protected final IAbilityData abilityProps;

	private int smoothScaling = 0;
	private int translate = 0;

	public AbilityButton(int x, int y, Ability ability, IAbilityData props, ISelectable onSelect, IHoverable onHover)
	{
		super(x, y, 64, 64, "", (button) ->
		{
		});
		this.ability = ability;
		this.abilityProps = props;
		this.onSelect = onSelect;
		this.onHover = onHover;
	}

	@Override
	public boolean mouseClicked(double x, double y, int i)
	{
		if (this.active && this.visible)
		{
			boolean clicked = this.clicked(x, y);
			boolean isFrontButton = i == 0 || i == 1;

			if (clicked && isFrontButton)
			{
				this.playDownSound(Minecraft.getInstance().getSoundHandler());
				this.onSelect.onSelect(this, i);
				return true;
			}
		}

		return false;
	}

	@Override
	public void renderButton(int x, int y, float f)
	{
		GlStateManager.disableDepthTest();
		GlStateManager.enableBlend();
		int size = 64;

		if (this.isHovered())
		{
			if (size + this.smoothScaling < 74)
				this.smoothScaling++;
			if (this.translate > -5)
				this.translate--;
			size += this.smoothScaling;
			this.onHover.onHover(this.ability);
		}
		else
		{
			if (size + this.smoothScaling > 64)
				this.smoothScaling--;
			if (this.translate <= 0)
				this.translate++;
			size += this.smoothScaling;
		}

		GlStateManager.translated(this.translate / 1.5, (this.translate * 2), 0);

		WyHelper.drawIcon(ModResourceLocations.ABILITY_SLOT, this.x, this.y, size, size);

		GlStateManager.translated(-this.translate / 1.5, -(this.translate * 2), 0);
		
		Ability first = this.abilityProps.getEquippedAbility(0);
		Ability second = this.abilityProps.getEquippedAbility(1);
		
		if(first != null && first.equals(this.ability))
			WyHelper.drawIcon(ModResourceLocations.LEFT_MOUSE_USED, this.x + 16, this.y + 40, 32, 32);
		
		if(second != null && second.equals(this.ability))
			WyHelper.drawIcon(ModResourceLocations.RIGHT_MOUSE_USED, this.x + 16, this.y + 40, 32, 32);
		
		GlStateManager.disableBlend();
		GlStateManager.enableDepthTest();
	}

	@OnlyIn(Dist.CLIENT)
	public interface ISelectable
	{
		void onSelect(AbilityButton button, int type);
	}
	
	@OnlyIn(Dist.CLIENT)
	public interface IHoverable
	{
		void onHover(Ability ability);
	}
}