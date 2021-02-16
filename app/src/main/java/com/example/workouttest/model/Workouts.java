package com.example.workouttest.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "workouts")
public class Workouts {
    @PrimaryKey(autoGenerate = true)
    Integer idWorkout;
    @ColumnInfo(name = "workoutName")
    String workoutName;

    @ColumnInfo(name = "workoutCategory")
    String workoutCategory;

    @ColumnInfo(name = "workoutAnimation")
    String workoutAnimation;

    public Workouts(Integer idWorkout, String workoutName,String workoutCategory, String workoutAnimation){
        this.idWorkout = idWorkout;
        this.workoutName = workoutName;
        this.workoutCategory = workoutCategory;
        this.workoutAnimation = workoutAnimation;
    }

    public Integer getIdWorkout() {
        return idWorkout;
    }

    public void setIdWorkout(Integer idWorkout) {
        this.idWorkout = idWorkout;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getWorkoutCategory() {
        return workoutCategory;
    }

    public void setWorkoutCategory(String workoutCategory) {
        this.workoutCategory = workoutCategory;
    }

    public String getWorkoutAnimation() {
        return workoutAnimation;
    }

    public void setWorkoutAnimation(String workoutAnimation) {
        this.workoutAnimation = workoutAnimation;
    }





}

