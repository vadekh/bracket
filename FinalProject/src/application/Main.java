//
// Title:           Tournament Bracket 
// Due Date:        May 3rd, 2018
// Files:           Main.java, Round.java, Matchup.java, Competitor.java, TeamLoader.java, application.css
// Course:          CS 400, Spring 2018
//
// Authors:         A-Team 30: Brady Erdman, Alec Chan, James Kuoppala, James Ungaretti, Vade Kamenitsa-Hale
// Email:           erdman3@wisc.edu, aechan@wisc.edu, kuoppala@wisc.edu, ungaretti@wisc.edu, kamenitsahal@wisc.edu
// Lecturer's Name: Deb Depeller
//
// No known bugs
//

package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;

/**
 * This is the Main class for creating and displaying our
 * Tournament Bracket GUI. It is responsible for reading
 * in a list of competitors from a file and 
 * constructing a bracket with proper seeding. The seeding 
 * will be such that the number 1 and 2 seeds can only meet
 * in the championship. Matchups for each round depend on
 * initial team seeding and the winner of previous matchups. 
 * The user chooses the winners of each matchup by entering in
 * scores for each matchup. Teams with high scores move on. Execution
 * continues until a winner has been chosen.
 * The bracket scales with 2^x number of teams.
 * 
 * @author A-Team
 *
 */
public class Main extends Application {

    // Window size
    private final int GUI_HEIGHT = 800;
    private final int GUI_WIDTH = 1400;

    @Override 
    public void start(Stage primaryStage) {
        // initial starting teams
        // this will be dynamic when we
        // read from file
        int teamNum = 16;
        // what round are we on
        int roundNum = (int) (Math.log(teamNum) / Math.log(2));

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, GUI_WIDTH, GUI_HEIGHT);
        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        
        // build our layout dynamically based on teamNum
        HBox hBox = new HBox(8);
        VBox[] vBoxArray = new VBox[roundNum + 1];
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
        
        for (int i = 0; i < vBoxArray.length; i++) {
            vBoxArray[i] = new VBox(8);
            for (int j = 0; j < teamNum; j++) {
                HBox innerHbox = new HBox(8);
                Label temp; //label for each team
                
                // change this to load team name from
                // our teams file
                //Check if the counter is even, if so, label as low # seed
                if(j % 2 == 0) {
                	temp = new Label("Team " + seedCounter);
                	seedCounter++;
                }
                //Counter is odd, label as high # seed
                else {
                	temp = new Label("Team " + (revSeedCounter));
                	revSeedCounter--;
                }
           
                TextField temp1 = new TextField();
                temp1.setPromptText("Enter Score: ");
                Button temp2 = new Button("Submit");
                if (i != 0) {
                    temp2.setDisable(true);
                    temp1.setDisable(true);
                    /*
                     * This is how I got the potential winners to appear for
                     * the second round. It is messy, but the only way I could figure it
                     * out given how James set up the code initially.
                     */
                    if(i == 1) {
                        String[] possibleWinners = new String[teamNum];
                        int priorTeamNum = teamNum * 2;
                        //Construct array of possible round 1 winners
                        for (int f = 0; f < possibleWinners.length; f++) {
                        	possibleWinners[f] = (f + 1) + "/" + priorTeamNum;
                        	priorTeamNum--;
                        }
                        /*
                         * The winner of most imbalanced matchup will face off 
                         * against the winner of the most balanced matchup in
                         * round 2.
                         */
                        //Current most imbalanced matchup
                        if(j % 2 == 0) {
                        	temp.setText("Team " + possibleWinners[arrayCounter]);
                        	arrayCounter++;
                        }
                        //Current most balanced matchup
                        else {
                        	temp.setText("Team " + possibleWinners[possibleWinners.length - 1 - revArrayCounter]);
                        	revArrayCounter++;
                        }
                    }
                    else {
                    	temp.setText("TBD");
                    } 
                }
                if (i != vBoxArray.length - 1) {
                    innerHbox.getChildren().addAll(temp, temp1, temp2);
                } else {
                    innerHbox.getChildren().addAll(temp);
                }
                vBoxArray[i].getChildren().add(innerHbox);
                if ((j + 1) % 2 == 0) {
                    vBoxArray[i].getChildren().add(new Label("             "));
                }
            }
            teamNum = teamNum / 2;
        }

        for (int i = 0; i < vBoxArray.length; i++) {
            hBox.getChildren().add(vBoxArray[i]);
        }
        
        Scene scena = new Scene(hBox, GUI_WIDTH, GUI_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setScene(scena);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

