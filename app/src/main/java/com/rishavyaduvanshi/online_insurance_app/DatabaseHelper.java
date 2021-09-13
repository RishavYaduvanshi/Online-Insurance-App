package com.rishavyaduvanshi.online_insurance_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Userdata.db";
    public static final String TABLE_NAME = "Usertable";
    public static final String col1 = "USERID";
    public static final String col2 = "USERNAME";
    public static final String col3 = "PASSWORD";
    public static final String col4 = "INSURANCETYPE1";
    public static final String col5 = "INSURANCETYPE2";
    public static final String col6 = "INSURANCETYPE3";
    public static final String col7 = "CURRENTDATE1";
    public static final String col8 = "CURRENTDATE2";
    public static final String col9 = "CURRENTDATE3";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME+"(USERID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT,PASSWORD TEXT,INSURANCETYPE1 TEXT,INSURANCETYPE2 TEXT,INSURANCETYPE3 TEXT,CURRENTDATE1 TEXT,CURRENTDATE2 TEXT,CURRENTDATE3 TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }
    public boolean insertData(String name  ,String password){//,String phoneNumber
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(col2,name);
        contentValues.put(col3,password);
        //contentValues.put(col4,phoneNumber);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public boolean insertYear(int type,String insurType,String userName,String currentDate){
        String s[] = {"INSURANCETYPE1","INSURANCETYPE2","INSURANCETYPE3"};
        String d[] = {col7,col8,col9};
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2,userName);
        contentValues.put(s[type],insurType);
        contentValues.put(d[type],currentDate);

        db.update(TABLE_NAME, contentValues,"USERNAME = ?",new String[]{userName});
        //long result = db.insert(TABLE_NAME,null,contentValues);

            return true;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return res;
    }
    public boolean updateData(String uid,String uname,String upass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col1,uid);
        contentValues.put(col2,uname);
        contentValues.put(col3,upass);

        //contentValues.put(col4,insurType);
        //contentValues.put(col4,uacc);
        db.update(TABLE_NAME, contentValues,"USERID = ?",new String[]{uid});
        return true;
    }

    public Integer deleteData(String uid){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"USERID = ?",new String[]{uid});
    }

    public boolean checkUserPass(String username,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE USERNAME = ? AND PASSWORD = ? ",new String[]{username,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;

    }
    public String getName(String uname) {

        String rv =" ";
        SQLiteDatabase db = this.getWritableDatabase();
        String whereclause = "USERNAME=?";
        String[] whereargs = new String[]{uname};
        Cursor csr = db.query(TABLE_NAME, null, whereclause, whereargs, null, null, null);
        if (csr.moveToFirst()) {
            rv = csr.getString(csr.getColumnIndex(col4)) + "  "
                    + csr.getString(csr.getColumnIndex(col5)) + "  "
                    + csr.getString(csr.getColumnIndex(col6));

        }
        return rv.replaceAll("null", "");
    }
    public String getName1(String uname) {

        String rv =" ";
        SQLiteDatabase db = this.getWritableDatabase();
        String whereclause = "USERNAME=?";
        String[] whereargs = new String[]{uname};
        Cursor csr = db.query(TABLE_NAME, null, whereclause, whereargs, null, null, null);
        if (csr.moveToFirst()) {
            rv = csr.getString(csr.getColumnIndex(col4))+" "+csr.getString(csr.getColumnIndex(col7));


        }
        return rv.replaceAll("null", "");
    }
    public String getName2(String uname) {

        String rv =" ";
        SQLiteDatabase db = this.getWritableDatabase();
        String whereclause = "USERNAME=?";
        String[] whereargs = new String[]{uname};
        Cursor csr = db.query(TABLE_NAME, null, whereclause, whereargs, null, null, null);
        if (csr.moveToFirst()) {
            rv =  csr.getString(csr.getColumnIndex(col5))+" "+csr.getString(csr.getColumnIndex(col8));


        }
        return rv.replaceAll("null", "");
    }
    public String getName3(String uname) {

        String rv =" ";
        SQLiteDatabase db = this.getWritableDatabase();
        String whereclause = "USERNAME=?";
        String[] whereargs = new String[]{uname};
        Cursor csr = db.query(TABLE_NAME, null, whereclause, whereargs, null, null, null);
        if (csr.moveToFirst()) {
            rv = csr.getString(csr.getColumnIndex(col6))+" "+csr.getString(csr.getColumnIndex(col9));

        }
        return rv.replaceAll("null", "");
    }
    public String currentDate(String uname) {

        String rv =" ";
        SQLiteDatabase db = this.getWritableDatabase();
        String whereclause = "USERNAME=?";
        String[] whereargs = new String[]{uname};
        Cursor csr = db.query(TABLE_NAME, null, whereclause, whereargs, null, null, null);
        if (csr.moveToFirst()) {
            rv = csr.getString(csr.getColumnIndex(col7));

        }
        return rv.replaceAll("null", "");
    }
    public Cursor getUData(String uname) {


        SQLiteDatabase db = this.getWritableDatabase();
        String whereclause = "USERNAME=?";
        String[] whereargs = new String[]{uname};
        Cursor csr = db.query(TABLE_NAME,null,whereclause,whereargs,null,null,null);

        return csr;
    }

}
