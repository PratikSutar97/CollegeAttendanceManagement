package com.example.attendancemanagementsystem;

public class FacultyData {
    public int fid;
    public String ffname,fcontact,faddress,username,password,subject1,subject2;

    public int getfid(){
        return fid;
    }
    public void setfid(int fid){
        this.fid=fid;
    }

    public String getffname(){
        return ffname;
    }
    public void setffname(String fname){
        this.ffname=fname;
    }

    public String getfcontact(){
        return fcontact;
    }
    public void setfcontact(String fcontact){
        this.fcontact=fcontact;
    }

    public String getfaddress(){
        return faddress;
    }
    public void setfaddress(String faddress){
        this.faddress=faddress;
    }

    public String getusername(){
        return username;
    }
    public void setusername(String username){
        this.username=username;
    }

    public String getpassword(){
        return password;
    }
    public void setpassword(String password){
        this.password=password;
    }

    public void setSubject1(String subject){
        this.subject1=subject;
    }
    public String getSubject1(){
        return subject1;
    }

    public void setSubject2(String subject){
        this.subject2=subject;
    }
    public String getSubject2(){
        return subject2;
    }
}
