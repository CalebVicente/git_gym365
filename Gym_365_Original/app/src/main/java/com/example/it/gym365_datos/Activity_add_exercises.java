package com.example.it.gym365_datos;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Activity_add_exercises extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void clickPecho(View view) {

        Intent intent = new Intent (Activity_add_exercises.this, Activity_add_category_exercise.class);
        String category_string = "pecho";
        Bundle category = new Bundle();
        category.putString("CATEGORY",category_string );
        intent.putExtras(category);

        startActivity(intent);

    }
    public void clickPiernas(View view) {

        Intent intent = new Intent (Activity_add_exercises.this, Activity_add_category_exercise.class);
        String category_string = "pierna";
        Bundle category = new Bundle();
        category.putString("CATEGORY",category_string );
        intent.putExtras(category);

        startActivity(intent);

    }
    public void clickEspalda(View view) {

        Intent intent = new Intent (Activity_add_exercises.this, Activity_add_category_exercise.class);
        String category_string = "espalda";
        Bundle category = new Bundle();
        category.putString("CATEGORY",category_string );
        intent.putExtras(category);

        startActivity(intent);

    }

    public void clickCardio(View view) {

        Intent intent = new Intent (Activity_add_exercises.this, Activity_add_category_exercise.class);
        String category_string = "cardio";
        Bundle category = new Bundle();
        category.putString("CATEGORY",category_string );
        intent.putExtras(category);

        startActivity(intent);

    }
    public void clickBiceps(View view) {

        Intent intent = new Intent (Activity_add_exercises.this, Activity_add_category_exercise.class);
        String category_string = "biceps";
        Bundle category = new Bundle();
        category.putString("CATEGORY",category_string );
        intent.putExtras(category);

        startActivity(intent);

    }
    public void clickAbdominal(View view) {

        Intent intent = new Intent (Activity_add_exercises.this, Activity_add_category_exercise.class);
        String category_string = "abdominal";
        Bundle category = new Bundle();
        category.putString("CATEGORY",category_string );
        intent.putExtras(category);

        startActivity(intent);

    }
    public void clickTriceps(View view) {

        Intent intent = new Intent (Activity_add_exercises.this, Activity_add_category_exercise.class);
        String category_string = "triceps";
        Bundle category = new Bundle();
        category.putString("CATEGORY",category_string );
        intent.putExtras(category);

        startActivity(intent);

    }
    public void clickHombro(View view) {

        Intent intent = new Intent (Activity_add_exercises.this, Activity_add_category_exercise.class);
        String category_string = "hombro";
        Bundle category = new Bundle();
        category.putString("CATEGORY",category_string );
        intent.putExtras(category);

        startActivity(intent);

    }


}