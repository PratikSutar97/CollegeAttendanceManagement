package com.example.attendancemanagementsystem;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddStudent extends AppCompatActivity {

    Button addStudent,cancel;
    EditText fn,contact,address;
    Spinner spinnersub1,spinnersub2,spinnersub3,spinnersub4;
    Spinner courseSpinner,yearSpinner;
    String courseStr,yearStr,sub1Str,sub2Str,sub3Str,sub4Str;

    private String[] courseArr=new String[]{"Bsc CS","Msc CS","Bsc CA","Msc CA"};
    private String[] yearArr=new String[]{"FY","SY","TY"};
    private ArrayList<String> subjectList=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        spinnersub1=findViewById(R.id.spinnerSub1);
        spinnersub2=findViewById(R.id.spinnerSub2);
        spinnersub3=findViewById(R.id.spinnerSub3);
        spinnersub4=findViewById(R.id.spinnerSub4);
        addStudent=findViewById(R.id.buttonaddstudent);
        fn=findViewById(R.id.editTextfirstname);

        contact=findViewById(R.id.editTextcontact);
        address=findViewById(R.id.editTextaddress);
        courseSpinner=findViewById(R.id.spinnerCourse);
        yearSpinner=findViewById(R.id.spinnerYear);
        cancel=findViewById(R.id.buttonCancel);

        subjectList.add("Android");
        subjectList.add("Java");
        subjectList.add("Cloud");
        subjectList.add("Python");
        subjectList.add("UX/UI");
        subjectList.add("Testing");
        subjectList.add("HTML");
        subjectList.add("C#");
        subjectList.add("C");
        subjectList.add("PHP");


        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                courseStr=(String)courseSpinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> adapter_course=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,courseArr);
        adapter_course.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseSpinner.setAdapter(adapter_course);

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                yearStr=(String)yearSpinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> adapter_year=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,yearArr);
        adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(adapter_year);

        /// Subject Spinners ////

        spinnersub1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                sub1Str=(String)spinnersub1.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayAdapter<String> adapter_subject1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,subjectList);
        adapter_subject1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnersub1.setAdapter(adapter_subject1);

        spinnersub2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                sub2Str=(String)spinnersub2.getSelectedItem();
                subjectList.remove(sub1Str);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> adapter_subject2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,subjectList);
        adapter_subject2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnersub2.setAdapter(adapter_subject2);

        spinnersub3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                sub3Str=(String)spinnersub3.getSelectedItem();
                subjectList.remove(sub2Str);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> adapter_subject3=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,subjectList);
        adapter_subject3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnersub3.setAdapter(adapter_subject3);

        spinnersub4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                sub4Str=(String)spinnersub4.getSelectedItem();
                subjectList.remove(sub3Str);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> adapter_subject4=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,subjectList);
        adapter_subject4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnersub4.setAdapter(adapter_subject4);


        ///////////
        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname=fn.getText().toString();
                String ct=contact.getText().toString();
                String addr=address.getText().toString();

                if(TextUtils.isEmpty(fname)){
                    fn.setError("Please Enter first name");
                }
                else if(TextUtils.isEmpty(ct)){
                    contact.setError("Please Enter contact number");
                }
                else if(TextUtils.isEmpty(addr)){
                    address.setError("Please Enter first name");
                }else {
                    StudentData studentData=new StudentData();
                    studentData.setSfname(fname);
                    studentData.setScontact(ct);
                    studentData.setSaddress(addr);
                    studentData.setCourse(courseStr);
                    studentData.setYear(yearStr);
                    studentData.setSub1(sub1Str);
                    studentData.setSub2(sub2Str);
                    studentData.setSub3(sub3Str);
                    studentData.setSub4(sub4Str);

                    DBAdapter dbAdapter=new DBAdapter(AddStudent.this);
                    int flag=dbAdapter.addStudent(studentData);
                    if(flag==1){
                        Toast.makeText(getApplicationContext(),"Student Added Successfully",Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(),"Error Adding Student Data",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intt=new Intent(AddStudent.this,Menu.class);
                startActivity(intt);
            }
        });
    }
}