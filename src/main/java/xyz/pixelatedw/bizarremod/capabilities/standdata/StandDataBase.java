package xyz.pixelatedw.bizarremod.capabilities.standdata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import xyz.pixelatedw.bizarremod.api.abilities.Ability;

public class StandDataBase implements IStandData
{

	private String stand = "";
	private boolean hasStandSummoned = false;
	
	private Ability[] hotbarAbilities = new Ability[2];
	private List<Ability> abilities = new ArrayList<Ability>();
	
	private Ability previouslyUsedAbility;
	private boolean isInCombatMode = false;

	
	@Override
	public boolean hasStandSummoned()
	{
		return this.hasStandSummoned;
	}

	@Override
	public void setStandSummoned(boolean value)
	{
		this.hasStandSummoned = value;
	}
	
	@Override
	public String getStand()
	{
		return this.stand;
	}

	@Override
	public void setStand(String standName)
	{
		this.stand = standName;
	}

	@Override
	public boolean addAbility(Ability abl)
	{
		Ability ogAbl = this.getAbility(abl);
		if(ogAbl == null)
		{
			this.abilities.add(abl);
			return true;
		}
		return false;
	}

	@Override
	public void removeAbility(Ability abl)
	{
		Ability ogAbl = this.getAbility(abl);
		if(ogAbl != null)
			this.abilities.remove(ogAbl);
	}

	@Override
	public Ability getAbility(Ability abl)
	{
		return this.getAbility(abl.getName());
	}
	
	@Override
	public Ability getAbility(String ablName)
	{
		return this.abilities.parallelStream().filter(ability -> ability.getName().equalsIgnoreCase(ablName)).findFirst().orElse(null);
	}
	
	@Override
	public int getAbilityPosition(Ability abl)
	{
		return this.getAbilityPosition(abl.getName());
	}
	
	@Override
	public int getAbilityPosition(String ablName)
	{
		return IntStream.range(0, this.abilities.size()).filter(i -> abilities.get(i).getName().equalsIgnoreCase(ablName)).findFirst().orElse(-1);
	}

	@Override
	public List<Ability> getAbilities()
	{
		return this.abilities.parallelStream().collect(Collectors.toList());
	}

	@Override
	public void clearAbilities()
	{
		this.abilities = this.abilities.stream().collect(Collectors.toList());
	}

	@Override
	public void clearAbilityFromList(List<Ability> list)
	{
		this.abilities = this.abilities.parallelStream().filter(ability -> !list.contains(ability)).collect(Collectors.toList());
	}

	@Override
	public int countAbilities()
	{
		return this.abilities.parallelStream().collect(Collectors.toList()).size();
	}

	@Override
	public void setAbilityInHotbar(int slot, Ability abl)
	{
		this.hotbarAbilities[slot] = abl;
	}

	@Override
	public void removeAbilityFromHotbar(int slot)
	{
		this.hotbarAbilities[slot] = null;
	}

	@Override
	public boolean hasAbilityInHotbar(Ability abl)
	{
		return Arrays.stream(this.hotbarAbilities).anyMatch(ability -> ability != null && ability.equals(abl));
	}
	
	@Override
	public Ability getAbilityInSlot(int slot)
	{
		return this.hotbarAbilities[slot];
	}

	@Override
	public Ability[] getHotbarAbilities()
	{
		return this.hotbarAbilities;
	}

	@Override
	public void clearHotbar()
	{
		this.hotbarAbilities = new Ability[8];
	}
	
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
