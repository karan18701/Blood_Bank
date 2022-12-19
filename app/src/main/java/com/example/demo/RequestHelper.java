package com.example.demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.demo.entity.UserRequest;

import java.util.ArrayList;

public class RequestHelper extends SQLiteOpenHelper {
    public static String dbName="Requestdata";
    public static String tableName="Postdetails";
    public static String idColumn="id";
    public static String nameColumn="name";
    public static String contactColumn="contact";
    public static String bloodgroupColumn="bloodgroup";
    public static String cityColumn="city";

    public RequestHelper(Context context) {
        super(context, dbName, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ tableName+ "(" +
                idColumn + " integer primary key autoincrement, " +
                nameColumn + " text, "+
                contactColumn + " text, "+
                bloodgroupColumn + " text, " +
                cityColumn + " text) "
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public Boolean insertRequestdata(UserRequest userRequest)
    {
        boolean result=true;
        try{
            SQLiteDatabase db= getWritableDatabase();
            ContentValues contentValues=new ContentValues();
            contentValues.put(nameColumn, userRequest.getName());
            contentValues.put(contactColumn, userRequest.getContact());
            contentValues.put(bloodgroupColumn, userRequest.getBloodgroup());
            contentValues.put(cityColumn, userRequest.getCity());

            result=db.insert(tableName, null, contentValues) > 0;
        }
        catch (Exception e)
        {
            result=false;
        }
        return result;
    }

    public ArrayList<UserRequest> getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Postdetails", null);
        ArrayList<UserRequest> users = new ArrayList<UserRequest>();
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            UserRequest user = new UserRequest();
            user.setId(cursor.getInt(0));
            user.setName( cursor.getString(1));
            user.setBloodgroup(cursor.getString(3));
            user.setContact(cursor.getString(2));
            user.setCity(cursor.getString(4));
            users.add(user);
            cursor.moveToNext();
        }
        return users;
    }
}
