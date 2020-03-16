package com.example.gym365;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        Intent intent = new Intent (MainActivity.this, Activity_tables.class);
        startActivity(intent);

    }

    public void clickButton2(View view) {

        Intent intent = new Intent (MainActivity.this, Activity_calendar.class);
        startActivity(intent);

    }

    public void clickButton3(View view) {

        Intent intent = new Intent (MainActivity.this, Activity_exercises.class);
        startActivity(intent);

    }

    public void clickButton4(View view) {

        Intent intent = new Intent (MainActivity.this, Activity_routes.class);
        startActivity(intent);

    }

    // ----------------------------------



}
