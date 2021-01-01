package com.example.attendancemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.se.omapi.Session;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button login;
    EditText username, password;
    Spinner spinnerloginas;
    String userrole;
    TextView warning;
    SharedPreferences sharedpreferences;
    private String[] userRoleString = new String[]{"ADMIN", "FACULTY"};
    public static final String MyPREFERENCES = "MyPrefs" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_main);


        login=findViewById(R.id.buttonlogin);
        username=findViewById(R.id.editTextusername);
        password=findViewById(R.id.editTextpassword);
        spinnerloginas=findViewById(R.id.spinnerloginas);
        warning=findViewById(R.id.warning);

        sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this);

        warning.setVisibility(View.INVISIBLE);
        spinnerloginas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                userrole=(String)spinnerloginas.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this,"Select Your Role",Toast.LENGTH_SHORT);
            }
        });

        ArrayAdapter<String> adapter_role=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,userRoleString);
        adapter_role.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerloginas.setAdapter(adapter_role);

        if(SaveSharedPreference.getUserName(MainActivity.this).length()!=0){
            if(SaveSharedPreference.getPrefUserrole(MainActivity.this).equals("ADMIN")){
                Intent intent=new Intent(MainActivity.this,Menu.class);
                startActivity(intent);
            }else{
                Intent intent = new Intent(MainActivity.this, FacultyHome.class);
                startActivity(intent);
            }
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_name=username.getText().toString();
                String pass_word=password.getText().toString();

                if(userrole.equals("ADMIN")){
                    if(TextUtils.isEmpty((user_name))){
                        username.setError("Username can't be empty");
                    }else if(TextUtils.isEmpty((pass_word))){
                        password.setError("Password Can't be empty");
                    }else {

                        if (user_name.equals("admin") & pass_word.equals("admin123")) {
                            SaveSharedPreference.setUserName(MainActivity.this,user_name);
                            SaveSharedPreference.setUserrole(MainActivity.this,userrole);
                            Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(MainActivity.this,Menu.class);

                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{

                    if(TextUtils.isEmpty((user_name))){
                        username.setError("Username can't be empty");
                    }else if(TextUtils.isEmpty((pass_word))){
                        password.setError("Password Can't be empty");
                    }

                    DBAdapter db=new DBAdapter(MainActivity.this);
                    FacultyData facultyData=db.validateFaculty(user_name,pass_word);

                    if(facultyData!=null){

                        SaveSharedPreference.setUserName(MainActivity.this,user_name);
                        SaveSharedPreference.setUserrole(MainActivity.this,userrole);

                        Intent intent = new Intent(MainActivity.this, FacultyHome.class);
                        startActivity(intent);
                    }else{
                       warning.setVisibility(View.VISIBLE);
                        warning.postDelayed(new Runnable() {
                            public void run() {
                                warning.setVisibility(View.INVISIBLE);
                            }
                        }, 3000);
                    }

                }
            }
        });
    }
}