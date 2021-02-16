package com.example.workouttest.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.workouttest.model.User;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void registerUser(User user);

    @Query("SELECT * from users where email =(:email)  and password = (:password)" )
    User login(String email, String password);

    @Query("SELECT * from users where id =(:userId)" )
    LiveData<User> getUserById(long userId);

}


