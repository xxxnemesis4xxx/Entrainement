package lauzon.levis.mag.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class EntrainementDatasource {
    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_NOM };

    public EntrainementDatasource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long createModel(String nom) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NOM_MODEL, nom);
        long insertId = database.insert(MySQLiteHelper.TABLE_MODEL, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_MODEL,
                allColumns, MySQLiteHelper.COLUMN_ID_MODEL + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        model newModel = cursorToModel(cursor);
        cursor.close();

        return newModel.getId();
    }

    public void createExercice(String nom,long id) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NOM, nom);
        values.put(MySQLiteHelper.COLUMN_REF_MODEL,id);
        long insertId = database.insert(MySQLiteHelper.TABLE_EXERCICE, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_EXERCICE,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        exercice newExercice = cursorToExercice(cursor);
        cursor.close();
    }

    public void deleteExercice(exercice Exercice) {
        long id = Exercice.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_EXERCICE, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<exercice> getAllExercices() {
        List<exercice> Exercices = new ArrayList<exercice>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_EXERCICE,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            exercice Exercice = cursorToExercice(cursor);
            Exercices.add(Exercice);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return Exercices;
    }

    public List<model> getAllModels() {
        List<model> Models = new ArrayList<model>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_MODEL,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            model Model = cursorToModel(cursor);
            Models.add(Model);
            cursor.moveToNext();
        }

        // make sure to close the cursor
        cursor.close();

        return Models;
    }

    private exercice cursorToExercice(Cursor cursor) {
        exercice Exercice = new exercice();
        Exercice.setId(cursor.getLong(0));
        Exercice.setNom(cursor.getString(1));
        return Exercice;
    }

    private model cursorToModel(Cursor cursor) {
        model Model = new model();
        Model.setId(cursor.getLong(0));
        Model.setNom(cursor.getString(1));
        return Model;
    }
}
