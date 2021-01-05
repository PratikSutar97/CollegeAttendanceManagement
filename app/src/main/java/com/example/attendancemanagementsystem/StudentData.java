package com.example.attendancemanagementsystem;

public class StudentData {
    public int sid;
    public String sfname,slname,scontact,saddress,course,year,sub1,sub2,sub3,sub4,sub5;

    public int getSid(){
        return sid;
    }
    public void setSid(int sid){this.sid=sid;}

    public String getSfname(){
        return sfname;
    }
    public void setSfname(String fname){
        this.sfname=fname;
    }
    public String getScontact(){return scontact;}
    public void setScontact(String scontact){
        this.scontact=scontact;
    }

    public String getSaddress(){
        return saddress;
    }
    public void setSaddress(String saddress){this.saddress=saddress;}

    public String getCourse(){
        return course;
    }
    public void setCourse(String course){
        this.course=course;
    }

    public String getYear(){
        return year;
    }
    public void setYear(String year){this.year=year;}

    public String getSub1(){return sub1;}
    public void setSub1(String sub1){this.sub1=sub1;};

    public String getSub2(){return sub2;}
    public void setSub2(String sub2){this.sub2=sub2;};

    public String getSub3(){return sub3;}
    public void setSub3(String sub3){this.sub3=sub3;};

    public String getSub4(){return sub4;}
    public void setSub4(String sub4){this.sub4=sub4;};



    private boolean isSelected;
    private String animal;
    public String getAnimal() {
        return animal;
    }
    public void setAnimal(String animal) {
        this.animal = animal;
    }
    public boolean getSelected() {
        return isSelected;
    }
    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
