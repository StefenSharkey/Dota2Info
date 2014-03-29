package com.dirtypepper.dota2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;

public class HeroListActivity extends Activity implements GridView.OnItemClickListener
{
    private final int HERO_WIDTH = 154;

    private ArrayList<String> heroesList;

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_list);
        Utilities.parents.push(getClass());

        heroesList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.heroes_all)));

        gridView = (GridView)findViewById(R.id.grid_view);
        gridView.setAdapter(new GridAdapter(getApplicationContext()));

        gridView.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
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
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
    {
        Log.wtf("view", String.valueOf(position));

        Intent intent = new Intent(HeroListActivity.this, HeroActivity.class);
        intent.putExtra("hero", heroesList.get(position));
        HeroListActivity.this.startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {
        Utilities.parents.pop();
        finish();
    }
}
