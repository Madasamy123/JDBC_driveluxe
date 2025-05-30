package com.Madasamy.driveluxe.dto;

public class RegisterRequest {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setUseremail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
