package com.dirtypepper.dota2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements View.OnClickListener
{
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utilities.parents.push(getClass());

        LinearLayout heroList = (LinearLayout)findViewById(R.id.heroes);
        heroList.setOnClickListener(this);

        LinearLayout randomCaptainsMode = (LinearLayout)findViewById(R.id.hero_random_captains_mode);
        randomCaptainsMode.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        return true;
    }

    @Override
    public void onClick(View view)
    {
        Log.wtf("view.getId()", view.toString());
        switch(view.getId())
        {
            case R.id.heroes:
                intent = new Intent(MainActivity.this, HeroListActivity.class);
                break;
            case R.id.hero_random_captains_mode:
                intent = new Intent(MainActivity.this, RandomCaptainsModeActivity.class);
                break;
        }
        MainActivity.this.startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {
        Utilities.parents.pop();
        finish();
    }
}
