package com.example.workouttest.fragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.workouttest.database.AppDatabase;
import com.example.workouttest.database.WorkoutsDao;
import com.example.workouttest.model.User;
import com.example.workouttest.model.Workouts;
import com.example.workouttest.viewModel.HomeViewModel;
import com.example.workouttest.R;
import com.example.workouttest.viewModel.LoginFragmentViewModel;
import com.example.workouttest.viewModel.RegisterFragmentViewModel;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RegisterFragmentViewModel registerFragmentViewModel;
    TextView userName;
    List<Workouts> workouts;
    User usertoSend;

    private LoginFragmentViewModel loginFragmentViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.setWorkoutsDao(AppDatabase.getInstance().workoutsDao());
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        LoginFragmentViewModel loggedUser = new ViewModelProvider(requireActivity()).get(LoginFragmentViewModel.class);
        Executor executor = Executors.newSingleThreadExecutor();

        executor.execute(new Runnable() {
            @Override
            public void run() {
                workouts = homeViewModel.getWorkouts();
                homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
                homeViewModel.setWorkouts(workouts);

            }
        });

        loggedUser.getUser().observe(getViewLifecycleOwner(), user -> {
            System.out.println("  logged userName   "+user.getUserName());
            System.out.println("  logged email   "+user.getEmail());
            System.out.println("  logged age   "+user.getAge());
                    userName = view.findViewById(R.id.userNameInformations);
                    usertoSend = user;
        });


        view.findViewById(R.id.startWorkout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginFragmentViewModel loggedUser = new ViewModelProvider(requireActivity()).get(LoginFragmentViewModel.class);
                loggedUser.setUser(usertoSend);

                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_homeFragment_to_workoutFragment);


            }
        });



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
    }

}