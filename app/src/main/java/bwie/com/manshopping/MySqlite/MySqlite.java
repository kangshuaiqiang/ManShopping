package bwie.com.manshopping.MySqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 黑白 on 2017/9/1.
 */

public class MySqlite extends SQLiteOpenHelper {
    public MySqlite(Context context) {
        super(context, "searchRecord.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table sear(id Integer primary key autoincrement,name varchar(20),label varchar(20)) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
