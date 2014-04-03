package com.dirtypepper.dota2;

import android.content.res.Resources;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Stack;

public class Utilities
{
    public static Stack<Class<?>> parents = new Stack<Class<?>>();

    public static String nameToDrawable(String name)
    {
        return name.replaceAll(" ", "_").replaceAll("-", "_").replaceAll("\'", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\?", "").replaceAll("\\!", "").toLowerCase();
    }

    public static String nameToDrawable(TextView name)
    {
        return String.valueOf(name.getText()).replaceAll(" ", "_").replaceAll("-", "_").replaceAll("\'", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\?", "").replaceAll("\\!", "").toLowerCase();
    }

    public static String nameToDrawable(String prefix, String name)
    {
        return prefix.replaceAll(" ", "_").replaceAll("-", "_").replaceAll("\'", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\?", "").replaceAll("\\!", "").toLowerCase() + "_" + name.replaceAll(" ", "_").replaceAll("-", "_").replaceAll("\'", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\?", "").replaceAll("\\!", "").toLowerCase();
    }

    public static String nameToDrawable(String prefix, TextView name)
    {
        return prefix.replaceAll(" ", "_").replaceAll("-", "_").replaceAll("\'", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\?", "").replaceAll("\\!", "").toLowerCase() + "_" + String.valueOf(name.getText()).replaceAll(" ", "_").replaceAll("-", "_").replaceAll("\'", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\?", "").replaceAll("\\!", "").toLowerCase();
    }

    public static String nameToDrawable(String prefix, String name, String suffix)
    {
        return prefix.replaceAll(" ", "_").replaceAll("-", "_").replaceAll("\'", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\?", "").replaceAll("\\!", "").toLowerCase() + "_" + name.replaceAll(" ", "_").replaceAll("-", "_").replaceAll("\'", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\?", "").replaceAll("\\!", "").toLowerCase() + "_" + suffix;
    }

    public static String nameToDrawable(String prefix, TextView name, String suffix)
    {
        return prefix.replaceAll(" ", "_").replaceAll("-", "_").replaceAll("\'", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\?", "").replaceAll("\\!", "").toLowerCase() + "_" + String.valueOf(name.getText()).replaceAll(" ", "_").replaceAll("-", "_").replaceAll("\'", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\?", "").replaceAll("\\!", "").toLowerCase() + "_" + suffix;
    }

    public static String nameToWebsite(String name)
    {
        return name.replaceAll(" ", "_").replaceAll("\'", "%27");
    }

    public static String nameToWebsite(TextView name)
    {
        return String.valueOf(name.getText()).replaceAll(" ", "_").replaceAll("\'", "%27").replaceAll("\\(", "").replaceAll("\\)", "");
    }

    public static int dpToPx(int dp, Resources resources)
    {
        final float scale = resources.getDisplayMetrics().density;
        return (int)(dp * scale + 0.5F);
    }

    public static String formatDamage(ArrayList damage)
    {
        return damage.get(0).toString().substring(0, damage.get(0).toString().lastIndexOf(".")) + " - " + damage.get(1).toString().substring(0, damage.get(1).toString().lastIndexOf("."));
    }

    public static String formatRoles(Hero hero)
    {
        String heroRoles = "";
        for(String s : hero.getRoles())
            heroRoles += " - " + s;
        return hero.getDamageType() + heroRoles;
    }

    public static String formatSpeed(String speed)
    {
        return speed.substring(0, speed.lastIndexOf("."));
    }

    public static String formatStats(ArrayList<Double> stats)
    {
        return stats.get(0).toString().substring(0, stats.get(0).toString().lastIndexOf(".")) + " + " + new DecimalFormat("0.00").format(stats.get(1));
    }

    public static void printStack()
    {
        for(Class c : parents)
        {
            Log.wtf("c.getName()", c.getName());
        }
    }

    public static int getHeight(WindowManager windowManager)
    {
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        return point.y;
    }

    public static int getWidth(WindowManager windowManager)
    {
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        return point.x;
    }
}
