package com.example.attendancemanagementsystem.Attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attendancemanagementsystem.DBAdapter;
import com.example.attendancemanagementsystem.R;
import com.example.attendancemanagementsystem.SaveSharedPreference;
import com.example.attendancemanagementsystem.StudentData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MarkAttendance extends AppCompatActivity {

    Button cancel;
    ArrayList<String> list = new ArrayList<String>();
    ListView listView;
    String[] listt;
    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);


        cancel=findViewById(R.id.buttonCancel);
        DBAdapter db=new DBAdapter(this);
        String subStr=SaveSharedPreference.getPrefSubject(this);

        list=db.getStudentBySubject(subStr);
        listView=findViewById(R.id.listview);
        listt=new String[list.size()];
        for(int i=0;i<list.size();i++){
            listt[i]=list.get(i);
        }
        final TextView textView = (TextView) findViewById(R.id.textview);
        final List<String> Players_list = new ArrayList<String>(Arrays.asList(listt));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Players_list);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (doubleBackToExitPressedOnce) {
                    //To Close Entire Application
                    view.setBackgroundColor(Color.RED);
                    doubleBackToExitPressedOnce = false;
                    return;
                }
                doubleBackToExitPressedOnce = true;
                String selectedItem = (String) parent.getItemAtPosition(position);
                textView.setText("The best football player is : " + selectedItem);
                view.setBackgroundColor(Color.GREEN);

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}