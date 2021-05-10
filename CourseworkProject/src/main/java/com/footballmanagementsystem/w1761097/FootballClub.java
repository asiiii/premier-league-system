package com.footballmanagementsystem.w1761097;

import java.util.*;

public class FootballClub extends SportsClub implements Comparable<FootballClub>{
    private int noOfWins;
    private int noOfDraws;
    private int noOfDefeats;
    private int noOfGoalsReceived;
    private int noOfGoalsScored;
    private int noOfPoints;
    private int noOfMatchesPlayed;
    private int goalDifference;

    //default constructor
    public FootballClub(){ }

    public FootballClub(String clubName, String clubLocation, String coachName, int noOfMembers, int noOfEmployees,
                        int noOfWins, int noOfDraws, int noOfDefeats, int noOfGoalsReceived, int noOfGoalsScored, int noOfMatchesPlayed, int noOfPoints, int goalDifference){
        super(clubName, clubLocation, coachName, noOfMembers, noOfEmployees);
        this.noOfWins = noOfWins;
        this.noOfDraws=noOfDraws;
        this.noOfDefeats=noOfDefeats;
        this.noOfGoalsReceived=noOfGoalsReceived;
        this.noOfGoalsScored=noOfGoalsScored;
        this.noOfMatchesPlayed=noOfMatchesPlayed;
        this.noOfPoints = noOfPoints;
        this.goalDifference=goalDifference;
    }

    public FootballClub(String clubName, int noOfPoints, int noOfWins, int noOfDraws, int noOfDefeats, int noOfGoalsScored, int noOfGoalsReceived, int goalDifference){
        this.setClubName(clubName);
        this.noOfPoints=noOfPoints;
        this.noOfWins = noOfWins;
        this.noOfDraws=noOfDraws;
        this.noOfDefeats=noOfDefeats;
        this.noOfGoalsReceived=noOfGoalsReceived;
        this.noOfGoalsScored=noOfGoalsScored;
        this.goalDifference=goalDifference;
    }

    //getters and setters for the number of wins in matched
    public int getNoOfWins(){
        return noOfWins;
    }

    public void setNoOfWins(int noOfWins){
        this.noOfWins = noOfWins;
    }

    //getters and setters for the number of draws in matches
    public int getNoOfDraws(){
        return noOfDraws;
    }

    public void setNoOfDraws(int noOfDraws){
        this.noOfDraws = noOfDraws;
    }

    //getters and setters for the number of losses in matches
    public int getNoOfDefeats(){
        return noOfDefeats;
    }

    public void setNoOfDefeats(int noOfDefeats){
        this.noOfDefeats = noOfDefeats;
    }

    //getters and setters for the total number of goals recieved
    public int getNoOfGoalsReceived(){
        return noOfGoalsReceived;
    }

    public void setNoOfGoalsReceived(int noOfGoalsReceived){
        this.noOfGoalsReceived = noOfGoalsReceived;
    }

    //getters and setters for the total number of goals scored
    public int getNoOfGoalsScored(){
        return noOfGoalsScored;
    }

    public void setNoOfGoalsScored(int noOfGoalsScored){
        this.noOfGoalsScored = noOfGoalsScored;
    }

    //getters and setters for the total number of points earned
    public int getNoOfPoints(){
        return noOfPoints;
    }

    public void setNoOfPoints(int noOfPoints){
        this.noOfPoints = noOfPoints;
    }

    //getters and setters for the number of matches played by a club
    public int getNoOfMatchesPlayed(){
        return noOfMatchesPlayed;
    }

    public void setNoOfMatchesPlayed(int noOfMatchesPlayed){
        this.noOfMatchesPlayed = noOfMatchesPlayed;
    }

    //getters and setters for the number of matches played by a club
    public int getGoalDifference(){
        return goalDifference;
    }

    public void setGoalDifference(int goalDifference){
        this.goalDifference = goalDifference;
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
        FootballClub footballClub=(FootballClub) o;
        return Objects.equals(noOfWins, footballClub.noOfWins) &&
                Objects.equals(noOfDraws, footballClub.noOfDraws) &&
                Objects.equals(noOfDefeats, footballClub. noOfDefeats) &&
                Objects.equals(noOfGoalsReceived, footballClub.noOfGoalsReceived) &&
                Objects.equals(noOfGoalsScored, footballClub.noOfGoalsScored) &&
                Objects.equals(noOfPoints, footballClub.noOfPoints) &&
                Objects.equals(noOfMatchesPlayed, footballClub.noOfMatchesPlayed);
    }

    //hashCode() method
    @Override
    public int hashCode(){
        return Objects.hash(noOfWins, noOfDraws, noOfDefeats, noOfGoalsReceived, noOfGoalsScored, noOfPoints, noOfMatchesPlayed);
    }

    //compareTo method() to compare the no of points of two clubs and return the result in integer
    @Override
    public int compareTo(FootballClub footballClub){
        if(noOfPoints == footballClub.noOfPoints){
            return 0;
        }else if(noOfPoints>footballClub.noOfPoints){
            return 1;
        }else{
            return -1;
        }
    }

    @Override
    public String toString(){
        return "Football Club: " + getClubName()
                + "\n   No of wins: " + noOfWins
                + "\n   No of draws: " + noOfDraws
                + "\n   No of defeats: " + noOfDefeats
                + "\n   No of goals received: " + noOfGoalsReceived
                + "\n   No of goals scored: " + noOfGoalsScored
                + "\n   No of matches played: " + noOfMatchesPlayed
                + "\n   No of points: " + noOfPoints;
    }

    public static Comparator<FootballClub> goalsScoredComparator=new Comparator<FootballClub>() {
        @Override
        public int compare(FootballClub footballClub1, FootballClub footballClub2) {
            boolean result= footballClub1.getNoOfGoalsScored()>footballClub2.getNoOfGoalsScored();
            if (result){
                return 1;
            }else if(!result){
                return -1;
            }else{
                return 0;
            }
        }
    };

    public static Comparator<FootballClub> winsComparator=new Comparator<FootballClub>() {
        @Override
        public int compare(FootballClub footballClub1, FootballClub footballClub2) {
            boolean result= footballClub1.getNoOfWins()>footballClub2.getNoOfWins();
            if (result){
                return 1;
            }else if(!result){
                return -1;
            }else{
                return 0;
            }
        }
    };
}
