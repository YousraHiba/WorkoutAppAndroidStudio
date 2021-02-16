package com.example.workouttest.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.workouttest.LoginViewModel;
import com.example.workouttest.R;
import com.example.workouttest.database.AppDatabase;
import com.example.workouttest.databinding.FragmentLoginBinding;
import com.example.workouttest.model.User;
import com.example.workouttest.viewModel.LoginFragmentViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private LoginFragmentViewModel loginFragmentViewModel;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        loginFragmentViewModel = new ViewModelProvider(this).get(LoginFragmentViewModel.class);
        loginFragmentViewModel.setUserDao(AppDatabase.getInstance().userDao());
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container,false);
        LoginViewModel loginViewModel = new LoginViewModel();
        binding.setLoginModel(loginViewModel);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginViewModel loginViewModel = binding.getLoginModel();

                Executor executor = Executors.newSingleThreadExecutor();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        User loggedUser =  loginFragmentViewModel.login(loginViewModel.getEmail(), loginViewModel.getPassword());

                        if(loggedUser != null){
                            // init live data
                            loginFragmentViewModel = new ViewModelProvider(requireActivity()).get(LoginFragmentViewModel.class);
                            // send user to home fragment on live data 
                            System.out.println(" Logged user okk");
                            loginFragmentViewModel.setUser(loggedUser);
                            NavHostFragment.findNavController(LoginFragment.this)
                                    .navigate(R.id.action_SecondFragment_to_homeFragment);
                        }else{
                            Snackbar.make(view.findViewById(R.id.button_second),"invalid email or password",2000 ).show();
                        }
                    }
                });
            }
        });

        view.findViewById(R.id.noRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Executor executor = Executors.newSingleThreadExecutor();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                            NavHostFragment.findNavController(LoginFragment.this)
                                    .navigate(R.id.action_LoginFragment_to_RegisterFragment);
                    }
                });
            }
        });
    }
}