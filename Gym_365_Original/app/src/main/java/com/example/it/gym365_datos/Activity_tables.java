package com.example.it.gym365_datos;




import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Activity_tables extends AppCompatActivity {

    private static final String TAG = "APMOV: DbAdapter";

    private DbAdapter dbAdapter;
    private ListView m_listview;

    private static final String DATABASE_NAME = "data";
    private static final String PRELOADED_DATABASE_NAME = "preloaded.db";

    private static final int ACTIVITY_CREATE=0;
    private static final int ACTIVITY_EDIT=1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        setContentView(R.layout.activity_tables);

        // Creamos un listview que va a contener el título de todas las notas y
        // en el que cuando pulsemos sobre un título lancemos una actividad de editar
        // la nota con el id correspondiente
        m_listview = (ListView) findViewById(R.id.id_list_tables);

        // rellenamos el listview con los títulos de todas las notas en la BD
        fillData();
    }

    private void fillData() {
        Cursor tablesCursor = dbAdapter.fetchAllTables();
        if (tablesCursor!=null) {

            // Creamos un array con los campos que queremos mostrar en el listview (sólo el título de la nota)
            String[] from = new String[]{DbAdapter.KEY_TABLES_NAME, DbAdapter.KEY_TABLES_SUBTITLE};

            // array con los campos que queremos ligar a los campos del array de la línea anterior (en este caso sólo text1)
            int[] to = new int[]{R.id.tablesname, R.id.tablessubtitle};

            // Creamos un SimpleCursorAdapter y lo asignamos al listview para mostrarlo
            SimpleCursorAdapter tables =
                    new SimpleCursorAdapter(this, R.layout.tables_name, tablesCursor, from, to, 0);

            m_listview.setAdapter(tables);
        }
    }


    public void showdialog(View view){
        /*Con esta función invocada desde tables_name.xml se esta creando un dialog
        * que te permite seleccionar si se desea ver los ejercicios que contienen la tabla
        * o se prefiere añadir ejercicios a la misma*/

        //Para tener acceso a los BottomSheetDialog, ha habido que añadir al Grandle esta implementación: implementation 'com.android.support:design:28.0.0'
        BottomSheetDialog dialog_table = new BottomSheetDialog(this);
        View view_dialog=getLayoutInflater().inflate(R.layout.dialog_table,null);
        dialog_table.setContentView(view_dialog);

        //Este código trata de extraer el texto de cada uno de los elementos del list view, y pasarselos a el dialog_title
        TextView textView = (TextView) findViewById(R.id.tablesname);
        String id = textView.getText().toString();
        TextView dialog_title = (TextView) dialog_table.findViewById(R.id.dialogtitle);
        if (dialog_title != null){
            dialog_title.setText(id);
        }
        TextView textView_subtitle = (TextView) findViewById(R.id.tablessubtitle);
        String id_subtitle = textView_subtitle.getText().toString();
        TextView dialog_subtitle = (TextView) dialog_table.findViewById(R.id.dialogsubtitle);
        if (dialog_subtitle != null){
            dialog_subtitle.setText(id_subtitle);
        }

        /*En esta parte del cógigo se accede al botón que se encuentra dentro del dialog, para poder realizar
        * la función onclick que nos llevará a la siguiente actividad.*/
        Button boton_dialog= (Button) view_dialog.findViewById(R.id.buttonAddExerciseToTable);
        boton_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Activity_tables.this, Activity_add_exercises.class);
                startActivity(intent);
            }
        });

        dialog_table.show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Se recrea el menu que aparece en ActionBar de la actividad.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }

    public void clickButtonAddTable(View view) {

        Intent intent = new Intent (Activity_tables.this, Activity_add_table.class);
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