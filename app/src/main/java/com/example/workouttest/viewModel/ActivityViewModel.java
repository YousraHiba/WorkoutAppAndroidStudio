package com.example.workouttest.viewModel;

import androidx.lifecycle.ViewModel;

import com.example.workouttest.database.ActivityDao;
import com.example.workouttest.database.UserDao;
import com.example.workouttest.model.Activity;
import com.example.workouttest.model.User;

import java.util.List;

public class ActivityViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private ActivityDao activityDao;

    public void setActivityDao(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    public void startActivity(Activity activity){
         activityDao.insertActivity(activity);
    }

    public void endActivity(Activity activity){
        activityDao.endActivity(activity);
    }

    public List<Activity> listActivityByUser(long userId ){
        List<Activity> activities = activityDao.getAllActivityByUser(userId);
        return activities;
    }

}