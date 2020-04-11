package com.example.it.gym365_datos;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Activity_add_table extends AppCompatActivity {

    private EditText nombreText;
    private EditText diaText;
    private EditText subtituloText;
    private Long mRowId;
    private DbAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // infla el layout
        setContentView(R.layout.activity_add_table);

        // obtiene referencia a los tres views que componen el layout de esta clase
        nombreText = (EditText) findViewById(R.id.nombre_tabla);
        diaText = (EditText) findViewById(R.id.dia_tabla);
        subtituloText = (EditText) findViewById(R.id.subtitulo_tabla);
        Button confirmButton = (Button) findViewById(R.id.confirm);

        //creamos el adaptador de la BD y la abrimos
        dbAdapter = new DbAdapter(this);
        dbAdapter.open();

        // obtiene id de fila de la tabla si se le ha pasado (hemos pulsado una nota para editarla)
        mRowId = (savedInstanceState == null) ? null :
                (Long) savedInstanceState.getSerializable(DbAdapter.KEY_TABLES_ID);
        if (mRowId == null) {
            Bundle extras = getIntent().getExtras();
            mRowId = extras != null ? extras.getLong(DbAdapter.KEY_TABLES_ID) : null;
        }

        // Si se le ha pasado un id (no era null) rellena el título y el cuerpo con los campos guardados en la BD
        // en caso contrario se dejan en blanco (editamos una nota nueva)
        if (mRowId != null) {
            Cursor note = dbAdapter.fetch_table(mRowId);
            nombreText.setText(note.getString(
                    note.getColumnIndexOrThrow(DbAdapter.KEY_TABLES_NAME)));
            diaText.setText(note.getString(
                    note.getColumnIndexOrThrow(DbAdapter.KEY_TABLES_DATE)));
            subtituloText.setText(note.getString(
                    note.getColumnIndexOrThrow(DbAdapter.KEY_TABLES_SUBTITLE)));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Se recrea el menu que aparece en ActionBar de la actividad.
        getMenuInflater().inflate(R.menu.menu_add_table, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Gestiona la seleccion de opciones en el menú
        int id = item.getItemId();
        if (id == R.id.action_delete) {
            if (mRowId != null) {
                dbAdapter.deleteNote(mRowId);
            }
            setResult(RESULT_OK);
            finish();
        }

        if (id == R.id.action_about) {
            System.out.println("APPMOV: About action...");
        }

        return super.onOptionsItemSelected(item);
    }


    public void saveTable(View view) {
        String name = nombreText.getText().toString();
        String day = diaText.getText().toString();
        String subtitle = subtituloText.getText().toString();

        if (mRowId == null) {
            long id = dbAdapter.createtable(name, day, subtitle);
            if (id > 0) {
                mRowId = id;
            }
        } else {
            dbAdapter.updateTable(mRowId, name, day, subtitle);

        }
        setResult(RESULT_OK);
        Intent intent = new Intent (Activity_add_table.this, Activity_tables.class);
        startActivity(intent);
        finish();
    }

    public void addExercise (View view) {

        Intent intent = new Intent (Activity_add_table.this, Activity_add_exercises.class);
        startActivity(intent);

    }

}