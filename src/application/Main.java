package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.TextField;


public class Main extends Application {

    // Window size
    private final int GUI_HEIGHT = 800;
    private final int GUI_WIDTH = 1400;
    private static Competitor[] initialCompetitors;
    private static Round[] roundsInternal;
    @Override 
    public void start(Stage primaryStage) {

        // the first cmd line argument will be the file path
        TeamLoader teamLoader = new TeamLoader("C:\\Users\\James\\git\\bracket\\src\\teams.txt");
        int teamNum = teamLoader.getNumTeams();

        // the number of rounds
        int roundNum = (int) (Math.log(teamNum) / Math.log(2));
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, GUI_WIDTH, GUI_HEIGHT);
        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        
        // build our layout dynamically based on teamNum
        HBox bracket = new HBox(8);
        bracket.setPadding(new Insets(20, 20, 20, 20));
        VBox[] rounds = new VBox[roundNum];
        String[] teams = new String[teamNum];
        for(int i = 0; i < initialCompetitors.length; i++)
        {
            teams[i] = initialCompetitors[i].getName();
        }
        /*
         * Given how the loop works, I had to initialize
         * the counters before it begins. 
         */
        //Counters used for team labeling
        int revSeedCounter = teamNum; //Highest # seed
        int seedCounter = 1; //Lowest # seed 
        //Used for iterating through matchup array in different directions
        int arrayCounter = 0; 
        int revArrayCounter = 0;
        Label firstPlace = new Label();
        Label secondPlace = new Label();
        Label thirdPlace = new Label();
        for (int i = 0; i < rounds.length; i++) {
            rounds[i] = new VBox(20);
            for (int j = 0; j < teamNum; j+=2) {
                VBox matchup = new VBox(8);
                HBox team1 = new HBox(8);
                HBox team2 = new HBox(8);
                Label team1Name;
                Label team2Name;
                if(i == 0)
                {
                    team1Name = new Label(teams[j].toString());
                    team2Name = new Label(teams[j + 1].toString());
                }
                else
                {
                    team1Name = new Label("TBD");
                    team2Name = new Label("TBD");
                }
                // change this to load team name from
                // our teams file
                //Check if the counter is even, if so, label as low # seed
                TextField team1Text = new TextField();
                TextField team2Text = new TextField();
                team1Text.setPromptText("Enter Score: ");
                team2Text.setPromptText("Enter Score: ");
                Button submitButton = new Button("Submit");
                if (i != 0) {
                    team1Text.setDisable(true);
                    team2Text.setDisable(true);
                    submitButton.setDisable(true);
                }
                int roundIndex = i;
                int matchupIndex = j/2;
                submitButton.setOnAction(new EventHandler<ActionEvent>()
                                {
                                    public void handle(ActionEvent e)
                                    {
                                        int score1 = Integer.parseInt(team1Text.getText());
                                        int score2 = Integer.parseInt(team2Text.getText());
                                        roundsInternal[roundIndex].getMatchups()[matchupIndex].setScore1(score1);
                                        roundsInternal[roundIndex].getMatchups()[matchupIndex].setScore2(score2);
                                        Competitor winner = roundsInternal[roundIndex].getMatchups()[matchupIndex].getWinner();
                                        if(roundIndex == roundNum - 1)
                                        {
                                            Competitor loser1 = roundsInternal[roundIndex - 1].getMatchups()[0].getLoser();
                                            Competitor loser2 = roundsInternal[roundIndex - 1].getMatchups()[1].getLoser();
                                            if(loser1.getScore() < loser2.getScore())
                                            {
                                                thirdPlace.setText("Third Place: " + loser2.getName());
                                            }
                                            else if (loser2.getScore() < loser1.getScore())
                                            {
                                                thirdPlace.setText("Third Place: " + loser1.getName());
                                            }
                                            else
                                            {
                                                thirdPlace.setText("Third Place: TIE between " + loser1.getName() + " and " + loser2.getName());
                                            }
                                            firstPlace.setText("Winner: " + winner.getName());
                                            secondPlace.setText("Second Place: " + roundsInternal[roundIndex].getMatchups()[matchupIndex].getLoser());
                                            firstPlace.setFont(Font.font("Verdana", 12));
                                            secondPlace.setFont(Font.font("Verdana", 12));
                                            thirdPlace.setFont(Font.font("Verdana", 12));
                                            firstPlace.setTextFill(Color.RED);
                                            secondPlace.setTextFill(Color.RED);
                                            thirdPlace.setTextFill(Color.RED);
                                            //Label thirdPlace = new Label() in progress
                                        }
                                        else
                                        {
                                            if(matchupIndex % 2 == 0)
                                            {
                                                roundsInternal[roundIndex + 1].getMatchups()[matchupIndex/2].setCompetitor1(winner);
                                                ((Label) ((HBox) ((VBox) rounds[roundIndex + 1].getChildren().get(matchupIndex/2)).getChildren().get(0)).getChildren().get(0)).setText(winner.getName());
                                                ((HBox) ((VBox) rounds[roundIndex + 1].getChildren().get(matchupIndex/2)).getChildren().get(0)).getChildren().get(1).setDisable(false);
                                                if(!(roundsInternal[roundIndex + 1].getMatchups()[matchupIndex/2].c1 == null || roundsInternal[roundIndex + 1].getMatchups()[matchupIndex/2].c2 == null))
                                                    ((HBox) ((VBox) rounds[roundIndex + 1].getChildren().get(matchupIndex/2)).getChildren().get(1)).getChildren().get(2).setDisable(false);
                                                submitButton.setDisable(true);
                                                team1Text.setDisable(true);
                                                team2Text.setDisable(true);
                                                
                                            }
                                            else
                                            {
                                                roundsInternal[roundIndex + 1].getMatchups()[matchupIndex/2].setCompetitor2(winner);
                                                ((Label) ((HBox) ((VBox) rounds[roundIndex + 1].getChildren().get(matchupIndex/2)).getChildren().get(1)).getChildren().get(0)).setText(winner.getName());
                                                ((HBox) ((VBox) rounds[roundIndex + 1].getChildren().get(matchupIndex/2)).getChildren().get(1)).getChildren().get(1).setDisable(false);
                                                if(!(roundsInternal[roundIndex + 1].getMatchups()[matchupIndex/2].c1 == null || roundsInternal[roundIndex + 1].getMatchups()[matchupIndex/2].c2 == null))
                                                    ((HBox) ((VBox) rounds[roundIndex + 1].getChildren().get(matchupIndex/2)).getChildren().get(1)).getChildren().get(2).setDisable(false);
                                                submitButton.setDisable(true);
                                                team1Text.setDisable(true);
                                                team2Text.setDisable(true);
                                            }
                                        }
                                        
                                    }
                                });
                    /*
                     * This is how I got the potential winners to appear for
                     * the second round. It is messy, but the only way I could figure it
                     * out given how James set up the code initially.
                     */
                team1.getChildren().addAll(team1Name, team1Text);
                team2.getChildren().addAll(team2Name, team2Text, submitButton);
                matchup.getChildren().addAll(team1, team2);
                rounds[i].getChildren().addAll(matchup);
            }
            teamNum = teamNum / 2;
        }

