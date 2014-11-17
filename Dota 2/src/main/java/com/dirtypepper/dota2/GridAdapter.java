package com.dirtypepper.dota2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {

    private final Context context;

    private final int[] images;
    private final String[] names;

    private final LayoutInflater inflater;

    public GridAdapter(Context context) {
        this.context = context;
        names = context.getResources().getStringArray(R.array.heroes_all);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for (String s : names) {
            Log.d("Hero", s);
        }

        images = new int[names.length];
        getHeroDrawables();
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int x) {
        return images[x];
    }

    @Override
    public long getItemId(int x) {
        return 0;
    }

    @Override
    public View getView(int x, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.grid_adapter, null);

            viewHolder.image = (ImageView) view.findViewById(R.id.grid_picture);
            viewHolder.name = (TextView) view.findViewById(R.id.grid_text);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.image.setImageResource(images[x]);
        viewHolder.name.setText(names[x]);

        return view;
    }

    public void getHeroDrawables() {
        for (int x = 0; x < images.length; x++) {
            images[x] = context.getResources().getIdentifier(Utilities.nameToResource("hero", names[x]), "drawable", context.getPackageName());
        }
    }

    static class ViewHolder {
        public ImageView image;
        public TextView name;
    }
}
