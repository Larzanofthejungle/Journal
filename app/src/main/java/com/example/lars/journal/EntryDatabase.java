package com.example.lars.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class EntryDatabase extends SQLiteOpenHelper {

    SQLiteDatabase db;
    Cursor cursor;
    ContentValues values;

    private static EntryDatabase instance;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("onCreate", "reached");
        String createTable =  "create table entries (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT, mood TEXT, timestamp TEXT);";
        sqLiteDatabase.execSQL(createTable);
        String testEntry =  "INSERT INTO entries (title, content, mood, timestamp)\n" +
                "VALUES ('Test', 'A little text for the sole purpose of testing', 'indifferent', '12:47');";
        String testEntry2 =  "INSERT INTO entries (title, content, mood, timestamp)\n" +
                "VALUES ('Test2', 'A little text for the sole purpose of testing', 'indifferent', '13:58');";
        sqLiteDatabase.execSQL(testEntry);
        sqLiteDatabase.execSQL(testEntry2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS entries;");
        onCreate(sqLiteDatabase);
    }

    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static EntryDatabase getInstance(Context context) {
        Log.d("instance", String.valueOf(instance));
        if (instance == null) {
            Log.d("instance", String.valueOf(instance));
            instance = new EntryDatabase(context, "entries", null, 1);
            instance.getWritableDatabase();
        }
        Log.d("instance", String.valueOf(instance));
        return instance;
    }

    public Cursor selectAll(){
        db = instance.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM entries", null);
        return cursor;
    }

    public void insert(JournalEntry entry){
        db = instance.getWritableDatabase();
        values = new ContentValues();
        values.put("title", entry.getTitle());
        values.put("content", entry.getContent());
        values.put("mood", entry.getMood());
        values.put("timestamp", entry.getTimestamp());
        db.insert("entries", null, values);
    }

    public void delete(long id){
        db = instance.getWritableDatabase();
        db.execSQL("DELETE FROM entries\n" +
                "WHERE _id = "+ id +";");
    }
}
