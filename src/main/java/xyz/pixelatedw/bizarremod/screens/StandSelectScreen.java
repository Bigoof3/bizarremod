package xyz.pixelatedw.bizarremod.screens;

import java.util.List;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.config.GuiUtils;
import xyz.pixelatedw.bizarremod.api.StandLogicHelper;
import xyz.pixelatedw.bizarremod.api.abilities.IStandAbility;
import xyz.pixelatedw.bizarremod.api.stands.GenericStandEntity;
import xyz.pixelatedw.bizarremod.api.stands.StandInfo;
import xyz.pixelatedw.bizarremod.data.entity.standdata.IStandData;
import xyz.pixelatedw.bizarremod.data.entity.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.data.world.ExtendedWorldData;
import xyz.pixelatedw.bizarremod.init.ModEntities;
import xyz.pixelatedw.bizarremod.packets.client.CSyncStandDataPacket;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.client.CSyncAbilityDataPacket;

@OnlyIn(Dist.CLIENT)
public class StandSelectScreen extends Screen
{
	private PlayerEntity player;
	private int currentStand = 1;
	private int currentAbility;
	private int maxStandsInList;
	private int page = 0;
	private List<Widget> abilityButtons = Lists.newArrayList();
	private IAbilityData abilityProps;
	private IStandData standProps;
	private ExtendedWorldData worldData;
	private String currentStandId;

	private int standSkinId = 0;
	
	public StandSelectScreen(PlayerEntity player)
	{
		super(new StringTextComponent(""));
		this.player = player;
		this.maxStandsInList = ModEntities.STANDS.size();
		
		this.abilityProps = AbilityDataCapability.get(this.player);
		this.standProps = StandDataCapability.get(this.player);
		this.worldData = ExtendedWorldData.get(this.player.world);
		
		this.currentStandId = this.standProps.getStand();
	}

