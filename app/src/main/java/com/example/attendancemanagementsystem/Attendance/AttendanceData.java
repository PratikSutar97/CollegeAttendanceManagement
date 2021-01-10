package com.example.attendancemanagementsystem.Attendance;

public class AttendanceData {
    public String sfname,status;
    public String date,count;

    public void setDate(String date){
        this.date=date;
    }
    public String getDate(){
        return this.date;
    }
    public void setStatus(String status){
        this.status=status;
    }
    public String getStatus(){
        return this.status;
    }
    public void setSfname(String name){
        this.sfname=name;
    }
    public String getSfname(){
        return this.sfname;
    }
    public void setcount(String cnt){
        this.count=cnt;
    }
    public String getcount(){
        return this.count;
    }
}
