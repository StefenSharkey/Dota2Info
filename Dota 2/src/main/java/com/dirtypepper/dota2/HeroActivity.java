package com.dirtypepper.dota2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class HeroActivity extends Activity
{
    private Hero currentHero;
    private ImageView imageView;
    private TextView textView;

    private XmlPullParser parser;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero);
        Utilities.parents.push(getClass());

        getActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        String value = intent.getStringExtra("hero");
        Log.wtf("Hero Value", value);
        textView = (TextView)findViewById(R.id.hero_name);
        textView.setText(value);
        getActionBar().setTitle(value);
        getHeroInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.hero, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Utilities.printStack();
        int id = item.getItemId();
        switch(id)
        {
            case android.R.id.home:
                Utilities.parents.pop();
                Intent parentActivityIntent = new Intent(this, Utilities.parents.pop());
                parentActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(parentActivityIntent);
                finish();
                break;
            case R.id.action_description:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(currentHero.getName() + " " + getResources().getString(R.string.description));
                Log.wtf("currentHero.getDescription()", currentHero.getDescription());
                builder.setMessage(currentHero.getDescription());
                builder.setNegativeButton(R.string.tips, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(HeroActivity.this);
                        builder1.setTitle(currentHero.getName() + " " + getResources().getString(R.string.tips));
                        builder1.setMessage(formatTips());
                        builder1.setNeutralButton(R.string.ok, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                dialogInterface.dismiss();
                            }
                        });
                        builder1.create().show();
                    }
                });
                builder.setNeutralButton(R.string.notes, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(HeroActivity.this);
                        builder1.setTitle(currentHero.getName() + " " + getResources().getString(R.string.ability_notes));
                        builder1.setMessage(formatNotes());
                        builder1.setNeutralButton(R.string.ok, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                dialogInterface.dismiss();
                            }
                        });
                        builder1.create().show();
                    }
                });
                builder.setPositiveButton(R.string.wiki, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://dota2.gamepedia.com/" + Utilities.nameToWebsite(currentHero.getName())));
                        startActivity(intent);
                    }
                });
                builder.create().show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        Utilities.parents.pop();
        finish();
    }

    public void getHeroInfo()
    {
        Log.wtf("getHeroInfo()", "Hello");
        parser = Xml.newPullParser();

        XmlPullParserFactory pullParserFactory;
        try
        {
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();
            InputStream inputStream = getApplicationContext().getAssets().open("heroes.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(inputStream, null);
            parseHeroXML(parser);
        } catch(XmlPullParserException e)
        {
            e.printStackTrace();
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    private void parseHeroXML(XmlPullParser parser) throws XmlPullParserException, IOException
    {
        Log.wtf("parseHeroXML()", "Hello");
        int eventType = parser.getEventType();
        currentHero = null;

        String name = null;
        String description = null;
        String ability = null;
        String affects = null;
        String damage = null;
        Boolean orbOfVenom = null;
        Integer blackKingBar = null;
        Integer linkensSphere = null;
        Integer diffusalBlade = null;
        Integer mantaStyle = null;
        String cooldown = null;
        String mana = null;
        Map<String, String> attributes = new LinkedHashMap<String, String>();
        String blackKingBarDescription = null;
        String linkensSphereDescription = null;
        String diffusalBladeDescription = null;
        String mantaStyleDescription = null;
        String altDescription = null;
        String aghanims = null;
        ArrayList<String> notes = new ArrayList<String>();

        while(eventType != XmlPullParser.END_DOCUMENT)
        {
            String heroName;
            switch(eventType) {
                case XmlPullParser.START_TAG:
                    heroName = parser.getName();
                    if (heroName.equals("hero"))
                        currentHero = new Hero();
                    else if (currentHero != null) {
                        if (heroName.equals("name"))
                            currentHero.setName(parser.nextText());
                        else if(heroName.equals("damageType") && currentHero.getName().equals(textView.getText()))
                            currentHero.setDamageType(parser.nextText());
                        else if(heroName.equals("attribute") && currentHero.getName().equals(textView.getText()))
                            currentHero.setAttribute(parser.nextText());
                        else if(heroName.equals("faction") && currentHero.getName().equals(textView.getText()))
                            currentHero.setFaction(parser.nextText());
                        else if(heroName.equals("description") && currentHero.getName().equals(textView.getText()))
                            currentHero.setDescription(parser.nextText());
                        else if(heroName.equals("role") && currentHero.getName().equals(textView.getText()))
                            currentHero.addRole(parser.nextText());
                        else if(heroName.equals("attributes") && currentHero.getName().equals(textView.getText()))
                        {
                            ArrayList<Double> tmp = new ArrayList<Double>();
                            tmp.add(Double.parseDouble(parser.getAttributeValue(null, "strengthBase")));
                            tmp.add(Double.parseDouble(parser.getAttributeValue(null, "strengthGain")));
                            currentHero.addAttributes("strength", tmp);

                            tmp = new ArrayList<Double>();
                            tmp.add(Double.parseDouble(parser.getAttributeValue(null, "agilityBase")));
                            tmp.add(Double.parseDouble(parser.getAttributeValue(null, "agilityGain")));
                            currentHero.addAttributes("agility", tmp);

                            tmp = new ArrayList<Double>();
                            tmp.add(Double.parseDouble(parser.getAttributeValue(null, "intelligenceBase")));
                            tmp.add(Double.parseDouble(parser.getAttributeValue(null, "intelligenceGain")));
                            currentHero.addAttributes("intelligence", tmp);

                            tmp = new ArrayList<Double>();
                            tmp.add(Double.parseDouble(parser.getAttributeValue(null, "damageMinimum")));
                            tmp.add(Double.parseDouble(parser.getAttributeValue(null, "damageMaximum")));
                            currentHero.addAttributes("damage", tmp);

                            tmp = new ArrayList<Double>();
                            tmp.add(Double.parseDouble(parser.getAttributeValue(null, "baseMoveSpeed")));
                            tmp.add(Double.parseDouble(parser.getAttributeValue(null, "baseArmor")));
                            currentHero.addAttributes("baseStats", tmp);
                        }
                        else if(heroName.equals("tips") && currentHero.getName().equals(textView.getText()))
                            currentHero.addTip(parser.nextText());
                        else if(heroName.equals("ability") && currentHero.getName().equals(textView.getText()))
                        {
                            name = parser.getAttributeValue(null, "name");
                            description = parser.getAttributeValue(null, "description");
                            ability = parser.getAttributeValue(null, "ability");
                            affects = parser.getAttributeValue(null, "affects");
                            if(parser.getAttributeValue(null, "damage") != null)
                                damage = parser.getAttributeValue(null, "damage");
                            if(parser.getAttributeValue(null, "orbOfVenom") != null)
                                orbOfVenom = Integer.parseInt(parser.getAttributeValue(null, "orbOfVenom")) == 1;
                            if(parser.getAttributeValue(null, "blackKingBar") != null)
                                blackKingBar = Integer.parseInt(parser.getAttributeValue(null, "blackKingBar"));
                            if(parser.getAttributeValue(null, "linkensSphere") != null)
                                linkensSphere = Integer.parseInt(parser.getAttributeValue(null, "linkensSphere"));
                            if(parser.getAttributeValue(null, "diffusalBlade") != null)
                                diffusalBlade = Integer.parseInt(parser.getAttributeValue(null, "diffusalBlade"));
                            if(parser.getAttributeValue(null, "mantaStyle") != null)
                                mantaStyle = Integer.parseInt(parser.getAttributeValue(null, "mantaStyle"));
                            if(parser.getAttributeValue(null, "cooldown") != null)
                                cooldown = parser.getAttributeValue(null, "cooldown");
                            if(parser.getAttributeValue(null, "mana") != null)
                                mana = parser.getAttributeValue(null, "mana");
                        }
                        else if(heroName.equals("abilityAttributes") && currentHero.getName().equals(textView.getText()))
                        {
                            Log.wtf("name", parser.getAttributeValue(null, "name"));
                            Log.wtf("value", parser.getAttributeValue(null, "value"));
                            attributes.put(parser.getAttributeValue(null, "name"), parser.getAttributeValue(null, "value"));
                        }
                        else if(heroName.equals("blackKingBar") && currentHero.getName().equals(textView.getText()))
                        {
                            blackKingBarDescription = parser.getAttributeValue(null, "description");
                            Log.wtf("Ability.toString()", description);
                        }
                        else if(heroName.equals("linkensSphere") && currentHero.getName().equals(textView.getText()))
                        {
                            linkensSphereDescription = parser.getAttributeValue(null, "description");
                            Log.wtf("Ability.toString()", description);
                        }
                        else if(heroName.equals("diffusalBlade") && currentHero.getName().equals(textView.getText()))
                        {
                            diffusalBladeDescription = parser.getAttributeValue(null, "description");
                            Log.wtf("Ability.toString()", description);
                        }
                        else if(heroName.equals("mantaStyle") && currentHero.getName().equals(textView.getText()))
                        {
                            mantaStyleDescription = parser.getAttributeValue(null, "description");
                            Log.wtf("Ability.toString()", description);
                        }
                        else if(heroName.equals("altDescription") && currentHero.getName().equals(textView.getText()))
                        {
                            altDescription = parser.getAttributeValue(null, "description");
                            Log.wtf("Ability.toString()", description);
                        }
                        else if(heroName.equals("aghanims") && currentHero.getName().equals(textView.getText()))
                        {
                            aghanims = parser.getAttributeValue(null, "description");
                            Log.wtf("Ability.toString()", description);
                        }
                        else if(heroName.equals("note") && currentHero.getName().equals(textView.getText()))
                            notes.add(parser.nextText());
                    }
                    break;
                case XmlPullParser.END_TAG:
                    heroName = parser.getName();
                    if (heroName.equalsIgnoreCase("hero") && currentHero != null && currentHero.getName().equals(textView.getText()))
                    {
                        printHeroes(currentHero);
                        return;
                    }
                    else if (heroName.equalsIgnoreCase("ability") && currentHero != null && currentHero.getName().equals(textView.getText()))
                    {
                        currentHero.addAbility(name, description, ability, affects, damage, attributes, orbOfVenom, blackKingBar, linkensSphere, diffusalBlade, mantaStyle, cooldown, mana, blackKingBarDescription, diffusalBladeDescription, linkensSphereDescription, mantaStyleDescription, altDescription, aghanims, notes);

                        name = null;
                        description = null;
                        ability = null;
                        affects = null;
                        damage = null;
                        orbOfVenom = null;
                        blackKingBar = null;
                        linkensSphere = null;
                        diffusalBlade = null;
                        mantaStyle = null;
                        cooldown = null;
                        mana = null;
                        attributes = new LinkedHashMap<String, String>();
                        blackKingBarDescription = null;
                        diffusalBladeDescription = null;
                        linkensSphereDescription = null;
                        mantaStyleDescription = null;
                        altDescription = null;
                        aghanims = null;
                        notes = new ArrayList<String>();
                    }
            }
            eventType = parser.next();
        }
    }

    private void printHeroes(Hero hero)
    {
        Log.wtf("printHeroes()", hero.getName());
        ArrayList<Double> stats;

        textView = (TextView)findViewById(R.id.hero_name);
        textView.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(getResources().getIdentifier(Utilities.nameToDrawable("faction", hero.getFaction()), "drawable", getApplicationContext().getPackageName())), null, null, null);

        imageView = (ImageView)findViewById(R.id.hero_picture);
        imageView.setImageResource(getResources().getIdentifier(Utilities.nameToDrawable("hero", hero.getName()), "drawable", getApplicationContext().getPackageName()));

        textView = (TextView)findViewById(R.id.hero_damage_type);
        textView.setText(Utilities.formatRoles(hero));

        if(hero.getAttribute().equals("Strength"))
            imageView = (ImageView)findViewById(R.id.hero_strength_icon);
        else if(hero.getAttribute().equals("Agility"))
            imageView = (ImageView)findViewById(R.id.hero_agility_icon);
        else if(hero.getAttribute().equals("Intelligence"))
            imageView = (ImageView)findViewById(R.id.hero_intelligence_icon);
        imageView.setImageResource(getResources().getIdentifier(Utilities.nameToDrawable("attribute", hero.getAttribute(), "main"), "drawable", getApplicationContext().getPackageName()));

        stats = hero.getAttributes("strength");
        if(stats != null)
        {
            textView = (TextView) findViewById(R.id.hero_strength);
            textView.setText(Utilities.formatStats(stats));
        }

        stats = hero.getAttributes("agility");
        if(stats != null)
        {
            textView = (TextView) findViewById(R.id.hero_agility);
            textView.setText(Utilities.formatStats(stats));
        }

        stats = hero.getAttributes("intelligence");
        if(stats != null)
        {
            textView = (TextView) findViewById(R.id.hero_intelligence);
            textView.setText(Utilities.formatStats(stats));
        }

        stats = hero.getAttributes("damage");
        if(stats != null)
        {
            textView = (TextView) findViewById(R.id.hero_damage);
            textView.setText(Utilities.formatDamage(stats));
        }

        stats = hero.getAttributes("baseStats");
        if(stats != null)
        {
            textView = (TextView) findViewById(R.id.hero_move_speed);
            textView.setText(Utilities.formatSpeed(stats.get(0).toString()));

            textView = (TextView) findViewById(R.id.hero_armor);
            textView.setText(stats.get(1).toString());
        }

        if(hero.getAbilities() != null)
            for(Ability ability : hero.getAbilities())
                printAbility(ability);
    }

    public void printAbility(Ability ability)
    {
        Log.wtf("printAbility()", ability.getName());
        LinearLayout heroAbilityParent = (LinearLayout)findViewById(R.id.hero_ability_parent);
        LinearLayout heroAbilityContainer = new LinearLayout(this);
        ImageView heroAbilityPicture = new ImageView(this);
        LinearLayout heroAbility = new LinearLayout(this);
        LinearLayout heroAbilityTitle = new LinearLayout(this);
        TextView heroAbilityName = new TextView(this);
        ImageView heroAbilityOrbOfVenomIcon = new ImageView(this);
        ImageView heroAbilityBlackKingBarIcon = new ImageView(this);
        ImageView heroAbilityDiffusalBladeIcon = new ImageView(this);
        ImageView heroAbilityLinkensSphereIcon = new ImageView(this);
        ImageView heroAbilityMantaStyleIcon = new ImageView(this);
        LinearLayout heroAbilityAbilityParent = new LinearLayout(this);
        LinearLayout heroAbilityAbility = new LinearLayout(this);
        TextView heroAbilityAbilityText = new TextView(this);
        TextView heroAbilityAbilityValue = new TextView(this);
        LinearLayout heroAbilityAffects = new LinearLayout(this);
        TextView heroAbilityAffectsText = new TextView(this);
        TextView heroAbilityAffectsValue = new TextView(this);
        LinearLayout heroAbilityDamage = new LinearLayout(this);
        TextView heroAbilityDamageText = new TextView(this);
        TextView heroAbilityDamageValue = new TextView(this);
        TextView heroAbilityDescription = new TextView(this);
        LinearLayout heroAbilityMisc = new LinearLayout(this);
        TextView heroAbilityCooldown = new TextView(this);
        TextView heroAbilityMana = new TextView(this);
        LinearLayout heroAbilityBlackKingBar = new LinearLayout(this);
        ImageView heroAbilityBlackKingBarPicture = new ImageView(this);
        TextView heroAbilityBlackKingBarDescription = new TextView(this);
        LinearLayout heroAbilityLinkensSphere = new LinearLayout(this);
        ImageView heroAbilityLinkensSpherePicture = new ImageView(this);
        TextView heroAbilityLinkensSphereDescription = new TextView(this);
        LinearLayout heroAbilityDiffusalBlade = new LinearLayout(this);
        ImageView heroAbilityDiffusalBladePicture = new ImageView(this);
        TextView heroAbilityDiffusalBladeDescription = new TextView(this);
        LinearLayout heroAbilityMantaStyle = new LinearLayout(this);
        ImageView heroAbilityMantaStylePicture = new ImageView(this);
        TextView heroAbilityMantaStyleDescription = new TextView(this);
        LinearLayout heroAbilityAghanimsScepter = new LinearLayout(this);
        ImageView heroAbilityAghanimsScepterPicture = new ImageView(this);
        TextView heroAbilityAghanimsScepterDescription = new TextView(this);
        TextView heroAbilityAltDescription = new TextView(this);

        heroAbilityContainer.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        heroAbilityContainer.setOrientation(LinearLayout.HORIZONTAL);
        heroAbilityContainer.setPadding(0, 0, 0, Utilities.dpToPx(5, getResources()));

        heroAbilityPicture.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 128));
        heroAbilityPicture.setAdjustViewBounds(true);
        Log.wtf("Utilities.nameToDrawable()", Utilities.nameToDrawable(currentHero.getName(), ability.getName()));
        heroAbilityPicture.setImageResource(getResources().getIdentifier(Utilities.nameToDrawable(currentHero.getName(), ability.getName()), "drawable", getApplicationContext().getPackageName()));

        heroAbility.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        heroAbility.setOrientation(LinearLayout.VERTICAL);
        heroAbility.setPadding(Utilities.dpToPx(5, getResources()), 0, Utilities.dpToPx(5, getResources()), Utilities.dpToPx(5, getResources()));

        heroAbilityName.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        heroAbilityName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        heroAbilityName.setTextAppearance(getApplicationContext(), R.style.TextColor);
        heroAbilityName.setText(ability.getName());

        heroAbilityTitle.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        heroAbilityTitle.setOrientation(LinearLayout.HORIZONTAL);
        heroAbilityTitle.setPadding(0, 0, Utilities.dpToPx(5, getResources()), 0);

        heroAbilityOrbOfVenomIcon.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        heroAbilityOrbOfVenomIcon.setPadding(Utilities.dpToPx(5, getResources()), 0, 0, 0);
        heroAbilityOrbOfVenomIcon.setScaleType(ImageView.ScaleType.CENTER);
        if(ability.getOrbOfVenom() != null)
            if(ability.getOrbOfVenom())
                heroAbilityOrbOfVenomIcon.setImageResource(R.drawable.ability_orb_of_venom);

        heroAbilityBlackKingBarIcon.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        heroAbilityBlackKingBarIcon.setPadding(Utilities.dpToPx(5, getResources()), 0, 0, 0);
        heroAbilityBlackKingBarIcon.setScaleType(ImageView.ScaleType.CENTER);
        if(ability.getBlackKingBar() != null)
            switch(ability.getBlackKingBar())
            {
                case 0:
                    heroAbilityBlackKingBarIcon.setImageResource(R.drawable.ability_black_king_bar_none);
                    break;
                case 1:
                    heroAbilityBlackKingBarIcon.setImageResource(R.drawable.ability_black_king_bar_partial);
                    break;
                case 2:
                    heroAbilityBlackKingBarIcon.setImageResource(R.drawable.ability_black_king_bar);
                    break;
            }

        heroAbilityLinkensSphereIcon.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        heroAbilityLinkensSphereIcon.setPadding(Utilities.dpToPx(5, getResources()), 0, 0, 0);
        heroAbilityLinkensSphereIcon.setScaleType(ImageView.ScaleType.CENTER);
        if(ability.getLinkensSphere() != null)
            switch(ability.getLinkensSphere())
            {
                case 0:
                    heroAbilityLinkensSphereIcon.setImageResource(R.drawable.ability_linkens_sphere_none);
                    break;
                case 1:
                    heroAbilityLinkensSphereIcon.setImageResource(R.drawable.ability_linkens_sphere_partial);
                    break;
                case 2:
                    heroAbilityLinkensSphereIcon.setImageResource(R.drawable.ability_linkens_sphere);
                    break;
            }

        heroAbilityDiffusalBladeIcon.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        heroAbilityDiffusalBladeIcon.setPadding(Utilities.dpToPx(5, getResources()), 0, 0, 0);
        heroAbilityDiffusalBladeIcon.setScaleType(ImageView.ScaleType.CENTER);
        if(ability.getDiffusalBlade() != null)
            switch(ability.getDiffusalBlade())
            {
                case 0:
                    heroAbilityDiffusalBladeIcon.setImageResource(R.drawable.ability_diffusal_blade_none);
                    break;
                case 1:
                    heroAbilityDiffusalBladeIcon.setImageResource(R.drawable.ability_diffusal_blade_partial);
                    break;
                case 2:
                    heroAbilityDiffusalBladeIcon.setImageResource(R.drawable.ability_diffusal_blade);
                    break;
            }

        heroAbilityMantaStyleIcon.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        heroAbilityMantaStyleIcon.setPadding(Utilities.dpToPx(5, getResources()), 0, 0, 0);
        heroAbilityMantaStyleIcon.setScaleType(ImageView.ScaleType.CENTER);
        if(ability.getMantaStyle() != null)
        {
            switch(ability.getMantaStyle())
            {
                case 0:
                    heroAbilityMantaStyleIcon.setImageResource(R.drawable.ability_manta_style_none);
                    break;
                case 1:
                    heroAbilityMantaStyleIcon.setImageResource(R.drawable.ability_manta_style_partial);
                    break;
                case 2:
                    heroAbilityMantaStyleIcon.setImageResource(R.drawable.ability_manta_style);
                    break;
            }
        }

        heroAbilityAbilityParent.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        heroAbilityAbilityParent.setOrientation(LinearLayout.HORIZONTAL);

        heroAbilityAbility.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        heroAbilityAbility.setOrientation(LinearLayout.VERTICAL);
        heroAbilityAbility.setPadding(0, 0, Utilities.dpToPx(5, getResources()), 0);

        heroAbilityAbilityText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        heroAbilityAbilityText.setGravity(Gravity.CENTER_HORIZONTAL);
        heroAbilityAbilityText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        heroAbilityAbilityText.setTextAppearance(getApplicationContext(), R.style.TextColor_Bold);
        heroAbilityAbilityText.setText(R.string.ability);

        heroAbilityAbilityValue.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        heroAbilityAbilityValue.setGravity(Gravity.CENTER_HORIZONTAL);
        heroAbilityAbilityValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        heroAbilityAbilityValue.setTextAppearance(getApplicationContext(), R.style.TextColor);
        heroAbilityAbilityValue.setText(ability.getAbility());

        if(ability.getAffects() != null)
        {
            heroAbilityAffects.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            heroAbilityAffects.setOrientation(LinearLayout.VERTICAL);
            heroAbilityAffects.setPadding(Utilities.dpToPx(5, getResources()), 0, Utilities.dpToPx(5, getResources()), 0);

            heroAbilityAffectsText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            heroAbilityAffectsText.setGravity(Gravity.CENTER_HORIZONTAL);
            heroAbilityAffectsText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            heroAbilityAffectsText.setTextAppearance(getApplicationContext(), R.style.TextColor_Bold);
            heroAbilityAffectsText.setText(R.string.affects);

            heroAbilityAffectsValue.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            heroAbilityAffectsValue.setGravity(Gravity.CENTER_HORIZONTAL);
            heroAbilityAffectsValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            heroAbilityAffectsValue.setTextAppearance(getApplicationContext(), R.style.TextColor);
            heroAbilityAffectsValue.setText(ability.getAffects());
        }

        if(ability.getDamage() != null)
        {
            heroAbilityDamage.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            heroAbilityDamage.setOrientation(LinearLayout.VERTICAL);
            heroAbilityDamage.setPadding(Utilities.dpToPx(5, getResources()), 0, Utilities.dpToPx(5, getResources()), 0);

            heroAbilityDamageText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            heroAbilityDamageText.setGravity(Gravity.CENTER_HORIZONTAL);
            heroAbilityDamageText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            heroAbilityDamageText.setTextAppearance(getApplicationContext(), R.style.TextColor_Bold);
            heroAbilityDamageText.setText(R.string.damage);

            heroAbilityDamageValue.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            heroAbilityDamageValue.setGravity(Gravity.CENTER_HORIZONTAL);
            heroAbilityDamageValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            heroAbilityDamageValue.setTextAppearance(getApplicationContext(), R.style.TextColor);
            heroAbilityDamageValue.setText(ability.getDamage());
        }

        heroAbilityDescription.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        heroAbilityDescription.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        heroAbilityDescription.setTextAppearance(getApplicationContext(), R.style.TextColor);
        heroAbilityDescription.setText(ability.getDescription());

        heroAbilityAbility.addView(heroAbilityAbilityText);
        heroAbilityAbility.addView(heroAbilityAbilityValue);
        heroAbilityAffects.addView(heroAbilityAffectsText);
        heroAbilityAffects.addView(heroAbilityAffectsValue);
        heroAbilityDamage.addView(heroAbilityDamageText);
        heroAbilityDamage.addView(heroAbilityDamageValue);
        heroAbilityAbilityParent.addView(heroAbilityAbility);
        heroAbilityAbilityParent.addView(heroAbilityAffects);
        heroAbilityAbilityParent.addView(heroAbilityDamage);
        heroAbilityTitle.addView(heroAbilityName);
        if(ability.getOrbOfVenom() != null)
            heroAbilityTitle.addView(heroAbilityOrbOfVenomIcon);
        if(ability.getBlackKingBar() != null)
            heroAbilityTitle.addView(heroAbilityBlackKingBarIcon);
        if(ability.getLinkensSphere() != null)
            heroAbilityTitle.addView(heroAbilityLinkensSphereIcon);
        if(ability.getDiffusalBlade() != null)
            heroAbilityTitle.addView(heroAbilityDiffusalBladeIcon);
        if(ability.getMantaStyle() != null)
            heroAbilityTitle.addView(heroAbilityMantaStyleIcon);
        heroAbility.addView(heroAbilityTitle);
        heroAbility.addView(heroAbilityAbilityParent);
        heroAbility.addView(heroAbilityDescription);

        for(Map.Entry<String, String> attribute : ability.getAttributes().entrySet())
        {
            LinearLayout heroAbilityAttribute = new LinearLayout(this);
            TextView heroAbilityAttributeText = new TextView(this);
            TextView heroAbilityAttributeValue = new TextView(this);

            heroAbilityAttribute.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            heroAbilityAttribute.setOrientation(LinearLayout.HORIZONTAL);
            heroAbilityAttribute.setPadding(0, 0, Utilities.dpToPx(5, getResources()), 0);

            heroAbilityAttributeText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            heroAbilityAttributeText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            heroAbilityAttributeText.setTextAppearance(getApplicationContext(), R.style.TextColor_Bold);
            heroAbilityAttributeText.setMaxWidth(Utilities.getWidth(getWindowManager())/2);
            heroAbilityAttributeText.setText(attribute.getKey() + ": ");

            heroAbilityAttributeValue.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            heroAbilityAttributeValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            heroAbilityAttributeValue.setTextAppearance(getApplicationContext(), R.style.TextColor);
            heroAbilityAttributeValue.setText(attribute.getValue());

            heroAbilityAttribute.addView(heroAbilityAttributeText);
            heroAbilityAttribute.addView(heroAbilityAttributeValue);
            heroAbility.addView(heroAbilityAttribute);
        }

        heroAbilityMisc.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        heroAbilityMisc.setOrientation(LinearLayout.HORIZONTAL);
        heroAbilityMisc.setPadding(0, 0, Utilities.dpToPx(5, getResources()), 0);

        if(ability.getCooldown() != null)
        {
            heroAbilityCooldown.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            heroAbilityCooldown.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ability_cooldown, 0, 0, 0);
            heroAbilityCooldown.setCompoundDrawablePadding(Utilities.dpToPx(5, getResources()));
            heroAbilityCooldown.setPadding(0, 0, Utilities.dpToPx(10, getResources()), 0);
            heroAbilityCooldown.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            heroAbilityCooldown.setTextAppearance(getApplicationContext(), R.style.TextColor);
            heroAbilityCooldown.setText(ability.getCooldown());
        }

        if(ability.getMana() != null)
        {
            heroAbilityMana.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            heroAbilityMana.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ability_mana, 0, 0, 0);
            heroAbilityMana.setCompoundDrawablePadding(Utilities.dpToPx(5, getResources()));
            heroAbilityMana.setPadding(0, 0, Utilities.dpToPx(10, getResources()), 0);
            heroAbilityMana.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            heroAbilityMana.setTextAppearance(getApplicationContext(), R.style.TextColor);
            heroAbilityMana.setText(ability.getMana());
        }

        if(ability.getBlackKingBarDescription() != null)
        {
            heroAbilityBlackKingBar.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            heroAbilityBlackKingBar.setOrientation(LinearLayout.HORIZONTAL);
            heroAbilityBlackKingBar.setGravity(Gravity.CENTER_VERTICAL);
            heroAbilityBlackKingBar.setPadding(0, 0, Utilities.dpToPx(5, getResources()), 0);

            heroAbilityBlackKingBarPicture.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            heroAbilityBlackKingBarPicture.setPadding(0, 0, Utilities.dpToPx(5, getResources()), 0);
            heroAbilityBlackKingBarPicture.setImageDrawable(heroAbilityBlackKingBarIcon.getDrawable());

            heroAbilityBlackKingBarDescription.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            heroAbilityBlackKingBarDescription.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            heroAbilityBlackKingBarDescription.setPadding(0, 0, Utilities.dpToPx(5, getResources()), 0);
            heroAbilityBlackKingBarDescription.setTextAppearance(getApplicationContext(), R.style.TextColor);
            heroAbilityBlackKingBarDescription.setText(ability.getBlackKingBarDescription());
        }

        if(ability.getLinkensSphereDescription() != null)
        {
            heroAbilityLinkensSphere.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            heroAbilityLinkensSphere.setOrientation(LinearLayout.HORIZONTAL);
            heroAbilityLinkensSphere.setGravity(Gravity.CENTER_VERTICAL);
            heroAbilityLinkensSphere.setPadding(0, 0, Utilities.dpToPx(5, getResources()), 0);

            heroAbilityLinkensSpherePicture.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            heroAbilityLinkensSpherePicture.setPadding(0, Utilities.dpToPx(5, getResources()), Utilities.dpToPx(5, getResources()), 0);
            heroAbilityLinkensSpherePicture.setImageDrawable(heroAbilityLinkensSphereIcon.getDrawable());

            heroAbilityLinkensSphereDescription.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            heroAbilityLinkensSphereDescription.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            heroAbilityLinkensSphereDescription.setPadding(0, 0, Utilities.dpToPx(10, getResources()), 0);
            heroAbilityLinkensSphereDescription.setTextAppearance(getApplicationContext(), R.style.TextColor);
            heroAbilityLinkensSphereDescription.setText(ability.getLinkensSphereDescription());
        }

        if(ability.getDiffusalBladeDescription() != null)
        {
            heroAbilityDiffusalBlade.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            heroAbilityDiffusalBlade.setOrientation(LinearLayout.HORIZONTAL);
            heroAbilityDiffusalBlade.setGravity(Gravity.CENTER_VERTICAL);
            heroAbilityDiffusalBlade.setPadding(0, 0, Utilities.dpToPx(5, getResources()), 0);

            heroAbilityDiffusalBladePicture.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            heroAbilityDiffusalBladePicture.setPadding(0, Utilities.dpToPx(5, getResources()), Utilities.dpToPx(5, getResources()), 0);
            heroAbilityDiffusalBladePicture.setImageDrawable(heroAbilityDiffusalBladeIcon.getDrawable());

            heroAbilityDiffusalBladeDescription.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            heroAbilityDiffusalBladeDescription.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            heroAbilityDiffusalBladeDescription.setPadding(0, 0, Utilities.dpToPx(10, getResources()), 0);
            heroAbilityDiffusalBladeDescription.setTextAppearance(getApplicationContext(), R.style.TextColor);
            heroAbilityDiffusalBladeDescription.setText(ability.getDiffusalBladeDescription());
        }

        if(ability.getMantaStyleDescription() != null)
        {
            heroAbilityMantaStyle.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            heroAbilityMantaStyle.setOrientation(LinearLayout.HORIZONTAL);
            heroAbilityMantaStyle.setGravity(Gravity.CENTER_VERTICAL);
            heroAbilityMantaStyle.setPadding(0, 0, Utilities.dpToPx(5, getResources()), 0);

            heroAbilityMantaStylePicture.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            heroAbilityMantaStylePicture.setPadding(0, Utilities.dpToPx(5, getResources()), Utilities.dpToPx(5, getResources()), 0);
            heroAbilityMantaStylePicture.setImageDrawable(heroAbilityMantaStyleIcon.getDrawable());

            heroAbilityMantaStyleDescription.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            heroAbilityMantaStyleDescription.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            heroAbilityMantaStyleDescription.setPadding(0, 0, Utilities.dpToPx(10, getResources()), 0);
            heroAbilityMantaStyleDescription.setTextAppearance(getApplicationContext(), R.style.TextColor);
            heroAbilityMantaStyleDescription.setText(ability.getMantaStyleDescription());
        }

        if(ability.getAghanims() != null)
        {
            heroAbilityAghanimsScepter.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            heroAbilityAghanimsScepter.setOrientation(LinearLayout.HORIZONTAL);
            heroAbilityAghanimsScepter.setGravity(Gravity.CENTER_VERTICAL);
            heroAbilityAghanimsScepter.setPadding(0, 0, Utilities.dpToPx(5, getResources()), 0);

            heroAbilityAghanimsScepterPicture.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            heroAbilityAghanimsScepterPicture.setPadding(0, Utilities.dpToPx(5, getResources()), Utilities.dpToPx(5, getResources()), 0);
            heroAbilityAghanimsScepterPicture.setImageResource(R.drawable.ability_aghanims_scepter);

            heroAbilityAghanimsScepterDescription.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            heroAbilityAghanimsScepterDescription.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            heroAbilityAghanimsScepterDescription.setPadding(0, 0, Utilities.dpToPx(10, getResources()), 0);
            heroAbilityAghanimsScepterDescription.setTextAppearance(getApplicationContext(), R.style.TextColor);
            heroAbilityAghanimsScepterDescription.setText(ability.getAghanims());
        }

        heroAbilityAltDescription.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        heroAbilityAltDescription.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        heroAbilityAltDescription.setPadding(0, 0, Utilities.dpToPx(10, getResources()), 0);
        heroAbilityAltDescription.setTextAppearance(getApplicationContext(), R.style.TextColor_Italic);
        heroAbilityAltDescription.setText(ability.getAltDescription());

        if(ability.getCooldown() != null || (ability.getCooldown() == null && ability.getMana() != null))
            heroAbilityMisc.addView(heroAbilityCooldown);
        if(ability.getMana() != null)
            heroAbilityMisc.addView(heroAbilityMana);
        heroAbility.addView(heroAbilityMisc);
        if(ability.getBlackKingBarDescription() != null)
        {
            heroAbilityBlackKingBar.addView(heroAbilityBlackKingBarPicture);
            heroAbilityBlackKingBar.addView(heroAbilityBlackKingBarDescription);
            heroAbility.addView(heroAbilityBlackKingBar);
        }
        if(ability.getLinkensSphereDescription() != null)
        {
            heroAbilityLinkensSphere.addView(heroAbilityLinkensSpherePicture);
            heroAbilityLinkensSphere.addView(heroAbilityLinkensSphereDescription);
            heroAbility.addView(heroAbilityLinkensSphere);
        }
        if(ability.getDiffusalBladeDescription() != null)
        {
            heroAbilityDiffusalBlade.addView(heroAbilityDiffusalBladePicture);
            heroAbilityDiffusalBlade.addView(heroAbilityDiffusalBladeDescription);
            heroAbility.addView(heroAbilityDiffusalBlade);
        }
        if(ability.getMantaStyleDescription() != null)
        {
            heroAbilityMantaStyle.addView(heroAbilityMantaStylePicture);
            heroAbilityMantaStyle.addView(heroAbilityMantaStyleDescription);
            heroAbility.addView(heroAbilityMantaStyle);
        }
        if(ability.getAghanims() != null)
        {
            heroAbilityAghanimsScepter.addView(heroAbilityAghanimsScepterPicture);
            heroAbilityAghanimsScepter.addView(heroAbilityAghanimsScepterDescription);
            heroAbility.addView(heroAbilityAghanimsScepter);
        }
        if(ability.getAltDescription() != null)
            heroAbility.addView(heroAbilityAltDescription);
        heroAbilityContainer.addView(heroAbilityPicture);
        heroAbilityContainer.addView(heroAbility);
        heroAbilityParent.addView(heroAbilityContainer);
    }

    public String formatNotes()
    {
        String notes = "";
        if(currentHero.getAbilities().size() == 0)
            return notes;
        for(Ability ability : currentHero.getAbilities())
        {
            notes += ability.getName() + ":\n";
            for(String s : ability.getNotes())
                notes += "* " + s + "\n";
            notes += "\n";
        }
        return notes.substring(0, notes.length()-2);
    }

    public String formatTips()
    {
        String tips = "";
        if(currentHero.getTips().size() == 0)
            return tips;
        for(String tip : currentHero.getTips())
            tips += "*" + tip + "\n\n";
        return tips.substring(0, tips.length()-4);
    }
}
