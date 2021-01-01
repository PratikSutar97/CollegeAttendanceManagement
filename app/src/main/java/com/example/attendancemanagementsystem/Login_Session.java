package com.example.attendancemanagementsystem;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Login_Session {
    public SharedPreferences preferences;
    public Login_Session(Context cntx){
        preferences= PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void  setUsername(String username){
        preferences.edit().putString("username",username).commit();
    }
    public String getUsername(){
        String username=preferences.getString("username","");
        return username;
    }
}
