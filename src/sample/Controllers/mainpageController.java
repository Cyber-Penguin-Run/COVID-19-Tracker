package sample.Controllers;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONObject;
import sample.Models.Country;
import sample.Models.CountrySnap;
import sample.Models.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.UUID;


public class mainpageController implements Initializable{

    //Strings to use to connect to the AWS MySQL server.
    final String hostname = "jdbc:mysql://class3368.c3qkvsmzsjaa.us-east-1.rds.amazonaws.com:3306/CovidTrack";
    final String rdbusername = "admin";
    final String rdbpassword ="IAmNotAdmin169!";

    //Strings to REQUEST from API
    String apiurl ="https://covid-19-tracking.p.rapidapi.com/v1/";
    String apikey = "af085016f0mshb822eda0980b34fp1265d5jsne7d490a3ea89";

    //Labels from mainpage.fxml
    @FXML
    private Label countryLable;//Lable for country name.
    @FXML
    private Label dateLable;//Lable for date
    @FXML
    private Label tcaseLable;//Lable for total case.
    @FXML
    private Label deathLable;//Lable for total deaths.
    @FXML
    private Label dcaseLable;//Lable for daily cases.


    //Buttons from mainpage.fxml
    @FXML
    private Button searchButton;

    @FXML
    private Button addUserButton;

    @FXML
    private Button saveButton;

    //Textfield from mainpage.fxml
    @FXML
    private TextField countryName;

    //Table from mainpage.fxml
    @FXML
    private TableView<CountrySnap> covidTableView;

    @FXML
    private TableColumn<CountrySnap, String> countryCol;
    @FXML
    private TableColumn<CountrySnap, String> dateCol;
    @FXML
    private TableColumn<CountrySnap, String> totcaseCol;
    @FXML
    private TableColumn<CountrySnap, String> totdeathCol;
    @FXML
    private TableColumn<CountrySnap, String> dcaseCol;