	@Override
	public void render(int x, int y, float f)
	{
		this.renderBackground();
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		int posX = (this.width - 256) / 2;
		int posY = (this.height - 256) / 2;
		
		this.minecraft.fontRenderer.drawStringWithShadow(this.currentStand + " / " + this.maxStandsInList, posX - 5, posY + 233, -1);
		
		// #TODO Rotate the model right/left
		// #TODO Filter by part origin / stats / ?
		
		StandInfo info = (StandInfo) ModEntities.STANDS.toArray()[this.currentStand - 1];
		GenericStandEntity stand = info.getStandEntity(this.player);
		InventoryScreen.drawEntityOnScreen(posX + 0, posY + 190, 50, 0.0F, 0.0F, stand);
		this.drawCenteredString(this.minecraft.fontRenderer, stand.getStandName(), posX, posY + 182, -1);
		WyHelper.drawIcon(this.getIcon(info), posX - 60, posY + 168, 32, 32);
		// Stats section	
		// #TODO The fancy stats circle would be cool, opengl style	
		if(this.page == 0 && info.getAbilities() != null)
		{
			for(int i = 0; i < info.getAbilities().length; i++)
			{
				if(this.currentAbility == i)
				{
					IStandAbility ability = (IStandAbility) info.getAbilities()[i];
					ability.renderDescription(this.minecraft.fontRenderer, posX, posY);
				}
			}
		}
		else if(this.page == 1)
		{
			String stat = "";
			
			// Destruction Power
			this.minecraft.fontRenderer.drawStringWithShadow("Destructive", posX + 70, posY + 70, -1);
			this.minecraft.fontRenderer.drawStringWithShadow("Power", posX + 83, posY + 83, -1);
			GuiUtils.drawGradientRect(1, posX + 60, posY + 95, posX + 140, posY + 96, WyHelper.hexToRGB("#FFFFFF").getRGB(), WyHelper.hexToRGB("#FFFFFF").getRGB());
			stat = StandLogicHelper.convertStandStat(stand.getDestructivePower()) + "";
			this.minecraft.fontRenderer.drawStringWithShadow(stat, posX + 98, posY + 102, -1);
	
			// Speed
			this.minecraft.fontRenderer.drawStringWithShadow("Speed", posX + 177, posY + 78, -1);
			GuiUtils.drawGradientRect(1, posX + 150, posY + 95, posX + 230, posY + 96, WyHelper.hexToRGB("#FFFFFF").getRGB(), WyHelper.hexToRGB("#FFFFFF").getRGB());
			stat = StandLogicHelper.convertStandStat(stand.getSpeed()) + "";
			this.minecraft.fontRenderer.drawStringWithShadow(stat, posX + 190, posY + 102, -1);
			
			// Range
			this.minecraft.fontRenderer.drawStringWithShadow("Range", posX + 266, posY + 78, -1);
			GuiUtils.drawGradientRect(1, posX + 240, posY + 95, posX + 320, posY + 96, WyHelper.hexToRGB("#FFFFFF").getRGB(), WyHelper.hexToRGB("#FFFFFF").getRGB());
			stat = StandLogicHelper.convertStandStat(stand.getRange()) + "";
			this.minecraft.fontRenderer.drawStringWithShadow(stat, posX + 278, posY + 102, -1);
			
			// Persistence
			this.minecraft.fontRenderer.drawStringWithShadow("Persistance", posX + 72, posY + 140, -1);
			GuiUtils.drawGradientRect(1, posX + 60, posY + 155, posX + 140, posY + 156, WyHelper.hexToRGB("#FFFFFF").getRGB(), WyHelper.hexToRGB("#FFFFFF").getRGB());
			stat = StandLogicHelper.convertStandStat(stand.getPersistance()) + "";
			this.minecraft.fontRenderer.drawStringWithShadow(stat, posX + 98, posY + 162, -1);
	
			// Precision
			this.minecraft.fontRenderer.drawStringWithShadow("Precision", posX + 170, posY + 140, -1);
			GuiUtils.drawGradientRect(1, posX + 150, posY + 155, posX + 230, posY + 156, WyHelper.hexToRGB("#FFFFFF").getRGB(), WyHelper.hexToRGB("#FFFFFF").getRGB());
			stat = StandLogicHelper.convertStandStat(stand.getPrecision()) + "";
			this.minecraft.fontRenderer.drawStringWithShadow(stat, posX + 190, posY + 162, -1);
			
			// Development Potential
			this.minecraft.fontRenderer.drawStringWithShadow("Development", posX + 250, posY + 130, -1);
			this.minecraft.fontRenderer.drawStringWithShadow("Potential", posX + 260, posY + 143, -1);
			GuiUtils.drawGradientRect(1, posX + 240, posY + 155, posX + 320, posY + 156, WyHelper.hexToRGB("#FFFFFF").getRGB(), WyHelper.hexToRGB("#FFFFFF").getRGB());
			stat = StandLogicHelper.convertStandStat(stand.getDevelopmentPotential()) + "";
			this.minecraft.fontRenderer.drawStringWithShadow(stat, posX + 278, posY + 162, -1);
		}
		else if(this.page == 2)
		{
			WyHelper.drawCenteredString(this.font, info.getStandTextures()[this.standSkinId][0], posX + 163, posY + 60, -1);
		}
		
		super.render(x, y, f);
	}
	
	@Override
	public void init()
	{
		this.init(0);
	}
	
