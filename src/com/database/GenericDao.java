package com.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class GenericDao extends SQLiteOpenHelper {

    private static final String TAG = "GenericDao";
    private SQLiteDatabase db;
    public static String dName = "posData";
    private String tName;
    private String sql;
    public static final String KEY_ID = "_id";
    private Context context;
    
    public GenericDao(Context ctx, String dbName, String sql, String tableName, int ver){
        super(ctx, dbName, null, ver);
        this.context = ctx;
        Log.i(TAG, "Creating or opening database [ " + dbName + " ].");
        this.sql = sql;
        dName = dbName;
        tName = tableName;
        try{
            Log.i(TAG, "Creating or opening the database [ " + dbName + " ].");
            db = getWritableDatabase();
        }catch(SQLiteException se){
            Log.e(TAG, "Cound not create and/or open the database [ " + dbName + " ] that will be used for reading and writing.", se);
        }
        try{
            db.execSQL(sql);
            Log.i(TAG, "Creating or opening [ " + sql + " ].");
        }catch(SQLException se){
            Log.e(TAG, "Cound not create the database table according to the SQL statement [ " + sql + " ].", se);
        }
    }

    public void close(){ 
            Log.i(TAG, "Closing the database [ " + dName + " ].");
            db.close(); 
    }
    
    @Override
    public void onCreate(SQLiteDatabase db){
        Log.i(TAG, "Trying to create database table if it isn't existed [ " + sql + " ].");
        try{
            db.execSQL(sql);
        }catch(SQLException se){
            Log.e(TAG, "Cound not create the database table according to the SQL statement [ " + sql + " ].", se);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
       Log.i(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
       try{
           db.execSQL("DROP TABLE IF EXISTS " + tName);
       }catch(SQLException se){
            Log.e(TAG, "Cound not drop the database table [ " + tName + " ].", se);
        }
       onCreate(db);
    }
    
    protected Cursor get(String table, String[] columns, String where){
        Cursor cursor =db.query(true, table, columns, where, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    protected Cursor get(String table, String[] columns, String where , String[] args){
        Cursor cursor =db.query(true, table, columns, where, args, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    
    protected long insert(String table, ContentValues values){
        return db.insert(table, null, values);
    }
    
    protected Cursor get(String table, String[] columns){
        return db.query(table, columns, null, null, null, null, null);
    } 
    
    protected Cursor get(String table, String[] columns, long id){
        Cursor cursor =db.query(true, table, columns, KEY_ID + "=" + id, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    
    public int delete(String table) {
        return db.delete(table, null , null);
    }
    
    protected int delete(String table, long id) {
        return db.delete(table, KEY_ID + "=" + id, null);
    }
    
    protected int delete(String table, String where , String[] args) {
        return db.delete(table, where , args);
    }
    
    protected int update(String table, long id, ContentValues values) {
        return db.update(table, values, KEY_ID + "=" + id, null);
    }
    
    protected int update(String table, String where, ContentValues values) {
        return db.update(table, values, where, null);
    }
    
    protected SQLiteDatabase getDB(){
    	return db;
    }

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}
}
