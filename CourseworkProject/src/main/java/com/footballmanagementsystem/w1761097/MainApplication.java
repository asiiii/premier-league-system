package com.footballmanagementsystem.w1761097;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

@SpringBootApplication
@RestController
@CrossOrigin()
public class MainApplication {
    @GetMapping("/displayPremierLeagueTable")
    public ArrayList<FootballClub> getPremierLeague(){
        return PremierLeagueManager.sortClubs(PremierLeagueManager.premierLeague);
    }

    @GetMapping("/generateRandomMatch")
    public ArrayList<Match> getGeneratedMatch(){
        PremierLeagueManager.matchDetails.get(PremierLeagueManager.generateRandomMatch());
        return PremierLeagueManager.matchDetails;
    }

    @GetMapping("/displayMatchTable")
    public ArrayList<Match> getMatchDetails(){
        return PremierLeagueManager.sortMatches(PremierLeagueManager.matchDetails);
    }

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/getMatchList/{date}")
    public ArrayList<Match> findMatches(@PathVariable String date){
        String[] dateArray = date.split("-");
        int year= Integer.parseInt(dateArray[0]);
        int month= Integer.parseInt(dateArray[1]);
        int day= Integer.parseInt(dateArray[2]);
        LocalDate dateInput=LocalDate.of(year, month, day);
        ArrayList<Match> foundMatches=new ArrayList<Match>();
        for(Match match: PremierLeagueManager.matchDetails){
            if(match.getDateOfMatch().equals(dateInput)){
                foundMatches.add(match);
            }
        }
        return foundMatches;
    }
    /*@RequestMapping(value="/getMatchList/{year}/{month}/{day}", method = RequestMethod.GET)
   // @ResponseBody
    public ArrayList<Match> findMatches(@PathVariable("year") String year, @PathVariable("month") String month, @PathVariable("day") String day){
        LocalDate dateInput=LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        ArrayList<Match> foundMatches=new ArrayList<Match>();
        for(Match match: PremierLeagueManager.matchDetails){
            if(match.getDateOfMatch().equals(dateInput)){
                foundMatches.add(match);
            }
        }
        return foundMatches;
    }*/

    public static void main(String[] args) {
        System.out.println("WELCOME TO THE PREMIER LEAGUE!");
        System.out.println(" ");
        PremierLeagueManager.loadData(); //retrieves previously saved data from file
        SpringApplication.run(MainApplication.class, args);
        boolean exit=false;
        while (!exit){
            displayMenu(); //displays menu
            int userInput=PremierLeagueManager.checkInput(); //calls method to check menu option entered by user
            System.out.println(" ");
            switch(userInput){
                case 1:
                    addClub(); //calls method to add club
                    break;
                case 2:
                    deleteClub(); //calls method to to relegate club
                    break;
                case 3:
                    displayStats(); //calls to display stats of club
                    break;
                case 4:
                    PremierLeagueManager.displayTable(); //calls to display Premier League table
                    break;
                case 5:
                    addPlayedMatch(); //calls method to add match details
                    break;
                case 6:
                    PremierLeagueManager.saveData(); //calls method to add club save Premier League data to file
                    break;
                case 7:
                    PremierLeagueManager.loadData();
                    break;
                case 8:
                    openGUI();
                    break;
                case 9:
                    boolean stop=false;
                    while(!stop){
                        Scanner scan= new Scanner(System.in);
                        //gets confirmation to quit program
                        System.out.print("Enter 'y' to confirm quitting or enter any key to go back to menu: ");
                        String choice=scan.next().toLowerCase();
                        System.out.println(" ");
                        if(choice.equals("y")){
                            System.out.println("You are now exiting the system!");
                            exit=true;
                        }
                        stop=true;
                    }
                    System.exit(-1);
                    break;
                default:
                    break;
            }
        }
    }

