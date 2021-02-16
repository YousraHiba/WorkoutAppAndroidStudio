package com.example.workouttest.viewModel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.workouttest.database.UserDao;
import com.example.workouttest.database.WorkoutsDao;
import com.example.workouttest.model.User;
import com.example.workouttest.model.Workouts;

import java.util.List;


public class HomeViewModel extends ViewModel {

    private WorkoutsDao workoutsDao;

    private final MutableLiveData selected = new MutableLiveData();

    public void setWorkoutsDao(WorkoutsDao workoutsDao) {
        this.workoutsDao = workoutsDao;
    }

    public List<Workouts>  getWorkouts (){
        List<Workouts> workouts = workoutsDao.getAllWorkouts();
        return workouts;
    }

    public void setWorkouts (List<Workouts> workouts){
        System.out.println(" set wotkout list "+workouts);
        selected.postValue(workouts);
    }

    public LiveData<List<Workouts>> getWorkoutsList () {
        return selected;
    }

}