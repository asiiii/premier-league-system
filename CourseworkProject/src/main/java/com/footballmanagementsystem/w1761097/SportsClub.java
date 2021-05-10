package com.footballmanagementsystem.w1761097;
import java.util.*;

public abstract class SportsClub implements java.io.Serializable{
    private String clubName;
    private String clubLocation;
    private String coachName;
    private int noOfMembers;
    private int noOfEmployees;

    public SportsClub(){

    }

    public SportsClub(String clubName, String clubLocation, String coachName, int noOfMembers, int noOfEmployees){
        this.clubName = clubName;
        this.clubLocation = clubLocation;
        this.coachName = coachName;
        this.noOfMembers = noOfMembers;
        this.noOfEmployees = noOfEmployees;
    }

    //getters and setters for name of club
    public String getClubName(){
        return clubName;
    }

    public void setClubName(String clubName){
        this.clubName = clubName;
    }

    //getters and setters for location of club
    public String getClubLocation(){
        return clubLocation;
    }

    public void setClubLocation(String clubLocation){
        this.clubLocation = clubLocation;
    }

    //getters and setters for the coach of the club
    public String getCoachName(){
        return clubLocation;
    }

    public void setCoachName(String coachName){
        this.coachName = coachName;
    }

    // getters and setters for the number of members in the club
    public int getNoOfMembers(){
        return noOfMembers;
    }

    public void setNoOfMembers(int noOfMembers){
        this.noOfMembers = noOfMembers;
    }

    //getters and setters for the number of employees working for the club
    public int getNoOfEmployees(){
        return noOfEmployees;
    }

    public void setNoOfEmployees(int noOfEmployees){
        this.noOfEmployees = noOfEmployees;
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
        SportsClub sportsClub=(SportsClub) o;
        return Objects.equals(clubName, sportsClub.clubName);
                		/*Objects.equals(clubLocation, sportsClub.clubLocation) &&
                		Objects.equals(coachName, sportsClub.coachName) &&
                		Objects.equals(noOfMembers, sportsClub.noOfMembers) &&
                		Objects.equals(noOfEmployees, sportsClub.noOfEmployees);*/
    }

    //hashCode() method
    @Override
    public int hashCode(){
        return Objects.hash(clubName, clubLocation, coachName, noOfMembers, noOfEmployees);
    }

    @Override
    public String toString(){
        return "Sports Club: " + clubName
                + "\n Location : " + clubLocation
                + "\n Coach: " + coachName
                + "\n No of Members: " + noOfMembers
                + "\n No of Employees: " + noOfEmployees;
    }
}
