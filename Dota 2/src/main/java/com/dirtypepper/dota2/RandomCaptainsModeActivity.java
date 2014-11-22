package com.dirtypepper.dota2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RandomCaptainsModeActivity extends Activity implements View.OnClickListener {

    private ArrayList<String> allHeroes;
    private ArrayList<String> currentHeroes;

    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_captains_mode);

        currentHeroes = new ArrayList<>();

        Utilities.parents.push(getClass());

        refillHeroesList();

        Log.d("Heroes Amount", String.valueOf(allHeroes.size()));

        randomizeHeroes();

        View[] listeners = {
            // Hero Repick Buttons
            (Button) findViewById(R.id.ban_hero_1_repick),
            (Button) findViewById(R.id.ban_hero_2_repick),
            (Button) findViewById(R.id.ban_hero_3_repick),
            (Button) findViewById(R.id.ban_hero_4_repick),
            (Button) findViewById(R.id.ban_hero_5_repick),
            (Button) findViewById(R.id.pick_hero_1_repick),
            (Button) findViewById(R.id.pick_hero_2_repick),
            (Button) findViewById(R.id.pick_hero_3_repick),
            (Button) findViewById(R.id.pick_hero_4_repick),
            (Button) findViewById(R.id.pick_hero_5_repick),

            // Hero Checkboxes
            (CheckBox) findViewById(R.id.ban_hero_1_checkbox),
            (CheckBox) findViewById(R.id.ban_hero_2_checkbox),
            (CheckBox) findViewById(R.id.ban_hero_3_checkbox),
            (CheckBox) findViewById(R.id.ban_hero_4_checkbox),
            (CheckBox) findViewById(R.id.ban_hero_5_checkbox),
            (CheckBox) findViewById(R.id.pick_hero_1_checkbox),
            (CheckBox) findViewById(R.id.pick_hero_2_checkbox),
            (CheckBox) findViewById(R.id.pick_hero_3_checkbox),
            (CheckBox) findViewById(R.id.pick_hero_4_checkbox),
            (CheckBox) findViewById(R.id.pick_hero_5_checkbox),

            // Hero Images
            (ImageView) findViewById(R.id.ban_hero_1_picture),
            (ImageView) findViewById(R.id.ban_hero_2_picture),
            (ImageView) findViewById(R.id.ban_hero_3_picture),
            (ImageView) findViewById(R.id.ban_hero_4_picture),
            (ImageView) findViewById(R.id.ban_hero_5_picture),
            (ImageView) findViewById(R.id.pick_hero_1_picture),
            (ImageView) findViewById(R.id.pick_hero_2_picture),
            (ImageView) findViewById(R.id.pick_hero_3_picture),
            (ImageView) findViewById(R.id.pick_hero_4_picture),
            (ImageView) findViewById(R.id.pick_hero_5_picture)};

        for (View view : listeners) {
            view.setOnClickListener(this);
        }
    }

    public void onClick(View view) {
        Button button;
        Intent intent;

        switch (view.getId()) {
            case android.R.id.home:
                Utilities.parents.pop();
                Intent parentActivityIntent = new Intent(this, Utilities.parents.pop());
                parentActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(parentActivityIntent);
                finish();
                break;
            case R.id.ban_hero_1_repick:
                randomizeHero((TextView) findViewById(R.id.ban_hero_1_name),
                              (ImageView) findViewById(R.id.ban_hero_1_picture));
                break;
            case R.id.ban_hero_2_repick:
                randomizeHero((TextView) findViewById(R.id.ban_hero_2_name),
                              (ImageView) findViewById(R.id.ban_hero_2_picture));
                break;
            case R.id.ban_hero_3_repick:
                randomizeHero((TextView) findViewById(R.id.ban_hero_3_name),
                              (ImageView) findViewById(R.id.ban_hero_3_picture));
                break;
            case R.id.ban_hero_4_repick:
                randomizeHero((TextView) findViewById(R.id.ban_hero_4_name),
                              (ImageView) findViewById(R.id.ban_hero_4_picture));
                break;
            case R.id.ban_hero_5_repick:
                randomizeHero((TextView) findViewById(R.id.ban_hero_5_name),
                              (ImageView) findViewById(R.id.ban_hero_5_picture));
                break;
            case R.id.pick_hero_1_repick:
                randomizeHero((TextView) findViewById(R.id.pick_hero_1_name),
                              (ImageView) findViewById(R.id.pick_hero_1_picture));
                break;
            case R.id.pick_hero_2_repick:
                randomizeHero((TextView) findViewById(R.id.pick_hero_2_name),
                              (ImageView) findViewById(R.id.pick_hero_2_picture));
                break;
            case R.id.pick_hero_3_repick:
                randomizeHero((TextView) findViewById(R.id.pick_hero_3_name),
                              (ImageView) findViewById(R.id.pick_hero_3_picture));
                break;
            case R.id.pick_hero_4_repick:
                randomizeHero((TextView) findViewById(R.id.pick_hero_4_name),
                              (ImageView) findViewById(R.id.pick_hero_4_picture));
                break;
            case R.id.pick_hero_5_repick:
                randomizeHero((TextView) findViewById(R.id.pick_hero_5_name),
                              (ImageView) findViewById(R.id.pick_hero_5_picture));
                break;

            case R.id.ban_hero_1_checkbox:
                button = (Button) findViewById(R.id.ban_hero_1_repick);
                if (((CheckBox) findViewById(R.id.ban_hero_1_checkbox)).isChecked()) {
                    currentHeroes.add((findViewById(R.id.ban_hero_1_name)).toString());
                    button.setEnabled(false);
                } else {
                    currentHeroes.remove((findViewById(R.id.ban_hero_1_name)).toString());
                    button.setEnabled(true);
                }
                break;
            case R.id.ban_hero_2_checkbox:
                button = (Button) findViewById(R.id.ban_hero_2_repick);
                if (((CheckBox) findViewById(R.id.ban_hero_2_checkbox)).isChecked()) {
                    currentHeroes.add((findViewById(R.id.ban_hero_2_name)).toString());
                    button.setEnabled(false);
                } else {
                    currentHeroes.remove((findViewById(R.id.ban_hero_2_name)).toString());
                    button.setEnabled(true);
                }
                break;
            case R.id.ban_hero_3_checkbox:
                button = (Button) findViewById(R.id.ban_hero_3_repick);
                if (((CheckBox) findViewById(R.id.ban_hero_3_checkbox)).isChecked()) {
                    currentHeroes.add((findViewById(R.id.ban_hero_3_name)).toString());
                    button.setEnabled(false);
                } else {
                    currentHeroes.remove((findViewById(R.id.ban_hero_3_name)).toString());
                    button.setEnabled(true);
                }
                break;
            case R.id.ban_hero_4_checkbox:
                button = (Button) findViewById(R.id.ban_hero_4_repick);
                if (((CheckBox) findViewById(R.id.ban_hero_4_checkbox)).isChecked()) {
                    currentHeroes.add((findViewById(R.id.ban_hero_4_name)).toString());
                    button.setEnabled(false);
                } else {
                    currentHeroes.remove((findViewById(R.id.ban_hero_4_name)).toString());
                    button.setEnabled(true);
                }
                break;
            case R.id.ban_hero_5_checkbox:
                button = (Button) findViewById(R.id.ban_hero_5_repick);
                if (((CheckBox) findViewById(R.id.ban_hero_5_checkbox)).isChecked()) {
                    currentHeroes.add((findViewById(R.id.ban_hero_5_name)).toString());
                    button.setEnabled(false);
                } else {
                    currentHeroes.remove((findViewById(R.id.ban_hero_5_name)).toString());
                    button.setEnabled(true);
                }
                break;
            case R.id.pick_hero_1_checkbox:
                button = (Button) findViewById(R.id.pick_hero_1_repick);
                if (((CheckBox) findViewById(R.id.pick_hero_1_checkbox)).isChecked()) {
                    currentHeroes.add((findViewById(R.id.pick_hero_1_name)).toString());
                    button.setEnabled(false);
                } else {
                    currentHeroes.remove((findViewById(R.id.pick_hero_1_name)).toString());
                    button.setEnabled(true);
                }
                break;
            case R.id.pick_hero_2_checkbox:
                button = (Button) findViewById(R.id.pick_hero_2_repick);
                if (((CheckBox) findViewById(R.id.pick_hero_2_checkbox)).isChecked()) {
                    currentHeroes.add((findViewById(R.id.pick_hero_2_name)).toString());
                    button.setEnabled(false);
                } else {
                    currentHeroes.remove((findViewById(R.id.pick_hero_2_name)).toString());
                    button.setEnabled(true);
                }
                break;
            case R.id.pick_hero_3_checkbox:
                button = (Button) findViewById(R.id.pick_hero_3_repick);
                if (((CheckBox) findViewById(R.id.pick_hero_3_checkbox)).isChecked()) {
                    currentHeroes.add((findViewById(R.id.pick_hero_3_name)).toString());
                    button.setEnabled(false);
                } else {
                    currentHeroes.remove((findViewById(R.id.pick_hero_3_name)).toString());
                    button.setEnabled(true);
                }
                break;
            case R.id.pick_hero_4_checkbox:
                button = (Button) findViewById(R.id.pick_hero_4_repick);
                if (((CheckBox) findViewById(R.id.pick_hero_4_checkbox)).isChecked()) {
                    currentHeroes.add((findViewById(R.id.pick_hero_4_name)).toString());
                    button.setEnabled(false);
                } else {
                    currentHeroes.remove((findViewById(R.id.pick_hero_4_name)).toString());
                    button.setEnabled(true);
                }
                break;
            case R.id.pick_hero_5_checkbox:
                button = (Button) findViewById(R.id.pick_hero_5_repick);
                if (((CheckBox) findViewById(R.id.pick_hero_5_checkbox)).isChecked()) {
                    currentHeroes.add((findViewById(R.id.pick_hero_5_name)).toString());
                    button.setEnabled(false);
                } else {
                    currentHeroes.remove((findViewById(R.id.pick_hero_5_name)).toString());
                    button.setEnabled(true);
                }
                break;

            case R.id.ban_hero_1_picture:
                intent = new Intent(RandomCaptainsModeActivity.this, HeroActivity.class);
                intent.putExtra("hero", ((TextView) findViewById(R.id.ban_hero_1_name)).getText());
                RandomCaptainsModeActivity.this.startActivity(intent);
                break;
            case R.id.ban_hero_2_picture:
                intent = new Intent(RandomCaptainsModeActivity.this, HeroActivity.class);
                intent.putExtra("hero", ((TextView) findViewById(R.id.ban_hero_2_name)).getText());
                RandomCaptainsModeActivity.this.startActivity(intent);
                break;
            case R.id.ban_hero_3_picture:
                intent = new Intent(RandomCaptainsModeActivity.this, HeroActivity.class);
                intent.putExtra("hero", ((TextView) findViewById(R.id.ban_hero_3_name)).getText());
                RandomCaptainsModeActivity.this.startActivity(intent);
                break;
            case R.id.ban_hero_4_picture:
                intent = new Intent(RandomCaptainsModeActivity.this, HeroActivity.class);
                intent.putExtra("hero", ((TextView) findViewById(R.id.ban_hero_4_name)).getText());
                RandomCaptainsModeActivity.this.startActivity(intent);
                break;
            case R.id.ban_hero_5_picture:
                intent = new Intent(RandomCaptainsModeActivity.this, HeroActivity.class);
                intent.putExtra("hero", ((TextView) findViewById(R.id.ban_hero_5_name)).getText());
                RandomCaptainsModeActivity.this.startActivity(intent);
                break;
            case R.id.pick_hero_1_picture:
                intent = new Intent(RandomCaptainsModeActivity.this, HeroActivity.class);
                intent.putExtra("hero", ((TextView) findViewById(R.id.pick_hero_1_name)).getText());
                RandomCaptainsModeActivity.this.startActivity(intent);
                break;
            case R.id.pick_hero_2_picture:
                intent = new Intent(RandomCaptainsModeActivity.this, HeroActivity.class);
                intent.putExtra("hero", ((TextView) findViewById(R.id.pick_hero_2_name)).getText());
                RandomCaptainsModeActivity.this.startActivity(intent);
                break;
            case R.id.pick_hero_3_picture:
                intent = new Intent(RandomCaptainsModeActivity.this, HeroActivity.class);
                intent.putExtra("hero", ((TextView) findViewById(R.id.pick_hero_3_name)).getText());
                RandomCaptainsModeActivity.this.startActivity(intent);
                break;
            case R.id.pick_hero_4_picture:
                intent = new Intent(RandomCaptainsModeActivity.this, HeroActivity.class);
                intent.putExtra("hero", ((TextView) findViewById(R.id.pick_hero_4_name)).getText());
                RandomCaptainsModeActivity.this.startActivity(intent);
                break;
            case R.id.pick_hero_5_picture:
                intent = new Intent(RandomCaptainsModeActivity.this, HeroActivity.class);
                intent.putExtra("hero", ((TextView) findViewById(R.id.pick_hero_5_name)).getText());
                RandomCaptainsModeActivity.this.startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.random_captains_mode, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_refresh:
                randomizeHeroes();
                return true;
            case R.id.deselect_all:
                ((CheckBox) findViewById(R.id.ban_hero_1_checkbox)).setChecked(false);
                ((CheckBox) findViewById(R.id.ban_hero_2_checkbox)).setChecked(false);
                ((CheckBox) findViewById(R.id.ban_hero_3_checkbox)).setChecked(false);
                ((CheckBox) findViewById(R.id.ban_hero_4_checkbox)).setChecked(false);
                ((CheckBox) findViewById(R.id.ban_hero_5_checkbox)).setChecked(false);
                ((CheckBox) findViewById(R.id.pick_hero_1_checkbox)).setChecked(false);
                ((CheckBox) findViewById(R.id.pick_hero_2_checkbox)).setChecked(false);
                ((CheckBox) findViewById(R.id.pick_hero_3_checkbox)).setChecked(false);
                ((CheckBox) findViewById(R.id.pick_hero_4_checkbox)).setChecked(false);
                ((CheckBox) findViewById(R.id.pick_hero_5_checkbox)).setChecked(false);
                findViewById(R.id.ban_hero_1_repick).setEnabled(true);
                findViewById(R.id.ban_hero_2_repick).setEnabled(true);
                findViewById(R.id.ban_hero_3_repick).setEnabled(true);
                findViewById(R.id.ban_hero_4_repick).setEnabled(true);
                findViewById(R.id.ban_hero_5_repick).setEnabled(true);
                findViewById(R.id.pick_hero_1_repick).setEnabled(true);
                findViewById(R.id.pick_hero_2_repick).setEnabled(true);
                findViewById(R.id.pick_hero_3_repick).setEnabled(true);
                findViewById(R.id.pick_hero_4_repick).setEnabled(true);
                findViewById(R.id.pick_hero_5_repick).setEnabled(true);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Utilities.parents.pop();
        finish();
    }

    public void randomizeHeroes() {
        TextView heroName;

        refillHeroesList();

        if (!((CheckBox) findViewById(R.id.ban_hero_1_checkbox)).isChecked()) {
            heroName = (TextView) findViewById(R.id.ban_hero_1_name);

            if (heroName.getText() != null) {
                currentHeroes.remove(String.valueOf(heroName.getText()));
            }

            heroName.setText(allHeroes.remove(random.nextInt(allHeroes.size())));

            ((ImageView) findViewById(R.id.ban_hero_1_picture)).setImageResource(getResources().getIdentifier(
                Utilities.nameToResource("hero", heroName), "drawable", getApplicationContext().getPackageName()));

            currentHeroes.add(String.valueOf(heroName.getText()));
        }

        if (!((CheckBox) findViewById(R.id.ban_hero_2_checkbox)).isChecked()) {
            heroName = (TextView) findViewById(R.id.ban_hero_2_name);

            if (heroName.getText() != null) {
                currentHeroes.remove(String.valueOf(heroName.getText()));
            }

            heroName.setText(allHeroes.remove(random.nextInt(allHeroes.size())));

            ((ImageView) findViewById(R.id.ban_hero_2_picture)).setImageResource(getResources().getIdentifier(
                Utilities.nameToResource("hero", heroName), "drawable", getApplicationContext().getPackageName()));

            currentHeroes.add(String.valueOf(heroName.getText()));
        }

        if (!((CheckBox) findViewById(R.id.ban_hero_3_checkbox)).isChecked()) {
            heroName = (TextView) findViewById(R.id.ban_hero_3_name);

            if (heroName.getText() != null) {
                currentHeroes.remove(String.valueOf(heroName.getText()));
            }

            heroName.setText(allHeroes.remove(random.nextInt(allHeroes.size())));

            ((ImageView) findViewById(R.id.ban_hero_3_picture)).setImageResource(getResources().getIdentifier(
                Utilities.nameToResource("hero", heroName), "drawable", getApplicationContext().getPackageName()));

            currentHeroes.add(String.valueOf(heroName.getText()));
        }

        if (!((CheckBox) findViewById(R.id.ban_hero_4_checkbox)).isChecked()) {
            heroName = (TextView) findViewById(R.id.ban_hero_4_name);

            if (heroName.getText() != null) {
                currentHeroes.remove(String.valueOf(heroName.getText()));
            }

            heroName.setText(allHeroes.remove(random.nextInt(allHeroes.size())));

            ((ImageView) findViewById(R.id.ban_hero_4_picture)).setImageResource(getResources().getIdentifier(
                Utilities.nameToResource("hero", heroName), "drawable", getApplicationContext().getPackageName()));

            currentHeroes.add(String.valueOf(heroName.getText()));
        }

        if (!((CheckBox) findViewById(R.id.ban_hero_5_checkbox)).isChecked()) {
            heroName = (TextView) findViewById(R.id.ban_hero_5_name);

            if (heroName.getText() != null) {
                currentHeroes.remove(String.valueOf(heroName.getText()));
            }

            heroName.setText(allHeroes.remove(random.nextInt(allHeroes.size())));

            ((ImageView) findViewById(R.id.ban_hero_5_picture)).setImageResource(getResources().getIdentifier(
                Utilities.nameToResource("hero", heroName), "drawable", getApplicationContext().getPackageName()));

            currentHeroes.add(String.valueOf(heroName.getText()));
        }

        if (!((CheckBox) findViewById(R.id.pick_hero_1_checkbox)).isChecked()) {
            heroName = (TextView) findViewById(R.id.pick_hero_1_name);

            if (heroName.getText() != null) {
                currentHeroes.remove(String.valueOf(heroName.getText()));
            }

            heroName.setText(allHeroes.remove(random.nextInt(allHeroes.size())));

            ((ImageView) findViewById(R.id.pick_hero_1_picture)).setImageResource(getResources().getIdentifier(
                Utilities.nameToResource("hero", heroName), "drawable", getApplicationContext().getPackageName()));

            currentHeroes.add(String.valueOf(heroName.getText()));
        }

        if (!((CheckBox) findViewById(R.id.pick_hero_2_checkbox)).isChecked()) {
            heroName = (TextView) findViewById(R.id.pick_hero_2_name);

            if (heroName.getText() != null) {
                currentHeroes.remove(String.valueOf(heroName.getText()));
            }

            heroName.setText(allHeroes.remove(random.nextInt(allHeroes.size())));

            ((ImageView) findViewById(R.id.pick_hero_2_picture)).setImageResource(getResources().getIdentifier(
                Utilities.nameToResource("hero", heroName), "drawable", getApplicationContext().getPackageName()));

            currentHeroes.add(String.valueOf(heroName.getText()));
        }

        if (!((CheckBox) findViewById(R.id.pick_hero_3_checkbox)).isChecked()) {
            heroName = (TextView) findViewById(R.id.pick_hero_3_name);

            if (heroName.getText() != null) {
                currentHeroes.remove(String.valueOf(heroName.getText()));
            }

            heroName.setText(allHeroes.remove(random.nextInt(allHeroes.size())));

            ((ImageView) findViewById(R.id.pick_hero_3_picture)).setImageResource(getResources().getIdentifier(
                Utilities.nameToResource("hero", heroName), "drawable", getApplicationContext().getPackageName()));

            currentHeroes.add(String.valueOf(heroName.getText()));
        }

        if (!((CheckBox) findViewById(R.id.pick_hero_4_checkbox)).isChecked()) {
            heroName = (TextView) findViewById(R.id.pick_hero_4_name);

            if (heroName.getText() != null) {
                currentHeroes.remove(String.valueOf(heroName.getText()));
            }

            heroName.setText(allHeroes.remove(random.nextInt(allHeroes.size())));

            ((ImageView) findViewById(R.id.pick_hero_4_picture)).setImageResource(getResources().getIdentifier(
                Utilities.nameToResource("hero", heroName), "drawable", getApplicationContext().getPackageName()));

            currentHeroes.add(String.valueOf(heroName.getText()));
        }

        if (!((CheckBox) findViewById(R.id.pick_hero_5_checkbox)).isChecked()) {
            heroName = (TextView) findViewById(R.id.pick_hero_5_name);

            if (heroName.getText() != null) {
                currentHeroes.remove(String.valueOf(heroName.getText()));
            }

            heroName.setText(allHeroes.remove(random.nextInt(allHeroes.size())));

            ((ImageView) findViewById(R.id.pick_hero_5_picture)).setImageResource(getResources().getIdentifier(
                Utilities.nameToResource("hero", heroName), "drawable", getApplicationContext().getPackageName()));

            currentHeroes.add(String.valueOf(heroName.getText()));
        }
    }

    public void randomizeHero(TextView heroName, ImageView heroImage) {
        allHeroes.add(String.valueOf(heroName.getText()));

        if (heroName.getText() != null) {
            currentHeroes.remove(String.valueOf(heroName.getText()));
        }

        heroName.setText(allHeroes.remove(random.nextInt(allHeroes.size())));
        heroImage.setImageResource(getResources().getIdentifier(Utilities.nameToResource("hero", heroName), "drawable",
                getApplicationContext().getPackageName()));

        currentHeroes.add(String.valueOf(heroName.getText()));
    }

    public void refillHeroesList() {
        allHeroes = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.heroes_all)));

        for (String hero : getResources().getStringArray(R.array.heroes_removed)) {
            allHeroes.remove(hero);
        }

        for (String hero : currentHeroes) {
            allHeroes.remove(hero);
        }
    }
}