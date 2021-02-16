package com.example.workouttest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.workouttest.database.AppDatabase;
import com.example.workouttest.fragments.RecycleViewClickInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity  {
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor prefEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPref = getApplicationContext().getSharedPreferences("firstLunch", Context.MODE_PRIVATE);
        prefEditor = sharedPref.edit();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (sharedPref.contains("firstLunch")) {
            System.out.println(" hehehehehehehehehheheeheh  ");
            AppDatabase.createInstance(getApplicationContext());
        } else {
            System.out.println(" hhohohohohohohohohohohohohoohhoho  ");
            prefEditor.putBoolean("firstLunch", true);
            prefEditor.apply();
            AppDatabase.createInstance(getApplicationContext());
            AppDatabase.getInstance().populateAllWorkouts();
        }



//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        System.out.println("onOptionsItemSelected");
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void OnItemClick(int position) {
//
//
//    }
}