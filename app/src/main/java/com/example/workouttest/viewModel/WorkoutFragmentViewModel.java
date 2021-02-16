package com.example.workouttest.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.workouttest.database.WorkoutsDao;
import com.example.workouttest.model.Workouts;

import java.util.List;

public class WorkoutFragmentViewModel extends ViewModel {

    private final MutableLiveData selected = new MutableLiveData();

    public void setSelectedWorkouts (Workouts workouts){
        selected.postValue(workouts);
    }

    public LiveData<Workouts> getSelectedWorkout () {
        return selected;
    }
}