package let.it.wag.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class DBHandler extends SQLiteOpenHelper {

    SQLiteDatabase db;
    private ViewList viewList;

    private static final int DATABSE_VERSION=2;
    private static final String DATABSE_NAME ="demo.db";
    public static final String TABLE_NAME = "demotable";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ID = "id";



    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, ViewList viewList) {
        super(context, DATABSE_NAME, factory, DATABSE_VERSION);
        this.viewList = viewList;
        db = getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(" CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                COLUMN_NAME + " TEXT);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public void addProduct(String name){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,name);
        db.insert(TABLE_NAME, null, values);
        db.close();
        getfromDatabase();
    }

    public void deleteProduct(String name){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(" DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + "=\"" + name + "\";");
        db.close();
    }

    public void getfromDatabase(){
        String temp="";
        ArrayList<String> arrayList = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = " SELECT * FROM " + TABLE_NAME + " WHERE 1 ";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("id"))!=null){
                 arrayList.add(c.getString(c.getColumnIndex("name")));
            }
            String arr[] = new String[arrayList.size()];
            for(int i = 0; i < arr.length; i++){
                arr[i] = arrayList.get(i);
            }
            viewList.setList(arr);
            c.moveToNext();
        }
    }
}
