package sample.Models;

import java.util.UUID;

public class User {
    private String userId;
    private String name;
    private String pass;
    private boolean adminRights;

    public User(String userId, String name, String pass, boolean adminRights){
        this.userId = userId;
        this.name = name;
        this.pass = pass;
        this.adminRights = adminRights;
    }

    public User(){}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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
