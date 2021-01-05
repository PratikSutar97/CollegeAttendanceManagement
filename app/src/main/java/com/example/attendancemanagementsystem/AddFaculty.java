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
    Spinner courseSpinner,yearSpinner;
    EditText fn,contact,address,username,password;
    TextView tv,txvsub11,txvsub22;
    String courseStr,yearStr,sub1Str,sub2Str;

    private String[] courseArr=new String[]{"Bsc CS","Msc CA"};
    private String[] yearArr=new String[]{"FY","SY","TY"};
    private String[] yearArr1=new String[]{"FY","SY"};
    private String[] BcsfySubs=new String[]{"C_Programming","Statistics","Discrete_Mathematics","Algebra_and_Calculus"};
    private String[] BcssySubs=new String[]{"Data_structures", "RDBMS","Applied_Algebra", "Analog_systems"};
    private String[] BcstySubs=new String[]{"Systems_Programming","Computer_Networking","Internet_programming","Java_programming"};
    private String[] MscfySubs=new String[]{"DOT_NET","DAA","Web_Programming","PHP"};
    private String[] MscsySubs=new String[]{"Android","PYthon","UI_UX","Cyber_Security"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faculty);

        spinnersub1=findViewById(R.id.spinnerSub1);
        spinnersub2=findViewById(R.id.spinnerSub2);
        courseSpinner=findViewById(R.id.spinnerCourse);
        yearSpinner=findViewById(R.id.spinnerYear);
        addFaculty=findViewById(R.id.buttonaddfaculty);
        fn=findViewById(R.id.editTextfirstname);
        contact=findViewById(R.id.editTextcontact);
        address=findViewById(R.id.editTextaddress);
        cancel=findViewById(R.id.buttonCancel);
        username=findViewById(R.id.editTextusername);
        password=findViewById(R.id.editTextpassword);
        tv=findViewById(R.id.textView);
        txvsub11=findViewById(R.id.textSub11);
        txvsub22=findViewById(R.id.textSub22);

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

        spinnersub2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                sub2Str=(String)spinnersub2.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                courseStr=(String)courseSpinner.getSelectedItem();

                if(courseStr.equals(courseArr[1])){
                    ArrayAdapter<String> adapter_year=new ArrayAdapter<String>(AddFaculty.this,android.R.layout.simple_spinner_item,yearArr1);
                    adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    yearSpinner.setAdapter(adapter_year);
                }else{
                    ArrayAdapter<String> adapter_year=new ArrayAdapter<String>(AddFaculty.this,android.R.layout.simple_spinner_item,yearArr);
                    adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    yearSpinner.setAdapter(adapter_year);
                }
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

                if(courseStr.equals(courseArr[0]) && yearStr.equals(yearArr[0])){
                    ArrayAdapter<String> adapter_subject2=new ArrayAdapter<String>(AddFaculty.this,android.R.layout.simple_spinner_item,BcsfySubs);
                    adapter_subject2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnersub2.setAdapter(adapter_subject2);

                    final ArrayAdapter<String> adapter_subject1=new ArrayAdapter<String>(AddFaculty.this,android.R.layout.simple_spinner_item,BcsfySubs);
                    adapter_subject1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnersub1.setAdapter(adapter_subject1);

                }else
                if(courseStr.equals(courseArr[0]) && yearStr.equals(yearArr[1])){
                    ArrayAdapter<String> adapter_subject2=new ArrayAdapter<String>(AddFaculty.this,android.R.layout.simple_spinner_item,BcssySubs);
                    adapter_subject2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnersub2.setAdapter(adapter_subject2);

                    final ArrayAdapter<String> adapter_subject1=new ArrayAdapter<String>(AddFaculty.this,android.R.layout.simple_spinner_item,BcssySubs);
                    adapter_subject1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnersub1.setAdapter(adapter_subject1);
                }else
                if(courseStr.equals(courseArr[0]) && yearStr.equals(yearArr[2])){
                    ArrayAdapter<String> adapter_subject2=new ArrayAdapter<String>(AddFaculty.this,android.R.layout.simple_spinner_item,BcstySubs);
                    adapter_subject2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnersub2.setAdapter(adapter_subject2);

                    final ArrayAdapter<String> adapter_subject1=new ArrayAdapter<String>(AddFaculty.this,android.R.layout.simple_spinner_item,BcstySubs);
                    adapter_subject1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnersub1.setAdapter(adapter_subject1);
                }else
                if(courseStr.equals(courseArr[1]) && yearStr.equals(yearArr[0])){
                    ArrayAdapter<String> adapter_subject2=new ArrayAdapter<String>(AddFaculty.this,android.R.layout.simple_spinner_item,MscfySubs);
                    adapter_subject2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnersub2.setAdapter(adapter_subject2);


                    final ArrayAdapter<String> adapter_subject1=new ArrayAdapter<String>(AddFaculty.this,android.R.layout.simple_spinner_item,MscfySubs);
                    adapter_subject1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnersub1.setAdapter(adapter_subject1);
                }else
                if(courseStr.equals(courseArr[1]) && yearStr.equals(yearArr[1])){
                    ArrayAdapter<String> adapter_subject2=new ArrayAdapter<String>(AddFaculty.this,android.R.layout.simple_spinner_item,MscsySubs);
                    adapter_subject2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnersub2.setAdapter(adapter_subject2);

                    final ArrayAdapter<String> adapter_subject1=new ArrayAdapter<String>(AddFaculty.this,android.R.layout.simple_spinner_item,MscsySubs);
                    adapter_subject1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnersub1.setAdapter(adapter_subject1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        addFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DBAdapter dbAdapter=new DBAdapter(AddFaculty.this);

                String fname=fn.getText().toString();
                String ct=contact.getText().toString();
                String addr=address.getText().toString();
                String user=username.getText().toString();
                String pass=password.getText().toString();

//                ArrayList<FacultyData> f= dbAdapter.getFacultyByUsername(user);
//
//                if(f.size()>0){
//                    username.setError("Username Already Exists");
//                }else
                if(TextUtils.isEmpty(fname)){
                    fn.setError("Please Enter first name");
                }
                else if(TextUtils.isEmpty(ct)){
                    contact.setError("Please Enter contact number");
                }
                else if(TextUtils.isEmpty(addr)){
                    address.setError("Please Enter first name");
                }else {
                    FacultyData facultyData=new FacultyData();
                    facultyData.setffname(fname);
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