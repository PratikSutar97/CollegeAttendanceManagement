package com.example.attendancemanagementsystem;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

public class SaveSharedPreference {
    static final String PREF_USER_NAME= "username";
    static final String PREF_ISLOGGEDIN= "idloggedin";
    static final String PREF_USERROLE= "userrole";
    static final String PREF_SUBJECT= "subject";
    static final String PREF_view_take_attendance="view_or_take_attendance";
    static final String PREF_one_more_lecture="take_ome_more_lecture";
    static final String PREF_lec_count="lec_count";
    static final String PREF_LEC_DATE="lec_date";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setPrefLecDate(Context ctx,String value){
        SharedPreferences.Editor editor=getSharedPreferences(ctx).edit();
        editor.putString(PREF_LEC_DATE,value);
        editor.commit();
    }

    public static String getPrefLecDate(Context ctx){
        return getSharedPreferences(ctx).getString(PREF_LEC_DATE,"");
    }
    public static void setPREF_lec_count(Context ctx,String value){
        SharedPreferences.Editor editor=getSharedPreferences(ctx).edit();
        editor.putString(PREF_lec_count,value);
        editor.commit();
    }
    public static String getPREF_lec_count(Context ctx){
        return getSharedPreferences(ctx).getString(PREF_lec_count,"");
    }

    public static void setPREF_one_more_lecture(Context ctx,String value){
        SharedPreferences.Editor editor=getSharedPreferences(ctx).edit();
        editor.putString(PREF_one_more_lecture,value);
        editor.commit();
    }

    public static String getPREF_one_more_lecture(Context ctx){
        return getSharedPreferences(ctx).getString(PREF_one_more_lecture,"");
    }

    public static void setPREF_view_take_attendance(Context ctx,String value){
        SharedPreferences.Editor editor=getSharedPreferences(ctx).edit();
        editor.putString(PREF_view_take_attendance,value);
        editor.commit();
    }
    public static String getPREF_view_take_attendance(Context ctx){
        return getSharedPreferences(ctx).getString(PREF_view_take_attendance,"");
    }
    public static void setUserName(Context ctx, String userName)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.commit();
    }

    public static String getUserName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }
    public static void setIsLoggedIn(Context ctx,String value){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_ISLOGGEDIN, value);
        editor.commit();
    }

    public static  String getPrefIsloggedin(Context ctx){
        return getSharedPreferences(ctx).getString(PREF_ISLOGGEDIN,"");
    }

    public static void setUserrole(Context ctx,String role){
        SharedPreferences.Editor editor=getSharedPreferences(ctx).edit();
        editor.putString(PREF_USERROLE,role);
        editor.commit();
    }
    public static String getPrefUserrole(Context ctx){
        return getSharedPreferences(ctx).getString(PREF_USERROLE,"");
    }

    public static void setSubject(Context ctx,String sub){
        SharedPreferences.Editor editor=getSharedPreferences(ctx).edit();
        editor.putString(PREF_SUBJECT,sub);
        editor.commit();
    }
    public static String getPrefSubject(Context ctx){
        return getSharedPreferences(ctx).getString(PREF_SUBJECT,"");
    }
}
