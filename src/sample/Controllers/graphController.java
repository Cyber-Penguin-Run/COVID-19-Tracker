package sample.Controllers;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import sample.Models.Country;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class graphController implements Initializable {
    //Strings to use to connect to the AWS MySQL server.
    final String hostname = "";
    final String rdbusername = "";
    final String rdbpassword = "";

    //Elements of the chart from bargraph.fxml.
    @FXML
    private BarChart<?, ?> caseChart;
    @FXML
    private CategoryAxis x;
    @FXML
    private NumberAxis y;


    //Method to insert data into Bargraph, inspired by Genuine Coder.
    private void XYdata(String sqlurl, String username, String password) throws SQLException {
        //Connecting to SQL database and getting the pre-made user UUID.
        Connection conn = (Connection) DriverManager.getConnection(sqlurl, username, password);
        Statement stmt = (Statement) conn.createStatement();
        String selectAll = "SELECT * FROM CovidCount";
        ResultSet rs = stmt.executeQuery(selectAll);
        XYChart.Series nameSet = new XYChart.Series<>();
        Country country = new Country();
        String str;
        while (rs.next()) {
            country.setName(rs.getString("CountryName"));
            str = rs.getString("TotalCases");
            country.setTotCase(str.replaceAll(",",""));
            nameSet.getData().add(new XYChart.Data(country.getName(), Integer.valueOf(country.getTotCase())));
        }
        caseChart.getData().addAll(nameSet);
        rs.close();
        stmt.close();
        conn.close();

    }


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
        try {
            XYdata(hostname, rdbusername, rdbpassword);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
