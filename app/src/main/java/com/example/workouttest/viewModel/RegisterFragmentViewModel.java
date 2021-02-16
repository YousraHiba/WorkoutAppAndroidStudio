package com.example.workouttest.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.workouttest.database.UserDao;
import com.example.workouttest.model.User;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class RegisterFragmentViewModel extends ViewModel {

    private UserDao userDao;

    private MutableLiveData<Long>  id =  new MutableLiveData<Long>();
    private LiveData<User> user ;

    public void setUserDao ( UserDao userDao) {
        this.userDao = userDao;
        this.user = Transformations.switchMap(id , newId ->userDao.getUserById(newId));
    }

    public void setId(long id){
        this.id.postValue(id);
    }

    public LiveData<User> getUser(){
        return user;
    }

    public void registerUser( User user){
        //TODO: add try catch
        userDao.registerUser(user);
    }









}
