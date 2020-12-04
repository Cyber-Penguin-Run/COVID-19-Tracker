package sample.Models;

import java.util.UUID;

public class User {
    private UUID Userid;
    private String name;
    private String pass;
    private boolean adminRights;

    public User(UUID Userid, String name, String pass, boolean adminRights){
        this.Userid = Userid;
        this.name = name;
        this.pass = pass;
        this.adminRights = adminRights;
    }

    public User(){}

    public UUID getUserid() {
        return Userid;
    }

    public void setUserid(UUID userid) {
        Userid = userid;
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
