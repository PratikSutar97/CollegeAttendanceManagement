package com.example.attendancemanagementsystem.Attendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.DatePicker;
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

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SelectCourse extends AppCompatActivity {

    ConstraintLayout hiddenView;
    Spinner subjectSpinner,spinnerLecno;
    ArrayList<FacultyData> facultyBeanList;
    String subjectStr,date,lecnoStr;
    TextView viewDate,errorMessage;
    private int mYear, mMonth, mDay, mHour, mMinute;
    Button btnaddStudent,btnCancel,yes,no;
    private ArrayList<String> subjectArrr=new ArrayList<String>();
    private ArrayList<String> lecnoArrr=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_course);

        hiddenView = findViewById(R.id.hidden_view);
        hiddenView.setVisibility(View.GONE);
        yes=findViewById(R.id.yes);
        no=findViewById(R.id.no);
        errorMessage=findViewById(R.id.errorMessage);
        errorMessage.setVisibility(View.GONE);

        final TextView moreoption=findViewById(R.id.moreoptiontextview);

        final Button btnDatePicker=findViewById(R.id.btnDatePicker);

        viewDate=findViewById(R.id.viewDate);
        btnDatePicker.setVisibility(View.GONE);
        spinnerLecno=findViewById(R.id.spinnerLecNo);
        spinnerLecno.setVisibility(View.GONE);
        viewDate.setVisibility(View.GONE);
        if(SaveSharedPreference.getPREF_view_take_attendance(SelectCourse.this).equals("Edit Attendance")){
            btnDatePicker.setVisibility(View.VISIBLE);
            spinnerLecno.setVisibility(View.VISIBLE);
        }

        Button btnDelete=findViewById(R.id.buttondeleAttend);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBAdapter db=new DBAdapter(SelectCourse.this);
                db.deleteAttend();
            }
        });

        final TextView title=findViewById(R.id.title);
        if(SaveSharedPreference.getPREF_view_take_attendance(SelectCourse.this).equals("Take Attendance")){
            title.setText("Proceed to take attendance");
        }else if(SaveSharedPreference.getPREF_view_take_attendance(SelectCourse.this).equals("Edit Attendance")){
            title.setText("Proceed to Edit attendance");
        }
        else{
            title.setText("Proceed to view attendance");
        }

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hiddenView.setVisibility(View.GONE);
                Intent ii=new Intent(SelectCourse.this,MarkAttendance.class);
                startActivity(ii);
                finish();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moreoption.setText("Select Another Subject Or Come Back Next Time !!");
                yes.setVisibility(View.GONE);
                no.setVisibility(View.GONE);
                btnCancel.setVisibility(View.VISIBLE);
                btnaddStudent.setVisibility(View.VISIBLE);
            }
        });

        btnaddStudent=findViewById(R.id.buttonaddstudent);
        btnCancel=findViewById(R.id.buttonCancel);
        subjectSpinner=findViewById(R.id.spinnerSubject);
        final DBAdapter db=new DBAdapter(this);
        facultyBeanList=db.getFacultyByUsername(SaveSharedPreference.getUserName(this));

        subjectArrr.add(" Select Subject ");
        subjectArrr.add(facultyBeanList.get(0).getSubject1());
        subjectArrr.add(facultyBeanList.get(0).getSubject2());

        //// Subject Spinner
        subjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                subjectStr=(String)subjectSpinner.getSelectedItem();
                if(subjectStr.equals(" Select Subject ")){
                    errorMessage.setText("Select Subject To Proceed !!!");
                    errorMessage.setVisibility(View.VISIBLE);
                }
                else{
                    errorMessage.setVisibility(View.GONE);
                    if(SaveSharedPreference.getPREF_view_take_attendance(SelectCourse.this).equals("Take Attendance")){
                        if(db.getcount(subjectStr)>0){
                            hiddenView.setVisibility(View.VISIBLE);
                            yes.setVisibility(View.VISIBLE);
                            no.setVisibility(View.VISIBLE);
                            btnCancel.setVisibility(View.GONE);
                            btnaddStudent.setVisibility(View.GONE);
                            moreoption.setText("One more "+subjectStr+" lecture today??");
                        }else{
                            hiddenView.setVisibility(View.GONE);
                            btnCancel.setVisibility(View.VISIBLE);
                            btnaddStudent.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> adapter_year=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,subjectArrr);
        adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectSpinner.setAdapter(adapter_year);

        lecnoArrr.add(" Select Lecture Number ");
        lecnoArrr.add("1");
        lecnoArrr.add("2");
        lecnoArrr.add("3");
        lecnoArrr.add("4");

        spinnerLecno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                lecnoStr=(String)spinnerLecno.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> adapter_lecno=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,lecnoArrr);
        adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLecno.setAdapter(adapter_lecno);


        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c=Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(SelectCourse.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                date=dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                                if(subjectStr.equals(" Select Subject ")){
                                    Toast.makeText(SelectCourse.this,"Please Select a Valid Subject",Toast.LENGTH_SHORT).show();
                                }else {
                                    viewDate.setVisibility(View.VISIBLE);
                                    DBAdapter db = new DBAdapter(SelectCourse.this);
                                    viewDate.setText(date);
                                    ArrayList<AttendanceData> ar = db.getDate(subjectStr, date);
                                    if (ar != null) {
                                        if (date.equals(ar.get(0).getDate())) {
                                            Toast.makeText(SelectCourse.this, "Equal Date -- " + ar.get(0).getDate(), Toast.LENGTH_SHORT).show();
                                            btnaddStudent.setVisibility(View.VISIBLE);
                                            errorMessage.setVisibility(View.GONE);
                                        }
                                    } else {
                                        errorMessage.setText("No Lecture Found For Selected Date  Or Lec Count!!");
                                        errorMessage.setVisibility(View.VISIBLE);
                                        btnaddStudent.setVisibility(View.GONE);
                                    }
                                }

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });



        btnaddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    SaveSharedPreference.setSubject(SelectCourse.this,subjectStr);
                if(subjectStr.equals(" Select Subject ")){
                    Toast.makeText(SelectCourse.this,"Please Select a Valid Subject",Toast.LENGTH_SHORT).show();
                }else{
                    if(SaveSharedPreference.getPREF_view_take_attendance(SelectCourse.this).equals("Take Attendance")){
                        SaveSharedPreference.setPREF_lec_count(SelectCourse.this,"");
                        if(db.getcount(subjectStr)==0){
                            Intent ii=new Intent(SelectCourse.this,MarkAttendance.class);
                            startActivity(ii);
                            finish();
                        }else{
                            hiddenView.setVisibility(View.VISIBLE);
                        }
                    }else if(SaveSharedPreference.getPREF_view_take_attendance(SelectCourse.this).equals("Edit Attendance")){
                        SaveSharedPreference.setPrefLecDate(SelectCourse.this,date);
                        SaveSharedPreference.setPREF_lec_count(SelectCourse.this,lecnoStr);
                        Intent ii=new Intent(SelectCourse.this,MarkAttendance.class);
                        startActivity(ii);
                        finish();
                    }
                    else{
                        SaveSharedPreference.setPREF_lec_count(SelectCourse.this,"");
                        Intent ii=new Intent(SelectCourse.this,ViewAttendance.class);
                        startActivity(ii);
                        finish();
                    }
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