    //method to add club to Premier League. All user inputs are entered here and addClubToLeague() method is called
    private static void addClub() {
        //checks to see if Premier League is full or not
        if(PremierLeagueManager.premierLeague.size()<= PremierLeagueManager.NO_OF_CLUBS_IN_LEAGUE){
            System.out.println("Proceed with adding a new football club. Please enter the relevant details when prompted.");
        }else{
            System.out.println("Sorry! Premier League is full. Cannot add more clubs.");
            return;
        }

        Scanner scan = new Scanner(System.in);

        //prompts user for details about football club
        String name;
        //checks if entered club name already exists and shows error message if so using the checkClub() method
        while (true){
            System.out.print("Enter name of club: ");
            name=scan.nextLine().toUpperCase();
            String checkName=PremierLeagueManager.checkClub(name);
            if(checkName.equals("not found")){
                break;
            }else{
                System.out.println("Error! Football Club is in Premier League. Please enter a new club.");
            }
        }

        System.out.print("Enter location of club: ");
        String location= scan.nextLine();

        System.out.print("Enter name of coach: ");
        String coachName= scan.nextLine();

        //each integer type variable is validates with numberCheck method
        int noOfMembers = PremierLeagueManager.numberCheck("Enter no of members: ");

        int noOfEmployees= PremierLeagueManager.numberCheck("Enter no of employees: ");

        //checks if user wants to enter statistics. If not club is added and all stats are set to 0
        System.out.print("To enter statistics for " + name + " enter 'y' or any other key to proceed to confirmation: ");
        String choice=scan.nextLine().toLowerCase();
        if (!choice.equals("y")){
            //user is asked for confirmation
            System.out.print("Enter 'y' to confirm your entry or any other key to cancel: ");
            String confirmation = scan.next().toLowerCase();
            if (confirmation.equals("y")){
                //method to add club to the league is called
                PremierLeagueManager.addClubToLeague(name, location, coachName, noOfMembers,noOfEmployees,
                        0,0,0,0,0,0,0,0);
            }else{
                System.out.println("Adding " + name + " was cancelled.");
            }
        }else {
            //each integer type variable is validates with numberCheck method

            int noOfWins = PremierLeagueManager.numberCheck("Enter no of wins: ");

            int noOfDraws = PremierLeagueManager.numberCheck("Enter no of draws: ");

            int noOfDefeats = PremierLeagueManager.numberCheck("Enter no of defeats: ");

            int noOfGoalsReceived = PremierLeagueManager.numberCheck("Enter no of goals received: ");

            int noOfGoalsScored = PremierLeagueManager.numberCheck("Enter no of goals scored: ");

            int noOfMatchesPlayed = PremierLeagueManager.numberCheck("Enter no of matches played: ");

            int noOfPoints = PremierLeagueManager.numberCheck("Enter no of points: ");

            //user is asked for confirmation
            System.out.print("Enter 'y' to confirm your entry or any other key to cancel: ");
            String confirmation = scan.next().toLowerCase();

            if (confirmation.equals("y")) {
                //method to add club to the league is called
                PremierLeagueManager.addClubToLeague(name, location, coachName, noOfMembers, noOfEmployees,
                        noOfWins, noOfDraws, noOfDefeats, noOfGoalsReceived, noOfGoalsScored, noOfMatchesPlayed, noOfPoints, noOfGoalsScored-noOfGoalsReceived);
            }else{
                System.out.println("Adding " + name + " was cancelled.");
            }
        }
        System.out.println(" ");
    }

    // method to relegate an existing club
    public static void deleteClub(){
        System.out.println("Proceed with relegating a football club.");
        String check="y";
        while (check.equals("y")){
            Scanner scan = new Scanner(System.in);
            while(true){
                System.out.print("Please enter name of club: ");
                //checks if club is in the league
                String clubName=PremierLeagueManager.checkClub(scan.nextLine().toUpperCase());
                if(clubName.equals("not found")){
                    //if club is not found error message is displayed and the user is asked to re-enter name
                    System.out.println("Error! The club name you entered is not in the Premier League.");
                    continue;
                }else{
                    for(FootballClub footballClub: PremierLeagueManager.premierLeague){
                        if(footballClub.getClubName().equals(clubName)){
                            Scanner scan2 = new Scanner(System.in);
                            //user is asked to confirm deletion
                            System.out.print("Enter 'y' to confirm your deletion or any other key to cancel: ");
                            if(scan2.next().toLowerCase().equals("y")) {
                                PremierLeagueManager.relegateClub(footballClub);
                            }else{
                                System.out.println("Relegation of " + clubName + " was cancelled");
                            }
                            break;
                        }
                    }
                }
                break;
            }
            System.out.println(" ");
            //user is given option to continue deleting or return to menu
            System.out.print("Enter 'y' to relegate other clubs or any other key to go back to menu: ");
            check=scan.next().toLowerCase();
        }
        System.out.println(" ");
    }

    //method to get user input and display the stats of the entered club
    public static void displayStats() {
        System.out.println("Proceed with displaying statistics of a football club.");
        String check="y";
        while (check.equals("y")){
            Scanner scan = new Scanner(System.in);
            boolean stop=false;
            while(!stop){
                System.out.print("Please enter name of club: ");
                //checks if club is in the league
                String clubName=PremierLeagueManager.checkClub(scan.nextLine().toUpperCase());
                if(clubName.equals("not found")){
                    //if club is not found error message is displayed and the user is asked to re-enter name
                    System.out.println("Error! The club name you entered is not in the Premier League.");
                }else{
                    //method to display club stats is called
                    PremierLeagueManager.displayClubStats(clubName);
                    stop=true;
                }
            }
            //user is given option to continue viewing stats of clubs or return to menu
            System.out.println(" ");
            System.out.print("Enter 'y' to display statistics more clubs or any other key to go back to menu: ");
            check=scan.next().toLowerCase();
        }
        System.out.println(" ");
    }


