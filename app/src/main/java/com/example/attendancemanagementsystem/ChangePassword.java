package com.example.attendancemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    EditText oldp,newp;
    String username;
    TextView warning,textView;
    Button buttonchangepwd,buttoncalcel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        buttonchangepwd=findViewById(R.id.buttonupdatePassword);
        buttoncalcel=findViewById(R.id.buttonCancel);
        oldp=findViewById(R.id.editTextoldpassword);
        newp=findViewById(R.id.editTextnewpassword);
        warning=findViewById(R.id.warning);
        textView=findViewById(R.id.textView);
        sharedpreferences= PreferenceManager.getDefaultSharedPreferences(this);
        username=sharedpreferences.getString("username","");
        warning.setVisibility(View.INVISIBLE);
        buttonchangepwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBAdapter db=new DBAdapter(ChangePassword.this);
                int flag=0;
                String pass=db.getPassword(username);
                textView.setText(pass+oldp.getText().toString());
                if(pass.equals(oldp.getText().toString())){
                    flag=db.updatePassword(username,newp.getText().toString());
                }
                else{
                    warning.setVisibility(View.VISIBLE);
                    warning.postDelayed(new Runnable() {
                        public void run() {
                            warning.setVisibility(View.INVISIBLE);
                        }
                    }, 3000);
                }
                if(flag==1){
                    Intent intent=new Intent(ChangePassword.this,FacultyHome.class);
                    startActivity(intent);
                    Toast.makeText(ChangePassword.this,"Password Changed Successfully",Toast.LENGTH_SHORT);
                }else{
                    Toast.makeText(ChangePassword.this,"Password Change Failed",Toast.LENGTH_SHORT);
                }
            }
        });

        buttoncalcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChangePassword.this,FacultyHome.class);
                startActivity(intent);
            }
        });
    }
}