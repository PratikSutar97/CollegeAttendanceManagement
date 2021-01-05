package com.example.attendancemanagementsystem.Attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.HorizontalScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.attendancemanagementsystem.DBAdapter;
import com.example.attendancemanagementsystem.R;
import com.example.attendancemanagementsystem.SaveSharedPreference;
import com.example.attendancemanagementsystem.StudentData;

import java.util.ArrayList;

public class ViewAttendance extends AppCompatActivity {
    TextView viewSubject;
    ArrayList<AttendanceData> studentBeanList;
    TableLayout table;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);

        table=findViewById(R.id.table);
        viewSubject=findViewById(R.id.textViewSubject);
        viewSubject.setText("( "+SaveSharedPreference.getPrefSubject(ViewAttendance.this)+" )");
        TableRow tbrow0=new TableRow(this);
        TextView tv0=new TextView(this);
        tv0.setText("Sr No.");
        tv0.setTextSize(20);
        tv0.setGravity(Gravity.CENTER);
        tv0.setTextColor(Color.WHITE);
        tbrow0.addView(tv0);

        TextView tv1=new TextView(this);
        tv1.setText("Name");
        tv1.setTextSize(20);
        tv1.setGravity(Gravity.CENTER);
        tv1.setTextColor(Color.WHITE);
        tbrow0.addView(tv1);

        TextView tv2=new TextView(this);
        tv2.setText("Date");
        tv2.setTextSize(20);
        tv2.setGravity(Gravity.CENTER);
        tv2.setTextColor(Color.WHITE);
        tbrow0.addView(tv2);

        TextView tv3=new TextView(this);
        tv3.setText("P / A");
        tv3.setTextSize(20);
        tv3.setTextColor(Color.WHITE);
        tv3.setGravity(Gravity.CENTER);
        tbrow0.addView(tv3);

        table.addView(tbrow0);

        TableRow.LayoutParams id= (TableRow.LayoutParams) tv0.getLayoutParams();
        id.width= ActionBar.LayoutParams.MATCH_PARENT;
        table.setLayoutParams(id);

        TableRow.LayoutParams name= (TableRow.LayoutParams) tv1.getLayoutParams();
        name.width=ActionBar.LayoutParams.MATCH_PARENT;
        name.leftMargin=10;
        table.setLayoutParams(name);

        TableRow.LayoutParams contact= (TableRow.LayoutParams) tv2.getLayoutParams();
        contact.width=ActionBar.LayoutParams.MATCH_PARENT;
        contact.leftMargin=50;
        table.setLayoutParams(contact);

        TableRow.LayoutParams course= (TableRow.LayoutParams) tv3.getLayoutParams();
        course.width=ActionBar.LayoutParams.MATCH_PARENT;
        course.leftMargin=50;
        table.setLayoutParams(course);

        loadData();
    }
    public void loadData(){

        DBAdapter db=new DBAdapter(this);
        studentBeanList=db.getAllAttendance(SaveSharedPreference.getPrefSubject(ViewAttendance.this));
        int len=studentBeanList.size();
        int i=0;
        for(i=0;i<len;i++){
            AttendanceData arr=studentBeanList.get(i);
            TableRow tbrow=new TableRow(this);

            TextView tvData=new TextView(this);
            tvData.setText(""+(i+1));
            tvData.setTextSize(20);
            tvData.setTextColor(Color.WHITE);
            tvData.setGravity(Gravity.CENTER);
            tbrow.addView(tvData);

            TextView tvData1=new TextView(this);
            tvData1.setText(arr.getSfname());
            tvData1.setTextSize(20);
            tvData1.setGravity(Gravity.CENTER);
            tvData1.setTextColor(Color.WHITE);
            tbrow.addView(tvData1);

            TextView tvData2=new TextView(this);
            tvData2.setText(arr.getDate());
            tvData2.setTextSize(20);
            tvData2.setGravity(Gravity.CENTER);
            tvData2.setTextColor(Color.WHITE);
            tbrow.addView(tvData2);

            TextView tvData3=new TextView(this);
            tvData3.setText(arr.getStatus());
            tvData3.setTextSize(20);
            tvData3.setGravity(Gravity.CENTER);
            tvData3.setTextColor(Color.WHITE);
            tbrow.addView(tvData3);

            table.addView(tbrow);

            TableLayout.LayoutParams lp= (TableLayout.LayoutParams) tbrow.getLayoutParams();
            lp.height= ActionBar.LayoutParams.WRAP_CONTENT;
            table.setLayoutParams(lp);;

///////////-----dynamic text tiews ////////////////////////

            TableRow.LayoutParams name1= (TableRow.LayoutParams) tvData1.getLayoutParams();
            name1.width=ActionBar.LayoutParams.MATCH_PARENT;
            name1.leftMargin=0;
            table.setLayoutParams(name1);

            TableRow.LayoutParams contact= (TableRow.LayoutParams) tvData2.getLayoutParams();
            contact.width=ActionBar.LayoutParams.MATCH_PARENT;
            contact.leftMargin=50;
            table.setLayoutParams(contact);

            TableRow.LayoutParams course= (TableRow.LayoutParams) tvData3.getLayoutParams();
            course.width=ActionBar.LayoutParams.MATCH_PARENT;
            course.leftMargin=50;
            table.setLayoutParams(course);
        }
    }
}