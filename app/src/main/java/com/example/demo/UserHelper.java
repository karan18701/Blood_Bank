package com.example.demo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.demo.entity.User;

import java.util.ArrayList;

public class UserHelper extends SQLiteOpenHelper {

    public static String dbName="Userdata";
    public static String tableName="Userdetails";
    public static String idColumn="id";
    public static String nameColumn="name";
    public static String contactColumn="contact";
    public static String genderColumn="gender";
    public static String bloodgroupColumn="bloodgroup";
    public static String cityColumn="city";
    public static String emailColumn="email";
    public static String passwordColumn="password";
    public static String donorColumn="donor";

    public UserHelper(Context context) {
        super(context, dbName, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ tableName+ "(" +
                idColumn + " integer primary key autoincrement, " +
                nameColumn + " text, "+
                genderColumn+ " text, "+
                bloodgroupColumn + " text, " +
                contactColumn + " text, "+
                cityColumn + " text, " +
                emailColumn + " text, " +
                passwordColumn + " text, "+
                donorColumn + " integer )"
                );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public Boolean insertuserdata(User user)
    {
        boolean result=true;
        try{
            SQLiteDatabase db= getWritableDatabase();
            ContentValues contentValues=new ContentValues();
            contentValues.put(nameColumn, user.getName());
            contentValues.put(genderColumn, user.getGender());
            contentValues.put(bloodgroupColumn, user.getBloodgroup());
            contentValues.put(contactColumn, user.getContact());
            contentValues.put(cityColumn, user.getCity());
            contentValues.put(emailColumn,user.getEmail());
            contentValues.put(passwordColumn, user.getPassword());
            contentValues.put(donorColumn, user.getDonor());
//            if(user.isDonor())

//            else
//                contentValues.put(donorColumn, 0);
            result=db.insert(tableName, null, contentValues) > 0;
        }
        catch (Exception e)
        {
            result=false;
        }
        return result;
    }

    @SuppressLint("Range")
    public User login(String email, String password){
        User userob=null;
//        String sql="select * from "+tableName+" where email = ? and password = ?";
        try{
            SQLiteDatabase db=getReadableDatabase();
            Cursor cursor=db.rawQuery("select * from "+tableName+" where email = ? and password = ?",new String[]{email,password});
            if(cursor.moveToFirst())
            {
                userob=new User();
                userob.setId(cursor.getInt(cursor.getColumnIndex(idColumn)));
                userob.setName(cursor.getString(cursor.getColumnIndex(nameColumn)));
                userob.setGender(cursor.getString(cursor.getColumnIndex(genderColumn)));
                userob.setBloodgroup(cursor.getString(cursor.getColumnIndex(bloodgroupColumn)));
                userob.setContact(cursor.getString(cursor.getColumnIndex(contactColumn)));
                userob.setCity(cursor.getString(cursor.getColumnIndex(cityColumn)));
                userob.setEmail(cursor.getString(cursor.getColumnIndex(emailColumn)));
                userob.setPassword(cursor.getString(cursor.getColumnIndex(passwordColumn)));
                userob.setDonor(cursor.getInt(cursor.getColumnIndex(donorColumn)));
//                int x= cursor.getColumnIndex(donorColumn);
//                if(x==0)
//                    userob.setDonor(false);
//                else
//                    userob.setDonor(true);

            }
        }
        catch (Exception e)
        {
            userob=null;
        }
        return userob;
    }

    @SuppressLint("Range")
    public User checkemail(String email){
        User userob=null;
//        String sql="select * from "+tableName+" where email = ? and password = ?";
        try{
            SQLiteDatabase db=getReadableDatabase();
            Cursor cursor=db.rawQuery("select * from "+tableName+" where email = ?",new String[]{email});
            if(cursor.moveToFirst())
            {
                userob=new User();
                userob.setId(cursor.getInt(cursor.getColumnIndex(idColumn)));
                userob.setName(cursor.getString(cursor.getColumnIndex(nameColumn)));
                userob.setGender(cursor.getString(cursor.getColumnIndex(genderColumn)));
                userob.setBloodgroup(cursor.getString(cursor.getColumnIndex(bloodgroupColumn)));
                userob.setContact(cursor.getString(cursor.getColumnIndex(contactColumn)));
                userob.setCity(cursor.getString(cursor.getColumnIndex(cityColumn)));
                userob.setEmail(cursor.getString(cursor.getColumnIndex(emailColumn)));
                userob.setPassword(cursor.getString(cursor.getColumnIndex(passwordColumn)));
                System.out.println("COmpeleted");
            }
        }
        catch (Exception e)
        {
            userob=null;
        }
        return userob;
    }

//    public Boolean updateuserdata(String name, String contact, String sex,String bloodgroup,String address,String city,String email,String password)
//    {
//        SQLiteDatabase DB = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", name);
//        contentValues.put("sex", sex);
//        contentValues.put("bloodgroup", bloodgroup);
//        contentValues.put("contact", contact);
//        contentValues.put("address", address);
//        contentValues.put("city", city);
//        contentValues.put("email",email);
//        contentValues.put("password", password);
//        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name});
//        if (cursor.getCount() > 0)
//        {
//            long result = DB.update("Userdetails", contentValues, "name=?", new String[]{name});
//            if (result == -1)
//            {
//                return false;
//            }
//            else {
//                return true;
//            }
//        }
//        else
//        {
//            return false;
//        }
//    }


    public boolean update(User user)
    {
        boolean result=true;
        try{
            SQLiteDatabase db= getWritableDatabase();
            ContentValues contentValues=new ContentValues();
            contentValues.put(nameColumn, user.getName());
            contentValues.put(genderColumn, user.getGender());
            contentValues.put(bloodgroupColumn, user.getBloodgroup());
            contentValues.put(contactColumn, user.getContact());
            contentValues.put(cityColumn, user.getCity());
            contentValues.put(emailColumn,user.getEmail());
            contentValues.put(passwordColumn, user.getPassword());
//            contentValues.put(donorColumn, user.getDonor());

            result=db.update(tableName, contentValues,"name=?", new String[]{user.getName()}) > 0;
        }
        catch (Exception e)
        {
            result=false;
        }
        return result;
    }
    public ArrayList<User> getdata (String bgrp,String city)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where "+bloodgroupColumn+"= '"+bgrp+"' and "+cityColumn+" = '"+city+"' and "+donorColumn+" = 1", null);
        ArrayList<User> users = new ArrayList<User>();
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            User user = new User();
            user.setId(cursor.getInt(0));
            user.setName( cursor.getString(1));
            user.setGender(cursor.getString(2));
            user.setBloodgroup(cursor.getString(3));
            user.setContact(cursor.getString(4));
            user.setCity(cursor.getString(5));
            user.setEmail(cursor.getString(6));
            user.setPassword(cursor.getString(7));
            user.setDonor(cursor.getInt(8));
            users.add(user);
            cursor.moveToNext();
        }
        return users;
    }
}
