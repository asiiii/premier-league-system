package com.footballmanagementsystem.w1761097;

import javafx.application.Application;
import javafx.collections.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import java.time.LocalDate;
import java.util.*;

import java.util.concurrent.ThreadLocalRandom;

public class JavaFXApplication extends Application {
    static Scene premierLeagueTableScene, scoredGoalsScene, winsScene, searchScene, foundScene; //declaring scenes
    @Override
    public void start(Stage primaryStage) {
        premierLeagueTable(); //callin method to display premier league table
    }

    //method to display premier league table
    public static void premierLeagueTable(){
        Stage stage=new Stage();
        stage.setTitle("Premier League Table");

        //Scene 1- Premier League Table
        //Pane for premier league table
        Pane pltPane=new Pane();
        pltPane.setMinSize(70, 550);
        pltPane.setStyle("-fx-background-color: linear-gradient(to left,#3AEEEE,#3838EE); -fx-font-family: Monospaced;");
        Label pltHeading=new Label("Premier League Table");
        pltHeading.setStyle("-fx-font-size: 20; -fx-underline:true");

        //declaring, initializing and populating observable list for TableView
        ObservableList <FootballClub> footballClubObservableList = FXCollections.observableArrayList();
        //each object in the sorted premier league arraylist is added to observable list
        for (FootballClub footballClub: PremierLeagueManager.sortClubs(PremierLeagueManager.premierLeague)){
            footballClubObservableList.add(new FootballClub(footballClub.getClubName(), footballClub.getNoOfPoints(), footballClub.getNoOfWins(), footballClub.getNoOfDraws(),
                    footballClub.getNoOfDefeats(), footballClub.getNoOfGoalsScored(), footballClub.getNoOfGoalsReceived(), footballClub.getGoalDifference()));
        }

        //TableView and Table Columns
        TableView <FootballClub> premierLeagueTable = new TableView<FootballClub> ();

        TableColumn<FootballClub, String> clubNameCol = new TableColumn<>("Club Name");
        clubNameCol.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        clubNameCol.setSortable(false); //sortable is set to false to disable in-built sorting

        TableColumn <FootballClub, String> pointsCol = new TableColumn<>("No. of Points");
        pointsCol.setCellValueFactory(new PropertyValueFactory<>("noOfPoints"));
        pointsCol.setSortable(false);

        TableColumn <FootballClub, String> winsCol = new TableColumn<>("No. of Wins");
        winsCol.setCellValueFactory(new PropertyValueFactory<>("noOfWins"));
        winsCol.setSortable(false);

        TableColumn <FootballClub, String> drawsCol = new TableColumn<>("No. of Draws");
        drawsCol.setCellValueFactory(new PropertyValueFactory<>("noOfDraws"));
        drawsCol.setSortable(false);

        TableColumn <FootballClub, String>defeatsCol = new TableColumn<>("No. of Defeats");
        defeatsCol.setCellValueFactory(new PropertyValueFactory<>("noOfDefeats"));
        defeatsCol.setSortable(false);

        TableColumn <FootballClub, String> scoredCol = new TableColumn<>("Goals Scored");
        scoredCol.setCellValueFactory(new PropertyValueFactory<>("noOfGoalsScored"));
        scoredCol.setSortable(false);

        TableColumn <FootballClub, String> receivedCol = new TableColumn<>("Goals Received");
        receivedCol.setCellValueFactory(new PropertyValueFactory<>("noOfGoalsReceived"));
        receivedCol.setSortable(false);

        TableColumn<FootballClub, String> differenceCol1 = new TableColumn<>("Goal Difference");
        differenceCol1.setCellValueFactory(new PropertyValueFactory<>("noOfGoalsReceived"));
        differenceCol1.setSortable(false);

        premierLeagueTable.setItems(footballClubObservableList); //TableView is populated with the data in observable list
        premierLeagueTable.getColumns().addAll(clubNameCol, pointsCol, winsCol, drawsCol, defeatsCol, scoredCol, receivedCol, differenceCol1);

        premierLeagueTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //button to sort premier league table according to no of goals scored
        Button scoredSortBtn1=new Button("Sort according to No of Goals Scored");
        scoredSortBtn1.setOnAction(event->{
            stage.setScene(scoredGoalsScene); //scoredGoalsScene is opened
        });

        //button to sort premier league table according no of wins
        Button winsSortBtn1=new Button("Sort according to No of Wins");
        winsSortBtn1.setOnAction(event -> {
            stage.setScene(winsScene);//wins scene is opened
        });

        //button to generate random match
        Button randomMatchBtn= new Button("Generate random match");
        randomMatchBtn.setOnAction(event -> {
            //stage is closed to new stage is opened
            stage.close();
            generateMatch();
        });

        //button to display match details table
        Button matchTableBtn= new Button("Display Match Table");
        matchTableBtn.setOnAction(event -> {
            //stage is closed to new stage is opened
            stage.close();
            matchTable();
        });

        //button to search for a match by date
        Button searchMatchBtn= new Button("Find a Match");
        searchMatchBtn.setOnAction(event -> {
            //stage is closed to new stage is opened
            stage.close();
            findMatch();
        });

        //button to return to menu
        Button menuBtn1= new Button("Return to Menu");
        menuBtn1.setOnAction(event -> {
            stage.close(); //stage is closed and return to menu
        });

        //tableview is added to vBox
        VBox vBox=new VBox();
        vBox.setMinSize(1020, 60);
        vBox.getChildren().add(premierLeagueTable);

        //setting the position of all elements
        pltHeading.setLayoutX(400);
        pltHeading.setLayoutY(25);
        vBox.setLayoutX(10);
        vBox.setLayoutY(60);
        scoredSortBtn1.setLayoutX(50);
        scoredSortBtn1.setLayoutY(500);
        winsSortBtn1.setLayoutX(500);
        winsSortBtn1.setLayoutY(500);
        randomMatchBtn.setLayoutX(50);
        randomMatchBtn.setLayoutY(550);
        matchTableBtn.setLayoutX(500);
        matchTableBtn.setLayoutY(550);
        searchMatchBtn.setLayoutX(50);
        searchMatchBtn.setLayoutY(600);
        menuBtn1.setLayoutX(500);
        menuBtn1.setLayoutY(600);

        //all elements are added to Pane
        pltPane.getChildren().addAll(pltHeading, vBox, scoredSortBtn1, winsSortBtn1, randomMatchBtn, matchTableBtn, searchMatchBtn, menuBtn1);


        //Scene 2- Premier Table League sorted According to no of Goals Scored (descending)
        //new Pane for Premier Table League sorted According to no of Goals Scored
        Pane pltGoalsScoredSortedPane= new Pane();
        pltGoalsScoredSortedPane.setMinSize(1040, 550);
        pltGoalsScoredSortedPane.setStyle("-fx-background-color: linear-gradient(to left,#3AEEEE,#3838EE); -fx-font-family: Monospaced;");
        Label pltScoredSortedHeading=new Label("Premier League Table- Sorted According to Goals Scored");
        pltScoredSortedHeading.setStyle("-fx-font-size: 20; -fx-underline:true");

        //declaring, initializing and populating observable list for TableView
        ObservableList <FootballClub> footballClubObservableList2 = FXCollections.observableArrayList();
        //each object in the sorted premier league arraylist is added to observable list
        for (FootballClub footballClub: PremierLeagueManager.sortScored(PremierLeagueManager.premierLeague)){
            footballClubObservableList2.add(new FootballClub(footballClub.getClubName(), footballClub.getNoOfPoints(), footballClub.getNoOfWins(), footballClub.getNoOfDraws(),
                    footballClub.getNoOfDefeats(), footballClub.getNoOfGoalsScored(), footballClub.getNoOfGoalsReceived(),  footballClub.getGoalDifference()));
        }

        //TableView and Table Columns
        TableView<FootballClub> premierLeagueTable2 = new TableView<FootballClub>();

        TableColumn<FootballClub, String> clubNameCol2 = new TableColumn<>("Club Name");
        clubNameCol2.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        clubNameCol2.setSortable(false);

        TableColumn<FootballClub, String> pointsCol2 = new TableColumn<>("No. of Points");
        pointsCol2.setCellValueFactory(new PropertyValueFactory<>("noOfPoints"));
        pointsCol2.setSortable(false);

        TableColumn<FootballClub, String> winsCol2 = new TableColumn<>("No. of Wins");
        winsCol2.setCellValueFactory(new PropertyValueFactory<>("noOfWins"));
        winsCol2.setSortable(false);

        TableColumn<FootballClub, String> drawsCol2 = new TableColumn<>("No. of Draws");
        drawsCol2.setCellValueFactory(new PropertyValueFactory<>("noOfDraws"));
        drawsCol2.setSortable(false);

        TableColumn<FootballClub, String> defeatsCol2 = new TableColumn<>("No. of Defeats");
        defeatsCol2.setCellValueFactory(new PropertyValueFactory<>("noOfDefeats"));
        defeatsCol2.setSortable(false);

        TableColumn<FootballClub, String> scoredCol2 = new TableColumn<>("Goals Scored");
        scoredCol2.setCellValueFactory(new PropertyValueFactory<>("noOfGoalsScored"));
        scoredCol2.setSortable(false);

        TableColumn<FootballClub, String> receivedCol2 = new TableColumn<>("Goals Received");
        receivedCol2.setCellValueFactory(new PropertyValueFactory<>("noOfGoalsReceived"));
        receivedCol2.setSortable(false);

        TableColumn<FootballClub, String> differenceCol2 = new TableColumn<>("Goal Difference");
        differenceCol2.setCellValueFactory(new PropertyValueFactory<>("noOfGoalsReceived"));
        differenceCol2.setSortable(false);

        premierLeagueTable2.setItems(footballClubObservableList2); //TableView is populated with the data in observable list
        premierLeagueTable2.getColumns().addAll(clubNameCol2, pointsCol2, winsCol2, drawsCol2, defeatsCol2, scoredCol2, receivedCol2, differenceCol2);

        premierLeagueTable2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox vBox2=new VBox();
        vBox2.setMinSize(1020, 60);
        vBox2.getChildren().add(premierLeagueTable2);

        //button to display premier league table
        Button pointsSortBtn1=new Button("Sort according to No of Points");
        pointsSortBtn1.setOnAction(event->{
            stage.setScene(premierLeagueTableScene); //opens premierLeagueTableScene
        });

        //button to sort premier league table according no of wins
        Button winsSortBtn2=new Button("Sort according to No of Wins");
        winsSortBtn2.setOnAction(event -> {
            stage.setScene(winsScene); //opens winScene
        });

        //button to return to menu
        Button menuBtn2= new Button("Return to Menu");
        menuBtn2.setOnAction(event -> {
            stage.close(); //stage is closed and returns to menu
        });

        //all elements are positions
        pltScoredSortedHeading.setLayoutX(130);
        pltScoredSortedHeading.setLayoutY(25);
        vBox2.setLayoutX(10);
        vBox2.setLayoutY(60);
        pointsSortBtn1.setLayoutX(50);
        pointsSortBtn1.setLayoutY(500);
        winsSortBtn2.setLayoutX(400);
        winsSortBtn2.setLayoutY(500);
        menuBtn2.setLayoutX(800);
        menuBtn2.setLayoutY(500);

        //all elements are added to pane
        pltGoalsScoredSortedPane.getChildren().addAll(pltScoredSortedHeading, vBox2, pointsSortBtn1, winsSortBtn2, menuBtn2);

        //scene is initialized
        scoredGoalsScene= new Scene(pltGoalsScoredSortedPane, 1040, 550);

        //Scene 2 ends

        //Scene 3- Premier League Table sorted according to No of Wins (descending)
        //new Pane for Premier League Table sorted according to No of Wins
        Pane pltWinsSortedPane= new Pane();
        pltWinsSortedPane.setMinSize(1040, 550);
        pltWinsSortedPane.setStyle("-fx-background-color: linear-gradient(to left,#3AEEEE,#3838EE); -fx-font-family: Monospaced;");
        Label pltWinsSortedHeading=new Label("Premier League Table- Sorted According to No of Wins");
        pltWinsSortedHeading.setStyle("-fx-font-size: 20; -fx-underline:true");

        //declaring, initializing and populating observable list for TableView
        ObservableList <FootballClub> footballClubObservableList3 = FXCollections.observableArrayList();
        //each object in the sorted premier league arraylist is added to observable list
        for (FootballClub footballClub: PremierLeagueManager.sortWins(PremierLeagueManager.premierLeague)){
            footballClubObservableList3.add(new FootballClub(footballClub.getClubName(), footballClub.getNoOfPoints(), footballClub.getNoOfWins(), footballClub.getNoOfDraws(),
                    footballClub.getNoOfDefeats(), footballClub.getNoOfGoalsScored(), footballClub.getNoOfGoalsReceived(), footballClub.getGoalDifference()));
        }

        //TableView and TableColumns
        TableView<FootballClub> premierLeagueTable3 = new TableView<FootballClub>();

        TableColumn<FootballClub, String> clubNameCol3 = new TableColumn<>("Club Name");
        clubNameCol3.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        clubNameCol3.setSortable(false);

        TableColumn<FootballClub, String> pointsCol3 = new TableColumn<>("No. of Points");
        pointsCol3.setCellValueFactory(new PropertyValueFactory<>("noOfPoints"));
        pointsCol3.setSortable(false);

        TableColumn<FootballClub, String> winsCol3 = new TableColumn<>("No. of Wins");
        winsCol3.setCellValueFactory(new PropertyValueFactory<>("noOfWins"));
        winsCol3.setSortable(false);

        TableColumn<FootballClub, String> drawsCol3 = new TableColumn<>("No. of Draws");
        drawsCol3.setCellValueFactory(new PropertyValueFactory<>("noOfDraws"));
        drawsCol3.setSortable(false);

        TableColumn<FootballClub, String> defeatsCol3 = new TableColumn<>("No. of Defeats");
        defeatsCol3.setCellValueFactory(new PropertyValueFactory<>("noOfDefeats"));
        defeatsCol3.setSortable(false);

        TableColumn<FootballClub, String> scoredCol3 = new TableColumn<>("Goals Scored");
        scoredCol3.setCellValueFactory(new PropertyValueFactory<>("noOfGoalsScored"));
        scoredCol3.setSortable(false);

        TableColumn<FootballClub, String> receivedCol3 = new TableColumn<>("Goals Received");
        receivedCol3.setCellValueFactory(new PropertyValueFactory<>("noOfGoalsReceived"));
        receivedCol3.setSortable(false);

        TableColumn<FootballClub, String> differenceCol3 = new TableColumn<>("Goal Difference");
        differenceCol3.setCellValueFactory(new PropertyValueFactory<>("noOfGoalsReceived"));
        differenceCol3.setSortable(false);

        premierLeagueTable3.setItems(footballClubObservableList3); //TableView is populated with the data in observable list
        premierLeagueTable3.getColumns().addAll(clubNameCol3, pointsCol3, winsCol3, drawsCol3, defeatsCol3, scoredCol3, receivedCol3, differenceCol3);

        premierLeagueTable3.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox vBox3=new VBox();
        vBox3.setMinSize(1020, 60);
        vBox3.getChildren().add(premierLeagueTable3);

        //button to display premier league table
        Button pointsSortBtn2=new Button("Sort according to No of Points");
        pointsSortBtn2.setOnAction(event->{
            stage.setScene(premierLeagueTableScene); //opens premierLeagueTableScene
        });

        //button to sort premier league table according to no of goals scored
        Button scoredSortBtn2=new Button("Sort according to No of Goals Scored");
        scoredSortBtn2.setOnAction(event->{
            stage.setScene(scoredGoalsScene); //scoredGoalsScene is opened
        });

        //button to return to menu
        Button menuBtn3= new Button("Return to Menu");
        menuBtn3.setOnAction(event -> {
            stage.close(); //stage is closed and returns to menu
        });

        //all elements are positioned
        pltWinsSortedHeading.setLayoutX(130);
        pltWinsSortedHeading.setLayoutY(25);
        vBox3.setLayoutX(10);
        vBox3.setLayoutY(60);
        pointsSortBtn2.setLayoutX(50);
        pointsSortBtn2.setLayoutY(500);
        scoredSortBtn2.setLayoutX(380);
        scoredSortBtn2.setLayoutY(500);
        menuBtn3.setLayoutX(800);
        menuBtn3.setLayoutY(500);

        //all elements are added to Pane
        pltWinsSortedPane.getChildren().addAll(pltWinsSortedHeading, vBox3, pointsSortBtn2, scoredSortBtn2, menuBtn3);

        //scene is initialized
        winsScene= new Scene(pltWinsSortedPane, 1040, 550);

        //Scene 3 ends

        //premierLeagueTableScene is initialized
        premierLeagueTableScene= new Scene(pltPane, 1040, 650);

        //scene is set and stage is shown
        stage.setScene(premierLeagueTableScene);
        stage.show();
    }

