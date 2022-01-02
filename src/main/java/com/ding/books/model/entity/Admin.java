package com.ding.books.model.entity;

public class Admin {

    private long admin_id;
    private String username;
    private String password;


    public long getAdminId() {
        return admin_id;
    }

    public void setAdminId(long admin_id) {
        this.admin_id = admin_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
