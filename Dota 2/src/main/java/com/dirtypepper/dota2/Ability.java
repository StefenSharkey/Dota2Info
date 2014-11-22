package com.dirtypepper.dota2;

import java.util.ArrayList;
import java.util.Map;

public class Ability {

    private String name;
    private String description;
    private String ability;
    private String affects;
    private String damage;
    private Map<String, String> attributes;
    private boolean orbOfVenom;
    private int blackKingBar;
    private int linkensSphere;
    private int diffusalBlade;
    private int mantaStyle;
    private String cooldown;
    private String mana;
    private String blackKingBarDescription;
    private String linkensSphereDescription;
    private String diffusalBladeDescription;
    private String mantaStyleDescription;
    private String altDescription;
    private String aghanims;
    private ArrayList<String> notes;

    public Ability(String name, String description, String ability, String affects, String damage, Map<String, String> attributes, boolean orbOfVenom, int blackKingBar, int linkensSphere, int diffusalBlade, int mantaStyle, String cooldown, String mana, String blackKingBarDescription, String diffusalBladeDescription, String linkensSphereDescription, String mantaStyleDescription, String altDescription, String aghanims, ArrayList<String> notes) {
        this.name = name;
        this.description = description;
        this.ability = ability;
        this.affects = affects;
        this.damage = damage;
        this.attributes = attributes;
        this.orbOfVenom = orbOfVenom;
        this.blackKingBar = blackKingBar;
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

    public boolean hasNotes() {
        return notes.size() != 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public String getAffects() {
        return affects;
    }

    public void setAffects(String affects) {
        this.affects = affects;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public boolean getOrbOfVenom() {
        return orbOfVenom;
    }

    public void setOrbOfVenom(boolean orbOfVenom) {
        this.orbOfVenom = orbOfVenom;
    }

    public int getBlackKingBar() {
        return blackKingBar;
    }

    public void setBlackKingBar(int blackKingBar) {
        this.blackKingBar = blackKingBar;
    }

    public int getLinkensSphere() {
        return linkensSphere;
    }

    public void setLinkensSphere(int linkensSphere) {
        this.linkensSphere = linkensSphere;
    }

    public int getDiffusalBlade() {
        return diffusalBlade;
    }

    public void setDiffusalBlade(int diffusalBlade) {
        this.diffusalBlade = diffusalBlade;
    }

    public int getMantaStyle() {
        return mantaStyle;
    }

    public void setMantaStyle(int mantaStyle) {
        this.mantaStyle = mantaStyle;
    }

    public String getCooldown() {
        return cooldown;
    }

    public void setCooldown(String cooldown) {
        this.cooldown = cooldown;
    }

    public String getMana() {
        return mana;
    }

    public void setMana(String mana) {
        this.mana = mana;
    }

    public String getBlackKingBarDescription() {
        return blackKingBarDescription;
    }

    public void setBlackKingBarDescription(String blackKingBarDescription) {
        this.blackKingBarDescription = blackKingBarDescription;
    }

    public String getLinkensSphereDescription() {
        return linkensSphereDescription;
    }

    public void setLinkensSphereDescription(String linkensSphereDescription) {
        this.linkensSphereDescription = linkensSphereDescription;
    }

    public String getDiffusalBladeDescription() {
        return diffusalBladeDescription;
    }

    public void setDiffusalBladeDescription(String diffusalBladeDescription) {
        this.diffusalBladeDescription = diffusalBladeDescription;
    }

    public String getMantaStyleDescription() {
        return mantaStyleDescription;
    }

    public void setMantaStyleDescription(String mantaStyleDescription) {
        this.mantaStyleDescription = mantaStyleDescription;
    }

    public String getAltDescription() {
        return altDescription;
    }

    public void setAltDescription(String altDescription) {
        this.altDescription = altDescription;
    }

    public String getAghanims() {
        return aghanims;
    }

    public void setAghanims(String aghanims) {
        this.aghanims = aghanims;
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<String> notes) {
        this.notes = notes;
    }

    public String toString() {
        return getName();
    }
}
