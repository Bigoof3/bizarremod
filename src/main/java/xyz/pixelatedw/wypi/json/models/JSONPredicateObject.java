package xyz.pixelatedw.wypi.json.models;

import org.apache.commons.lang3.tuple.Pair;

public class JSONPredicateObject
{

	private String predicateName;
	private Pair<String, Double>[] predicates;
	
	public JSONPredicateObject(String name, Pair<String, Double>... pairs)
	{
		this.predicateName = name;
		this.predicates = pairs;
	}
	
	public String getName()
	{
		return this.predicateName;
	}
	
	public Pair<String, Double>[] getPredicates()
	{
		return this.predicates;
	}
	
}
