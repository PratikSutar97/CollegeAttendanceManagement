package com.example.attendancemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attendancemanagementsystem.Attendance.SelectCourse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FacultyHome extends AppCompatActivity {

    FloatingActionButton fab;
    Button logoutbutton, changePassbutton,buttongetStudentDetail,buttonTakeAttendance;
    String username,isLoggedIn;
    TextView t;
    //SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
    SharedPreferences sharedpreferences;
    Boolean isAllFabsVisible;
    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_home);
        fab=findViewById(R.id.fab);
        logoutbutton=findViewById(R.id.buttonlogout);
        changePassbutton=findViewById(R.id.buttonchangepassword);
        t=findViewById(R.id.username);
        logoutbutton.setVisibility(View.GONE);
        changePassbutton.setVisibility(View.GONE);
        buttongetStudentDetail=findViewById(R.id.buttongetstudent);
        buttonTakeAttendance=findViewById(R.id.buttonTakeAttendance);

        isAllFabsVisible = false;

        sharedpreferences=PreferenceManager.getDefaultSharedPreferences(this);
        username=sharedpreferences.getString("username","");
        isLoggedIn=sharedpreferences.getString("loggedid","");
        t.setText("Welcome, "+username);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!isAllFabsVisible){
                    logoutbutton.setVisibility(View.VISIBLE);
                    changePassbutton.setVisibility(View.VISIBLE);
                    isAllFabsVisible = true;
                }
                else{
                    logoutbutton.setVisibility(View.GONE);
                    changePassbutton.setVisibility(View.GONE);

                    isAllFabsVisible = false;
                }
            }
        });

        buttonTakeAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(FacultyHome.this, SelectCourse.class);
                startActivity(i);
            }
        });

        buttongetStudentDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FacultyHome.this,ListStudentsByCourse.class);
                startActivity(intent);
            }
        });

        changePassbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FacultyHome.this,ChangePassword.class);
                startActivity(intent);
            }
        });

        logoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveSharedPreference.setUserName(FacultyHome.this,"");
                SaveSharedPreference.setIsLoggedIn(FacultyHome.this,"false");
                Intent intent=new Intent(FacultyHome.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            //To Close Entire Application
            moveTaskToBack(true);
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}