    //method to add scores and date of a played match between two clubs in the Premier League
    public static void addPlayedMatch(){
        System.out.println("Proceed with adding a played match");
        Scanner scan =new Scanner(System.in);
        System.out.println(" ");
        String homeTeam;
        //gets name of home team, shows error if the club isn't added to the premier league and prompts user again
        while (true){
            System.out.print("Enter the name of the Home team: ");
            homeTeam=PremierLeagueManager.checkClub(scan.nextLine().toUpperCase());
            if(homeTeam.equals("not found")){
                System.out.println("Error! The club name you entered is not in the Premier League.");
            }else{
                break;
            }
        }
        System.out.println(" ");
        //gets no of goals scored by home team, checked for integer by number check method
        int homeTeamScore=PremierLeagueManager.numberCheck("Enter number of goals scored by " + homeTeam +  " in match: ");

        System.out.println(" ");
        String awayTeam;
        //gets name of away team, shows error if it is same as home team or if it is not added to the premier league and prompts user again
        while (true){
            System.out.print("Enter the name of Away team: ");
            awayTeam=scan.nextLine().toUpperCase();
            if (!homeTeam.equals(awayTeam)){
                awayTeam=PremierLeagueManager.checkClub(awayTeam);
                if(awayTeam.equals("not found")){
                    System.out.println("Error! The club name you entered is not in the Premier League.");
                    continue;
                }
            }else{
                System.out.println("Error! Please enter a different club.");
                continue;
            }
            break;
        }
        System.out.println(" ");
        //gets no of goals scored by away team, checked for integer by number check method
        int awayTeamScore=PremierLeagueManager.numberCheck("Enter number of goals scored by " + awayTeam +  " in match: ");

        System.out.println(" ");

        //gets the year of match and checks if it is an integer and in the range 2000-2021
        boolean check= false; //boolean used to check the validity of date entries
        int yearInput=0;
        while(!check){
            yearInput = PremierLeagueManager.numberCheck("Enter year of match: "); //checks if user input is an integer
            if(yearInput>1999 && yearInput<2022){
                //checks if range is valid
                check=true;
            }else{
                System.out.println("Error! Please enter a valid year (between 2000 and 2021)"); //displays error message and prompts for user to reenter
            }
        }

        check= false;
        //gets the month of match and checks if it is an integer and in the range 1-12
        int monthInput=0;
        while(!check){
            monthInput = PremierLeagueManager.numberCheck("Enter month of match (as a number): "); //checks if user input is an integer
            if(monthInput>0 && monthInput<13){
                //checks if range is valid
                check=true;
            }else{
                System.out.println("Please enter a valid month!"); //displays error message and prompts for user to reenter
            }
        }

        check= false;
        int dayInput=0;
        //gets the month of match and checks if it is an integer and if it is a valid day
        while(!check){
            dayInput = PremierLeagueManager.numberCheck("Enter day of match: "); //checks if user input is an integer
            check = PremierLeagueManager.dateCheck(check, monthInput, dayInput); //checks if user input is a valid day
        }

        LocalDate dateOfMatch= LocalDate.of(yearInput, monthInput, dayInput); //initializes dateOfMatch with a correct date

        System.out.println(" ");
        //asks user to confirm match
        System.out.print("Enter 'y' to confirm your entry or any other key to cancel: ");
        String confirmation = scan.next().toLowerCase();
        if (confirmation.equals("y")){
            //calls method to add the match details
            int matchCode= PremierLeagueManager.createPlayedMatch(homeTeam, awayTeam, homeTeamScore, awayTeamScore, dateOfMatch);
            System.out.println(" ");
            //confirmation message is displayed
            System.out.println("Match details of match " + matchCode + " successfully added!");
        }
        System.out.println(" ");
    }

    //method to launch Angular GUI
    public static void openGUI(){
       ProcessBuilder build = new ProcessBuilder();
        build.command("cmd.exe", "/C" ,"cd \"C:\\Users\\Asiri Ekanayake\\Documents\\OOP\\w1761097\\GUIApplication\" && ng serve --open");
        build.redirectErrorStream(true);
        try{
            Process process= build.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            line=reader.readLine();
            System.out.println(line);
        } catch (IOException ex) {
            System.out.println("Error in opening GUI!");
        }
        /* //method to call JavaFX Application
        try{
            Application.launch(JavaFXApplication.class, args); //method call to launch GUI
        }catch (IllegalStateException e){
            System.out.println("Can only launch GUI once!");
        }*/
    }

    //method to display menu options
    public static void displayMenu() {
        System.out.println("--------------------------------MENU--------------------------------");
        System.out.println("Enter 1 to add a new football club to the Premier League");
        System.out.println("Enter 2 to relegate an existing football club in the Premier League");
        System.out.println("Enter 3 to display statistics of a football club");
        System.out.println("Enter 4 to view Premier League Table");
        System.out.println("Enter 5 to add a played match");
        System.out.println("Enter 6 to save data in a file");
        System.out.println("Enter 7 to retrieve data from file");
        System.out.println("Enter 8 to launch GUI Application");
        System.out.println("Enter 9 to quit");
        System.out.println("--------------------------------------------------------------------");
        System.out.println(" ");
    }
}
