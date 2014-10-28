package com.realwakka.organicpractice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by realwakka on 14. 10. 27.
 */
public class DataHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "pharmacist";
    public static final String TABLE_INCORRECT = "incorrect_list";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PROBLEM = "problem";
    public static final String COLUMN_GROUP="problem_group";

    private static final int DB_VER=1;
    private static final String DB_CREATE="create table "+TABLE_INCORRECT+" ( "+COLUMN_ID+" integer primary key autoincrement, " + COLUMN_PROBLEM
            + " integer not null," + COLUMN_GROUP + " integer not null);";



    public DataHelper(Context context){
        super(context,DB_NAME,null,DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_INCORRECT);
        onCreate(db);
    }
}
