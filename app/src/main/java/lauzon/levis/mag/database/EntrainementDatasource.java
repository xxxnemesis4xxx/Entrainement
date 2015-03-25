package lauzon.levis.mag.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import java.sql.Date;

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
        values.put(MySQLiteHelper.COLUMN_REF_MODEL,id);
        values.put(MySQLiteHelper.COLUMN_NOM, nom);
        long insertId = database.insert(MySQLiteHelper.TABLE_EXERCICE, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_EXERCICE,
                null, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        exercice newExercice = cursorToExercice(cursor);
        cursor.close();
    }

    public void updateExercice(String goal, long id) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_GOAL, goal);

        database.update(MySQLiteHelper.TABLE_EXERCICE,values,MySQLiteHelper.COLUMN_ID + " = " + id, null);
    }

    public void deleteModel(long ID) {
        database.delete(MySQLiteHelper.TABLE_MODEL, MySQLiteHelper.COLUMN_ID
                + " = " + ID, null);
        database.delete(MySQLiteHelper.TABLE_EXERCICE, MySQLiteHelper.COLUMN_REF_MODEL
                + " = " + ID, null);
    }

    public List<exercice> getAllExercices() {
        List<exercice> Exercices = new ArrayList<exercice>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_EXERCICE,
                null, null, null, null, null, null);

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

    public List<exercice> getALLExerciceForOneModel(long ID) {
        List<exercice> Exercices = new ArrayList<exercice>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_EXERCICE,
                null, MySQLiteHelper.COLUMN_REF_MODEL + " = " + ID, null, null, null, null);

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

    public List<entrainement> getAllEntrainements(long datedebut, long datefin) {
        List<entrainement> Entrainements = new ArrayList<entrainement>();


        Cursor cursor = database.query(MySQLiteHelper.TABLE_ENTRAINEMENT,
                null, MySQLiteHelper.COLUMN_DATE + " >= " + datedebut + " and " + MySQLiteHelper.COLUMN_DATE
                        + " <= " + datefin, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            entrainement Entrainement = cursorToEntrainement(cursor);
            Entrainements.add(Entrainement);
            cursor.moveToNext();
        }

        cursor.close();

        return Entrainements;
    }

    public exercice getExercice(long ID) {
        exercice Exercice = new exercice();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_EXERCICE,
                null, MySQLiteHelper.COLUMN_ID + " = " + ID, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Exercice = cursorToExercice(cursor);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();

        return Exercice;
    }

    public void createTrainingDay(Long date,String idModel) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_REF_MODEL,idModel);
        values.put(MySQLiteHelper.COLUMN_DATE, date);
        long insertId = database.insert(MySQLiteHelper.TABLE_ENTRAINEMENT, null,
                values);
    }

    private exercice cursorToExercice(Cursor cursor) {
        exercice Exercice = new exercice();
        Exercice.setId(cursor.getLong(0));
        Exercice.setNom(cursor.getString(1));
        Exercice.setRefidmodel(cursor.getLong(2));
        Exercice.setGoal(cursor.getString(3));
        return Exercice;
    }

    private model cursorToModel(Cursor cursor) {
        model Model = new model();
        Model.setId(cursor.getLong(0));
        Model.setNom(cursor.getString(1));
        return Model;
    }

    private entrainement cursorToEntrainement(Cursor cursor) {
        entrainement Entrainement = new entrainement();
        Entrainement.setId(cursor.getLong(0));
        Entrainement.setDate(cursor.getLong(1));
        Entrainement.setInfosupp(cursor.getString(3));
        Entrainement.setRating(cursor.getInt(2));
        Entrainement.setRefidmodel(cursor.getLong(4));

        return Entrainement;
    }

}