        for (int i = 0; i < rounds.length; i++) {
            bracket.getChildren().add(rounds[i]);
        }
        bracket.getChildren().addAll(firstPlace, secondPlace, thirdPlace);
        Scene scena = new Scene(bracket, GUI_WIDTH, GUI_HEIGHT);
        primaryStage.setTitle("Bracket");
        primaryStage.setScene(scene);
        primaryStage.setScene(scena);
        primaryStage.show();
    }

    public static void main(String[] args) {
        TeamLoader teamLoader = new TeamLoader("C:\\Users\\James\\git\\bracket\\src\\teams.txt");   //for testing
        int teamNum = teamLoader.getNumTeams();
        int roundNum = (int) (Math.log(teamNum) / Math.log(2));
        initialCompetitors = new Competitor[teamNum];
        roundsInternal = new Round[roundNum];
        for(int i = 0; i < teamNum; i++)
        {
            initialCompetitors[i] = new Competitor(i, teamLoader.getTeams()[i]);
        }
        initialCompetitors = seed(initialCompetitors);
        roundsInternal[0] = new Round(teamNum, initialCompetitors);
        teamNum/=2;
        roundsInternal[0].setMatchups();
        for(int i = 1; i < roundNum; i++)
        {
            roundsInternal[i] = new Round(teamNum, new Competitor[teamNum]);
            roundsInternal[i].setMatchups();
            teamNum/=2;
        }
        
        launch(args);
    }
    
    //better seeding algorithm
    public static Competitor[] seed(Competitor[] teams)
    {
        int height = 1;
        int length = teams.length;
        Competitor[][] big = new Competitor[length][1];
        for(int i = 0; i < teams.length; i++)
        {
            big[i][0] = teams[i];
        }
        while(height <= teams.length - 1)
        {
            Competitor[][] bigTemp = new Competitor[big.length/2][height*2];
            for(int i = 0; i < big.length/2; i++)
            {
                Competitor[] temp = Stream.concat(Arrays.stream(big[i]), Arrays.stream(big[(big.length - 1) - i])).toArray(Competitor[]::new);
                bigTemp[i] = temp;
                
            }
            
            big = bigTemp;
            height *= 2;
        }
        Competitor[] newTeam = new Competitor[teams.length];
        for(int i = 0; i < teams.length; i++)
        {
            newTeam[i] = big[0][i];
        }
        return newTeam;
        
    }
   
}
