package com.edominguez.compasstest.utils;

import static com.edominguez.compasstest.utils.ConstantsKt.EMPTY_STRING;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    private final Context context;
    private static final String TAG = "com.compasstest.app";
    private static final String HTML = "_HTML";

    public Preferences(Context ctx) {
        context = ctx;
    }

    //----------------------------- Set Preferences ---------------------------------------------

    public void setHTMLData(String data) { setValue(context, HTML, data); }

    //----------------------------- Get Preferences ---------------------------------------------

    public String getHTMLData() { return getStringValue(context, HTML, EMPTY_STRING); }

    /*---------------------------------------------------------------------------*/

    private static void setValue(Context ctx, String key, String value) {
        SharedPreferences prefs = getPreferences(ctx);
        prefs.edit().putString(key, value).apply();
    }

    private static void setValue(Context ctx, String key, int value) {
        SharedPreferences prefs = getPreferences(ctx);
        prefs.edit().putInt(key, value).apply();
    }

    private static String getStringValue(Context ctx, String key, String value) {
        SharedPreferences prefs = getPreferences(ctx);
        return prefs.getString(key, value);
    }

    private static int getIntValue(Context ctx, String key, int value) {
        SharedPreferences prefs = getPreferences(ctx);
        return prefs.getInt(key, value);
    }

    private static SharedPreferences getPreferences(Context ctx) {
        return ctx.getSharedPreferences(TAG, Context.MODE_PRIVATE);
    }

    @SuppressLint("ApplySharedPref")
    public static void deletePreference(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        prefs.edit().remove(key).commit();
    }

}
