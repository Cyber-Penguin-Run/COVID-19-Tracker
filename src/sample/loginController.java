package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class loginController implements Initializable {

    @FXML
    private Button loginButton;
    @FXML
    private Button exitButton;
    @FXML
    private TextField usernameText;
    @FXML
    private TextField passwordText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        //Button to login to the application.
        loginButton.setOnAction((event)->{

        });

        //Button to exit the application.
        exitButton.setOnAction((event) -> {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();
        });

    }
}
