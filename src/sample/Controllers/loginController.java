package sample.Controllers;

import com.mysql.jdbc.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
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

    final String hostname = "jdbc:mysql://class3368.c3qkvsmzsjaa.us-east-1.rds.amazonaws.com:3306/CovidTrack";
    final String rdbusername = "admin";
    final String rdbpassword ="IAmNotAdmin169!";




    private void userLogin(String url){
        try{
            Connection logConn = (Connection) DriverManager.getConnection(url, rdbusername, rdbpassword);
            Statement logstmt = (Statement) logConn.createStatement();
            String selectUsers = "SELECT * FROM Users WHERE Username";
            ResultSet rsUser =logstmt.executeQuery(selectUsers);



        }catch(Exception ex){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Error");
            a.setHeaderText("Invalid Login");
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        //Button to login to the application.
        loginButton.setOnAction((event)->{
            userLogin(hostname);
        });

        //Button to exit the application.
        exitButton.setOnAction((event) -> {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();
        });

    }
}
