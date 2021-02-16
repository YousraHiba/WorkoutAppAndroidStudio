package com.example.workouttest.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.workouttest.model.Activity;
import com.example.workouttest.model.Workouts;

import java.util.List;
@Dao
public interface ActivityDao {
    @Insert
    void insertActivity(Activity activity);

    @Update
    void endActivity(Activity activity);

    @Query("SELECT * from activity where userId =(:userID)")
    List<Activity> getAllActivityByUser(long userID );

    @Query("SELECT COUNT(userID) FROM activity")
    LiveData<Integer> getRowCount();
}