    //Method to read from JSON GET and to set lables to respective values.
    private void readJson(String uInput, String url, String apikey) {
        try {
            //Credit to jinu jawad m on Youtube.
            URL jObj = new URL(url + uInput);
            HttpURLConnection httpCon = (HttpURLConnection) jObj.openConnection();
            httpCon.setRequestProperty("x-rapidapi-key", apikey);
            int responseCode = httpCon.getResponseCode();

            System.out.println("Sending GET request to "+url+uInput);
            System.out.println("Response Code "+responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while((inputLine = in.readLine())!=null){
                response.append(inputLine);
            }
            in.close();
            //end of credit

            //Creating Objects
            JSONObject countryresponse= new JSONObject(response.toString());

            //Setting lables to JSON key values.
            countryLable.setText(countryresponse.getString("Country_text"));
            dateLable.setText(String.valueOf(java.time.LocalDate.now()));
            tcaseLable.setText(countryresponse.getString("Total Cases_text"));
            deathLable.setText(countryresponse.getString("Total Deaths_text"));
            dcaseLable.setText(countryresponse.getString("New Cases_text"));


        }catch (Exception e){
            Alert invalidCountry = new Alert(Alert.AlertType.ERROR);
            invalidCountry.setTitle("Error");
            invalidCountry.setHeaderText("Must provide country name.");
            invalidCountry.show();
        }




    }

    //Method to Save a snapshot of the JSON GET.
    private void saveCountry(String uInput, String apiurl, String apikey, String sqlurl, String username, String password){
        //GET request from API and creating country object.
        try {
            //Credit to jinu jawad m on Youtube.
            URL jObj = new URL(apiurl + uInput);
            HttpURLConnection httpCon = (HttpURLConnection) jObj.openConnection();
            httpCon.setRequestProperty("x-rapidapi-key", apikey);
            int responseCode = httpCon.getResponseCode();

            System.out.println("Sending GET request to " + apiurl + uInput);
            System.out.println("Response Code " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //end of credit

            //Creating country object.
            Country searchCountry = new Country();
            JSONObject countryresponse= new JSONObject(response.toString());

            //Inserting values into Country object
            searchCountry.setCountryid(UUID.randomUUID());
            searchCountry.setName(countryresponse.getString("Country_text"));
            searchCountry.setDate(String.valueOf(java.time.LocalDate.now()));
            searchCountry.setTotCase(countryresponse.getString("Total Cases_text"));
            searchCountry.setTotDeaths(countryresponse.getString("Total Deaths_text"));
            searchCountry.setNewCase(countryresponse.getString("New Cases_text"));

            //Connecting to SQL database and getting the pre-made user UUID.
            Connection conn = (Connection) DriverManager.getConnection(sqlurl, username, password);
            Statement stmt = (Statement)conn.createStatement();
            String selectUserID = "SELECT UserId FROM Users";
            ResultSet rs = stmt.executeQuery(selectUserID);
            User user = new User();
            while(rs.next()) {
                user.setUserId(UUID.fromString(rs.getString(1)));
            }

            //Inserting objects from list to table. Credit to Alvin Alexander on alvinalexander.com
            String query = " insert into CovidCount (Id, CountryName, Date, TotalCases, TotalDeaths, NewCases, UserId)"
                    + " values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pStmt = (PreparedStatement) conn.prepareStatement(query);
            pStmt.setString(1, searchCountry.getCountryid().toString());
            pStmt.setString(2, searchCountry.getName());
            pStmt.setString(3, searchCountry.getDate());
            pStmt.setString(4, searchCountry.getTotCase());
            pStmt.setString(5, searchCountry.getTotDeaths());
            pStmt.setString(6, searchCountry.getNewCase());
            pStmt.setString(7, user.getUserId().toString());
            pStmt.execute();

            //closing connections.
            pStmt.close();
            rs.close();
            stmt.close();
            conn.close();

        }catch(Exception e){
            Alert invalidCountry = new Alert(Alert.AlertType.ERROR);
            invalidCountry.setTitle("Error");
            invalidCountry.setHeaderText("Must provide country name.");
            invalidCountry.show();
        }


    }

    //Method for a ObservableList, inspired by Jaret Wright on Youtube.
    public ObservableList<CountrySnap> getCountry() throws SQLException {
        ObservableList<CountrySnap> countries = FXCollections.observableArrayList();
        Connection conn = (Connection) DriverManager.getConnection(hostname, rdbusername, rdbpassword);
        Statement stmt = (Statement)conn.createStatement();
        String selectdata = "SELECT * FROM CovidCount";
        ResultSet rs = stmt.executeQuery(selectdata);
        while(rs.next()){
            countries.add(new CountrySnap(rs.getString("CountryName"),rs.getString("Date"),rs.getString("TotalCases"),rs.getString("TotalDeaths"), rs.getString("NewCases")));
        }


        return countries;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        countryCol.setCellValueFactory(new PropertyValueFactory<CountrySnap, String>("countrySnap"));
        dateCol.setCellValueFactory(new PropertyValueFactory<CountrySnap, String>("dateSnap"));
        totcaseCol.setCellValueFactory(new PropertyValueFactory<CountrySnap, String>("totcaseSnap"));
        totdeathCol.setCellValueFactory(new PropertyValueFactory<CountrySnap, String>("totdeathSnap"));
        dcaseCol.setCellValueFactory(new PropertyValueFactory<CountrySnap, String>("newcaseSnap"));
        try {
            covidTableView.setItems(getCountry());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        addUserButton.setVisible(false);

        searchButton.setOnAction((event)->{
            String userInput = countryName.getText();
            readJson(userInput, apiurl, apikey );
        });

        saveButton.setOnAction((event)->{
            String userInput = countryName.getText();
            saveCountry(userInput, apiurl, apikey, hostname, rdbusername, rdbpassword);
            try {
                covidTableView.setItems(getCountry());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });


    }
}
