package com.core;

public class Customer {
    public static final String DATABASE_TABLE = "CustomerBook";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
        "create table if not exists CustomerBook (_id integer primary key autoincrement , name text not null, registerDate long not null);";
    
    public static final String COL_NAME = "name";
    public static final String COL_DATE = "registerDate";
    
    
    private String name;
    private long registerDate;
    private int id;
    
    public Customer(int id ,String name , long date){
    	this.setId(id);
    	this.registerDate = date;
    	this.setName(name);
    }
    
    public Customer(String name){
    	this.setName(name);
    }
    
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public long getRegisterDate() {
		return registerDate;
	}
	
	public void setRegisterDate(long registerDate) {
		this.registerDate = registerDate;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}