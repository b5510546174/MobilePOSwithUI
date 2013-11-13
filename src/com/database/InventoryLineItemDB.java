package com.database;

import android.content.Context;

import com.core.InventoryLineItem;

public class InventoryLineItemDB extends GenericDao implements
		InventoryLineItemBookDao {

	public InventoryLineItemDB(Context ctx ){
		super(ctx, null, null, null, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public long insert(InventoryLineItem ili) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int inventoryLineItemId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(InventoryLineItem ili) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public InventoryLineItem[] findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
