package com.example.workouttest.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
@Entity(tableName = "activity")
public class Activity {
    public Integer getIdActivity() {
        return idActivity;
    }

    public long getUserid() {
        return userid;
    }

    public Integer getWorkoutid() {
        return workoutid;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public Activity(Integer idActivity, long userid, Integer workoutid, String activityStatus) {
        this.idActivity = idActivity;
        this.userid = userid;
        this.workoutid = workoutid;
        this.activityStatus = activityStatus;
    }

    @PrimaryKey(autoGenerate = true)
    Integer idActivity;

    @ForeignKey(entity =User.class,
            parentColumns = "id",
            childColumns = "userID")
    long userid;

    @ForeignKey(entity =Workouts.class,
            parentColumns = "idWorkout",
            childColumns = "idWorkout")
    Integer workoutid;

    @ColumnInfo(name = "activityStatus")
    String activityStatus;

}


