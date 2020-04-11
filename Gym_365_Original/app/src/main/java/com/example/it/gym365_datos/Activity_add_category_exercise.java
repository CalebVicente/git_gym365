package com.example.it.gym365_datos;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Activity_add_category_exercise extends AppCompatActivity {

    private DbAdapter dbAdapter;
    private ListView m_listview;

    private static final String DATABASE_NAME = "data";
    private static final String PRELOADED_DATABASE_NAME = "preloaded.db";



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //En este Bundle estamos recogiendo la selección de la categoría del ejercicio seleccionada en Activity_exercises.java
        Bundle select_category = getIntent().getExtras();
        String category = select_category.getString("CATEGORY");
        String category_query= "'"+category+"'";


        //creamos el adaptador de la BD y la abrimos
        dbAdapter = new DbAdapter(this);
        dbAdapter.open();

        // Lo siguiente se ejecuta sólo la primera vez que se instala la
        // aplicación.
        // Si no existe la base de datos, la copiamos del directorio assets
        SharedPreferences mPreferences = this.getSharedPreferences("first_time", Context.MODE_PRIVATE);
        Boolean firstTime = mPreferences.getBoolean("firstTime", true);
        if (firstTime) {
            try {
                String destPath = "/data/data/" + getPackageName() + "/databases/" + DATABASE_NAME;

                System.out.println("Traza: no existe el fichero");
                InputStream in = getAssets().open(PRELOADED_DATABASE_NAME);
                OutputStream out = new FileOutputStream(destPath);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
                in.close();
                out.flush();
                out.close();
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putBoolean("firstTime", false);
                editor.commit();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //inflamos el layout
        setContentView(R.layout.activity_add_category_exercise);

        ImageView img= (ImageView) findViewById(R.id.image_activity_list_exercise2);

        switch (category) {
            case "pecho":
                img.setImageResource(R.drawable.imagen_pecho);
                break;
            case "biceps":
                img.setImageResource(R.drawable.imagen_biceps);
                break;
            case "triceps":
                img.setImageResource(R.drawable.imagen_triceps);
                break;
            case "espalda":
                img.setImageResource(R.drawable.imagen_espalda);
                break;
            case "cardio":
                img.setImageResource(R.drawable.imagen_cardio);
                break;
            case "pierna":
                img.setImageResource(R.drawable.imagen_piernas);
                break;
            case "abdominal":
                img.setImageResource(R.drawable.imagen_abdominal);
                break;
            case "hombro":
                img.setImageResource(R.drawable.imagen_hombro);
                break;
        }





        // Creamos un listview que va a contener el título de todas las notas y
        // en el que cuando pulsemos sobre un título lancemos una actividad de editar
        // la nota con el id correspondiente
        m_listview = (ListView) findViewById(R.id.id_list_view2);

        // rellenamos el listview con los títulos de todas las notas en la BD
        fillData(category_query);
    }

    private void fillData(String category) {
        Cursor notesCursor = dbAdapter.fetch_exercise(category);
        if (notesCursor!=null) {
            // Creamos un array con los campos que queremos mostrar en el listview (sólo el título de la nota)
            String[] from = new String[]{DbAdapter.KEY_NAME, DbAdapter.KEY_CATEGORY};

            // array con los campos que queremos ligar a los campos del array de la línea anterior (en este caso sólo text1)
            int[] to = new int[]{R.id.text1, R.id.category};

            // Creamos un SimpleCursorAdapter y lo asignamos al listview para mostrarlo
            SimpleCursorAdapter notes =
                    new SimpleCursorAdapter(this, R.layout.list_exercise_add_exercise, notesCursor, from, to, 0);
            m_listview.setAdapter(notes);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Se recrea el menu que aparece en ActionBar de la actividad.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void add_one_exercise (View view)
    {

        Intent intent = new Intent (Activity_add_category_exercise.this, Activity_add_exercises.class);
        startActivity(intent);

    }
    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        fillData();
    }
    */
}