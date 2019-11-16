package xyz.pixelatedw.bizarremod.screens;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.config.GuiUtils;
import xyz.pixelatedw.bizarremod.ModMain;
import xyz.pixelatedw.bizarremod.api.WyHelper;
import xyz.pixelatedw.bizarremod.capabilities.standdata.IStandData;
import xyz.pixelatedw.bizarremod.capabilities.standdata.StandDataCapability;
import xyz.pixelatedw.bizarremod.entities.stands.GenericStandEntity;
import xyz.pixelatedw.bizarremod.entities.stands.StandInfo;
import xyz.pixelatedw.bizarremod.helpers.StandLogicHelper;
import xyz.pixelatedw.bizarremod.init.ModNetwork;
import xyz.pixelatedw.bizarremod.packets.server.SSyncStandDataPacket;

@OnlyIn(Dist.CLIENT)
public class StandSelectScreen extends Screen
{
	private PlayerEntity player;
	private int currentStand = 1;
	private int maxStandsInList;
	private int page = 0;
	
	public StandSelectScreen(PlayerEntity player)
	{
		super(new StringTextComponent(""));
		this.player = player;
		this.maxStandsInList = StandLogicHelper.getRegisteredStands().size();		
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
		
		StandInfo info = (StandInfo) StandLogicHelper.getRegisteredStands().values().toArray()[this.currentStand - 1];
		GenericStandEntity stand = info.getStandEntity(this.player);
		InventoryScreen.drawEntityOnScreen(posX + 0, posY + 190, 50, 0.0F, 0.0F, stand);

		// Stats section	
		// #TODO The fancy stats circle would be cool, opengl style	
		if(this.page == 0)
		{
			this.minecraft.fontRenderer.drawStringWithShadow("- Mold Infestation -", posX + 130, posY + 90, -1);
			this.minecraft.fontRenderer.drawStringWithShadow(TextFormatting.AQUA + " Passive", posX + 155, posY + 100, -1);
			
			this.minecraft.fontRenderer.drawStringWithShadow("Produces a potent mold that rots and destroys", posX + 70, posY + 115, -1);
			this.minecraft.fontRenderer.drawStringWithShadow("the flesh of those it infects.", posX + 70, posY + 125, -1);
			this.minecraft.fontRenderer.drawStringWithShadow("The mold's growth is triggered when the potential", posX + 70, posY + 135, -1);
			this.minecraft.fontRenderer.drawStringWithShadow("victims lower their current altitude.", posX + 70, posY + 145, -1);
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
		
		super.render(x, y, f);
	}
	
	@Override
	public void init()
	{
		int posX = (this.width - 256) / 2;
		int posY = (this.height - 256) / 2;
		IStandData props = StandDataCapability.get(this.player);
		
		Button previousButton = new Button(posX - 20, posY + 200, 90, 20, "Previous", b -> 
		{
			
		});
	
		Button chooseButton = new Button(posX + 90, posY + 200, 90, 20, "Choose", b -> 
		{
			StandInfo info = (StandInfo) StandLogicHelper.getRegisteredStands().values().toArray()[this.currentStand - 1];
			props.setStand(info.getStandId());
			ModNetwork.sendTo(new SSyncStandDataPacket(props), (ServerPlayerEntity)this.player);
			ModMain.proxy.openScreen(-1, this.player);
		});
				
		Button nextButton = new Button(posX + 200, posY + 200, 90, 20, "Next", b -> 
		{
			
		});

		Button abilitiesButton = new Button(posX + 90, posY + 20, 50, 20, "Abilities", b -> 
		{
			this.page = 0;
			
			this.init();
		});
		
		Button statsButton = new Button(posX + 150, posY + 20, 50, 20, "Stats", b -> 
		{
			this.page = 1;
			
			this.init();
		});
		
		Button optionsButton = new Button(posX + 210, posY + 20, 50, 20, "Options", b -> 
		{
			this.page = 2;
			
			this.init();
		});	
			
		if(this.currentStand == 1)
			previousButton.active = false;		

		if(this.currentStand >= this.maxStandsInList)
			nextButton.active = false;
		
		if(this.page == 0)
			abilitiesButton.active = false;	
		else if(this.page == 1)
			statsButton.active = false;
		
		optionsButton.active = false;
		
		this.addButton(previousButton);
		this.addButton(chooseButton);
		this.addButton(nextButton);
		
		this.addButton(abilitiesButton);
		this.addButton(statsButton);
		this.addButton(optionsButton);
	}

}
