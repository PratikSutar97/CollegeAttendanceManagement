package com.example.attendancemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.SmsManager;
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

public class AddFaculty extends AppCompatActivity {

    Button addFaculty,cancel;
    Spinner spinnersub1,spinnersub2;
    EditText fn,ln,contact,address,username,password;
    TextView tv;
    String sub1Str,sub2Str;
    private ArrayList<String> subjectList=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faculty);

        spinnersub1=findViewById(R.id.spinnerSub1);
        spinnersub2=findViewById(R.id.spinnerSub2);
        addFaculty=findViewById(R.id.buttonaddfaculty);
        fn=findViewById(R.id.editTextfirstname);
        ln=findViewById(R.id.editTextlastname);
        contact=findViewById(R.id.editTextcontact);
        address=findViewById(R.id.editTextaddress);
        cancel=findViewById(R.id.buttonCancel);
        username=findViewById(R.id.editTextusername);
        password=findViewById(R.id.editTextpassword);
        tv=findViewById(R.id.textView);

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
        ////// SPINNER
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


        ////

        addFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBAdapter dbAdapter=new DBAdapter(AddFaculty.this);

                String fname=fn.getText().toString();
                String lname=ln.getText().toString();
                String ct=contact.getText().toString();
                String addr=address.getText().toString();
                String user=username.getText().toString();
                String pass=password.getText().toString();

                ArrayList<FacultyData> f= dbAdapter.getFacultyByUsername(user);

                if(f.size()>0){
                    username.setError("Username Already Exists");
                }else
                if(TextUtils.isEmpty(fname)){
                    fn.setError("Please Enter first name");
                }
                else if(TextUtils.isEmpty(lname)){
                    ln.setError("Please Enter last name");
                }
                else if(TextUtils.isEmpty(ct)){
                    contact.setError("Please Enter contact number");
                }
                else if(TextUtils.isEmpty(addr)){
                    address.setError("Please Enter first name");
                }else {
                    FacultyData facultyData=new FacultyData();
                    facultyData.setffname(fname);
                    facultyData.setflname(lname);
                    facultyData.setfcontact(ct);
                    facultyData.setfaddress(addr);
                    facultyData.setusername(user);
                    facultyData.setpassword(pass);
                    facultyData.setSubject1(sub1Str);
                    facultyData.setSubject2(sub2Str);



                    int flag=dbAdapter.addFaculty(facultyData);
                    if(flag==1){
                        SmsManager smsManager=SmsManager.getDefault();
                        smsManager.sendTextMessage(ct,null,"You Are Successfully Registered As Faculty !! Your Login Credentials For Attendance App Are :- username="+user+" password="+pass,null,null);
                        Toast.makeText(getApplicationContext(),"Faculty Data Added Successfully",Toast.LENGTH_SHORT);
                        Intent intent=new Intent(AddFaculty.this,Menu.class);
                        startActivity(intent);;
                    }else{
                        Toast.makeText(getApplicationContext(),"Error Adding faculty Data",Toast.LENGTH_SHORT);
                    }
                }

            }
        });
        
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intt=new Intent(AddFaculty.this,Menu.class);
                startActivity(intt);
            }
        });
    }
    
}