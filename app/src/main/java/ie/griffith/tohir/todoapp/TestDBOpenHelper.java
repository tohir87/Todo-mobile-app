package ie.griffith.tohir.todoapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class TestDBOpenHelper extends SQLiteOpenHelper {

    public TestDBOpenHelper(Context context, String name, CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase db){
        // create the database
        db.execSQL(create_table);
    }

    public void onUpgrade(SQLiteDatabase db, int version_old, int version_new){
        // drop table and recreate
        db.execSQL(drop_table);
        db.execSQL(create_table);
    }

    // constants needed
    private static final String create_table = "create table todo(" +
            "ID integer primary key autoincrement," +
            "TITLE string, " +
            "TASK string, " +
            "STATUS integer default 0, " +
            "DATE_CREATED datetime, " +
            "DATE_COMPLETED datetime default null" +
            ")";

    private static final String drop_table = "drop table todo";
}
