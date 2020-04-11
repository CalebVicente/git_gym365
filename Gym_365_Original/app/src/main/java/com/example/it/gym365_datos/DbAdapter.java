package com.example.it.gym365_datos;

/*
 * Asignatura Aplicaciones Moviles - UC3M
 * Update: 13/02/2018.
 *
 * Based in code by Google with Apache License, Version 2.0
 *
 * Copyright (C) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

// Clase adaptadora que nos va a facilitar el uso de la BD
public class DbAdapter {
    private static final String TAG = "APMOV: DbAdapter"; // Usado en los mensajes de Log

    //Nombre de la base de datos, tablas y versión
    private static final String DATABASE_NAME = "data";
    private static final String DATABASE_TABLE = "all_exercises";
    private static final String DATABASE_TABLE_TABLES="tables";
    private static final String DATABASE_TABLE_EXERCISES="exercises_table";
    private static final int DATABASE_VERSION = 2;

    //campos de la tabla all_exercises de la base de datos
    public static final String KEY_VIDEO = "link_video";
    public static final String KEY_PHOTO = "link_photo";
    public static final String KEY_CATEGORY = "category";
    public static final String KEY_NAME = "name";
    public static final String KEY_ROWID = "_id";

    //campos de la tabla tables de la base de datos
    public static final String KEY_TABLES_SUBTITLE = "subtitulo";
    public static final String KEY_TABLES_DATE = "dia";
    public static final String KEY_TABLES_NAME = "nombre";
    public static final String KEY_TABLES_ID = "_id";

    //campos de la tabla tables de la base de datos
    public static final String KEY_TREPETICIONES = "repeticiones";
    public static final String KEY_TPESO = "peso";
    public static final String KEY_TID_TABLE = "id_table";
    public static final String KEY_TID_ALL_EXERCISE = "id_allexercise";
    public static final String KEY_T_ID = "_id";



    // Sentencia SQL para crear las tablas de las bases de datos
    private static final String DATABASE_CREATE = "create table " + DATABASE_TABLE + " (" +
            KEY_ROWID +" integer primary key autoincrement, " +
            KEY_NAME +" text not null, " +
            KEY_CATEGORY +" text not null, " +
            KEY_PHOTO +" text not null, " +
            KEY_VIDEO + " text );";

    private static final String DATABASE_TABLES_CREATE = "create table " + DATABASE_TABLE_TABLES + " (" +
            KEY_TABLES_ID +" integer primary key autoincrement, " +
            KEY_TABLES_NAME +" text not null, " +
            KEY_TABLES_SUBTITLE + "text" +
            KEY_TABLES_DATE + " text );";

    private static final String DATABASE_EXERCISE_CREATE = "create table " + DATABASE_TABLE_EXERCISES + " (" +
            KEY_T_ID + " integer primary key autoincrement, " +
            KEY_TID_TABLE +" integer not null, " +
            KEY_TID_ALL_EXERCISE + " integer not null, " +
            KEY_TPESO +" text ," +
            KEY_TID_ALL_EXERCISE + "text," +
            " FOREIGN KEY (" + KEY_TID_TABLE + ") REFERENCES " + DATABASE_TABLE_TABLES + "(" + KEY_TABLES_ID +")," +
            " FOREIGN KEY (" + KEY_TID_ALL_EXERCISE +") REFERENCES " + DATABASE_TABLE + "(" + KEY_ROWID + "));";

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private final Context mCtx;


    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DATABASE_CREATE);
            db.execSQL(DATABASE_TABLES_CREATE);
            db.execSQL(DATABASE_EXERCISE_CREATE);
        }

        // Sobreescribir el siguiente método y poner disableWriteAheadLogging es necesario a partir
        // de API 28
        @Override
        public void onOpen(SQLiteDatabase db) {
            super.onOpen(db);
            db.disableWriteAheadLogging();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_TABLES);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_EXERCISES);
            onCreate(db);
        }
    }

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     *
     * @param ctx the Context within which to work
     */

    public DbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    /**
     * Open the notes database. If it cannot be opened, try to create a new
     * instance of the database. If it cannot be created, throw an exception to
     * signal the failure
     *
     * @return this (self reference, allowing this to be chained in an
     *         initialization call)
     * @throws SQLException if the database could be neither opened or created
     */
    public DbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }


    /**
     * Create a new note using the title and body provided. If the note is
     * successfully created return the new rowId for that note, otherwise return
     * a -1 to indicate failure.
     *
     * @param name the name of the exercise
     * @param date the category of the exercise
     * @return rowId or -1 if failed
     */
    public long createtable(String name, String date, String subtitle) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TABLES_NAME, name);
        initialValues.put(KEY_TABLES_DATE, date);
        initialValues.put(KEY_TABLES_SUBTITLE, subtitle);

        return mDb.insert(DATABASE_TABLE_TABLES, null, initialValues);
    }

    /**
     * Delete the note with the given rowId
     *
     * @param rowId id of note to delete
     * @return true if deleted, false otherwise
     */
    public boolean deleteNote(long rowId) {

        return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    /**
     * Return a Cursor over the list of all notes in the database
     *
     * @return Cursor over all notes
     */
    public Cursor fetchAllTables() {

        return mDb.query(DATABASE_TABLE_TABLES, new String[] {KEY_TABLES_ID, KEY_TABLES_NAME, KEY_TABLES_DATE, KEY_TABLES_SUBTITLE}, null, null, null, null, null);
    }

    /**
     * Return a Cursor positioned at the note that matches the given rowId
     *
     * @param category id of note to retrieve
     * @return Cursor positioned to matching note, if found
     * @throws SQLException if note could not be found/retrieved
     */
    public Cursor fetch_exercise(String category) throws SQLException {


        return mDb.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                        KEY_NAME, KEY_CATEGORY, KEY_PHOTO, KEY_VIDEO}, KEY_CATEGORY + "=" + category, null,
                null, null, null, null);

    }
    /**
     * Return a Cursor positioned at the note that matches the given rowId
     *
     * @param rowid id of exercise to retrieve
     * @return Cursor positioned to matching note, if found
     * @throws SQLException if note could not be found/retrieved
     */
    public Cursor fetch_table(long rowid) throws SQLException {


        return mDb.query(true, DATABASE_TABLE_TABLES, new String[] {KEY_TABLES_ID,
                        KEY_TABLES_NAME, KEY_TABLES_DATE, KEY_TABLES_SUBTITLE}, KEY_TABLES_ID + "=" + rowid, null,
                null, null, null, null);

    }

    /**
     * Update the note using the details provided. The note to be updated is
     * specified using the rowId, and it is altered to use the title and body
     * values passed in
     *
     * @param rowId id of note to update
     * @param date value to set note title to
     * @param subtitle value to set note body to
     * @return true if the note was successfully updated, false otherwise
     */
    public boolean updateTable(long rowId, String name, String date, String subtitle) {
        ContentValues args = new ContentValues();
        args.put(KEY_TABLES_NAME, name);
        args.put(KEY_TABLES_DATE, date);
        args.put(KEY_TABLES_SUBTITLE, subtitle);

        return mDb.update(DATABASE_TABLE_TABLES, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
}
