package sample.Models;


public class Country {
    private String countryid;
    private String name;
    private String date;
    private String TotCase;
    private String TotDeaths;
    private String NewCase;

    public void Country(String countryid, String name, String date, String TotCase, String TotDeaths, String NewCase){
        this.countryid = countryid;
        this.name = name;
        this.date = date;
        this.TotCase = TotCase;
        this.TotDeaths = TotDeaths;
        this.NewCase = NewCase;
    }

    public void Country(){

    }

    public String getCountryid() {
        return countryid;
    }

    public void setCountryid(String countryid) {
        this.countryid = countryid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotCase() {
        return TotCase;
    }

    public void setTotCase(String totCase) {
        TotCase = totCase;
    }

    public String getTotDeaths() {
        return TotDeaths;
    }

    public void setTotDeaths(String totDeaths) {
        TotDeaths = totDeaths;
    }

    public String getNewCase() {
        return NewCase;
    }

    public void setNewCase(String newCase) {
        NewCase = newCase;
    }
}
