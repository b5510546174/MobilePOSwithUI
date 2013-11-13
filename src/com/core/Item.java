package com.core;

public class Item {
    public static final String DATABASE_TABLE = "Inventory3";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
        "create table if not exists Inventory3 (_id integer primary key autoincrement , status integer, description_id integer not null, inventorylineitem_id integer not null);";
   
    public static final String COL_INVENTORYLINEITEM_ID = "inventorylineitem_id";
    public static final String COL_DESCRIPTION_ID = "description_id";
    public static final String COL_STATUS = "status";
	
    public static final int STATUS_SOLD = 0;
    public static final int STATUS_STOCK = 1;
    public static final int STATUS_OTHER = 2;
    
    private int _id;
    private int status;
	private int inventoryLineItemId;
	private ItemDescription description;
	
	public Item(int inventoryLineItemId , ItemDescription description){
		this.setStatus(STATUS_STOCK);
		this.inventoryLineItemId = inventoryLineItemId;
		this.setDescription(description);
	}
	
	public void setInventoryLineItemId(int inventoryLineItemId){
		this.inventoryLineItemId = inventoryLineItemId;
	}
	
	public int getInventoryLineItemId (){
		return inventoryLineItemId;
	}
	
	@Override
	public boolean equals(Object o) {
		if(Item.class != o.getClass()) return false;
		return get_id()  == ((Item)o).get_id();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public ItemDescription getDescription() {
		return description;
	}

	public void setDescription(ItemDescription description) {
		this.description = description;
	}
	public String toString()
	{
		return "ID : " + _id;
	}
}