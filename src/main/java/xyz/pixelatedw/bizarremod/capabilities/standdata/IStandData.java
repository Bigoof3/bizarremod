package xyz.pixelatedw.bizarremod.capabilities.standdata;

import java.util.List;

import xyz.pixelatedw.bizarremod.api.abilities.Ability;

public interface IStandData
{

	String getStand();
	void setStand(String standName);
	
	boolean hasStandSummoned();
	void setStandSummoned(boolean value);

	boolean addAbility(Ability abl);
	void removeAbility(Ability abl);
	Ability getAbility(Ability abl);
	Ability getAbility(String ablName);
	int getAbilityPosition(Ability abl);
	int getAbilityPosition(String ablName);
	List<Ability> getAbilities();
	void clearAbilities();
	void clearAbilityFromList(List<Ability> list); 
	int countAbilities();
	
	void setAbilityInHotbar(int slot, Ability abl);
	void removeAbilityFromHotbar(int slot);
	boolean hasAbilityInHotbar(Ability abl);
	Ability getAbilityInSlot(int slot);
	Ability[] getHotbarAbilities();
	void clearHotbar();
	
	Ability getPreviouslyUsedAbility();
	void setPreviouslyUsedAbility(Ability abl);
}
