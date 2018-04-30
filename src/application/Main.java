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

public class Main extends Application {

    // Window size
    private final int GUI_HEIGHT = 800;
    private final int GUI_WIDTH = 1400;
    private static ArrayList<Competitor> initialCompetitors;
    private static Round[] rounds;
    @Override 
    public void start(Stage primaryStage) {

        // the first cmd line argument will be the file path
        String fileName = getParameters().getRaw().get(0);
        TeamLoader teamLoader = new TeamLoader(fileName);
        //TeamLoader teamLoader = new TeamLoader("C:\\Users\\James\\git\\bracket\\src\\teams.txt"); // for testing


        // initial starting teams
        // this will be dynamic when we
        // read from file
        int teamNum = teamLoader.getNumTeams();
        // the number of rounds
        int roundNum = (int) (Math.log(teamNum) / Math.log(2));
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, GUI_WIDTH, GUI_HEIGHT);
        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        
        // build our layout dynamically based on teamNum
        HBox bracket = new HBox(8);
        VBox[] rounds = new VBox[roundNum];
        Object[] teams = seed(teamLoader.getTeams().toArray());
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
        
        for (int i = 0; i < rounds.length; i++) {
            rounds[i] = new VBox(20);
            for (int j = 0; j < teamNum; j+=2) {
                VBox matchup = new VBox(8);
                HBox team1 = new HBox(8);
                HBox team2 = new HBox(8);
                Label team1Name = new Label(teams[j].toString());
                Label team2Name = new Label(teams[j + 1].toString());
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
                    /*
                     * This is how I got the potential winners to appear for
                     * the second round. It is messy, but the only way I could figure it
                     * out given how James set up the code initially.
                     */
                team1.getChildren().addAll(team1Name, team1Text, submitButton);
                team2.getChildren().addAll(team2Name, team2Text);
                matchup.getChildren().addAll(team1, team2);
                rounds[i].getChildren().addAll(matchup);
            }
            teamNum = teamNum / 2;
        }

        for (int i = 0; i < rounds.length; i++) {
            bracket.getChildren().add(rounds[i]);
        }
        bracket.getChildren().add(new Label("Winner: TBD"));
        Scene scena = new Scene(bracket, GUI_WIDTH, GUI_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setScene(scena);
        primaryStage.show();
    }

    public static void main(String[] args) {
        TeamLoader teamLoader = new TeamLoader("C:\\Users\\James\\git\\bracket\\src\\teams.txt");   //for testing
        int teamNum = teamLoader.getNumTeams();
        int roundNum = (int) (Math.log(teamNum) / Math.log(2));
        initialCompetitors = new ArrayList<Competitor>();
        rounds = new Round[roundNum];
        for(int i = 0; i < teamNum; i++)
        {
            initialCompetitors.add(new Competitor(i, teamLoader.getTeams().get(i)));
        }
        rounds[0] = new Round(teamNum, initialCompetitors);
        launch(args);
    }
    
    //better seeding algorithm
    public Object[] seed(Object[] teams)
    {
        String[][] test = new String[5][5];
        int height = 1;
        int length = teams.length;
        Object[][] big = new String[length][1];
        for(int i = 0; i < teams.length; i++)
        {
            big[i][0] = teams[i];
        }
        while(height <= teams.length - 1)
        {
            String[][] bigTemp = new String[big.length/2][height*2];
            for(int i = 0; i < big.length/2; i++)
            {
                String[] temp = Stream.concat(Arrays.stream(big[i]), Arrays.stream(big[(big.length - 1) - i])).toArray(String[]::new);
                bigTemp[i] = temp;
                
            }
            
            big = bigTemp;
            height *= 2;
        }
        Object[] newTeam = new String[teams.length];
        for(int i = 0; i < teams.length; i++)
        {
            newTeam[i] = big[0][i];
        }
        return newTeam;
        
    }
   
}

