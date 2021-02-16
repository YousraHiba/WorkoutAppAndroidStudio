package com.example.workouttest.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigator;
import androidx.navigation.fragment.NavHostFragment;

import com.example.workouttest.viewModel.RegisterFragmentViewModel;
import com.example.workouttest.R;
import com.example.workouttest.database.AppDatabase;
import com.example.workouttest.database.UserDao;
import com.example.workouttest.databinding.FragmentFirstBinding;
import com.example.workouttest.model.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RegisterFragment extends Fragment {

    private FragmentFirstBinding binding;
    private RegisterFragmentViewModel registerFragmentViewModel;
    UserDao userDao;
    User user;
    EditText name,email,password,age;




    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        registerFragmentViewModel = new ViewModelProvider(this).get(RegisterFragmentViewModel.class);
        registerFragmentViewModel.setUserDao(AppDatabase.getInstance().userDao());
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false);
        User user = new User("", "", "", "");
        binding.setUser(user);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name = view.findViewById(R.id.editTextTextPersonName);
        email = view.findViewById(R.id.editTextTextEmailAddress);
        password = view.findViewById(R.id.editTextTextPassword);
        age = view.findViewById(R.id.editTextNumber);

        String nameString= name.getText().toString();
        String emailString= name.getText().toString();

        String passwordString= name.getText().toString();

        String ageString= name.getText().toString();






        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name !=null   || password != null ) {


                    System.out.println(" on click  ");

                    User user = binding.getUser();

                    Executor executor = Executors.newSingleThreadExecutor();
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {

                            registerFragmentViewModel.registerUser(user);
                            NavHostFragment.findNavController(RegisterFragment.this)
                                    .navigate(R.id.action_RegisterFragment_to_LoginFragment);
                            getActivity().getFragmentManager().popBackStack();
                        }
                    });

                }
                else
                {
                    Snackbar.make(view.findViewById(R.id.button_first),"invalid email or password",2000 ).show();

                }
            }
        });

        view.findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(RegisterFragment.this)
                        .navigate(R.id.action_RegisterFragment_to_LoginFragment);
            }
        });




    }
}