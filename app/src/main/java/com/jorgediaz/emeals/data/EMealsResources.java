package com.jorgediaz.emeals.data;

import android.content.Context;
import android.graphics.Color;

import androidx.core.content.ContextCompat;

import com.jorgediaz.emeals.core.interfaces.AppResources;

public class EMealsResources implements AppResources {

    private final Context context;

    public EMealsResources(Context context) {
        this.context = context;
    }

    @Override
    public String getString(int resId) {
        return context.getString(resId);
    }

    @Override
    public String getString(int resId, Object... others) {
        return context.getString(resId, others);
    }

    @Override
    public int getColor(int resId) {
        return ContextCompat.getColor(context, resId);
    }

    @Override
    public int parseColor(String color) {
        return Color.parseColor(color);
    }
}
