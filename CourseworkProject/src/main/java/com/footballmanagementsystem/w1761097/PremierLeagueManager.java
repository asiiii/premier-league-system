package com.footballmanagementsystem.w1761097;

import javafx.scene.control.Alert;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class PremierLeagueManager implements LeagueManager{

    static final int NO_OF_CLUBS_IN_LEAGUE = 20; //a fixed value for the no of clubs that can play in the Premier League
    static ArrayList <FootballClub> premierLeague= new ArrayList<FootballClub>(); //arraylist that holds the clubs in the Premier league
    static ArrayList <Match> matchDetails= new ArrayList<Match>(); //arraylist that holds the clubs in the Premier league

    //method to add club to the Premier League
    public static void addClubToLeague(String name, String location, String coachName, int noOfMembers, int noOfEmployees, int noOfWins, int noOfDraws,
                                       int noOfDefeats, int noOfGoalsReceived, int noOfGoalsScored, int noOfMatchesPlayed, int noOfPoints, int goalDifference){
        //new football club is created
        FootballClub footballClub=new FootballClub(name, location, coachName, noOfMembers, noOfEmployees, noOfWins, noOfDraws, noOfDefeats,
                noOfGoalsReceived, noOfGoalsScored, noOfMatchesPlayed, noOfPoints, goalDifference);
        premierLeague.add(footballClub); //football club is added
        System.out.println(name + " successfully added to the League!");
    }

    // method to relegate an existing club
    public static void relegateClub(FootballClub chosenClub){
        for(Iterator<FootballClub> itr =premierLeague.iterator();itr.hasNext();){
            FootballClub footballClub= itr.next();
            if(footballClub.getClubName().equals(chosenClub.getClubName())){
                itr.remove(); //football club is removed from premier league arraylist
                System.out.println(chosenClub.getClubName() + " was successfully relegated from Premier League!");
                break;
            }
        }
    }

    //method to view club stats
    public static void displayClubStats(String clubName){
        for(FootballClub footballClub: PremierLeagueManager.premierLeague) {
            //checks if entered club is in Premier League
            if (footballClub.getClubName().equals(clubName)) {
                System.out.println(footballClub.toString()); //club stats are displayed through toString() method
                break;
            }
        }
    }

    //method to sort clubs according to no of points (from highest to lowest) and display it in table format
    public static void displayTable() {
        System.out.println("Proceed with displaying the Premier League Table according to number of points");
        ArrayList<FootballClub> sortedList=sortClubs(premierLeague); //method to sort premier league according to no of points

        //club names and stats are displayed in a table format
        System.out.println("Premier League Table: ");
        System.out.println(" ");
        System.out.println("        Club Name            No of Points    No of Wins    No of Draws    No of Defeats    Goal Difference");
        System.out.println(" ");
        System.out.println("------------------------------------------------------------------------------------------------------------");
        final int MAX_COLUMN_WIDTH= 33;
        for (FootballClub footballClub: sortedList){
            int spaceCount=MAX_COLUMN_WIDTH-footballClub.getClubName().length();
            System.out.printf( "%s %"+spaceCount+"s %15s %14s %14s %16s", footballClub.getClubName(), footballClub.getNoOfPoints(), footballClub.getNoOfWins(), footballClub.getNoOfDraws(), footballClub.getNoOfDefeats(), footballClub.getGoalDifference());
            System.out.println(" ");
        }
        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.println(" ");
    }

    //method to add scores and date of a played match between two clubs in the Premier League
    public static int createPlayedMatch(String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore, LocalDate dateOfMatch) {
        //new match object is created
        Match match=new Match(homeTeam, awayTeam, homeTeamScore, awayTeamScore, dateOfMatch);
        matchDetails.add(match); //new match added
        System.out.println(" ");

        //compareTo method is called to find the winner and loser of the match or if it was a draw
        switch(match.compareTo()){
            case "home":
                //scenario where home team wins, updateStats method is called
                updateStats(match.getHomeTeam(), match.getHomeTeamScore(), match.getAwayTeam(), match.getAwayTeamScore());
                break;
            case "away":
                //scenario where away team wins, updateStats method is called
                updateStats(match.getAwayTeam(), match.getAwayTeamScore(), match.getHomeTeam(), match.getHomeTeamScore());
                break;
            case "draw":
                //scenario when the match is a draw
                for (FootballClub footballClub: premierLeague){
                    if(footballClub.getClubName().equals(match.getHomeTeam()) || footballClub.getClubName().equals(match.getAwayTeam())){
                        //no of matches, no of draws and no of points for both clubs are updated
                        footballClub.setNoOfMatchesPlayed(footballClub.getNoOfMatchesPlayed()+1);
                        footballClub.setNoOfDraws(footballClub.getNoOfDraws()+1);
                        footballClub.setNoOfPoints(footballClub.getNoOfPoints()+1);

                        if (footballClub.getClubName().equals(match.getHomeTeam())){
                            //goals scored and received for home team is updated
                            footballClub.setNoOfGoalsScored(footballClub.getNoOfGoalsScored()+match.getHomeTeamScore());
                            footballClub.setNoOfGoalsReceived(footballClub.getNoOfGoalsReceived()+match.getAwayTeamScore());
                        }else{
                            //goals scored and received for away team is updated
                            footballClub.setNoOfGoalsScored(footballClub.getNoOfGoalsScored()+match.getAwayTeamScore());
                            footballClub.setNoOfGoalsReceived(footballClub.getNoOfGoalsReceived()+match.getHomeTeamScore());
                        }
                        footballClub.setGoalDifference(footballClub.getNoOfGoalsScored()-footballClub.getNoOfGoalsReceived());
                    }
                }
                break;
            default:
                break;
        }
        return match.getMatchCode();
    }

    //method to save the premier league data and match data to a text file in a binary stream
    public static void saveData(){

        System.out.println("Proceed with saving Premier League and Match data to file");
        System.out.println(" ");
        try{
            File file1 = new File("Football club info.txt"); //creates text file
            //opens file output stream and object output stream
            FileOutputStream fileOutputStream = new FileOutputStream(file1);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for(FootballClub footballClub:premierLeague){
                objectOutputStream.writeObject(footballClub); //writes each Football club object to file
            }

            File file2=new File("Match info.txt");
            fileOutputStream=new FileOutputStream(file2);
            objectOutputStream=new ObjectOutputStream(fileOutputStream);

            for(Match match:matchDetails){
                objectOutputStream.writeObject(match); //writes each Match object to file
            }

            //closes streams
            objectOutputStream.close();
            fileOutputStream.close();

            System.out.println("Information has been successfully saved to the file");
        }catch (FileNotFoundException e) {
            //exception when file cannot be found is handled
            System.out.println("Error! File not found, unable to save data.");
        }catch (IOException e){
            //exception when there is a problem in IO is handled
            System.out.println("Error in saving data to a text file!");
            e.printStackTrace();
            System.out.println(" ");
        }
        System.out.println(" ");
    }



    //method to load saved data from file to the premier league and match details
    public static void loadData(){
        System.out.println("Proceed with retrieving Premier League data to file");
        System.out.println(" ");
        try{
            //opens file input stream and object input stream
            FileInputStream fileInputStream = new FileInputStream("Football club info.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            //reads data from file to premier league arraylist
            for(;;){
                try{
                    premierLeague.add((FootballClub) objectInputStream.readObject());
                }catch(EOFException e){
                    break;
                }
            }

            fileInputStream= new FileInputStream("Match info.txt");
            objectInputStream=new ObjectInputStream(fileInputStream);
            //reads data from file to match details arraylist
            for(;;){
                try{
                    matchDetails.add((Match) objectInputStream.readObject());
                }catch(EOFException e){
                    break;
                }
            }

            //closes streams
            objectInputStream.close();
            fileInputStream.close();
            System.out.println("Information loaded successfully!");
        }catch (FileNotFoundException e){
            //exception when file cannot be found is handled
            System.out.println("Error! File not found, unable to load data.");
        }catch (IOException e){
            //exception when there is a problem in IO is handled
            System.out.println("Error! Cannot initialize stream, unable to load data");
        } catch (ClassNotFoundException e) {
            //exception when the class is not found is handled
            System.out.println("Error! Cannot find class, unable to load data");
        }
        System.out.println(" ");
    }

    //checks the value entered as a menu option, only valid numbers are allowed
    public static int checkInput(){
        while (true){
            Scanner scan= new Scanner(System.in);
            System.out.print("Enter your option: ");
            int number;
            try{
                number=scan.nextInt();
                if (number>0 && number<10){
                    return number;
                }else{
                    System.out.println("Input invalid! Please enter a number from 1-9.");
                }
            }catch (InputMismatchException e){
                System.out.println("Input invalid! Please enter a number from 1-9.");
            }
        }
    }

    //method to check if club is in the premier league, returns not found if not
    public static String checkClub(String clubName){
        for(FootballClub footballClub:premierLeague){
            if(footballClub.getClubName().equals(clubName)){
                return clubName;
            }
        }
        return "not found";
    }

    //method to check if entered input is a number, user is prompted to re-enter if not
    public static int numberCheck(String statement){
        int number;
        while(true){
            Scanner scanner=new Scanner(System.in);
            System.out.print(statement);
            try{
                number=scanner.nextInt();
                break;
            }catch (InputMismatchException e){
                System.out.println("Please enter only numerical values!");
            }
        }
        return number;
    }

    //method to update the stats of winner and loser of a match
    private static void updateStats(String winner, int winnerScore, String loser, int loserScore){
        for(FootballClub footballClub: premierLeague){
            if(footballClub.getClubName().equals(winner)){
                //for the wining club, no of matches, no of wins, no of goals scored and received and no of points are updated
                footballClub.setNoOfMatchesPlayed(footballClub.getNoOfMatchesPlayed()+1);
                footballClub.setNoOfWins(footballClub.getNoOfWins() + 1);
                footballClub.setNoOfGoalsScored(footballClub.getNoOfGoalsScored()+winnerScore);
                footballClub.setNoOfGoalsReceived(footballClub.getNoOfGoalsReceived()+loserScore);
                footballClub.setNoOfPoints(footballClub.getNoOfPoints()+3);
                footballClub.setGoalDifference(footballClub.getNoOfGoalsScored()-footballClub.getNoOfGoalsReceived());
            }
            else if(footballClub.getClubName().equals(loser)){
                //for the losing club, no of matches, no of defeats, no of goals scored and received and no of points are updated
                footballClub.setNoOfMatchesPlayed(footballClub.getNoOfMatchesPlayed()+1);
                footballClub.setNoOfDefeats(footballClub.getNoOfDefeats() + 1);
                footballClub.setNoOfGoalsReceived(footballClub.getNoOfGoalsReceived()+winnerScore);
                footballClub.setNoOfGoalsScored(footballClub.getNoOfGoalsScored()+loserScore);
                footballClub.setGoalDifference(footballClub.getNoOfGoalsScored()-footballClub.getNoOfGoalsReceived());
            }
        }
    }

    //method to sort an arraylist of FootballClub Objects according to no of points and return the sorted array list
    public static ArrayList<FootballClub> sortClubs(ArrayList<FootballClub> clubList){
        for (int j=0; j<clubList.size()-1;j++){
            for (int i=0; i<clubList.size()-j-1;i++){
                int result=clubList.get(i).compareTo(clubList.get(i+1)); //returns the result of the comparison of points
                //if club1 is less than club2 the indexes of the clubs are swapped
                if (result < 0){
                    FootballClub temp=clubList.get(i);
                    clubList.set(i, clubList.get(i+1));
                    clubList.set(i+1, temp);
                }else if (result==0){
                    //if the no of points are equal, the one with the highest goal difference (goals scored-goals received)
                    if((clubList.get(i).getGoalDifference())< (clubList.get(i+1).getGoalDifference())){
                        FootballClub temp=clubList.get(i);
                        clubList.set(i, clubList.get(i+1));
                        clubList.set(i+1, temp);
                    }
                }
            }
        }
        return clubList;
    }

    //method to check the validity of a day
    public static boolean dateCheck(boolean check, int monthInput, int dayInput) {
        //boolean check is set as false and is reinitialized as true if day is valid
        if(dayInput>0 && dayInput<32){
            //checks if a day is not between 1 and 31
            if(monthInput==2){
                //if the month is February, checks if day is greater than 29
                if (dayInput>29){
                    System.out.println("Error! Please enter a valid day for February month.");
                }else{
                    check=true;
                }
            }else if(monthInput==4 || monthInput==6 || monthInput==9 || monthInput==11){
                //if day is a month with 30 days check if day is greater than 30
                if(dayInput>30){
                    System.out.println("Error! Please enter a valid day for a month with only 30 days.");
                }else{
                    check=true;
                }
            }else{
                check=true;
            }
        }else{
            System.out.println("Please enter a valid day!");
        }
        return check; //check is return confirming the validity of the day
    }

    //GUI Functionality
    //returns a randomly generated match object
    public static int generateRandomMatch(){
        Random rand=new Random();
        //the indexes are randomly generated to decide the home team and away team
        int homeTeamIndex=rand.nextInt(premierLeague.size());
        int awayTeamIndex=rand.nextInt(premierLeague.size());
        //checks if away team is same as home team, if so random index is generate again
        while(awayTeamIndex==homeTeamIndex){
            awayTeamIndex=rand.nextInt(premierLeague.size());
        }

        //the scores for each club are randomly generated
        int homeTeamScore=rand.nextInt(10);
        int awayTeamScore=rand.nextInt(10);

        //randomly generate date
        //minDay and maxDay define the bounds
        long minDay = LocalDate.of(2000, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2021, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate dateOfMatch = LocalDate.ofEpochDay(randomDay);

        //method to create new match
        return createPlayedMatch(premierLeague.get(homeTeamIndex).getClubName(), premierLeague.get(awayTeamIndex).getClubName(), homeTeamScore, awayTeamScore, dateOfMatch);
    }

    //For JavaFX- method to sort an arraylist of FootballClub Objects according to no of goals scored and return the sorted array list
    public static ArrayList<FootballClub> sortScored(ArrayList<FootballClub> clubList){
        Collections.sort(clubList, FootballClub.goalsScoredComparator);
        Collections.reverse(clubList);
        return clubList;
    }

    //For JavaFX- method to sort an arraylist of FootballClub Objects according to no of wins and return the sorted array list
    public static ArrayList<FootballClub> sortWins(ArrayList<FootballClub> clubList){
        Collections.sort(clubList, FootballClub.winsComparator);
        Collections.reverse(clubList);
        return clubList;
    }

    //method to sort an arraylist of Match Objects according to date of match and return the sorted array list
    public static ArrayList<Match> sortMatches(ArrayList<Match> matchList){
        Collections.sort(matchList, Match.matchDateComparator);
        return matchList;
    }

    //displays error message
    public static void errorAlert(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

}
