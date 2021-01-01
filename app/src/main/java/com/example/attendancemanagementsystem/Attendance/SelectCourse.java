package com.example.attendancemanagementsystem.Attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.attendancemanagementsystem.DBAdapter;
import com.example.attendancemanagementsystem.FacultyData;
import com.example.attendancemanagementsystem.R;
import com.example.attendancemanagementsystem.SaveSharedPreference;

import java.util.ArrayList;

public class SelectCourse extends AppCompatActivity {

    Spinner subjectSpinner, courseSpinner;
    ArrayList<FacultyData> facultyBeanList;
    String courseStr,subjectStr;
    Button btnaddStudent,btnCancel;
    //private String[] courseArr=new String[]{"Bsc CS","Msc CS","Bsc CA","Msc CA"};
    private ArrayList<String> courseArrr=new ArrayList<String>();
    private ArrayList<String> subjectArrr=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_course);

        btnaddStudent=findViewById(R.id.buttonaddstudent);
        btnCancel=findViewById(R.id.buttonCancel);
        subjectSpinner=findViewById(R.id.spinnerSubject);

        courseArrr.add("Bsc CS");
        courseArrr.add("Msc CS");
        courseArrr.add("Bsc CA");
        courseArrr.add("Msc CA");

        DBAdapter db=new DBAdapter(this);
        facultyBeanList=db.getFacultyByUsername(SaveSharedPreference.getUserName(this));

        subjectArrr.add(facultyBeanList.get(0).getSubject1());
        subjectArrr.add(facultyBeanList.get(0).getSubject2());

        //// Subject Spinner
        subjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                subjectStr=(String)subjectSpinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> adapter_year=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,subjectArrr);
        adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectSpinner.setAdapter(adapter_year);

        btnaddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveSharedPreference.setSubject(SelectCourse.this,subjectStr);
                Intent i=new Intent(SelectCourse.this,MarkAttendance.class);
                startActivity(i);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}