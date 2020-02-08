package xyz.pixelatedw.wypi.data.ability;

import java.util.List;

import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public interface IAbilityData
{
	boolean addUnlockedAbility(Ability abl);
	boolean setUnlockedAbility(int slot, Ability abl);
	boolean removeUnlockedAbility(Ability abl);
	boolean hasUnlockedAbility(Ability abl);
	Ability getUnlockedAbility(Ability abl);
	Ability getUnlockedAbility(int slot);
	List<Ability> getUnlockedAbilities(AbilityCategory category);
	void clearUnlockedAbilities(AbilityCategory category);
	void clearUnlockedAbilityFromList(AbilityCategory category, List<Ability> list); 
	int countUnlockedAbilities(AbilityCategory category);

	boolean addEquippedAbility(Ability abl);
	boolean setEquippedAbility(int slot, Ability abl);
	boolean removeEquippedAbility(Ability abl);
	boolean hasEquippedAbility(Ability abl);
	Ability getEquippedAbility(Ability abl);
	Ability getEquippedAbility(int slot);
	List<Ability> getEquippedAbilities(AbilityCategory category);
	void clearEquippedAbilities(AbilityCategory category);
	void clearEquippedAbilityFromList(AbilityCategory category, List<Ability> list); 
	int countEquippedAbilities(AbilityCategory category);
	
	Ability getPreviouslyUsedAbility();
	void setPreviouslyUsedAbility(Ability abl);
}
