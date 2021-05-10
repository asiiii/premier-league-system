package com.footballmanagementsystem.w1761097;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Match implements Serializable {
    private int matchCode;
    private static int matchCounter=1;
    private String homeTeam;
    private int homeTeamScore;
    private String awayTeam;
    private int awayTeamScore;
    private LocalDate dateOfMatch;


    public Match(){
        System.out.println("A match has been played!");
    }

    public Match(String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore, LocalDate dateOfMatch){
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore =awayTeamScore;
        this.dateOfMatch=dateOfMatch;
        while (checkMatchCode(matchCode)){
            this.matchCode=matchCounter++; // each time a match object is created match code increments
        }
    }

    public Match(int matchCode, String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore, LocalDate dateOfMatch){
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore =awayTeamScore;
        this.dateOfMatch=dateOfMatch;
        this.matchCode=matchCode;
    }

    //getter for match code
    public int getMatchCode(){
        return matchCode;
    }

    //getters and setters for home team
    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam){
        this.homeTeam = homeTeam;
    }

    //getters and setters for away team
    public String getAwayTeam(){
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam){
        this.awayTeam = awayTeam;
    }

    //getters and setters for the home team score in the match
    public int getHomeTeamScore(){
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore){
        this.homeTeamScore = homeTeamScore;
    }

    //getters and setters for away team score
    public int getAwayTeamScore(){
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore){
        this.awayTeamScore = awayTeamScore;
    }

    //getters and setters for date of match
    public LocalDate getDateOfMatch(){
        return dateOfMatch;
    }

    public void setDateOfMatch(LocalDate dateOfMatch){
        this.dateOfMatch=dateOfMatch;
    }

    //compareTo() method compares the scores of the two clubs and returns the result
    public String compareTo(){
        if(homeTeamScore == awayTeamScore){
            return "draw";
        }else if(homeTeamScore > awayTeamScore){
            return "home";
        }else{
            return "away";
        }
    }

    public boolean checkMatchCode(int matchCode){
        boolean check=true;
        for(Match match: PremierLeagueManager.matchDetails){
            if(matchCode==match.getMatchCode()){
                return true;
            }
        }
        return false;
    }

    public static Comparator<Match> matchDateComparator=new Comparator<Match>() {
        @Override
        public int compare(Match match1, Match match2) {
            int result= match1.getDateOfMatch().compareTo(match2.getDateOfMatch());
            if (result> 0){
                return 1;
            }else if(result<0){
                return -1;
            }else{
                return 0;
            }
        }
    };
}
