package com.dirtypepper.dota2;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Hero
{
    private String name;
    private String damageType;
    private String attribute;
    private String faction;
    private String description;
    private ArrayList<String> roles;
    private Map<String, ArrayList<Double>> attributes;
    private ArrayList<String> tips;

    public ArrayList<Ability> abilities;

    public Hero()
    {
        name = "";
        damageType = "";
        attribute = "";
        faction = "";
        description = "";
        roles = new ArrayList<String>();
        attributes = new TreeMap<String, ArrayList<Double>>();
        tips = new ArrayList<String>();

        abilities = new ArrayList<Ability>();
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setDamageType(String damageType)
    {
        this.damageType = damageType;
    }

    public String getDamageType()
    {
        return damageType;
    }

    public void setAttribute(String attribute)
    {
        this.attribute = attribute;
    }

    public String getAttribute()
    {
        return attribute;
    }

    public void setFaction(String faction)
    {
        this.faction = faction;
    }

    public String getFaction()
    {
        return faction;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }

    public void addRole(String role)
    {
        roles.add(role);
    }

    public ArrayList<String> getRoles()
    {
        return roles;
    }

    public void addAttributes(String attributes, ArrayList<Double> value)
    {
        this.attributes.put(attributes, value);
    }

    public ArrayList<Double> getAttributes(String attributes)
    {
        return this.attributes.get(attributes);
    }

    public void addTip(String tip)
    {
        tips.add(tip);
    }

    public ArrayList<String> getTips()
    {
        return tips;
    }

    public void addAbility(String name, String description, String ability, String affects, String damage, Map<String, String> attributes, Boolean orbOfVenom, Integer blackKingBar, Integer linkensSphere, Integer diffusalBlade, Integer mantaStyle, String cooldown, String mana, String blackKingBarDescription, String diffusalBladeDescription, String linkensSphereDescription, String mantaStyleDescription, String altDescription, String aghanims, ArrayList<String> notes)
    {
        abilities.add(new Ability(name, description, ability, affects, damage, attributes, orbOfVenom, blackKingBar, linkensSphere, diffusalBlade, mantaStyle, cooldown, mana, blackKingBarDescription, diffusalBladeDescription, linkensSphereDescription, mantaStyleDescription, altDescription, aghanims, notes));
    }

    public ArrayList<Ability> getAbilities()
    {
        return abilities;
    }
}