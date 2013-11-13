package com.core;

public class ItemDescription {
    public static final String DATABASE_TABLE = "ItemDescriptionBook";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
        "create table if not exists ItemDescriptionBook (_id integer primary key autoincrement , name text not null , barcode integer not null , description text not null, price real not null);";
    
    public static final String COL_DESCRIPTION = "description";
    public static final String COL_PRICE= "price";
    public static final String COL_NAME = "name";
    public static final String COL_BARCODE = "barcode";
    
	public int _id;
	private String name;
	private float price;
	private int barcode;
	private String itemDescription;
	
	public ItemDescription(String name, String itemDescription, float price, int barcode){
		this.setItemDescription(itemDescription);
		this.setName(name);
		this.setPrice(price);
		this.setBarcode(barcode);
	}

	public int getId() {
		return _id;
	}

	public void setId(int id) {
		this._id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getBarcode() {
		return barcode;
	}

	public void setBarcode(int barcode) {
		this.barcode = barcode;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
}
