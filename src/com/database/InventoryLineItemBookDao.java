package com.database;

import com.core.InventoryLineItem;

public interface InventoryLineItemBookDao {
	public long insert(InventoryLineItem ili);
	public int delete(int inventoryLineItemId);
	public int update(InventoryLineItem ili);
	public InventoryLineItem[] findAll();
}