package com.footballmanagementsystem.w1761097;

import java.util.*;

public class UniversityFootballClub extends FootballClub{
    private String nameOfUniversity;
    private String lecturerInCharge;

    //default constructor
    public UniversityFootballClub(){
        System.out.println("University Football club successfully added.");
    }

    //getter and setter for the name of the university
    public String getNameOfUniversity(){
        return nameOfUniversity;
    }

    public void setNameOfUniversity(String nameOfUniversity){
        this.nameOfUniversity=nameOfUniversity;
    }

    //getter and setter for the lecturer in charge of the university club
    public String getLecturerInCharge(){
        return lecturerInCharge;
    }

    public void setLecturerInCharge(String lecturerInCharge){
        this.lecturerInCharge=lecturerInCharge;
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
        UniversityFootballClub uniFootballClub=(UniversityFootballClub) o;
        return Objects.equals(nameOfUniversity, uniFootballClub.nameOfUniversity) &&
                Objects.equals(lecturerInCharge, uniFootballClub.lecturerInCharge);
    }

    //hashCode() method
    @Override
    public int hashCode(){
        return Objects.hash(nameOfUniversity, lecturerInCharge);
    }
}

