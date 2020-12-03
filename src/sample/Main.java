package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("CIS 3368 COVID-19 Tracker Login");
        primaryStage.setScene(new Scene(root, 378, 420));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