	public void init(int index)
	{
		int posX = (this.width - 256) / 2;
		int posY = (this.height - 256) / 2;
		StandInfo info = (StandInfo) ModEntities.STANDS.toArray()[this.currentStand - 1];

		if(info.getStandTextures().length < this.standSkinId + 1)
			this.standSkinId = 0;
		
		info.setStandId(info.getStandTextures()[this.standSkinId][1]);
		
		if(this.currentAbility >= info.getAbilities().length)
			this.currentAbility = 0;
		
		this.buttons.clear();
		
		Button previousButton = new Button(posX - 20, posY + 200, 90, 20, "Previous", b -> 
		{
			if(this.currentStand == 1)
				return;
			
			this.currentStand--;
			this.init(index);
		});
	
		Button chooseButton = new Button(posX + 90, posY + 200, 90, 20, "Choose", b -> 
		{
			StandInfo currentInfo = (StandInfo) ModEntities.STANDS.toArray()[this.currentStand - 1];
			
			//if(this.worldData.isStandUsed(currentInfo))
			//	return;
			
			this.standProps.setStand(currentInfo.getDefaultStandId());
			//this.worldData.addUsedStand(currentInfo);
			
			this.abilityProps.clearEquippedAbilities(AbilityCategory.ALL);
			this.abilityProps.clearUnlockedAbilities(AbilityCategory.ALL);
						
			for(Ability abl : currentInfo.getAbilities())
				this.abilityProps.addUnlockedAbility(abl);
			
			List<Ability> activeAbilities = StandLogicHelper.getActiveAbilities(this.abilityProps, currentInfo);
			
			if(activeAbilities.size() >= 1)
				this.abilityProps.setEquippedAbility(0, activeAbilities.get(0) != null ? activeAbilities.get(0) : null);
			if(activeAbilities.size() >= 2)
				this.abilityProps.setEquippedAbility(1, activeAbilities.get(1) != null ? activeAbilities.get(1) : null);
			
			this.onClose();
		});
				
		Button nextButton = new Button(posX + 200, posY + 200, 90, 20, "Next", b -> 
		{
			if(this.currentStand >= this.maxStandsInList)
				return;
			
			this.currentStand++;
			this.init(index);
		});

		Button abilitiesButton = new Button(posX + 80, posY + 20, 50, 20, "Abilities", b -> 
		{
			if(!b.active)
				return;
			
			this.page = 0;			
			this.init();
		});
		
		Button statsButton = new Button(posX + 140, posY + 20, 50, 20, "Stats", b -> 
		{
			if(!b.active)
				return;
			
			this.page = 1;		
			this.init();
		});
		
		Button optionsButton = new Button(posX + 200, posY + 20, 50, 20, "Options", b -> 
		{
			if(!b.active)
				return;
		
			this.page = 2;		
			this.init();
		});	

		//if(this.worldData.isStandUsed(info))
		//	chooseButton.active = false;
		
		if(this.currentStand == 1)
			previousButton.active = false;		

		if(this.currentStand >= this.maxStandsInList)
			nextButton.active = false;
		
		if(this.page == 0)
		{
			abilitiesButton.active = false;
			if(info.getAbilities() != null)
			{
				int i = 0;
				for(Ability ability : info.getAbilities())
				{
					final int j = i;
					Button abilityButton = new Button(posX + 90 + (i * 30), posY + 170, 20, 20, "", b -> 
					{
						if(!b.active)
							return;

						this.currentAbility = j;
						this.init(j);
					});

					if(index == i)
						abilityButton.active = false;
					this.addButton(abilityButton);
					i++;
				}
			}
		}
		else if(this.page == 1)
			statsButton.active = false;
		else if(this.page == 2)
		{
			optionsButton.active = false;
			
			int i = 0;
			for(String[] texture : info.getStandTextures())
			{
				final int j = i;
				Button skinButton = new Button(posX + 100 + (i * 30), posY + 80, 20, 20, "", b -> 
				{
					if(!b.active)
						return;

					this.standSkinId = j;
					this.init(j);
				});

				if(index == i)
					skinButton.active = false;
				this.addButton(skinButton);
				i++;
			}
		}
		
		this.addButton(previousButton);
		this.addButton(chooseButton);
		this.addButton(nextButton);
		
		this.addButton(abilitiesButton);
		this.addButton(statsButton);
		this.addButton(optionsButton);
	}

	@Override
	public void onClose()
	{		
		WyNetwork.sendToServer(new CSyncStandDataPacket(this.standProps));
		WyNetwork.sendToServer(new CSyncAbilityDataPacket(this.abilityProps));
		super.onClose();
	}

	private ResourceLocation getIcon(StandInfo currentStandInfo)
	{
		return new ResourceLocation(APIConfig.PROJECT_ID, "textures/ui/icons/" + currentStandInfo.getStandId() + ".png");
	}

}
