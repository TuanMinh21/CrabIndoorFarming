package com.iot.crabindoorfarming.model;

public class User {
    private String email;
    private String password;
    private static volatile User instance;

    private User() {
    }

    public static User getInstance() {

        if(instance == null){
            synchronized(User.class) {
                if(instance == null) {
                    instance = new User();
                }
            }
        }
        return instance;
    }

    public void setUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
