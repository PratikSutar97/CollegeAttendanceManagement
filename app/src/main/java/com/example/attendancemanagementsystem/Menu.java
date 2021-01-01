package com.example.attendancemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Menu extends AppCompatActivity {

    Button logoutbutton,addStudent,addFaculty,viewStudent,deleteStudent,viewFaculty,deleteFaculty;
    FloatingActionButton fab;
    Boolean isAllFabsVisible;
    String isLoggedIn;
    SharedPreferences sharedpreferences;
    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        logoutbutton=findViewById(R.id.buttonlogout);
        final DBAdapter db=new DBAdapter(this);
        logoutbutton.setVisibility(View.GONE);

        fab=findViewById(R.id.fab);
        addStudent=findViewById(R.id.buttonaddstudent);
        addFaculty=findViewById(R.id.buttonaddfaculty);
        viewStudent=findViewById(R.id.buttonViewstudent);
        viewFaculty=findViewById(R.id.buttonviewfaculty);
        deleteStudent=findViewById(R.id.buttonClearStudent);
        deleteFaculty=findViewById(R.id.buttonClearStudent2);


        isAllFabsVisible = false;


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!isAllFabsVisible){
                    logoutbutton.setVisibility(View.VISIBLE);
                    isAllFabsVisible = true;
                }
                else{
                    logoutbutton.setVisibility(View.GONE);
                    isAllFabsVisible = false;
                }
            }
        });
        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Menu.this,AddStudent.class);
                startActivity(intent);
            }
        });
        addFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Menu.this,AddFaculty.class);
                startActivity(intent);
            }
        });
        deleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               int flag= db.deleteAllStudents();
               if(flag==1) {
                   Toast.makeText(getApplicationContext(), "Students Record Cleared", Toast.LENGTH_SHORT);
               }else{
                   Toast.makeText(getApplicationContext(),"Could not clear Students Record",Toast.LENGTH_SHORT);
               }
            }
        });

        deleteFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int flag=db.deleteAllFaculty();
                if(flag==1) {
                    Toast.makeText(getApplicationContext(), "Faculty Record Cleared", Toast.LENGTH_SHORT);
                }else{
                    Toast.makeText(getApplicationContext(),"Could not clear Faculty Record",Toast.LENGTH_SHORT);
                }
            }
        });

        viewFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Menu.this,ViewFaculty.class);
                startActivity(intent);
            }
        });
        viewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Menu.this,ViewStudent.class);
                startActivity(intent);
            }
        });

        logoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveSharedPreference.setUserName(Menu.this,"");
                SaveSharedPreference.setIsLoggedIn(Menu.this,"false");
                Intent intent=new Intent(Menu.this,MainActivity.class);
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