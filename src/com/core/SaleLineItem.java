package com.core;

import java.util.ArrayList;

public class SaleLineItem {
	public static final String DATABASE_TABLE = "SaleLineItems";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
        "create table if not exists SaleLineItems (_id integer primary key autoincrement , items text not null);";
   
    public static final String COL_ITEMS = "items";
    
    private int id;
    private ArrayList<Item> items;
    
    public SaleLineItem(){
    	items = new ArrayList<Item>();
    }
    public int getAmount()
    {
    	return items.size();
    }
    
    public void insertItem(Item item){
    	items.add(item);
    }
    
    public Item[] getItems()
    {
    	Item[] itemsTmp = new Item[items.size()];
    	for(int i = 0 ; i < items.size() ; i++){
    		itemsTmp[i] = items.get(i);
    	}
    	return itemsTmp;
    }
    
    public void addItem(Item item)
    {
    	items.add(item);
    }
    
    public String getItemsString(){
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0 ; i < items.size() ; i++)
    		sb.append(items.get(i).get_id()).append(" ");
    	return sb.toString();
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
