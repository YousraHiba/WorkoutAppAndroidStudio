package com.example.workouttest;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class LoginViewModel extends BaseObservable {

    private String email;

    private String password;

    @Bindable
    public String getEmail() {
        return email;
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
