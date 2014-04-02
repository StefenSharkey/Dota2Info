package com.dirtypepper.dota2;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class GridAdapter extends BaseAdapter
{
    private Context context;

    private Integer[] images;
    private ArrayList<String> names;

    public GridAdapter(Context context)
    {
        this.context = context;
        names = new ArrayList<String>(Arrays.asList(context.getResources().getStringArray(R.array.heroes_all)));
        for(String s : names)
            Log.wtf("Hero", s);
        images = new Integer[names.size()];
        getHeroDrawables();
    }

    @Override
    public int getCount()
    {
        return images.length;
    }

    @Override
    public Object getItem(int x)
    {
        return images[x];
    }

    @Override
    public long getItemId(int x)
    {
        return 0;
    }

    @Override
    public View getView(int x, View view, ViewGroup viewGroup)
    {
        LinearLayout linearLayout = new LinearLayout(context);
        ImageView imageView = new ImageView(context);
        TextView textView = new TextView(context);

        linearLayout.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, GridView.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(0, Utilities.dpToPx(8, context.getResources()), 0, Utilities.dpToPx(2, context.getResources()));

        imageView.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, GridView.LayoutParams.WRAP_CONTENT));
        imageView.setImageResource(images[x]);
        imageView.setScaleType(ImageView.ScaleType.CENTER);

        textView.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, GridView.LayoutParams.WRAP_CONTENT));
        textView.setText(names.get(x));
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        textView.setTextAppearance(context, R.style.TextColor);

        linearLayout.addView(imageView);
        linearLayout.addView(textView);

        return linearLayout;
    }

    public void getHeroDrawables()
    {
        for(int x = 0; x < images.length; x++)
//            images[x] = context.getResources().getIdentifier(Utilities.nameToDrawable("hero", names.get(x), "small"), "drawable", context.getPackageName());
            images[x] = context.getResources().getIdentifier(Utilities.nameToDrawable("hero", names.get(x)), "drawable", context.getPackageName());
    }
}
