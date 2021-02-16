package com.example.workouttest.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.workouttest.database.UserDao;
import com.example.workouttest.model.User;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LoginFragmentViewModel extends ViewModel {

    private UserDao userDao;
    //private LiveData<User> user ;

    private final MutableLiveData<User> selected = new MutableLiveData();

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User login(String email, String password ){
        User loggedUser = userDao.login( email,  password);
        return loggedUser;
    }

    public void setUser (User user){ selected.postValue(user);}

    public LiveData<User> getUser () { return selected; }

}
