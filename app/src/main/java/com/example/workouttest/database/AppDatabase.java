package com.example.workouttest.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.workouttest.model.Activity;
import com.example.workouttest.model.User;
import com.example.workouttest.model.Workouts;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Workouts.class, Activity.class}, version=1, exportSchema = false)

public abstract class AppDatabase  extends RoomDatabase{

    static private AppDatabase instance = null;

    static public void createInstance (Context context) {
        System.out.println("  createInstance db ");
       instance = Room.databaseBuilder(context, AppDatabase.class, "workout.db").build();
    }

//    static public void createWorkOutInstance (Context context) {
//        System.out.println("  createInstance workout tabel ");
//        instance = Room.databaseBuilder(context, AppDatabase.class, "users.db").build();
//    }

    public static AppDatabase getInstance() {
        return instance;
    }

    public abstract UserDao userDao();
    public abstract WorkoutsDao workoutsDao();
    public abstract ActivityDao activityDao();
    private static AppDatabase appDatabase;
    private static final String dbName= "users";

//
//    public static synchronized AppDatabase getAppDatabase(Context context){
//        if(appDatabase == null){
//            appDatabase= Room.databaseBuilder(context,AppDatabase.class,dbName)
//                    .fallbackToDestructiveMigration()
//                    .build();
//        }
//        return appDatabase;
//    }


    public void populateUser(){
        User testUser = new User("test","test@test", "testPassword" , "23");
        userDao().registerUser(testUser);
    }

    public void populate (){
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println( " populaite populaite populaite ");
                populateUser();
            }
        });

    }

    public void populateWorkouts(){
       Workouts workout1  = new Workouts(null," Goblet squat","leg","gobletsquat");
       Workouts workout2  = new Workouts(null," Pendulum lunges","leg","pendulumlunges");
       Workouts workout3  = new Workouts(null," Romanian deadlifts","leg","romaniandeadlifts");
       Workouts workout4  = new Workouts(null," Step-ups","leg","steoups");
        Workouts workout5  = new Workouts(null," Weighted hip bridges","leg","weightedhipbridges");
        Workouts workout6  = new Workouts(null," Standing chest press","chest","standing ");

        Workouts workout7  = new Workouts(null," Burpee","chest","burpee");

        Workouts workout8  = new Workouts(null," Incline push-up","chest","inclinepushup");

        Workouts workout9  = new Workouts(null, " Plank-to-push-up","chest","planktopushup");

        Workouts workout10  = new Workouts(null," Diamond push-up","chest","diamondpushup");


        workoutsDao().insertWorkouts(workout1);
        workoutsDao().insertWorkouts(workout2);
        workoutsDao().insertWorkouts(workout3);
        workoutsDao().insertWorkouts(workout4);
        workoutsDao().insertWorkouts(workout5);
        workoutsDao().insertWorkouts(workout6);
        workoutsDao().insertWorkouts(workout7);
        workoutsDao().insertWorkouts(workout8);
        workoutsDao().insertWorkouts(workout9);
        workoutsDao().insertWorkouts(workout10);


    }

    public void populateAllWorkouts (){
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println( " populaite populaite populaite workouuuuttttssss ");
                populateWorkouts();
            }
        });

    }

}
