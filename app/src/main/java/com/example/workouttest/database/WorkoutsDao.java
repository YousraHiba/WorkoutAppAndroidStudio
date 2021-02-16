package com.example.workouttest.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.workouttest.model.Workouts;

import java.util.List;
@Dao
public interface WorkoutsDao {

        @Insert
        void insertWorkouts(Workouts workouts);

        @Query("SELECT * from workouts where idWorkout =(:id)")
        Workouts getWorkoutById(int id);

        @Query("SELECT * from workouts")
        List<Workouts> getAllWorkouts();
}