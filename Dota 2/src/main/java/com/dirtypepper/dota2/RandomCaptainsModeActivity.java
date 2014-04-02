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

public class RandomCaptainsModeActivity extends Activity implements View.OnClickListener
{
    private ArrayList<String> heroesList;
    private ArrayList<String> removedHeroesList;
    private Random random;

    private Button button;
    private Intent intent;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_captains_mode);

        Utilities.parents.push(getClass());

        random = new Random();
        refillHeroesList();

        Log.wtf("Heroes Amount", String.valueOf(heroesList.size()));

        randomizeHeroes();

        Button[] buttonListeners = {
                (Button)findViewById(R.id.ban_hero_1_repick),
                (Button)findViewById(R.id.ban_hero_2_repick),
                (Button)findViewById(R.id.ban_hero_3_repick),
                (Button)findViewById(R.id.ban_hero_4_repick),
                (Button)findViewById(R.id.ban_hero_5_repick),
                (Button)findViewById(R.id.pick_hero_1_repick),
                (Button)findViewById(R.id.pick_hero_2_repick),
                (Button)findViewById(R.id.pick_hero_3_repick),
                (Button)findViewById(R.id.pick_hero_4_repick),
                (Button)findViewById(R.id.pick_hero_5_repick)};
        for(Button button : buttonListeners)
            button.setOnClickListener(this);

        CheckBox[] checkBoxListeners = {
                (CheckBox)findViewById(R.id.ban_hero_1_checkbox),
                (CheckBox)findViewById(R.id.ban_hero_2_checkbox),
                (CheckBox)findViewById(R.id.ban_hero_3_checkbox),
                (CheckBox)findViewById(R.id.ban_hero_4_checkbox),
                (CheckBox)findViewById(R.id.ban_hero_5_checkbox),
                (CheckBox)findViewById(R.id.pick_hero_1_checkbox),
                (CheckBox)findViewById(R.id.pick_hero_2_checkbox),
                (CheckBox)findViewById(R.id.pick_hero_3_checkbox),
                (CheckBox)findViewById(R.id.pick_hero_4_checkbox),
                (CheckBox)findViewById(R.id.pick_hero_5_checkbox)};
        for(CheckBox checkBox : checkBoxListeners)
            checkBox.setOnClickListener(this);

        ImageView[] imageViewListeners = {
                (ImageView)findViewById(R.id.ban_hero_1_picture),
                (ImageView)findViewById(R.id.ban_hero_2_picture),
                (ImageView)findViewById(R.id.ban_hero_3_picture),
                (ImageView)findViewById(R.id.ban_hero_4_picture),
                (ImageView)findViewById(R.id.ban_hero_5_picture),
                (ImageView)findViewById(R.id.pick_hero_1_picture),
                (ImageView)findViewById(R.id.pick_hero_2_picture),
                (ImageView)findViewById(R.id.pick_hero_3_picture),
                (ImageView)findViewById(R.id.pick_hero_4_picture),
                (ImageView)findViewById(R.id.pick_hero_5_picture),
        };
        for(ImageView imageView : imageViewListeners)
            imageView.setOnClickListener(this);
    }

    public void onClick(View view)
    {
        switch(view.getId())
        {
            case android.R.id.home:
                Utilities.parents.pop();
                Intent parentActivityIntent = new Intent(this, Utilities.parents.pop());
                parentActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(parentActivityIntent);
                finish();
                break;
            case R.id.ban_hero_1_repick:
                randomizeHeroes((TextView)findViewById(R.id.ban_hero_1_name), (ImageView)findViewById(R.id.ban_hero_1_picture));
                break;
            case R.id.ban_hero_2_repick:
                randomizeHeroes((TextView)findViewById(R.id.ban_hero_2_name), (ImageView)findViewById(R.id.ban_hero_2_picture));
                break;
            case R.id.ban_hero_3_repick:
                randomizeHeroes((TextView)findViewById(R.id.ban_hero_3_name), (ImageView)findViewById(R.id.ban_hero_3_picture));
                break;
            case R.id.ban_hero_4_repick:
                randomizeHeroes((TextView)findViewById(R.id.ban_hero_4_name), (ImageView)findViewById(R.id.ban_hero_4_picture));
                break;
            case R.id.ban_hero_5_repick:
                randomizeHeroes((TextView)findViewById(R.id.ban_hero_5_name), (ImageView)findViewById(R.id.ban_hero_5_picture));
                break;
            case R.id.pick_hero_1_repick:
                randomizeHeroes((TextView)findViewById(R.id.pick_hero_1_name), (ImageView)findViewById(R.id.pick_hero_1_picture));
                break;
            case R.id.pick_hero_2_repick:
                randomizeHeroes((TextView)findViewById(R.id.pick_hero_2_name), (ImageView)findViewById(R.id.pick_hero_2_picture));
                break;
            case R.id.pick_hero_3_repick:
                randomizeHeroes((TextView)findViewById(R.id.pick_hero_3_name), (ImageView)findViewById(R.id.pick_hero_3_picture));
                break;
            case R.id.pick_hero_4_repick:
                randomizeHeroes((TextView)findViewById(R.id.pick_hero_4_name), (ImageView)findViewById(R.id.pick_hero_4_picture));
                break;
            case R.id.pick_hero_5_repick:
                randomizeHeroes((TextView)findViewById(R.id.pick_hero_5_name), (ImageView)findViewById(R.id.pick_hero_5_picture));
                break;

            case R.id.ban_hero_1_checkbox:
                button = (Button)findViewById(R.id.ban_hero_1_repick);
                if(((CheckBox)findViewById(R.id.ban_hero_1_checkbox)).isChecked())
                    button.setEnabled(false);
                else
                    button.setEnabled(true);
                break;
            case R.id.ban_hero_2_checkbox:
                button = (Button)findViewById(R.id.ban_hero_2_repick);
                if(((CheckBox)findViewById(R.id.ban_hero_2_checkbox)).isChecked())
                    button.setEnabled(false);
                else
                    button.setEnabled(true);
                break;
            case R.id.ban_hero_3_checkbox:
                button = (Button)findViewById(R.id.ban_hero_3_repick);
                if(((CheckBox)findViewById(R.id.ban_hero_3_checkbox)).isChecked())
                    button.setEnabled(false);
                else
                    button.setEnabled(true);
                break;
            case R.id.ban_hero_4_checkbox:
                button = (Button)findViewById(R.id.ban_hero_4_repick);
                if(((CheckBox)findViewById(R.id.ban_hero_4_checkbox)).isChecked())
                    button.setEnabled(false);
                else
                    button.setEnabled(true);
                break;
            case R.id.ban_hero_5_checkbox:
                button = (Button)findViewById(R.id.ban_hero_5_repick);
                if(((CheckBox)findViewById(R.id.ban_hero_5_checkbox)).isChecked())
                    button.setEnabled(false);
                else
                    button.setEnabled(true);
                break;
            case R.id.pick_hero_1_checkbox:
                button = (Button)findViewById(R.id.pick_hero_1_repick);
                if(((CheckBox)findViewById(R.id.pick_hero_1_checkbox)).isChecked())
                    button.setEnabled(false);
                else
                    button.setEnabled(true);
                break;
            case R.id.pick_hero_2_checkbox:
                button = (Button)findViewById(R.id.pick_hero_2_repick);
                if(((CheckBox)findViewById(R.id.pick_hero_2_checkbox)).isChecked())
                    button.setEnabled(false);
                else
                    button.setEnabled(true);
                break;
            case R.id.pick_hero_3_checkbox:
                button = (Button)findViewById(R.id.pick_hero_3_repick);
                if(((CheckBox)findViewById(R.id.pick_hero_3_checkbox)).isChecked())
                    button.setEnabled(false);
                else
                    button.setEnabled(true);
                break;
            case R.id.pick_hero_4_checkbox:
                button = (Button)findViewById(R.id.pick_hero_4_repick);
                if(((CheckBox)findViewById(R.id.pick_hero_4_checkbox)).isChecked())
                    button.setEnabled(false);
                else
                    button.setEnabled(true);
                break;
            case R.id.pick_hero_5_checkbox:
                button = (Button)findViewById(R.id.pick_hero_5_repick);
                if(((CheckBox)findViewById(R.id.pick_hero_5_checkbox)).isChecked())
                    button.setEnabled(false);
                else
                    button.setEnabled(true);
                break;

            case R.id.ban_hero_1_picture:
                intent = new Intent(RandomCaptainsModeActivity.this, HeroActivity.class);
                intent.putExtra("hero", ((TextView)findViewById(R.id.ban_hero_1_name)).getText());
                RandomCaptainsModeActivity.this.startActivity(intent);
                break;
            case R.id.ban_hero_2_picture:
                intent = new Intent(RandomCaptainsModeActivity.this, HeroActivity.class);
                intent.putExtra("hero", ((TextView)findViewById(R.id.ban_hero_2_name)).getText());
                RandomCaptainsModeActivity.this.startActivity(intent);
                break;
            case R.id.ban_hero_3_picture:
                intent = new Intent(RandomCaptainsModeActivity.this, HeroActivity.class);
                intent.putExtra("hero", ((TextView)findViewById(R.id.ban_hero_3_name)).getText());
                RandomCaptainsModeActivity.this.startActivity(intent);
                break;
            case R.id.ban_hero_4_picture:
                intent = new Intent(RandomCaptainsModeActivity.this, HeroActivity.class);
                intent.putExtra("hero", ((TextView)findViewById(R.id.ban_hero_4_name)).getText());
                RandomCaptainsModeActivity.this.startActivity(intent);
                break;
            case R.id.ban_hero_5_picture:
                intent = new Intent(RandomCaptainsModeActivity.this, HeroActivity.class);
                intent.putExtra("hero", ((TextView)findViewById(R.id.ban_hero_5_name)).getText());
                RandomCaptainsModeActivity.this.startActivity(intent);
                break;
            case R.id.pick_hero_1_picture:
                intent = new Intent(RandomCaptainsModeActivity.this, HeroActivity.class);
                intent.putExtra("hero", ((TextView)findViewById(R.id.pick_hero_1_name)).getText());
                RandomCaptainsModeActivity.this.startActivity(intent);
                break;
            case R.id.pick_hero_2_picture:
                intent = new Intent(RandomCaptainsModeActivity.this, HeroActivity.class);
                intent.putExtra("hero", ((TextView)findViewById(R.id.pick_hero_2_name)).getText());
                RandomCaptainsModeActivity.this.startActivity(intent);
                break;
            case R.id.pick_hero_3_picture:
                intent = new Intent(RandomCaptainsModeActivity.this, HeroActivity.class);
                intent.putExtra("hero", ((TextView)findViewById(R.id.pick_hero_3_name)).getText());
                RandomCaptainsModeActivity.this.startActivity(intent);
                break;
            case R.id.pick_hero_4_picture:
                intent = new Intent(RandomCaptainsModeActivity.this, HeroActivity.class);
                intent.putExtra("hero", ((TextView)findViewById(R.id.pick_hero_4_name)).getText());
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
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.random_captains_mode, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        switch(id)
        {
            case R.id.action_refresh:
                randomizeHeroes();
                return true;
            case R.id.deselect_all:
                ((CheckBox)findViewById(R.id.ban_hero_1_checkbox)).setChecked(false);
                ((CheckBox)findViewById(R.id.ban_hero_2_checkbox)).setChecked(false);
                ((CheckBox)findViewById(R.id.ban_hero_3_checkbox)).setChecked(false);
                ((CheckBox)findViewById(R.id.ban_hero_4_checkbox)).setChecked(false);
                ((CheckBox)findViewById(R.id.ban_hero_5_checkbox)).setChecked(false);
                ((CheckBox)findViewById(R.id.pick_hero_1_checkbox)).setChecked(false);
                ((CheckBox)findViewById(R.id.pick_hero_2_checkbox)).setChecked(false);
                ((CheckBox)findViewById(R.id.pick_hero_3_checkbox)).setChecked(false);
                ((CheckBox)findViewById(R.id.pick_hero_4_checkbox)).setChecked(false);
                ((CheckBox)findViewById(R.id.pick_hero_5_checkbox)).setChecked(false);
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
    public void onBackPressed()
    {
        Utilities.parents.pop();
        finish();
    }

    public void randomizeHeroes()
    {
        refillHeroesList();

//        Log.wtf("TextView Contents", String.valueOf(textView.getText()));

        if(!((CheckBox)findViewById(R.id.ban_hero_1_checkbox)).isChecked())
        {
            textView = (TextView)findViewById(R.id.ban_hero_1_name);
            textView.setText(heroesList.remove(random.nextInt(heroesList.size())));
            ((ImageView)findViewById(R.id.ban_hero_1_picture)).setImageResource(getResources().getIdentifier(Utilities.nameToDrawable("hero", textView), "drawable", getApplicationContext().getPackageName()));
        }

        if(!((CheckBox)findViewById(R.id.ban_hero_2_checkbox)).isChecked())
        {
            textView = (TextView)findViewById(R.id.ban_hero_2_name);
            textView.setText(heroesList.remove(random.nextInt(heroesList.size())));
            ((ImageView)findViewById(R.id.ban_hero_2_picture)).setImageResource(getResources().getIdentifier(Utilities.nameToDrawable("hero", textView), "drawable", getApplicationContext().getPackageName()));
        }

        if(!((CheckBox)findViewById(R.id.ban_hero_3_checkbox)).isChecked())
        {
            textView = (TextView)findViewById(R.id.ban_hero_3_name);
            textView.setText(heroesList.remove(random.nextInt(heroesList.size())));
            ((ImageView)findViewById(R.id.ban_hero_3_picture)).setImageResource(getResources().getIdentifier(Utilities.nameToDrawable("hero", textView), "drawable", getApplicationContext().getPackageName()));
        }

        if(!((CheckBox)findViewById(R.id.ban_hero_4_checkbox)).isChecked())
        {
            textView = (TextView)findViewById(R.id.ban_hero_4_name);
            textView.setText(heroesList.remove(random.nextInt(heroesList.size())));
            ((ImageView)findViewById(R.id.ban_hero_4_picture)).setImageResource(getResources().getIdentifier(Utilities.nameToDrawable("hero", textView), "drawable", getApplicationContext().getPackageName()));
        }

        if(!((CheckBox)findViewById(R.id.ban_hero_5_checkbox)).isChecked())
        {
            textView = (TextView)findViewById(R.id.ban_hero_5_name);
            textView.setText(heroesList.remove(random.nextInt(heroesList.size())));
            ((ImageView)findViewById(R.id.ban_hero_5_picture)).setImageResource(getResources().getIdentifier(Utilities.nameToDrawable("hero", textView), "drawable", getApplicationContext().getPackageName()));
        }

        if(!((CheckBox)findViewById(R.id.pick_hero_1_checkbox)).isChecked())
        {
            textView = (TextView)findViewById(R.id.pick_hero_1_name);
            textView.setText(heroesList.remove(random.nextInt(heroesList.size())));
            ((ImageView)findViewById(R.id.pick_hero_1_picture)).setImageResource(getResources().getIdentifier(Utilities.nameToDrawable("hero", textView), "drawable", getApplicationContext().getPackageName()));
        }

        if(!((CheckBox)findViewById(R.id.pick_hero_2_checkbox)).isChecked())
        {
            textView = (TextView)findViewById(R.id.pick_hero_2_name);
            textView.setText(heroesList.remove(random.nextInt(heroesList.size())));
            ((ImageView)findViewById(R.id.pick_hero_2_picture)).setImageResource(getResources().getIdentifier(Utilities.nameToDrawable("hero", textView), "drawable", getApplicationContext().getPackageName()));
        }

        if(!((CheckBox)findViewById(R.id.pick_hero_3_checkbox)).isChecked())
        {
            textView = (TextView)findViewById(R.id.pick_hero_3_name);
            textView.setText(heroesList.remove(random.nextInt(heroesList.size())));
            ((ImageView)findViewById(R.id.pick_hero_3_picture)).setImageResource(getResources().getIdentifier(Utilities.nameToDrawable("hero", textView), "drawable", getApplicationContext().getPackageName()));
        }

        if(!((CheckBox)findViewById(R.id.pick_hero_4_checkbox)).isChecked())
        {
            textView = (TextView)findViewById(R.id.pick_hero_4_name);
            textView.setText(heroesList.remove(random.nextInt(heroesList.size())));
            ((ImageView)findViewById(R.id.pick_hero_4_picture)).setImageResource(getResources().getIdentifier(Utilities.nameToDrawable("hero", textView), "drawable", getApplicationContext().getPackageName()));
        }

        if(!((CheckBox)findViewById(R.id.pick_hero_5_checkbox)).isChecked())
        {
            textView = (TextView)findViewById(R.id.pick_hero_5_name);
            textView.setText(heroesList.remove(random.nextInt(heroesList.size())));
            ((ImageView)findViewById(R.id.pick_hero_5_picture)).setImageResource(getResources().getIdentifier(Utilities.nameToDrawable("hero", textView), "drawable", getApplicationContext().getPackageName()));
        }
    }

    public void randomizeHeroes(TextView textView, ImageView imageView)
    {
        heroesList.add(String.valueOf(textView.getText()));
        textView.setText(heroesList.remove(random.nextInt(heroesList.size())));
        imageView.setImageResource(getResources().getIdentifier(Utilities.nameToDrawable("hero", textView), "drawable", getApplicationContext().getPackageName()));
    }

    public void refillHeroesList()
    {
        heroesList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.heroes_all)));
        removedHeroesList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.heroes_removed)));
        for(String s : removedHeroesList)
            heroesList.remove(s);
    }
}