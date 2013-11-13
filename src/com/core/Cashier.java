package com.core;

import com.database.CashierBookDB;

import android.content.Context;

public class Cashier {
    public static final String DATABASE_TABLE = "CashierBook";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
        "create table if not exists CashierBook (_id integer primary key autoincrement , name text not null, user text not null , password text not null);";
    
    public static final String COL_NAME = "name";
    public static final String COL_USER = "user";
    public static final String COL_PASS = "password";
    
    private int id;
    private String name;
    private String user;
    private String password;
    
    public Cashier() {
		// TODO Auto-generated constructor stub
	}
    
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
