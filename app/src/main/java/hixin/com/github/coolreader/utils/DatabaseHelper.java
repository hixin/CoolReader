package hixin.com.github.coolreader.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hixin on 2017/9/6.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private  static final String DB_NAME= "CoolReader";
    private static  DatabaseHelper instance = null;
    private static final int DB_VERSION = 1;
    public static final String DELETE_TABLE_DATA = "delete from ";
    public static final String DROP_TABLE = "drop table if exists ";

    private DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion == 1){

        }
    }
    public static synchronized DatabaseHelper instance(Context context){
        if(instance == null){
            instance = new DatabaseHelper(context,DB_NAME,null,DB_VERSION);
        }
        return instance;
    }
}
