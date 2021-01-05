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
    Spinner courseSpinner,yearSpinner;
    String courseStr="",yearStr="",sub1Str,sub2Str,sub3Str,sub4Str;

    private String[] courseArr=new String[]{"Bsc CS","Msc CA"};
    private String[] yearArr=new String[]{"FY","SY","TY"};
    private String[] BcsfySubs=new String[]{"C_Programming","Statistics","Discrete_Mathematics","Algebra_and_Calculus"};
    private String[] BcssySubs=new String[]{"Data_structures", "RDBMS","Applied_Algebra", "Analog_systems"};
    private String[] BcstySubs=new String[]{"Systems_Programming","Computer_Networking","Internet_programming","Java_programming"};
    private String[] MscfySubs=new String[]{"DOT_NET","DAA","Web_Programming","PHP"};
    private String[] MscsySubs=new String[]{"Android","PYthon","UI_UX","Cyber_Security"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        addStudent=findViewById(R.id.buttonaddstudent);
        fn=findViewById(R.id.editTextfirstname);

        contact=findViewById(R.id.editTextcontact);
        address=findViewById(R.id.editTextaddress);
        courseSpinner=findViewById(R.id.spinnerCourse);
        yearSpinner=findViewById(R.id.spinnerYear);
        cancel=findViewById(R.id.buttonCancel);

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


        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //// set subStr //////

                if(courseStr.equals(courseArr[0]) && yearStr.equals(yearArr[0])){
                    sub1Str=BcsfySubs[0];
                    sub2Str=BcsfySubs[1];
                    sub3Str=BcsfySubs[2];
                    sub4Str=BcsfySubs[3];
                }else
                if(courseStr.equals(courseArr[0]) && yearStr.equals(yearArr[1])){
                    sub1Str=BcssySubs[0];
                    sub2Str=BcssySubs[1];
                    sub3Str=BcssySubs[2];
                    sub4Str=BcssySubs[3];
                }else
                if(courseStr.equals(courseArr[0]) && yearStr.equals(yearArr[2])){
                    sub1Str=BcstySubs[0];
                    sub2Str=BcstySubs[1];
                    sub3Str=BcstySubs[2];
                    sub4Str=BcstySubs[3];
                }else
                if(courseStr.equals(courseArr[1]) && yearStr.equals(yearArr[0])){
                    sub1Str=MscfySubs[0];
                    sub2Str=MscfySubs[1];
                    sub3Str=MscfySubs[2];
                    sub4Str=MscfySubs[3];
                }else
                if(courseStr.equals(courseArr[1]) && yearStr.equals(yearArr[1])){
                    sub1Str=MscsySubs[0];
                    sub2Str=MscsySubs[1];
                    sub3Str=MscsySubs[2];
                    sub4Str=MscsySubs[3];
                }

                ////////////////////////
                Toast.makeText(AddStudent.this,sub1Str+sub2Str+sub3Str+sub4Str,Toast.LENGTH_SHORT).show();
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