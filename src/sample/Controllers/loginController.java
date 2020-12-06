package sample.Controllers;

import com.mysql.jdbc.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Models.User;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.UUID;

public class loginController implements Initializable {

    //Button on the login.fxml
    @FXML
    private Button continButton;
    @FXML
    private Button loginButton;
    @FXML
    private Button exitButton;
    @FXML
    private TextField usernameText;
    @FXML
    private PasswordField passwordText;

    //Strings to use to connect to the AWS MySQL server.
    final String hostname = "jdbc:mysql://class3368.c3qkvsmzsjaa.us-east-1.rds.amazonaws.com:3306/CovidTrack";
    final String rdbusername = "admin";
    final String rdbpassword ="IAmNotAdmin169!";



    //Method for to authenticate the user.
    private void userLogin(String url){
        try{
            Connection logConn = (Connection) DriverManager.getConnection(url, rdbusername, rdbpassword);
            Statement logstmt = (Statement) logConn.createStatement();
            String selectUsers = "SELECT * FROM Users";
            ResultSet rsUser = logstmt.executeQuery(selectUsers);
            ArrayList<String> useridList = new ArrayList<String>();
            ArrayList<String> userList = new ArrayList<String>();
            ArrayList<String> passwordList = new ArrayList<String>();
            ArrayList<Boolean> adminList = new ArrayList<Boolean>();
            //Credit to Bart1612 from Stackoverflow.
            while(rsUser.next()){
                useridList.add(rsUser.getString("UserId"));
                userList.add(rsUser.getString("Username"));
                passwordList.add(rsUser.getString("Password"));
                adminList.add(rsUser.getBoolean("Administrator"));
            }
            //End of credit to Bart1612 from Stackoverflow.
            //testing what the object has System.out.println(userList);
            //testing what the object has System.out.println(passwordList);
            //testing what the object has System.out.println(adminList);
            for (int i = 0; i <= userList.size(); i++){
                if (usernameText.getText().equals(userList.get(i)) && passwordText.getText().equals(passwordList.get(i))){
                    User user = new User();
                    user.setUserId(UUID.fromString(useridList.get(i)));
                    user.setName(userList.get(i));
                    user.setPass(passwordList.get(i));
                    user.setAdminRights(adminList.get(i));
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Success!");
                    successAlert.setHeaderText("You have logged in. Please click \"Continue\".");
                    successAlert.show();
                    continButton.setVisible(true);
                    //testing what the object has System.out.println(user.getUserId());
                }
                else{
                    Alert invalidLogAlert = new Alert(Alert.AlertType.ERROR);
                    invalidLogAlert.setTitle("Error!");
                    invalidLogAlert.setHeaderText("Invalid Login.");
                    invalidLogAlert.show();
                }
            }
            //closing connections
            rsUser.close();
            logstmt.close();
            logConn.close();


        }catch(Exception ex){
            Alert serverError = new Alert(Alert.AlertType.ERROR);
            serverError.setTitle("Error!");
            serverError.setHeaderText("You are not connected to the AWS Server!");
        }
    }

    //Method to change to another scene, inspired by Jaret Wright on Youtube.
    @FXML
    private void sceneChange(ActionEvent event) throws IOException {
        FXMLLoader userLoader = new FXMLLoader();
        userLoader.setLocation(getClass().getResource("../Scenes/mainpage.fxml"));
        Parent covidTrackParent = userLoader.load();
        Scene covidTrackScene = new Scene(covidTrackParent,1124, 529);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("COVID-19 Tracker Main Menu");
        window.setScene(covidTrackScene);
        window.show();
    }//end of credit.


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Hiding the continue button on start.
        continButton.setVisible(false);


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
