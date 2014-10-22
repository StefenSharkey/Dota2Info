package com.dirtypepper.dota2;

import java.util.ArrayList;
import java.util.Map;

public class Ability {

    private final String name;
    private final String description;
    private final String ability;
    private final String affects;
    private final String damage;
    private final Map<String, String> attributes;
    private final boolean orbOfVenom;
    private final int blackKingBar;
    private final int linkensSphere;
    private final int diffusalBlade;
    private final int mantaStyle;
    private final String cooldown;
    private final String mana;
    private final String blackKingBarDescription;
    private final String linkensSphereDescription;
    private final String diffusalBladeDescription;
    private final String mantaStyleDescription;
    private final String altDescription;
    private final String aghanims;
    private final ArrayList<String> notes;

    public Ability(String name, String description, String ability, String affects, String damage, Map<String, String> attributes, boolean orbOfVenom, int blacKingBar, int linkensSphere, int diffusalBlade, int mantaStyle, String cooldown, String mana, String blackKingBarDescription, String diffusalBladeDescription, String linkensSphereDescription, String mantaStyleDescription, String altDescription, String aghanims, ArrayList<String> notes) {
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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAbility() {
        return ability;
    }

    public String getAffects() {
        return affects;
    }

    public String getDamage() {
        return damage;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public boolean getOrbOfVenom() {
        return orbOfVenom;
    }

    public int getBlackKingBar() {
        return blackKingBar;
    }

    public int getLinkensSphere() {
        return linkensSphere;
    }

    public int getDiffusalBlade() {
        return diffusalBlade;
    }

    public int getMantaStyle() {
        return mantaStyle;
    }

    public String getCooldown() {
        return cooldown;
    }

    public String getMana() {
        return mana;
    }

    public String getBlackKingBarDescription() {
        return blackKingBarDescription;
    }

    public String getLinkensSphereDescription() {
        return linkensSphereDescription;
    }

    public String getDiffusalBladeDescription() {
        return diffusalBladeDescription;
    }

    public String getMantaStyleDescription() {
        return mantaStyleDescription;
    }

    public String getAltDescription() {
        return altDescription;
    }

    public String getAghanims() {
        return aghanims;
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    public String toString() {
        return getName() + " | " + getDescription() + " | " + getAbility() + " | " + getAffects() + " | " + getDamage();
    }
}
