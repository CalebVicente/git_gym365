package com.example.it.gym365_datos;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import android.support.v7.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //  Metodo que procesa la pulsacion (onClick) del boton
    //  se indica en el atributo android.onClick del elemento Button definido en XML

    public void clickButton1(View view) {

        Intent intent = new Intent (MenuActivity.this, Activity_tables.class);
        startActivity(intent);

    }

    public void clickButton2(View view) {

        Intent intent = new Intent (MenuActivity.this, Activity_calendar.class);
        startActivity(intent);

    }

    public void clickButton3(View view) {

        Intent intent = new Intent (MenuActivity.this, Activity_exercises.class);
        startActivity(intent);

    }

    public void clickButton4(View view) {

        Intent intent = new Intent (MenuActivity.this, Activity_routes.class);
        startActivity(intent);

    }

    // ----------------------------------



}
