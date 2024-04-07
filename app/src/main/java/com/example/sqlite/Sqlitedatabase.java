package com.example.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Sqlitedatabase extends SQLiteOpenHelper {
    private Context context;
    public static final String database_name = "data.db";
    public static final int database_version = 1;
    public static final String aaa = "da";

    public Sqlitedatabase(@Nullable Context context) {
        super( context, database_name, null, database_version );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public Cursor fetchall()
    {
        SQLiteDatabase sqlitedatabase=this.getWritableDatabase();
        String q="select *from person";
        Cursor cursor=sqlitedatabase.rawQuery(q,null);
        return cursor;

    }
}
