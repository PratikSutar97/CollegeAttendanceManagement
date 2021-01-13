package com.example.attendancemanagementsystem.Attendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attendancemanagementsystem.DBAdapter;
import com.example.attendancemanagementsystem.R;
import com.example.attendancemanagementsystem.SaveSharedPreference;

import java.util.ArrayList;

public class MarkAttendance extends AppCompatActivity {

    Button cancel;
    ArrayList<String> list = new ArrayList<String>();
    String[] listt;
    TextView dateView;
    String subStr;
    TableLayout table;
    DBAdapter db=new DBAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);

        cancel=findViewById(R.id.buttonCancel);
        table=findViewById(R.id.table);
        dateView=findViewById(R.id.dateView);
        dateView.setText(db.getDateTime());

        subStr=SaveSharedPreference.getPrefSubject(this);

        if(SaveSharedPreference.getPREF_lec_count(MarkAttendance.this).equals("")) {
            db.markAttendOnStart(subStr);
        }

        list=db.getStudentBySubject(subStr);
        listt=new String[list.size()];
        for(int i=0;i<list.size();i++) {
            listt[i] = list.get(i);
        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        loadData();
    }

    @SuppressLint("ClickableViewAccessibility")
    public void loadData(){
        int i=0;
        LinearLayout constraintLayout=findViewById(R.id.scrollLinear);
        for(i=0;i<listt.length;i++){
            final Button btn=new Button(this);
            btn.setText(listt[i]);
            btn.setTextSize(20);
            btn.setTextColor(Color.parseColor("#D8D8D8"));
            btn.setBackgroundResource( R.drawable.bt_style);
            constraintLayout.addView(btn);

            final int j=i;
            final AttendanceData attendanceData=new AttendanceData();
            attendanceData.setSfname(listt[i]);
            final int finalI = i;
            btn.setOnTouchListener(new OnSwipeTouchListener(MarkAttendance.this){
                public void onSwipeTop() {
                    Toast.makeText(MarkAttendance.this, "top--", Toast.LENGTH_SHORT).show();
                }
                public void onSwipeRight() {
                    attendanceData.setStatus("Present");
                    btn.setTextColor(Color.WHITE);
                    btn.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
                    btn.setText(listt[finalI]+"     ---------> Marked Present");

                    btn.setBackgroundColor(Color.parseColor("#6825F48A"));
                    Toast.makeText(MarkAttendance.this,"--"+subStr,Toast.LENGTH_SHORT).show();
                    int flag=0;
                    if(SaveSharedPreference.getPREF_lec_count(MarkAttendance.this).equals("")){
                        flag=db.MscCaAttendance(attendanceData,subStr,"","");
                    }else{
                        flag=db.MscCaAttendance(attendanceData,subStr,SaveSharedPreference.getPREF_lec_count(MarkAttendance.this),SaveSharedPreference.getPrefLecDate(MarkAttendance.this));
                    }

                    if(flag==1){
                        Toast.makeText(getApplicationContext(),"Attendance Marked",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Error Marking Attendance",Toast.LENGTH_SHORT).show();
                    }
                }
                public void onSwipeLeft() {
                    btn.setBackgroundColor(Color.parseColor("#5BF44336"));
                    attendanceData.setStatus("Absent");
                    btn.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                    btn.setTextColor(Color.WHITE);
                    btn.setText("Marked Absent <---------     "+listt[finalI]);

                    int flag=0;
                    if(SaveSharedPreference.getPREF_lec_count(MarkAttendance.this).equals("")){
                        flag=db.MscCaAttendance(attendanceData,subStr,"","");
                    }else{
                        flag=db.MscCaAttendance(attendanceData,subStr,SaveSharedPreference.getPREF_lec_count(MarkAttendance.this),SaveSharedPreference.getPrefLecDate(MarkAttendance.this));
                    }

                    if(flag==1){
                        Toast.makeText(getApplicationContext(),"Attendance Marked",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Error Marking Attendance",Toast.LENGTH_SHORT).show();
                    }
                }
                public void onSwipeBottom() {
                    Toast.makeText(MarkAttendance.this, "bottom", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}