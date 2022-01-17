package com.stardust.autojs.core.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Transaction {

    private final SQLiteDatabase mDatabase;

    public Transaction(SQLiteDatabase database) {
        mDatabase = database;
    }

    public SQLiteDatabase getDatabase() {
        return mDatabase;
    }

    public void executeSql(String sqlStatement, String[] arguments, StatementCallback callback, StatementErrorCallback errorCallback) {
        Cursor cursor = mDatabase.rawQuery(sqlStatement, arguments);
        callback.handleEvent(this, DatabaseResultSet.fromCursor(cursor));
    }

    void succeed() {
        mDatabase.setTransactionSuccessful();
    }

    void end() {
        mDatabase.endTransaction();
    }
}
