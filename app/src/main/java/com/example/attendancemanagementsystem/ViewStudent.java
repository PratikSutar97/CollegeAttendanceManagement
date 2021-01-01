package com.example.attendancemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ViewStudent extends AppCompatActivity {

    ArrayList<StudentData> studentBeanList;
    TextView id,name,contact,course,year;
    TableLayout table;
    ImageView logo;
    HorizontalScrollView scrollView;
    private ArrayAdapter<String> listAdapter;
    DBAdapter db=new DBAdapter(this);
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);

        Toast.makeText(ViewStudent.this,"Retrived Name :- ",Toast.LENGTH_SHORT);
        table=findViewById(R.id.table);
        logo=findViewById(R.id.logo_id);
        scrollView=findViewById(R.id.scroll);
        scrollView.setHorizontalScrollBarEnabled(true);
        scrollView.setVisibility(View.INVISIBLE);
        Glide.with(this).load(R.drawable.tenor1).into(logo);
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadData();
                scrollView.setVisibility(View.VISIBLE);
                logo.setVisibility(View.GONE);

            }
        },2000);

    }

    public void loadData(){
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
        tv2.setText("Contact");
        tv2.setTextSize(20);
        tv2.setGravity(Gravity.CENTER);
        tv2.setTextColor(Color.WHITE);
        tbrow0.addView(tv2);

        TextView tv3=new TextView(this);
        tv3.setText("Course");
        tv3.setTextSize(20);
        tv3.setTextColor(Color.WHITE);
        tv3.setGravity(Gravity.CENTER);
        tbrow0.addView(tv3);

        TextView tv4=new TextView(this);
        tv4.setText("Year");
        tv4.setTextSize(20);
        tv4.setGravity(Gravity.CENTER);
        tv4.setTextColor(Color.WHITE);
        tbrow0.addView(tv4);

        TextView tv5=new TextView(this);
        tv5.setText("Subject 1");
        tv5.setTextSize(20);
        tv5.setGravity(Gravity.CENTER);
        tv5.setTextColor(Color.WHITE);
        tbrow0.addView(tv5);

        TextView tv6=new TextView(this);
        tv6.setText("Subject 2");
        tv6.setTextSize(20);
        tv6.setGravity(Gravity.CENTER);
        tv6.setTextColor(Color.WHITE);
        tbrow0.addView(tv6);

        TextView tv7=new TextView(this);
        tv7.setText("Subject 3");
        tv7.setTextSize(20);
        tv7.setGravity(Gravity.CENTER);
        tv7.setTextColor(Color.WHITE);
        tbrow0.addView(tv7);

        TextView tv8=new TextView(this);
        tv8.setText("Subject 4");
        tv8.setTextSize(20);
        tv8.setGravity(Gravity.CENTER);
        tv8.setTextColor(Color.WHITE);
        tbrow0.addView(tv8);

        table.addView(tbrow0);
        studentBeanList=db.getAllStudents();
        int len=studentBeanList.size();
        int i=0;
        for(i=0;i<len;i++){
            StudentData arr=studentBeanList.get(i);
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
            tvData2.setText(arr.getScontact());
            tvData2.setTextSize(20);
            tvData2.setGravity(Gravity.CENTER);
            tvData2.setTextColor(Color.WHITE);
            tbrow.addView(tvData2);

            TextView tvData3=new TextView(this);
            tvData3.setText(arr.getCourse());
            tvData3.setTextSize(20);
            tvData3.setGravity(Gravity.CENTER);
            tvData3.setTextColor(Color.WHITE);
            tbrow.addView(tvData3);

            TextView tvData4=new TextView(this);
            tvData4.setText(arr.getYear());
            tvData4.setTextSize(20);
            tvData4.setGravity(Gravity.CENTER);
            tvData4.setTextColor(Color.WHITE);
            tbrow.addView(tvData4);

            TextView tvData5=new TextView(this);
            tvData5.setText(arr.getSub1());
            tvData5.setTextSize(20);
            tvData5.setGravity(Gravity.CENTER);
            tvData5.setTextColor(Color.WHITE);
            tbrow.addView(tvData5);

            TextView tvData6=new TextView(this);
            tvData6.setText(arr.getSub2());
            tvData6.setTextSize(20);
            tvData6.setGravity(Gravity.CENTER);
            tvData6.setTextColor(Color.WHITE);
            tbrow.addView(tvData6);

            TextView tvData7=new TextView(this);
            tvData7.setText(arr.getSub3());
            tvData7.setTextSize(20);
            tvData7.setGravity(Gravity.CENTER);
            tvData7.setTextColor(Color.WHITE);
            tbrow.addView(tvData7);

            TextView tvData8=new TextView(this);
            tvData8.setText(arr.getSub4());
            tvData8.setTextSize(20);
            tvData8.setGravity(Gravity.CENTER);
            tvData8.setTextColor(Color.WHITE);
            tbrow.addView(tvData8);

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

            TableRow.LayoutParams year= (TableRow.LayoutParams) tvData4.getLayoutParams();
            year.width=ActionBar.LayoutParams.MATCH_PARENT;
            year.leftMargin=50;
            table.setLayoutParams(year);

            TableRow.LayoutParams sub1= (TableRow.LayoutParams) tvData5.getLayoutParams();
            sub1.width=ActionBar.LayoutParams.MATCH_PARENT;
            sub1.leftMargin=50;
            table.setLayoutParams(sub1);

            TableRow.LayoutParams sub2= (TableRow.LayoutParams) tvData6.getLayoutParams();
            sub2.width=ActionBar.LayoutParams.MATCH_PARENT;
            sub2.leftMargin=50;
            table.setLayoutParams(sub2);

            TableRow.LayoutParams sub3= (TableRow.LayoutParams) tvData7.getLayoutParams();
            sub3.width=ActionBar.LayoutParams.MATCH_PARENT;
            sub3.leftMargin=50;
            table.setLayoutParams(sub3);

            TableRow.LayoutParams sub4= (TableRow.LayoutParams) tvData8.getLayoutParams();
            sub4.width=ActionBar.LayoutParams.MATCH_PARENT;
            sub4.leftMargin=50;
            table.setLayoutParams(sub4);

        }

        TableRow.LayoutParams id= (TableRow.LayoutParams) tv0.getLayoutParams();
        id.width=ActionBar.LayoutParams.MATCH_PARENT;
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

        TableRow.LayoutParams year= (TableRow.LayoutParams) tv4.getLayoutParams();
        year.width=ActionBar.LayoutParams.MATCH_PARENT;
        year.leftMargin=50;
        table.setLayoutParams(year);

        TableRow.LayoutParams sub1= (TableRow.LayoutParams) tv5.getLayoutParams();
        sub1.width=ActionBar.LayoutParams.MATCH_PARENT;
        sub1.leftMargin=50;
        table.setLayoutParams(sub1);

        TableRow.LayoutParams sub2= (TableRow.LayoutParams) tv6.getLayoutParams();
        sub2.width=ActionBar.LayoutParams.MATCH_PARENT;
        sub2.leftMargin=50;
        table.setLayoutParams(sub2);

        TableRow.LayoutParams sub3= (TableRow.LayoutParams) tv7.getLayoutParams();
        sub3.width=ActionBar.LayoutParams.MATCH_PARENT;
        sub3.leftMargin=50;
        table.setLayoutParams(sub3);

        TableRow.LayoutParams sub4= (TableRow.LayoutParams) tv8.getLayoutParams();
        sub4.width=ActionBar.LayoutParams.MATCH_PARENT;
        sub4.leftMargin=50;
        table.setLayoutParams(sub4);

    }
}