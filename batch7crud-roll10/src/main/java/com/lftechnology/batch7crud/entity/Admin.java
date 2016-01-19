package com.lftechnology.batch7crud.entity;

/**
 * @Author binodnme
 * Created on 1/17/16
 */
public class Admin extends Person {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
