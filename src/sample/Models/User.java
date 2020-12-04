package sample.Models;

public class User {
    private String name;
    private String pass;
    private boolean adminRights;

    public User(String name, String pass, boolean adminRights){
        this.name = name;
        this.pass = pass;
        this.adminRights = adminRights;
    }

    public User(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isAdminRights() {
        return adminRights;
    }

    public void setAdminRights(boolean adminRights) {
        this.adminRights = adminRights;
    }
}
