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

public class Activity_exercises extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // ------------------------------

    public void clickPecho(View view) {

        Intent intent = new Intent (this, Activity_pecho.class);
        startActivity(intent);

    }
    public void clickPiernas(View view) {

        Intent intent = new Intent (this, Activity_piernas.class);
        startActivity(intent);

    }
    public void clickEspalda(View view) {

        Intent intent = new Intent (this, Activity_espalda.class);
        startActivity(intent);

    }
    public void clickBrazos(View view) {

        Intent intent = new Intent (this, Activity_brazos.class);
        startActivity(intent);

    }
    public void clickCardio(View view) {

        Intent intent = new Intent (this, Activity_cardio.class);
        startActivity(intent);

    }

}
