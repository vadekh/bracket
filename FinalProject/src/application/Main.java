package application;

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

    private final int GUI_HEIGHT = 800;
    private final int GUI_WIDTH = 1200;

    @Override public void start(Stage primaryStage) {
        int teamNum = 16;
        int roundNum = (int) (Math.log(teamNum) / Math.log(2));
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, GUI_WIDTH, GUI_HEIGHT);
        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        HBox hBox = new HBox(8);
        VBox[] vBoxArray = new VBox[roundNum + 1];
        for (int i = 0; i < vBoxArray.length; i++) {
            vBoxArray[i] = new VBox(8);
            for (int j = 0; j < teamNum; j++) {
                HBox innerHbox = new HBox(8);
                Label temp = new Label("Team " + (j + 1));
                TextField temp1 = new TextField();
                temp1.setPromptText("Enter Score: ");
                Button temp2 = new Button("Submit");
                if (i != 0) {
                    temp2.setDisable(true);
                    temp1.setDisable(true);
                    temp.setText("TBD");
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

