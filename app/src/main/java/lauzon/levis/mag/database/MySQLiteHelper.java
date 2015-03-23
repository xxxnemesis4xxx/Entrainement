package lauzon.levis.mag.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_EXERCICE = "exercice";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOM = "nom";
    public static final String COLUMN_REF_MODEL = "idmodel";
    public static final String COLUMN_GOAL = "goal";

    public static final String TABLE_MODEL = "model";
    public static final String COLUMN_ID_MODEL = "_id";
    public static final String COLUMN_NOM_MODEL = "nom";

    public static final String TABLE_ENTRAINEMENT = "entrainement";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_INFOSUPP = "retour";

    private static final String DATABASE_NAME = "entrainement.db";
    private static final int DATABASE_VERSION = 6;

    // Database creation sql statement
    private static final String CREATE_TABLE_MODEL = "create table "
            + TABLE_MODEL + "(" + COLUMN_ID_MODEL
            + " integer primary key autoincrement, " + COLUMN_NOM
            + " text not null);";

    private static final String CREATE_TABLE_EXERCICE = "create table "
            + TABLE_EXERCICE + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_NOM
            + " text not null, " + COLUMN_REF_MODEL + " integer NOT NULL, " + COLUMN_GOAL + " TEXT, FOREIGN KEY ("
            + COLUMN_REF_MODEL +") REFERENCES " + TABLE_MODEL + " ("+ COLUMN_ID +"));";

    private static final String CREATE_TABLE_ENTRAINEMENT = "create table "
            + TABLE_ENTRAINEMENT + "(" + COLUMN_ID + " interger primary key autoincrement, "
            + COLUMN_DATE + " DATETIME NOT NULL, " + COLUMN_RATING + " INTERGER NOT NULL, "
            + COLUMN_INFOSUPP + " TEXT, " + COLUMN_REF_MODEL + " integer NOT NULL, "
            + COLUMN_GOAL + " TEXT, FOREIGN KEY (" + COLUMN_REF_MODEL +") REFERENCES "
            + TABLE_MODEL + " ("+ COLUMN_ID +"));";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_MODEL);
        database.execSQL(CREATE_TABLE_EXERCICE);
        database.execSQL(CREATE_TABLE_ENTRAINEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENTRAINEMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCICE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MODEL);
        onCreate(db);
    }

}
