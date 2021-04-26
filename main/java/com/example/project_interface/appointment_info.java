package com.example.project_interface;

public class appointment_info {
    private String company,time,  id,email;


    public String getUsername() {
        return email;
    }

    public String getEmail() {
        return email;
    }

    public appointment_info(String email, String company, String time, String id) {
        this.email = email;
        this.company = company;
        this.time = time;
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public String getTime() {
        return time;
    }

    public String getId() {
        return id;
    }

    public appointment_info() {
    }
}