    public static void generateMatch(){
        Random rand=new Random();
        //the indexes are randomly generated to decide the home team and away team
        int homeTeamIndex=rand.nextInt(PremierLeagueManager.premierLeague.size());
        int awayTeamIndex=rand.nextInt(PremierLeagueManager.premierLeague.size());
        //checks if away team is same as home team, if so random index is generate again
        while(awayTeamIndex==homeTeamIndex){
            awayTeamIndex=rand.nextInt(PremierLeagueManager.premierLeague.size());
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
        PremierLeagueManager.createPlayedMatch(PremierLeagueManager.premierLeague.get(homeTeamIndex).getClubName(), PremierLeagueManager.premierLeague.get(awayTeamIndex).getClubName(), homeTeamScore, awayTeamScore, dateOfMatch);

        //GUI to display teams, scores and match date
        Stage resultStage=new Stage();
        resultStage.setTitle("Match Result");

        Pane matchResultPane=new Pane();
        matchResultPane.setMinSize(70, 550);
        matchResultPane.setStyle("-fx-background-color: linear-gradient(to left,#3AEEEE,#3838EE); -fx-font-family: Monospaced;");
        Label resultHeading=new Label("Match Result");
        resultHeading.setStyle("-fx-font-size: 20; -fx-underline:true");

        Label dateLabel=new Label("Date: " + dateOfMatch);
        dateLabel.setStyle("-fx-font-size: 14");

        Label teamName = new Label("Teams");
        Label homeTeam= new Label("Home Team: " + PremierLeagueManager.premierLeague.get(homeTeamIndex).getClubName());
        Label awayTeam= new Label("Away Team: " + PremierLeagueManager.premierLeague.get(awayTeamIndex).getClubName());

        VBox teamCol=new VBox();
        teamCol.getChildren().addAll(teamName, homeTeam, awayTeam);
        teamCol.setStyle("-fx-font-size: 14");

        Label teamScore = new Label("Score");
        Label homeTeamScoreLabel= new Label(" " +homeTeamScore);
        Label awayTeamScoreLabel= new Label(" " + awayTeamScore);

        VBox scoreCol = new VBox();
        scoreCol.getChildren().addAll(teamScore, homeTeamScoreLabel, awayTeamScoreLabel);
        scoreCol.setStyle("-fx-font-size: 14");

        //button to view premier league table (updated)
        Button pltBtn=new Button("View Premier League Table");
        pltBtn.setOnAction(event->{
            resultStage.close();
            premierLeagueTable();
        });

        //button to return to menu
        Button menuBtn= new Button("Return to Menu");
        menuBtn.setOnAction(event -> {
            resultStage.close();
        });

        resultHeading.setLayoutX(200);
        resultHeading.setLayoutY(25);
        dateLabel.setLayoutX(80);
        dateLabel.setLayoutY(55);
        teamCol.setLayoutX(80);
        teamCol.setLayoutY(80);
        scoreCol.setLayoutX(350);
        scoreCol.setLayoutY(80);
        pltBtn.setLayoutX(80);
        pltBtn.setLayoutY(160);
        menuBtn.setLayoutX(80);
        menuBtn.setLayoutY(200);

        matchResultPane.getChildren().addAll(resultHeading, dateLabel, teamCol, scoreCol, pltBtn, menuBtn);
        resultStage.setScene(new Scene(matchResultPane, 500, 300));
        resultStage.show();
    }

    //method to display match table
    public static void matchTable(){
        Stage matchTableStage=new Stage();
        matchTableStage.setTitle("Match Table");

        Pane matchTablePane=new Pane();
        matchTablePane.setMinSize(70, 550);
        matchTablePane.setStyle("-fx-background-color: linear-gradient(to left,#3AEEEE,#3838EE); -fx-font-family: Monospaced;");
        Label matchTableHeading=new Label("Match Details Table");
        matchTableHeading.setStyle("-fx-font-size: 20; -fx-underline:true");

        ObservableList <Match> matchObservableList = FXCollections.observableArrayList();
        //observablelist is populated with the sorted matchDetails arraylist
        for (Match match: PremierLeagueManager.sortMatches(PremierLeagueManager.matchDetails)){
            matchObservableList.add(new Match(match.getMatchCode(), match.getHomeTeam(), match.getAwayTeam(), match.getHomeTeamScore(), match.getAwayTeamScore(), match.getDateOfMatch()));
        }

        //TableView and TableColumns
        TableView<Match> matchTableView=new TableView<Match>();

        TableColumn<Match, String> matchDateCol=new TableColumn<>("Date of Match");
        matchDateCol.setCellValueFactory(new PropertyValueFactory<>("dateOfMatch"));
        matchDateCol.setSortable(false);

        TableColumn<Match, String> matchCodeCol=new TableColumn<>("Match Code");
        matchCodeCol.setCellValueFactory(new PropertyValueFactory<>("matchCode"));
        matchCodeCol.setSortable(false);

        TableColumn<Match, String> homeTeamCol=new TableColumn<>("Home Team");
        homeTeamCol.setCellValueFactory(new PropertyValueFactory<>("homeTeam"));
        homeTeamCol.setSortable(false);

        TableColumn<Match, String> homeTeamScoreCol=new TableColumn<>("Home Team Score");
        homeTeamScoreCol.setCellValueFactory(new PropertyValueFactory<>("homeTeamScore"));
        homeTeamScoreCol.setSortable(false);

        TableColumn<Match, String> awayTeamCol=new TableColumn<>("Away Team");
        awayTeamCol.setCellValueFactory(new PropertyValueFactory<>("awayTeam"));
        awayTeamCol.setSortable(false);

        TableColumn<Match, String> awayTeamScoreCol=new TableColumn<>("Away Team Score");
        awayTeamScoreCol.setCellValueFactory(new PropertyValueFactory<>("awayTeamScore"));
        awayTeamScoreCol.setSortable(false);

        matchTableView.setItems(matchObservableList);
        matchTableView.getColumns().addAll(matchDateCol, matchCodeCol, homeTeamCol, homeTeamScoreCol, awayTeamCol, awayTeamScoreCol);

        matchTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox vBox=new VBox();
        vBox.setMinSize(700, 550);
        vBox.getChildren().add(matchTableView);

        //button to view premier league table
        Button pltBtn=new Button("View Premier League Table");
        pltBtn.setOnAction(event->{
            matchTableStage.close();
            premierLeagueTable();
        });

        //button to return to menu
        Button menuBtn= new Button("Return to Menu");
        menuBtn.setOnAction(event -> {
            matchTableStage.close();
        });

        matchTableHeading.setLayoutX(230);
        matchTableHeading.setLayoutY(25);
        vBox.setLayoutX(50);
        vBox.setLayoutY(60);
        pltBtn.setLayoutX(80);
        pltBtn.setLayoutY(520);
        menuBtn.setLayoutX(350);
        menuBtn.setLayoutY(520);

        matchTablePane.getChildren().addAll(matchTableHeading, vBox, pltBtn, menuBtn);
        matchTableStage.setScene(new Scene(matchTablePane, 800, 580));
        matchTableStage.show();
    }

    //method to find match
    public static void findMatch(){
        Stage searchStage=new Stage();
        searchStage.setTitle("Find a Match");

        //find match pane- user enters date
        Pane searchPane=new Pane();
        searchPane.setMinSize(500, 200);
        searchPane.setStyle("-fx-background-color: linear-gradient(to left,#3AEEEE,#3838EE); -fx-font-family: Monospaced;");

        Label searchHeading = new Label ("Enter year, month and date to find match (YYYY-MM-DD)");
        searchHeading.setStyle("-fx-font-size: 14; -fx-font-family: Monospaced;");

        Label dateLabel=new Label("Enter date of match: ");

        TextField dateBox=new TextField();

        VBox labelCol=new VBox();
        labelCol.getChildren().addAll(dateLabel);
        labelCol.setStyle("-fx-font-size: 14; -fx-font-family: Monospaced;");

        VBox textBoxCol=new VBox();
        textBoxCol.getChildren().addAll(dateBox);

        //button to view premier league table
        Button pointsSortBtn1=new Button("Display Premier League Table");
        pointsSortBtn1.setOnAction(event2->{
            searchStage.close();
            premierLeagueTable();
        });

        //button to return to menu
        Button menuBtn1= new Button("Return to Menu");
        menuBtn1.setOnAction(event -> {
            searchStage.close();
        });

        Button searchBtn=new Button("Search");
        //when search button is entered user input is validated
        searchBtn.setOnAction(event -> {
            //checks if textbox is empty, if so user is altered to fill is
            if(dateBox.getText().isEmpty()){
                PremierLeagueManager.errorAlert("Field is empty!", "Please enter date to proceed.");
            }else if(!dateBox.getText().contains("-")){
                //checks if date format is correct
                PremierLeagueManager.errorAlert("Invalid input!", "Please enter a correct date format.");
            }else{
                String userInput=dateBox.getText();
                String[] arr=userInput.split("-", 3);

                boolean numCheck=false;
                //checks if year, month and day are numerical
                for (String spiltString:arr){
                    try{
                        int num=Integer.parseInt(spiltString);
                    }catch (IllegalArgumentException e){
                        numCheck=true;
                        break;
                    }
                }
                //if year, month and date are numerical
                if(!numCheck){
                    boolean dateCheck=true;
                    int yearInput=Integer.parseInt(arr[0]);
                    int monthInput=Integer.parseInt(arr[1]);
                    int dayInput=Integer.parseInt(arr[2]);

                    //validates year
                    if(yearInput<2000 || yearInput>2021){
                        PremierLeagueManager.errorAlert("Year input is invalid!", "Please enter a valid year (2000-2021).");
                        dateCheck=false;
                    }else if(monthInput<1 || monthInput>12){
                        //validates month
                        PremierLeagueManager.errorAlert("Month input is invalid!", "Please enter a valid month (1-12).");
                        dateCheck=false;
                    }else if(dayInput>0 && dayInput<32){
                        //validates day
                        if(monthInput==2) {
                            if (dayInput > 29) {
                                PremierLeagueManager.errorAlert("Date input is invalid!", "Please enter a valid date for February month.");
                                dateCheck=false;
                            }
                        }else if(monthInput==4 || monthInput==6 || monthInput==9 || monthInput==11){
                            if(dayInput>30){
                                PremierLeagueManager.errorAlert("Date input is invalid!", "Please enter a valid date for a month with 30 days.");
                                dateCheck=false;
                            }
                        }
                    }else{
                        //error alert is shown if input is not numerical
                        PremierLeagueManager.errorAlert("Date input is invalid!", "Please enter a valid date.");
                        dateCheck=false;
                    }
                    if(dateCheck){
                        LocalDate dateInput = LocalDate.of(yearInput, monthInput, dayInput);

                        //an observable list of match objects where date is equal to input date is created
                        ObservableList <Match> foundMatchObservableList = FXCollections.observableArrayList();
                        boolean found=false; //used to check if entered date exists in arraylist
                        for(Match match: PremierLeagueManager.matchDetails){
                            if(match.getDateOfMatch().equals(dateInput)){
                                found=true;
                                foundMatchObservableList.add(match);
                            }
                        }
                        //if date is not found error message is shown
                        if(!found){
                            PremierLeagueManager.errorAlert("Date not found!", "There is no record of a match for this date");
                        }else{
                            //if date is found the list of matches is dislayed in a TableView
                            Pane foundPane=new Pane();
                            foundPane.setMinSize(800, 550);
                            foundPane.setStyle("-fx-background-color: linear-gradient(to left,#3AEEEE,#3838EE); -fx-font-family: Monospaced;");

                            Label foundHeading = new Label("Matches played on " + dateInput);
                            foundHeading.setStyle("-fx-font-size: 16; -fx-font-family: Monospaced; -fx-underline:true");

                            TableView<Match> matchTableView=new TableView<Match>();

                            TableColumn<Match, String> matchDateCol=new TableColumn<>("Date of Match");
                            matchDateCol.setCellValueFactory(new PropertyValueFactory<>("dateOfMatch"));
                            matchDateCol.setSortable(false);

                            TableColumn<Match, String> matchCodeCol=new TableColumn<>("Match Code");
                            matchCodeCol.setCellValueFactory(new PropertyValueFactory<>("matchCode"));
                            matchCodeCol.setSortable(false);

                            TableColumn<Match, String> homeTeamCol=new TableColumn<>("Home Team");
                            homeTeamCol.setCellValueFactory(new PropertyValueFactory<>("homeTeam"));
                            homeTeamCol.setSortable(false);

                            TableColumn<Match, String> homeTeamScoreCol=new TableColumn<>("Home Team Score");
                            homeTeamScoreCol.setCellValueFactory(new PropertyValueFactory<>("homeTeamScore"));
                            homeTeamScoreCol.setSortable(false);

                            TableColumn<Match, String> awayTeamCol=new TableColumn<>("Away Team");
                            awayTeamCol.setCellValueFactory(new PropertyValueFactory<>("awayTeam"));
                            awayTeamCol.setSortable(false);

                            TableColumn<Match, String> awayTeamScoreCol=new TableColumn<>("Away Team Score");
                            awayTeamScoreCol.setCellValueFactory(new PropertyValueFactory<>("awayTeamScore"));
                            awayTeamScoreCol.setSortable(false);

                            matchTableView.setItems(foundMatchObservableList);
                            matchTableView.getColumns().addAll(matchDateCol, matchCodeCol, homeTeamCol, homeTeamScoreCol, awayTeamCol, awayTeamScoreCol);

                            matchTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

                            VBox vBox=new VBox();
                            vBox.setMinSize(700, 450);
                            vBox.getChildren().add(matchTableView);

                            //button to return to searchScene
                            Button searchMatchBtn= new Button("Find a Match");
                            searchMatchBtn.setOnAction(event2 -> {
                                searchStage.setScene(searchScene);
                            });

                            //button to display premier league table
                            Button pointsSortBtn2=new Button("Display Premier League Table");
                            pointsSortBtn2.setOnAction(event2->{
                                searchStage.close();
                                premierLeagueTable();
                            });

                            //button to return to menu
                            Button menuBtn2= new Button("Return to Menu");
                            menuBtn2.setOnAction(event2 -> {
                                searchStage.close();
                            });

                            foundHeading.setLayoutX(210);
                            foundHeading.setLayoutY(25);
                            vBox.setLayoutX(50);
                            vBox.setLayoutY(60);
                            searchMatchBtn.setLayoutX(60);
                            searchMatchBtn.setLayoutY(475);
                            pointsSortBtn2.setLayoutX(200);
                            pointsSortBtn2.setLayoutY(475);
                            menuBtn2.setLayoutX(450);
                            menuBtn2.setLayoutY(475);

                            foundPane.getChildren().addAll(foundHeading, vBox, searchMatchBtn, pointsSortBtn2, menuBtn2);
                            foundScene=new Scene(foundPane, 800, 550);
                            searchStage.setScene(foundScene);
                            searchStage.show();
                        }
                    }
                }else{
                    PremierLeagueManager.errorAlert("Date input is invalid!", "Please enter a numeric value only.");
                }
                dateBox.clear();
            }
        });

        searchHeading.setLayoutX(40);
        searchHeading.setLayoutY(25);
        labelCol.setLayoutX(60);
        labelCol.setLayoutY(60);
        textBoxCol.setLayoutX(300);
        textBoxCol.setLayoutY(60);
        searchBtn.setLayoutX(60);
        searchBtn.setLayoutY(150);
        pointsSortBtn1.setLayoutX(160);
        pointsSortBtn1.setLayoutY(150);
        menuBtn1.setLayoutX(400);
        menuBtn1.setLayoutY(150);

        searchPane.getChildren().addAll(searchHeading, labelCol, textBoxCol, searchBtn, pointsSortBtn1, menuBtn1);
        searchScene = new Scene(searchPane, 550, 200);
        searchStage.setScene(searchScene);
        searchStage.show();
    }
}

