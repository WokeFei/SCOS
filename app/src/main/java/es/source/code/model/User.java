package es.source.code.model;

import java.io.Serializable;

public class User implements Serializable{
    // User 包含三个域为:userName，password，oldUser
    private String userName;
    private String password;
    private Boolean oldUser;

    public User(String userName,String password,Boolean oldUser){
        this.userName = userName;
        this.password = password;
        this.oldUser = oldUser;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public Boolean isOldUser() {
        return oldUser;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setOldUser(Boolean oldUser){
        this.oldUser = oldUser;
    }
}



