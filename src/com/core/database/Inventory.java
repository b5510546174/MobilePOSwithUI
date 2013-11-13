package com.core.database;

import android.content.Context;

import com.database.InventoryDB;

public class Inventory {
	InventoryDB inventoryDB;
	 public boolean removeAll(Context con,int[] IDs)
	 {
		 inventoryDB = new InventoryDB(con);
		 inventoryDB.deleteAll(IDs);
		 inventoryDB.close();
		return true;
		 
	 }
}
