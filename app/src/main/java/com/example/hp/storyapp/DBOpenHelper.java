package com.example.hp.storyapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 10/15/2016.
 */
public class DBOpenHelper extends SQLiteOpenHelper {




    SQLiteOpenHelper sqLiteOpenHelper;

    public static final String COLUMN_NAME3 = "name";

    public static final String COLUMN_ID = "id";

    public  static final String TABLE_NAME3 = "students";

    public static final String COLUMN_TYPE3 = "type";
    public static final String COLUMN_DESC3 = "description";
    public static final String  COLUMN_IMAGE3 = "image";

    public static final String DATABASE_NAME3 = "story.db";

    public static final int DATABASE_VERSION = 14;

   SQLiteDatabase database;


    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME3, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE students ( name TEXT,type TEXT,description TEXT,image TEXT )";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL( "DROP TABLE IF EXISTS students ");
        onCreate(db);

    }
    public boolean insertStoryDetail(String name, String type, String description, String image )
    {
        database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("type", type);
        contentValues.put("description", description);
        contentValues.put("image", image);
        long result=  database.insert( TABLE_NAME3 ,null,contentValues);
        return (result != -1);
    }




    public List<Story> findAll2(){

       database = getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from students", null);
         List<Story>  storyLists = cursartoList(cursor);
        return storyLists;
    }
    public List<Story> cursartoList(Cursor cursor) {


        List<Story> storyList = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {


                Story story4 = new Story();
                story4.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME3)));
                story4.setType(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE3)));
                story4.setDeccription(cursor.getString(cursor.getColumnIndex(COLUMN_DESC3)));

             story4.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE3)));
               storyList.add(story4);
            }
        }
        return storyList;
    }


    public void open(){

        Log.d("open", " database is open ");
        database = getWritableDatabase();

    }
    public void close(){

        Log.d("open", " database is open ");
        close();

    }


}
