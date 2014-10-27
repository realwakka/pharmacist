package com.realwakka.organicpractice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by realwakka on 14. 10. 27.
 */
public class IncorrectDataSource {

    // Database fields
    private SQLiteDatabase database;
    private DataHelper dbHelper;
    private String[] allColumns = { DataHelper.COLUMN_ID,
            DataHelper.COLUMN_PROBLEM, DataHelper.COLUMN_GROUP };

    public IncorrectDataSource(Context context) {
        dbHelper = new DataHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Incorrect addIncorrect(int comment,int group) {
        ContentValues values = new ContentValues();
        values.put(DataHelper.COLUMN_PROBLEM, comment);
        values.put(DataHelper.COLUMN_GROUP, group);

        long insertId = database.insert(DataHelper.TABLE_INCORRECT, null,values);

        Cursor cursor = database.query(DataHelper.TABLE_INCORRECT,
                allColumns, DataHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();

        Incorrect newComment = cursorToIncorrect(cursor);
        cursor.close();
        return newComment;
    }

    public void deleteIncorrect(int problem,int group) {
        database.delete(DataHelper.TABLE_INCORRECT, DataHelper.COLUMN_PROBLEM
                + " = " + problem, null);
    }

    public List<Incorrect> getIncorrectByGroup(int group) {
        List<Incorrect> comments = new ArrayList<Incorrect>();

        Cursor cursor = database.query(DataHelper.TABLE_INCORRECT,
                allColumns, DataHelper.COLUMN_GROUP+"=?", new String[]{group+""}, null, null, null);


        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Incorrect comment = cursorToIncorrect(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    public boolean isContains(int problem){
        List<Incorrect> comments = new ArrayList<Incorrect>();

        Cursor cursor = database.query(DataHelper.TABLE_INCORRECT,
                allColumns, DataHelper.COLUMN_PROBLEM+"=?", new String[]{problem+""}, null, null, null);

        boolean ret = true;
        if(cursor.getCount()==0){
            ret = false;
        }else{
            ret = true;
        }
        cursor.close();

        return ret;
    }


    public List<Incorrect> getAllIncorrect() {
        List<Incorrect> comments = new ArrayList<Incorrect>();

        Cursor cursor = database.query(DataHelper.TABLE_INCORRECT,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Incorrect comment = cursorToIncorrect(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    private Incorrect cursorToIncorrect(Cursor cursor) {
        return new Incorrect(cursor.getLong(0),cursor.getInt(1),cursor.getInt(2));
    }
}
