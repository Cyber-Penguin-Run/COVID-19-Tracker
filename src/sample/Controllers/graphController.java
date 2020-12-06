package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class graphController implements Initializable {

    //Method to change to another scene, inspired by Jaret Wright on Youtube.
    @FXML
    private void sceneChange(ActionEvent event) throws IOException {
        FXMLLoader userLoader = new FXMLLoader();
        userLoader.setLocation(getClass().getResource("../Scenes/mainpage.fxml"));
        Parent covidGraphParent = userLoader.load();
        Scene covidGraphScene = new Scene(covidGraphParent,1124, 529);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("COVID-19 Tracker Main Menu");
        window.setScene(covidGraphScene);
        window.show();
    }//end of credit.


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
