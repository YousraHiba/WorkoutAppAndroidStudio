package com.example.workouttest.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "users")
public class User {


    @PrimaryKey(autoGenerate = true)
    long id;
    @ColumnInfo(name = "userName")
    String userName;
    @ColumnInfo(name = "email")
    String email;
    @ColumnInfo(name = "password")
    String password;
    @ColumnInfo(name = "age")
    String age;

    public User(){
        this.id = 0;
    }

    @Ignore
    public User(String userName, String email, String password, String age) {
        this();
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
