package com.example.workouttest.fragments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.workouttest.R;
import com.example.workouttest.WorkoutsListAdapter;
import com.example.workouttest.model.User;
import com.example.workouttest.model.Workouts;
import com.example.workouttest.viewModel.HomeViewModel;
import com.example.workouttest.viewModel.LoginFragmentViewModel;
import com.example.workouttest.viewModel.WorkoutFragmentViewModel;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class WorkoutFragment extends Fragment  implements RecycleViewClickInterface {

    private WorkoutFragmentViewModel mViewModel;
    private HomeViewModel homeViewModel;
    private WorkoutFragmentViewModel workoutFragmentViewModel;
    User usertoSend;

   List<Workouts> workoutsList;

    public static WorkoutFragment newInstance() {
        return new WorkoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        workoutFragmentViewModel = new ViewModelProvider(this).get(WorkoutFragmentViewModel.class);
        HomeViewModel workoutListLiveData = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.workout_fragment, container, false);
        workoutListLiveData.getWorkoutsList().observe(getViewLifecycleOwner(), workoutsList -> {
            this.workoutsList = workoutsList;
            RecyclerView list = root.findViewById(R.id.recyclerview);
            WorkoutsListAdapter workoutrAdapter = new WorkoutsListAdapter(workoutsList, this);
            list.setAdapter(workoutrAdapter);
            list.setLayoutManager(new LinearLayoutManager(root.getContext()));
            RecycleViewClickInterface recycleViewClickInterface ;
            list.setAdapter(new WorkoutsListAdapter(workoutsList,this));

            LoginFragmentViewModel loggedUser = new ViewModelProvider(requireActivity()).get(LoginFragmentViewModel.class);
            loggedUser.getUser().observe(getViewLifecycleOwner(), user -> {
                System.out.println("  logged userName   "+user.getUserName());
                System.out.println("  logged email   "+user.getEmail());
                System.out.println("  logged age   "+user.getAge());
                usertoSend = user;
            });
        });
        return root ;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(WorkoutFragmentViewModel.class);
    }


    @Override
    public void OnItemClick(int position) {
        WorkoutFragmentViewModel live = new ViewModelProvider(requireActivity()).get(WorkoutFragmentViewModel.class);
        LoginFragmentViewModel loggedUser = new ViewModelProvider(requireActivity()).get(LoginFragmentViewModel.class);
        loggedUser.setUser(usertoSend);
        live.setSelectedWorkouts(this.workoutsList.get(position));
        NavHostFragment.findNavController(WorkoutFragment.this).navigate(R.id.action_workoutFragment_to_activityFragment);
    }




}