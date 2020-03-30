package com.example.gym365;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtener referencia al TextView que visualizara el saludo
        TextView text_hello = findViewById(R.id.text_message);

        // Recuperamos la informacion salvada en la preferencia
        Context context = getApplicationContext();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String name = prefs.getString("name", "");

        // Construimos el saludo a mostrar
        text_hello.setText("Bienvenido " + name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            System.out.println("APPMOV: Settings action...");
            // Creamos el Intent que va a lanzar la segunda activity (SecondActivity)
            Intent intent = new Intent(this, SettingsActivity.class);
            // Iniciamos la nueva actividad
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_about) {
            System.out.println("APPMOV: About action...");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // ------------------------------

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

    @Override
    public void onResume(){
        // Tenemos que implementar este método para que cuando volvamos
        // a MainActivity después de rellenar nuestro nombre en SettingsActivity,
        // ponga el nuevo nombre en el interfaz
        super.onResume();
        // Obtener referencia al TextView que visualizara el saludo
        TextView text_hello = findViewById(R.id.text_message);

        // Recuperamos la informacion salvada en la preferencia
        Context context = getApplicationContext();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String name = prefs.getString("name", "No name defined yet!");

        // Construimos el saludo a mostrar
        text_hello.setText("Bienvenido " + name);
    }


}