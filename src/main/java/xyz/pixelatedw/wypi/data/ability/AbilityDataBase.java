package xyz.pixelatedw.wypi.data.ability;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class AbilityDataBase implements IAbilityData
{

	private List<Ability> unlockedAbilities = new ArrayList<Ability>();
	private List<Ability> equippedAbilities = new ArrayList<Ability>(APIConfig.MAX_SELECTED_ABILITIES);
	
	private Ability previouslyUsedAbility;

	/*
	 *  Unlocked Abilities
	 */
	
	@Override
	public boolean addUnlockedAbility(Ability abl)
	{
		Ability ogAbl = this.getUnlockedAbility(abl);
		if(ogAbl == null)
		{
			this.unlockedAbilities.add(abl);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean setUnlockedAbility(int slot, Ability abl)
	{
		Ability ogAbl = this.getUnlockedAbility(abl);
		if(ogAbl == null)
		{
			this.unlockedAbilities.set(slot, abl);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeUnlockedAbility(Ability abl)
	{
		Ability ogAbl = this.getUnlockedAbility(abl);
		if(ogAbl != null)
		{
			this.unlockedAbilities.remove(ogAbl);
			return true;
		}
		return false;
	}

	@Override
	public boolean hasUnlockedAbility(Ability abl)
	{
		return this.unlockedAbilities.parallelStream().anyMatch(ability -> ability != null && ability.equals(abl));
	}
	
	@Override
	public Ability getUnlockedAbility(Ability abl)
	{
		return this.unlockedAbilities.parallelStream().filter(ability -> ability != null && ability.equals(abl)).findFirst().orElse(null);
	}
	
	@Override
	public Ability getUnlockedAbility(int slot)
	{
		return this.unlockedAbilities.size() > slot ? this.unlockedAbilities.get(slot) : null;
	}

	@Override
	public List<Ability> getUnlockedAbilities(AbilityCategory category)
	{
		return this.unlockedAbilities.parallelStream().filter(ability -> ability != null && (ability.getCategory() == category || category == AbilityCategory.ALL)).collect(Collectors.toList());
	}

	@Override
	public void clearUnlockedAbilities(AbilityCategory category)
	{
		this.unlockedAbilities = this.unlockedAbilities.stream().filter(ability -> ability.getCategory() != category && category != AbilityCategory.ALL).collect(Collectors.toList());
	}

	@Override
	public void clearUnlockedAbilityFromList(AbilityCategory category, List<Ability> list)
	{
		this.unlockedAbilities = this.unlockedAbilities.parallelStream().filter(ability -> ability.getCategory() != category && !list.contains(ability)).collect(Collectors.toList());
	}

	@Override
	public int countUnlockedAbilities(AbilityCategory category)
	{
		return this.unlockedAbilities.parallelStream().filter(ability -> ability.getCategory() == category || category == AbilityCategory.ALL).collect(Collectors.toList()).size();
	}

	/*
	 *  Equipped Abilities
	 */
	
	@Override
	public boolean addEquippedAbility(Ability abl)
	{
		Ability ogAbl = this.getEquippedAbility(abl);
		if(ogAbl == null)
		{
			this.equippedAbilities.add(abl);
			return true;
		}
		return false;
	}

	@Override
	public boolean setEquippedAbility(int slot, Ability abl)
	{
		Ability ogAbl = this.getEquippedAbility(abl);
		if(ogAbl == null)
		{
			this.equippedAbilities.set(slot, abl);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean removeEquippedAbility(Ability abl)
	{
		Ability ogAbl = this.getUnlockedAbility(abl);
		if(ogAbl != null)
		{
			this.equippedAbilities.remove(ogAbl);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean hasEquippedAbility(Ability abl)
	{
		return this.equippedAbilities.parallelStream().anyMatch(ability -> ability != null && ability.equals(abl));
	}
	
	@Override
	public Ability getEquippedAbility(Ability abl)
	{
		return this.equippedAbilities.parallelStream().filter(ability -> ability != null && ability.equals(abl)).findFirst().orElse(null);
	}
	
	@Override
	public Ability getEquippedAbility(int slot)
	{
		return this.equippedAbilities.size() > slot ? this.equippedAbilities.get(slot) : null;
	}

	@Override
	public List<Ability> getEquippedAbilities(AbilityCategory category)
	{
		return this.equippedAbilities.parallelStream().filter(ability -> ability != null && (ability.getCategory() == category || category == AbilityCategory.ALL)).collect(Collectors.toList());
	}

	@Override
	public void clearEquippedAbilities(AbilityCategory category)
	{
		this.equippedAbilities = this.equippedAbilities.stream().filter(ability -> ability.getCategory() != category && category != AbilityCategory.ALL).collect(Collectors.toList());
	}
	
	@Override
	public void clearEquippedAbilityFromList(AbilityCategory category, List<Ability> list)
	{
		this.equippedAbilities = this.equippedAbilities.parallelStream().filter(ability -> ability.getCategory() != category && !list.contains(ability)).collect(Collectors.toList());	
	}

	@Override
	public int countEquippedAbilities(AbilityCategory category)
	{
		return this.equippedAbilities.parallelStream().filter(ability -> ability.getCategory() == category || category == AbilityCategory.ALL).collect(Collectors.toList()).size();
	}
	
	/*
	 *  Previously Used Ability
	 */
	
	@Override
	public Ability getPreviouslyUsedAbility()
	{
		return this.previouslyUsedAbility;
	}

	@Override
	public void setPreviouslyUsedAbility(Ability abl)
	{
		this.previouslyUsedAbility = abl;
	}
}
