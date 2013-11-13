package com.core;

public class InventoryLineItem {
	 public static final String DATABASE_TABLE = "InventoryLineItemBook";
	 public static final int DATABASE_VERSION = 1;
	 public static final String TABLE_CREATE =
			 "create table if not exists InventoryLineItemBook (_id integer primary key autoincrement , date long not null);";
	 
	 public static final String COL_DATE = "date";
	 
	 private int id;
	 private long date;
	 
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
