package sample.Controllers;

import com.mysql.jdbc.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Models.User;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

public class loginController implements Initializable {

    //Button on the login.fxml
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
            ArrayList<String> userList = new ArrayList<String>();
            ArrayList<String> passwordList = new ArrayList<String>();
            ArrayList<Boolean> adminList = new ArrayList<Boolean>();
            //Credit to Bart1612 from Stackoverflow.
            while(rsUser.next()){
                userList.add(rsUser.getString("Username"));
                passwordList.add(rsUser.getString("Password"));
                adminList.add(rsUser.getBoolean("Administrator"));
            }
            //End of credit to Bart1612 from Stackoverflow.
            System.out.println(userList);
            System.out.println(passwordList);
            System.out.println(adminList);
            for (int i = 0; i <= userList.size(); i++){
                if (usernameText.getText().equals(userList.get(i)) && passwordText.getText().equals(passwordList.get(i))){
                    User user = new User();
                    user.setUserid(UUID.randomUUID());
                    user.setName(userList.get(i));
                    user.setPass(passwordList.get(i));
                    user.setAdminRights(adminList.get(i));
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Success!");
                    a.setHeaderText("You have logged in.");
                    a.show();
                    System.out.println(user.getUserid());
                }
                else{
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Error!");
                    a.setHeaderText("Invalid Login.");
                    a.show();
                }
            }


        }catch(Exception ex){

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
