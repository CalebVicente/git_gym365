package com.example.gym365;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_Agregar_table extends AppCompatActivity{

    private EditText mTitleText;
    private EditText mBodyText;
    private Long mRowId;
    private DBaccess dbAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_table);

        // obtiene referencia a los tres views que componen el layout
        mTitleText = (EditText) findViewById(R.id.name);
        Button confirmButton = (Button) findViewById(R.id.confirm);

        //creamos el adaptador de la BD y la abrimos
        dbAdapter = new DBaccess(this);
        dbAdapter.open();

        // obtiene id de fila de la tabla si se le ha pasado (hemos pulsado una nota para editarla)
        mRowId = (savedInstanceState == null) ? null :
                (Long) savedInstanceState.getSerializable(DBaccess.KEY_ROWID);
        if (mRowId == null) {
            Bundle extras = getIntent().getExtras();
            mRowId = extras != null ? extras.getLong(DBaccess.KEY_ROWID) : null;
        }

        // Si se le ha pasado un id (no era null) rellena el título y el cuerpo con los campos guardados en la BD
        // en caso contrario se dejan en blanco (editamos una nota nueva)
        if (mRowId != null) {
            Cursor note = dbAdapter.fetchNote(mRowId);
            mTitleText.setText(note.getString(
                    note.getColumnIndexOrThrow(DBaccess.KEY_NAME)));
            mBodyText.setText(note.getString(
                    note.getColumnIndexOrThrow(DBaccess.KEY_DAY)));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public void clickButton3(View view) {

        Intent intent = new Intent (this, Activity_exercises.class);
        startActivity(intent);

    }


    //Botón que guarda los cambios
    public void saveNote(View view) {

        String name = mTitleText.getText().toString();
        String day = mBodyText.getText().toString();

        if (mRowId == null) {
            long id = dbAdapter.createNote(name, day);
            if (id > 0) {
                mRowId = id;
            }
        } else {
            dbAdapter.updateNote(mRowId, name, day);
        }
        setResult(RESULT_OK);
        finish();
    }



}


