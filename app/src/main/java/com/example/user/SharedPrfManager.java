package com.example.user;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrfManager {

    private SharedPreferences sharedPreferences;

    public SharedPrfManager(Context context) {
        if (context != null) {
            sharedPreferences = context.getSharedPreferences("shared_pref", Context.MODE_PRIVATE);
        }

    }

    public void saveUser(String name,
                         String phone,
                         String id) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("user_state", true);
        editor.putString("name", name);
        editor.putString("phone", phone);
        editor.putString("id", id);
        editor.apply();

    }

    public void logout() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("user_state", false);
        editor.putString("name", "");
        editor.putString("phone", "");
        editor.putString("id", "");
        editor.apply();

    }


    public String getName() {
        return sharedPreferences.getString("name", "");
    }
    public String getPhone() {
        return sharedPreferences.getString("phone", "");
    }
    public String getId() {
        return sharedPreferences.getString("id", "");
    }
    public boolean isEntered() {
        return sharedPreferences.getBoolean("user_state",false);
    }


}
