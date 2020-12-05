package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;


public class mainpageController implements Initializable{

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

    //Textfield from mainpage.fxml
    @FXML
    private TextField countryName;

    //Table from mainpage.fxml
    @FXML
    private TableView covidTableView;


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
            JSONObject countryresponse= new JSONObject(response.toString());
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addUserButton.setVisible(false);

        searchButton.setOnAction((event)->{
            String userInput = countryName.getText();
            readJson(userInput, apiurl, apikey );
        });

    }
}
