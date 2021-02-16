package com.example.workouttest.fragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.workouttest.database.AppDatabase;
import com.example.workouttest.model.Activity;
import com.example.workouttest.model.User;
import com.example.workouttest.viewModel.ActivityViewModel;
import com.example.workouttest.R;
import com.example.workouttest.model.Workouts;
import com.example.workouttest.viewModel.HomeViewModel;
import com.example.workouttest.viewModel.LoginFragmentViewModel;
import com.example.workouttest.viewModel.WorkoutFragmentViewModel;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ActivityFragment extends Fragment {

    private ActivityViewModel mViewModel;
    Workouts workouts;
    TextView wotkoutName;
    User loggedUser;



    public static ActivityFragment newInstance() {
        return new ActivityFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

       // System.out.println("welcom to activitu fragments==========");
        mViewModel = new ViewModelProvider(this).get(ActivityViewModel.class);
        mViewModel.setActivityDao(AppDatabase.getInstance().activityDao());
        View root= inflater.inflate(R.layout.activity_fragment, container, false);

        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        WorkoutFragmentViewModel liveData = new ViewModelProvider(requireActivity()).get(WorkoutFragmentViewModel.class);

        liveData.getSelectedWorkout().observe(getViewLifecycleOwner(), workouts -> {

            wotkoutName = view.findViewById(R.id.workoutName);
            wotkoutName.setText(workouts.getWorkoutName());
            this.workouts = workouts;
        });
        LoginFragmentViewModel loggedUserLiveData = new ViewModelProvider(requireActivity()).get(LoginFragmentViewModel.class);
        loggedUserLiveData.getUser().observe(getViewLifecycleOwner(), user -> {
            System.out.println("  logged userName on activity  "+user.getUserName());
            this.loggedUser  = user;
        });

        view.findViewById(R.id.startActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Executor executor = Executors.newSingleThreadExecutor();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("start activity ");
                        Activity activity = new Activity(null, loggedUser.getId(), workouts.getIdWorkout(), "started");
                        System.out.println(" activity "+ activity.getIdActivity());
                         mViewModel.startActivity(activity);
                    }
                });

            }


        });

    }





        @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ActivityViewModel.class);
        // TODO: Use the ViewModel
    }

}