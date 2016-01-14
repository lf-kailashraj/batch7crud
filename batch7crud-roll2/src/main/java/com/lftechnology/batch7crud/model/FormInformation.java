package com.lftechnology.batch7crud.model;

/**
 * Created by romit on 1/14/16.
 */
public class FormInformation {
    private int userId;
    private String userName;
    private String address;
    private String email;
    private String contact;

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getContact() {
        return contact;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
