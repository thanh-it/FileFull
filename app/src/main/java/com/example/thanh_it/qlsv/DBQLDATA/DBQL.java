package com.example.thanh_it.qlsv.DBQLDATA;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBQL extends SQLiteOpenHelper {
    private static final int version = 1   ;
    private static final String name = "myDb";
    private static final String SQL_CREATE_ACCOUNT="Create table ACCOUNT (" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "USER TEXT ," +
            "PASS TEXT ,"+
            "PHONE INTEGER)";
    private static final String SQL_CREATE_LOP="Create table LOP (" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "MALOP TEXT ," +
            "TENLOP TEXT)";
    private static final String SQL_CREATE_STUDENT="Create table STUDENT (" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "MSSV TEXT ," +
            "NAME TEXT ,"+
            "SN TEXT,"+
            "MALOP TEXT )";
    public DBQL(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ACCOUNT);
        db.execSQL(SQL_CREATE_LOP);
        db.execSQL(SQL_CREATE_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_table = String.format("DROP TABLE IF EXISTS %s", db);

        db.execSQL(drop_table);

        onCreate(db);
        Log.d("onUpgrade","HELLO");
    }
}
