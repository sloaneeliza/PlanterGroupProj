package com.example.plantergroupproj;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPrefs {

    private static final String PREFS_NAME = "user_prefs";
    private static final String KEY_USERNAME = "username";

    public static void setName(Context context, String name) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString(KEY_USERNAME, name).apply();
    }
    public static String getName(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(KEY_USERNAME, null);
    }
}