package com.dirtypepper.dota2;

import java.util.ArrayList;
import java.util.Map;

public class Ability
{
    private String name;
    private String description;
    private String ability;
    private String affects;
    private String damage;
    private Map<String, String> attributes;
    private Boolean orbOfVenom;
    private Integer blackKingBar;
    private Integer linkensSphere;
    private Integer diffusalBlade;
    private Integer mantaStyle;
    private String cooldown;
    private String mana;
    private String blackKingBarDescription;
    private String linkensSphereDescription;
    private String diffusalBladeDescription;
    private String mantaStyleDescription;
    private String altDescription;
    private String aghanims;
    private ArrayList<String> notes;

    public Ability(String name, String description, String ability, String affects, String damage, Map<String, String> attributes, Boolean orbOfVenom, Integer blacKingBar, Integer linkensSphere, Integer diffusalBlade, Integer mantaStyle, String cooldown, String mana, String blackKingBarDescription, String diffusalBladeDescription, String linkensSphereDescription, String mantaStyleDescription, String altDescription, String aghanims, ArrayList<String> notes)
    {
        this.name = name;
        this.description = description;
        this.ability = ability;
        this.affects = affects;
        this.damage = damage;
        this.attributes = attributes;
        this.orbOfVenom = orbOfVenom;
        this.blackKingBar = blacKingBar;
        this.linkensSphere = linkensSphere;
        this.diffusalBlade = diffusalBlade;
        this.mantaStyle = mantaStyle;
        this.cooldown = cooldown;
        this.mana = mana;
        this.blackKingBarDescription = blackKingBarDescription;
        this.diffusalBladeDescription = diffusalBladeDescription;
        this.linkensSphereDescription = linkensSphereDescription;
        this.mantaStyleDescription = mantaStyleDescription;
        this.altDescription = altDescription;
        this.aghanims = aghanims;
        this.notes = notes;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public String getAbility()
    {
        return ability;
    }

    public String getAffects()
    {
        return affects;
    }

    public String getDamage()
    {
        return damage;
    }

    public Map<String, String> getAttributes()
    {
        return attributes;
    }

    public Boolean getOrbOfVenom()
    {
        return orbOfVenom;
    }

    public Integer getBlackKingBar()
    {
        return blackKingBar;
    }

    public Integer getLinkensSphere()
    {
        return linkensSphere;
    }

    public Integer getDiffusalBlade()
    {
        return diffusalBlade;
    }

    public Integer getMantaStyle()
    {
        return mantaStyle;
    }

    public String getCooldown()
    {
        return cooldown;
    }

    public String getMana()
    {
        return mana;
    }

    public String getBlackKingBarDescription()
    {
        return blackKingBarDescription;
    }

    public String getLinkensSphereDescription()
    {
        return linkensSphereDescription;
    }

    public String getDiffusalBladeDescription()
    {
        return diffusalBladeDescription;
    }

    public String getMantaStyleDescription()
    {
        return mantaStyleDescription;
    }

    public String getAltDescription()
    {
        return altDescription;
    }

    public String getAghanims()
    {
        return aghanims;
    }

    public ArrayList<String> getNotes()
    {
        return notes;
    }

    public String toString()
    {
        return getName() + " | " + getDescription() + " | " + getAbility() + " | " + getAffects() + " | " + getDamage();
    }
}
