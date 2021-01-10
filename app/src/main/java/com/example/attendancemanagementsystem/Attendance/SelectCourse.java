package com.example.attendancemanagementsystem.Attendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attendancemanagementsystem.DBAdapter;
import com.example.attendancemanagementsystem.FacultyData;
import com.example.attendancemanagementsystem.R;
import com.example.attendancemanagementsystem.SaveSharedPreference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class SelectCourse extends AppCompatActivity {

    ImageButton arrow;
    ConstraintLayout fixed_layout;
    LinearLayout hiddenView;
    EditText lec_count;

    Spinner subjectSpinner;
    ArrayList<FacultyData> facultyBeanList;
    String courseStr,subjectStr;
    Button btnaddStudent,btnCancel,yes,no,go;
    TextView t3;
    int yes_no_flag;
    //private String[] courseArr=new String[]{"Bsc CS","Msc CS","Bsc CA","Msc CA"};
    private ArrayList<String> courseArrr=new ArrayList<String>();
    private ArrayList<String> subjectArrr=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_course);

       // cardView = findViewById(R.id.base_cardview);
        arrow = findViewById(R.id.arrow_button);
        hiddenView = findViewById(R.id.hidden_view);
        fixed_layout=findViewById(R.id.fixed_layout);
        yes=findViewById(R.id.yes);
        no=findViewById(R.id.no);


        Button btnDelete=findViewById(R.id.buttondeleAttend);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBAdapter db=new DBAdapter(SelectCourse.this);
                db.deleteAttend();
            }
        });


        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hiddenView.getVisibility() == View.VISIBLE ) {
                    hiddenView.setVisibility(View.GONE);
                    arrow.setImageResource(R.drawable.ic_launcher1_expand_arrow_foreground);
                }
                else {
                    hiddenView.setVisibility(View.VISIBLE);
                    arrow.setImageResource(R.drawable.ic_launcher1_expand_arrow_up_foreground);
                }
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hiddenView.setVisibility(View.GONE);
                Intent ii=new Intent(SelectCourse.this,MarkAttendance.class);
                startActivity(ii);

            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hiddenView.setVisibility(View.GONE);
                arrow.setImageResource(R.drawable.ic_launcher1_expand_arrow_foreground);
            }
        });

        t3=findViewById(R.id.t3);
        t3.setVisibility(View.GONE);
        fixed_layout.setVisibility(View.INVISIBLE);
        btnaddStudent=findViewById(R.id.buttonaddstudent);
        btnCancel=findViewById(R.id.buttonCancel);
        subjectSpinner=findViewById(R.id.spinnerSubject);

        courseArrr.add("Bsc CS");
        courseArrr.add("Msc CS");
        courseArrr.add("Bsc CA");
        courseArrr.add("Msc CA");

        final DBAdapter db=new DBAdapter(this);
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
                    if(SaveSharedPreference.getPREF_view_take_attendance(SelectCourse.this).equals("Take Attendance")){

                        if(db.getcount(subjectStr)==0){
                            Intent ii=new Intent(SelectCourse.this,MarkAttendance.class);
                            startActivity(ii);
                        }else{
                            fixed_layout.setVisibility(View.VISIBLE);
                        }
                    }else{
                        Intent ii=new Intent(SelectCourse.this,ViewAttendance.class);
                        startActivity(ii);
                    }
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