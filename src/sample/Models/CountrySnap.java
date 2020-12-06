package sample.Models;
import javafx.beans.property.SimpleStringProperty;

import java.util.UUID;

public class CountrySnap {
    private UUID countryID;
    private SimpleStringProperty countrySnap;
    private SimpleStringProperty dateSnap;
    private SimpleStringProperty totcaseSnap;
    private SimpleStringProperty totdeathSnap;
    private SimpleStringProperty newcaseSnap;

    public CountrySnap(UUID countryID, String countrySnap, String dateSnap, String totcaseSnap, String totdeathSnap, String newcaseSnap) {
        this.countryID = countryID;
        this.countrySnap = new SimpleStringProperty(countrySnap);
        this.dateSnap = new SimpleStringProperty(dateSnap);
        this.totcaseSnap = new SimpleStringProperty(totcaseSnap);
        this.totdeathSnap = new SimpleStringProperty(totdeathSnap);
        this.newcaseSnap = new SimpleStringProperty(newcaseSnap);
    }

    public CountrySnap(){

    }

    public UUID getCountryID() {
        return countryID;
    }

    public void setCountryID(UUID countryID) {
        this.countryID = countryID;
    }

    public String getCountrySnap() {
        return countrySnap.get();
    }

    public SimpleStringProperty countrySnapProperty() {
        return countrySnap;
    }

    public void setCountrySnap(String countrySnap) {
        this.countrySnap.set(countrySnap);
    }

    public String getDateSnap() {
        return dateSnap.get();
    }

    public SimpleStringProperty dateSnapProperty() {
        return dateSnap;
    }

    public void setDateSnap(String dateSnap) {
        this.dateSnap.set(dateSnap);
    }

    public String getTotcaseSnap() {
        return totcaseSnap.get();
    }

    public SimpleStringProperty totcaseSnapProperty() {
        return totcaseSnap;
    }

    public void setTotcaseSnap(String totcaseSnap) {
        this.totcaseSnap.set(totcaseSnap);
    }

    public String getTotdeathSnap() {
        return totdeathSnap.get();
    }

    public SimpleStringProperty totdeathSnapProperty() {
        return totdeathSnap;
    }

    public void setTotdeathSnap(String totdeathSnap) {
        this.totdeathSnap.set(totdeathSnap);
    }

    public String getNewcaseSnap() {
        return newcaseSnap.get();
    }

    public SimpleStringProperty newcaseSnapProperty() {
        return newcaseSnap;
    }

    public void setNewcaseSnap(String newcaseSnap) {
        this.newcaseSnap.set(newcaseSnap);
    }
}


