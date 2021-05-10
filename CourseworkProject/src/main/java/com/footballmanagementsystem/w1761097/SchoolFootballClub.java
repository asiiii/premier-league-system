package com.footballmanagementsystem.w1761097;

import java.util.*;

public class SchoolFootballClub extends FootballClub{
    private String nameOfSchool;
    private String teacherInCharge;

    //default constructor
    public SchoolFootballClub(){
        System.out.println("School Football club successfully added.");
    }

    //getter and setter for the name of the school
    public String getNameOfSchool(){
        return nameOfSchool;
    }

    public void setNameOfSchool(String nameOfSchool){
        this.nameOfSchool=nameOfSchool;
    }

    //getter and setter for the teacher in charge of the school club
    public String getTeacherInCharge(){
        return teacherInCharge;
    }

    public void setTeacherInCharge(String nameOfSchool){
        this.teacherInCharge = teacherInCharge;
    }

    //equals() method check whether attributes of the class are equal to the ones passed in the parameter and returns the boolean result
    @Override
    public boolean equals(Object o){
        if(this==o){
            return true;
        }
        if(o == null || getClass()!=o.getClass()){
            return false;
        }
        SchoolFootballClub schoolFootballClub=(SchoolFootballClub) o;
        return Objects.equals(nameOfSchool, schoolFootballClub.nameOfSchool) &&
                Objects.equals(teacherInCharge, schoolFootballClub.teacherInCharge);
    }

    //hashCode() method
    @Override
    public int hashCode(){
        return Objects.hash(nameOfSchool, teacherInCharge);
    }
}
