package com.example.attendancemanagementsystem;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.attendancemanagementsystem.Attendance.AttendanceData;
import com.example.attendancemanagementsystem.Attendance.SelectCourse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DBAdapter extends SQLiteOpenHelper {

    private static final String FACULTY = "faculty";
    private static final String STUDENT = "student";
    private static final String ATTENDANCE_SESSION = "attendance_session";
    private static final String ATTENDANCE = "attendance";

    private static final String FACULTY_ID = "faculty_id";
    private static final String FACULTY_FIRSTNAME = "faculty_firstname";

    private static final String FACULTY_MO_NO = "faculty_mobilenumber";
    private static final String FACULTY_ADDRESS = "faculty_address";
    private static final String FACULTY_USERNAME = "faculty_username";
    private static final String FACULTY_PASSWORD = "faculty_password";
    private static final String FACULTY_SUBJECT1= "faculty_subject1";
    private static final String FACULTY_SUBJECT2= "faculty_subject2";

    private static final String STUDENT_ID = "student_id";
    private static final String STUDENT_FIRSTNAME = "student_firstname";
    private static final String STUDENT_MO_NO = "student_mobilenumber";
    private static final String STUDENT_ADDRESS = "student_address";
    private static final String STUDENT_COURSE = "student_course";
    private static final String STUDENT_YEAR = "student_year";
    private static final String STUDENT_SUBJECT1 = "student_subject1";
    private static final String STUDENT_SUBJECT2 = "student_subject2";
    private static final String STUDENT_SUBJECT3 = "student_subject3";
    private static final String STUDENT_SUBJECT4 = "student_subject4";

    private static final String ATTENDANCE_SESSION_ID = "attendance_session_id";
    private static final String ATTENDANCE_SESSION_FACULTY_ID = "attendance_session_faculty_id";
    private static final String ATTENDANCE_SESSION_DEPARTMENT = "attendance_session_department";
    private static final String ATTENDANCE_SESSION_CLASS = "attendance_session_class";
    private static final String ATTENDANCE_SESSION_DATE = "attendance_session_date";
    private static final String ATTENDANCE_SESSION_SUBJECT = "attendance_session_subject";

    private static final String SESSION_ID = "attendance_session_id";
    private static final String ATTENDANCE_STUDENT_ID = "attendance_student_id";
    private static final String ATTENDANCE_STATUS = "attendance_status";

    public DBAdapter(@Nullable Context context) {
        super(context,"Attendance",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String queryFaculty="CREATE TABLE "+FACULTY+"("+
//                FACULTY_ID+"INTEGER PRIMARY KEY AUTOINCREMENT,"+
//                FACULTY_FIRSTNAME+"TEXT,"+
//                FACULTY_LASTNAME+"TEXT,"+
//                FACULTY_MO_NO + " TEXT, " +
//                FACULTY_ADDRESS + " TEXT," +
//                FACULTY_USERNAME + " TEXT," +
//                FACULTY_PASSWORD + " TEXT " + ")";
//
//        String queryStudent = "CREATE TABLE " + STUDENT + " (" +
//                STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                STUDENT_FIRSTNAME + " TEXT, " +
//                STUDENT_LASTNAME + " TEXT, " +
//                STUDENT_MO_NO + " TEXT, " +
//                STUDENT_ADDRESS + " TEXT," +
//                STUDENT_COURSE + " TEXT," +
//                STUDENT_YEAR + " TEXT " + ")";
//
//        String queryAttendanceSession = "CREATE TABLE " + ATTENDANCE + " (" +
//                ATTENDANCE_SESSION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                ATTENDANCE_SESSION_FACULTY_ID + " INTEGER, " +
//                ATTENDANCE_SESSION_DEPARTMENT + " TEXT, " +
//                ATTENDANCE_SESSION_CLASS + " TEXT, " +
//                ATTENDANCE_SESSION_DATE + " DATE," +
//                ATTENDANCE_SESSION_SUBJECT + " TEXT" + ")";
//
//        String queryAttendance = "CREATE TABLE " + ATTENDANCE + " (" +
//                SESSION_ID + " INTEGER, " +
//                ATTENDANCE_STUDENT_ID + " INTEGER, " +
//                ATTENDANCE_STATUS + " TEXT " + ")";
//
//        try{
//            db.execSQL(queryFaculty);
//            db.execSQL(queryStudent);
//            db.execSQL(queryAttendanceSession);
//            db.execSQL(queryAttendance);
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            Log.e("Create TAble","Create Table Failed");
//        }

    }


    //////////////////////////////////// STUDENT QUERIES //////////////////////////////////////


    public int deleteAllStudents(){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="delete from student";

        try{
            db.execSQL(query);
            return 1;
        }catch(Exception e){
            Log.e("Delete Error","Cannot delete Students"+e);
            return 0;
        }
    }

    public ArrayList<String> getStudentBySubject(String sub){
        SQLiteDatabase db=this.getWritableDatabase();
        ArrayList<String> arr=new ArrayList<>();
        String q="select student_firstname from student where student_subject1='"+sub+"' or student_subject2='"+sub+"' or student_subject3='"+sub+"' or student_subject4='"+sub+"'";

        Cursor c=db.rawQuery(q,null);

        if(c.moveToNext()){
            do{
                StudentData sd=new StudentData();
                sd.setSfname(c.getString(0));
                arr.add(c.getString(0));

            }while (c.moveToNext());
        }
        c.close();
        if(c==null)
            return null;
        else
            return arr;
    }

    public  ArrayList<StudentData> getStudentsByCourse(String Course){
        SQLiteDatabase db=this.getWritableDatabase();

        ArrayList<StudentData> arr=new ArrayList<>();
        String query="select * from student where student_course='"+Course+"'";
        Cursor cursor=db.rawQuery(query,null);

        if(cursor.moveToNext()){
            do{
                StudentData studentData=new StudentData();
                studentData.setSid(Integer.parseInt(cursor.getString(0)));
                studentData.setSfname(cursor.getString(1));
                studentData.setScontact(cursor.getString(2));
                studentData.setCourse(cursor.getString(4));
                studentData.setYear(cursor.getString(5));
                arr.add(studentData);
            }while (cursor.moveToNext());
        }

        cursor.close();
        if(cursor==null)
            return null;
        else
            return arr;
    }


    public ArrayList<StudentData> getAllStudents(){
        SQLiteDatabase db=this.getWritableDatabase();
        StudentData[] data=new StudentData[40];

        ArrayList<StudentData> arr=new ArrayList<>();
        String query="select * from student";
        Cursor cursor=db.rawQuery(query,null);

        if(cursor.moveToNext()){
            do{
                StudentData studentData=new StudentData();
                studentData.setSid(Integer.parseInt(cursor.getString(0)));
                studentData.setSfname(cursor.getString(1));
                studentData.setScontact(cursor.getString(2));
                studentData.setCourse(cursor.getString(4));
                studentData.setYear(cursor.getString(5));
                studentData.setSub1(cursor.getString(6));
                studentData.setSub2(cursor.getString(7));
                studentData.setSub3(cursor.getString(8));
                studentData.setSub4(cursor.getString(9));
                arr.add(studentData);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return arr;
    }

    public int addStudent(StudentData studentData){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        String att1="insert into '"+studentData.getSub1()+"' (student_firstname) values ('"+studentData.getSfname()+"')";
        sqLiteDatabase.execSQL(att1);
        String att2="insert into '"+studentData.getSub2()+"' (student_firstname) values ('"+studentData.getSfname()+"')";
        sqLiteDatabase.execSQL(att2);
        String att3="insert into '"+studentData.getSub3()+"' (student_firstname) values ('"+studentData.getSfname()+"')";
        sqLiteDatabase.execSQL(att3);
        String att4="insert into '"+studentData.getSub4()+"' (student_firstname) values ('"+studentData.getSfname()+"')";
        sqLiteDatabase.execSQL(att4);

        String queryStudent = "CREATE TABLE IF NOT EXISTS " + STUDENT + " (" +
                STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT DEfault 0, " +
                STUDENT_FIRSTNAME + " TEXT, " +
                STUDENT_MO_NO + " TEXT, " +
                STUDENT_ADDRESS + " TEXT," +
                STUDENT_COURSE + " TEXT," +
                STUDENT_YEAR + " TEXT," +
                STUDENT_SUBJECT1 + " TEXT," +
                STUDENT_SUBJECT2 + " TEXT," +
                STUDENT_SUBJECT3 + " TEXT," +
                STUDENT_SUBJECT4 + " TEXT " + ")";

        String query="insert into "+STUDENT+"("+STUDENT_FIRSTNAME+","+STUDENT_MO_NO+","+STUDENT_ADDRESS+","+STUDENT_COURSE+","+STUDENT_YEAR+","+STUDENT_SUBJECT1+","+STUDENT_SUBJECT2+","+STUDENT_SUBJECT3+","+STUDENT_SUBJECT4+")" +
                " values('"+studentData.getSfname()+"'," +
                "'"+studentData.getScontact()+"'," +
                "'"+studentData.getSaddress()+"'," +
                "'"+studentData.getCourse()+"'," +
                "'"+studentData.getYear()+"'," +
                "'"+studentData.getSub1()+"'," +
                "'"+studentData.getSub2()+"'," +
                "'"+studentData.getSub3()+"'," +
                "'"+studentData.getSub4()+"')";

        try {
            sqLiteDatabase.execSQL(queryStudent);
            sqLiteDatabase.execSQL(query);
            sqLiteDatabase.close();
            Log.e("add student","Data Inserted ");
            return 1;
        }catch (Exception e){
            Log.e("add student","Error--- "+e);
            return 0;
        }

    }
    //////////////////////////////////// ATTENDANCE //////////////////////////////////////////////////////

    public int insertformorelectures(){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="delete from faculty";
        db.execSQL(query);
        return 0;
    }

    public int getcount(String sub){

        SQLiteDatabase db=this.getWritableDatabase();
        String query="select * from '"+sub+"' where lec_date='"+getDateTime()+"'";
        Cursor c;
        int lec_countt=0;
        try {
            c = db.rawQuery(query, null);
            lec_countt=c.getCount();
            return lec_countt;
        }catch (Exception e){
            Log.e("Count Error","Errorr-- "+e);
        }
//        }
        return lec_countt;
    }

    public int deleteAttend(){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="delete from PYthon where lec_date='"+getDateTime()+"'";
        db.execSQL(query);
        return 0;
    }
    public int markAttendOnStart(String sub){
        SQLiteDatabase db=this.getWritableDatabase();
        int cnt=getcount(sub);
        int ofset=0;
        if(cnt>0){
            ofset=cnt-1;
            String getLecCount="select lec_count from '"+sub+"' where lec_date='"+getDateTime()+"' limit 1 offset '"+ofset+"' ";
            try {
                Cursor c1 = db.rawQuery(getLecCount, null);
                c1.moveToNext();
                cnt = Integer.parseInt(c1.getString(0));
            }catch (Exception e){
                Log.e("Errorr","Errorr-----"+cnt+"---"+e);
            }
            cnt++;
        }else{
            cnt++;
        }

        String getStudents="select student_firstname from student where student_subject1='"+sub+"' or student_subject2='"+sub+"' or student_subject3='"+sub+"' or student_subject4='"+sub+"' ";
        Cursor cursor=db.rawQuery(getStudents,null);

        if(cursor.moveToNext()){
            do{
                String q="insert into '"+sub+"' (student_firstname,lec_date,lec_count) values( '"+cursor.getString(0)+"','"+getDateTime()+"','"+cnt+"'  )";
                db.execSQL(q);
            }while (cursor.moveToNext());
        }
        return  0;
    }
    public int MscCaAttendance(AttendanceData attendanceData,String sub){
        SQLiteDatabase db=this.getWritableDatabase();
        String updateQuery="update '"+sub+"' set status='"+attendanceData.getStatus()+"' where student_firstname='"+attendanceData.getSfname()+"' and lec_date='"+getDateTime()+"' ";

        try{
            db.execSQL(updateQuery);
            return 1;
        }catch (Exception e){
            Log.e("Attendacne Error","Attendance Error ---"+e);
            return 0;
        }
    }

    public ArrayList<AttendanceData> getAllAttendance(String course){
        SQLiteDatabase db=this.getWritableDatabase();

        ArrayList<AttendanceData> arr=new ArrayList<>();
        String query="select * from '"+course+"'";
        Cursor cursor;
        try {
             cursor = db.rawQuery(query, null);
        }catch (Exception e){
            Log.e("---error",""+e);

            return null;
        }

        if(cursor.moveToNext()){
            do{
                AttendanceData attendanceData=new AttendanceData();
                attendanceData.setSfname(cursor.getString(0));
                attendanceData.setStatus(cursor.getString(2));
                attendanceData.setDate(cursor.getString(1));
                attendanceData.setcount(cursor.getString(3));
                arr.add(attendanceData);
            }while (cursor.moveToNext());
        }

        cursor.close();
        if(cursor==null)
            return null;
        else
            return arr;
    }
    public String getDateTime() {

        SimpleDateFormat dateFormat = new SimpleDateFormat(

                "dd-MM-yyyy ", Locale.getDefault());

        Date date = new Date();

        return dateFormat.format(date);
    }
    /////////////////////////////////// FACULTY QUERIES //////////////////////////////////////
    public int deleteAllFaculty(){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="delete from faculty";

        try{
            db.execSQL(query);
            return 1;
        }catch(Exception e){
            Log.e("Delete Error","Cannot delete Faculty Data");
            return 0;
        }
    }


    public FacultyData validateFaculty(String username,String password){

        SQLiteDatabase db=this.getWritableDatabase();
        String query="select * from faculty where faculty_username='"+username+"'and faculty_password='"+password+"'";
        Cursor cursor=db.rawQuery(query,null);

        if(cursor.moveToNext()){
            FacultyData facultyData = new FacultyData();
            facultyData.setfid(Integer.parseInt(cursor.getString(0)));
            facultyData.setffname(cursor.getString(1));
            facultyData.setfcontact(cursor.getString(2));
            facultyData.setfaddress(cursor.getString(3));
            facultyData.setusername(cursor.getString(4));
            facultyData.setpassword(cursor.getString(5));
            facultyData.setSubject1(cursor.getString(6));
          facultyData.setSubject2(cursor.getString(7));
            cursor.close();
            return facultyData;
        }
        return null;
    }

    public String getPassword(String username){
        SQLiteDatabase db=getWritableDatabase();
        String password="";
        String query="select faculty_password from faculty where faculty_username='"+username+"'";
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToNext()){
            password=cursor.getString(0);
        }
        return password;
    }

    public int updatePassword(String username,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="update faculty set faculty_password='"+password+"' where faculty_username='"+username+"'";
        try{
            db.execSQL(query);
            return 1;
        }catch(Exception e){
            Log.e("Update Pass","Error --"+e);
            return 0;
        }
    }

    public ArrayList<FacultyData> getAllFaculty(){
        SQLiteDatabase db=this.getWritableDatabase();
        FacultyData[] data=new FacultyData[40];

        ArrayList<FacultyData> arr=new ArrayList<>();
        String query="select * from faculty";
        Cursor cursor=db.rawQuery(query,null);

        if(cursor.moveToNext()){
            do{
                cursor.getColumnCount();
                FacultyData facultyData=new FacultyData();
                facultyData.setfid(Integer.parseInt(cursor.getString(0)));
                facultyData.setffname(cursor.getString(1));
                facultyData.setfcontact(cursor.getString(2));
                facultyData.setusername(cursor.getString(4));
                facultyData.setpassword(cursor.getString(5));
                facultyData.setSubject1(cursor.getString(6));
                facultyData.setSubject2(cursor.getString(7));
                arr.add(facultyData);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return arr;
    }

    public ArrayList<FacultyData> getFacultyByUsername(String username){
        SQLiteDatabase db=this.getWritableDatabase();
        FacultyData[] data=new FacultyData[40];

        ArrayList<FacultyData> arr=new ArrayList<>();
        String query="select * from faculty where faculty_username='"+username+"'";
        Cursor cursor=db.rawQuery(query,null);

        if(cursor.moveToNext()){
            do{
                FacultyData facultyData=new FacultyData();
                facultyData.setfid(Integer.parseInt(cursor.getString(0)));
                facultyData.setffname(cursor.getString(1));
                facultyData.setfcontact(cursor.getString(2));
                facultyData.setfaddress(cursor.getString(3));
                facultyData.setusername(cursor.getString(4));
                facultyData.setpassword(cursor.getString(5));
                facultyData.setSubject1(cursor.getString(6));
                facultyData.setSubject2(cursor.getString(7));
                arr.add(facultyData);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return arr;
    }
    public int addFaculty(FacultyData facultyData){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        String queryFaculty = "CREATE TABLE IF NOT EXISTS " + FACULTY + " (" +
                FACULTY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT DEfault 0, " +
                FACULTY_FIRSTNAME + " TEXT, " +
                FACULTY_MO_NO + " TEXT, " +
                FACULTY_ADDRESS + " TEXT," +
                FACULTY_USERNAME + " TEXT," +
                FACULTY_PASSWORD + " TEXT, " +
                FACULTY_SUBJECT1 + " TEXT, " +
                FACULTY_SUBJECT2 + " TEXT " + ")";

        String query="insert into "+FACULTY+"("+FACULTY_FIRSTNAME+","+FACULTY_MO_NO+","+FACULTY_ADDRESS+","+FACULTY_USERNAME+","+FACULTY_PASSWORD+","+FACULTY_SUBJECT1+","+FACULTY_SUBJECT2+")" +
                " values('"+facultyData.getffname()+"'," +
                "'"+facultyData.getfcontact()+"'," +
                "'"+facultyData.getfaddress()+"'," +
                "'"+facultyData.getusername()+"'," +
                "'"+facultyData.getpassword()+"'," +
                "'"+facultyData.getSubject1()+"'," +
                "'"+facultyData.getSubject2()+"')";

        try {
            sqLiteDatabase.execSQL(queryFaculty);
            sqLiteDatabase.execSQL(query);
            sqLiteDatabase.close();
            Log.e("add faculty","Data Inserted ");
            return 1;
        }catch (Exception e){
            Log.e("add faculty","Error--- "+e);
            return 0;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
