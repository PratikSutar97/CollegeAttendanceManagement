package com.example.attendancemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListStudentsByCourse extends AppCompatActivity {

    ArrayList<StudentData> studentBeanList;
    Button btnViewStudent;

    TextView id,name,contact,course,year,textView;
    TableLayout table;
    Spinner courseSpinner;
    String courseStr;
    private String[] courseArr=new String[]{"Bsc CS","Msc CS","Bsc CA","Msc CA"};
    private ArrayAdapter<String> listAdapter;
    DBAdapter db=new DBAdapter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_students_by_course);

        textView=findViewById(R.id.textView1);
        btnViewStudent=findViewById(R.id.viewStudent);
        courseSpinner=findViewById(R.id.spinnerCourse);
        Toast.makeText(ListStudentsByCourse.this,"Retrived Name :- ",Toast.LENGTH_SHORT);
        table=findViewById(R.id.table);
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

        btnViewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });

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

        table.addView(tbrow0);
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
    }
    public void loadData(){

        studentBeanList=db.getStudentsByCourse(courseStr);

        if(studentBeanList==null){
            textView.setText("No data");
        }else{


            int len=studentBeanList.size();
            int i=0;
            for(i=0;i<len;i++){
                StudentData arr=studentBeanList.get(i);
                TableRow tbrow=new TableRow(this);

                TextView tvData=new TextView(this);
                tvData.setText(""+(arr.getSid()));
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

            }


        }
    }
}