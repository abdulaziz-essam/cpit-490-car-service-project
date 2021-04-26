package com.example.project_interface;

public class Module {
    private String user,email,pass;

    public Module(String user, String email, String pass) {
        this.user = user;
        this.email = email;
        this.pass = pass;
    }

    public String getUser() {
        return user;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public Module() {
    }
}
