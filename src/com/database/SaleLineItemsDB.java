package com.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.core.Customer;
import com.core.Item;
import com.core.ItemDescription;
import com.core.Sale;
import com.core.SaleLineItem;

public class SaleLineItemsDB extends GenericDao implements SaleLineItemDao {

	public SaleLineItemsDB(Context ctx){
		super(ctx, GenericDao.dName, SaleLineItem.TABLE_CREATE, SaleLineItem.DATABASE_TABLE, SaleLineItem.DATABASE_VERSION);
	}
	
	@Override
	public long insert(SaleLineItem sli) {
		
		Cursor cursor = super.getDB().query(SaleLineItem.DATABASE_TABLE, null, "_id=(SELECT MAX(_id))", null, null, null, null);
		cursor.moveToFirst();
		int cursorId  = cursor.getColumnIndex(GenericDao.KEY_ID);
		int id = cursor.getInt(cursorId)+1;
		Item[] items = sli.getItems();
		for(int i = 0 ; i < items.length ; i++){
			ContentValues cv = new ContentValues();
			cv.put(GenericDao.KEY_ID , id);
			cv.put(SaleLineItem.COL_ITEM_ID , items[i].get_id());
		}
        return id;
	}

	@Override
	public int delete(SaleLineItem sli) {
		return delete(sli.getId());
	}

	@Override
	public int delete(int sliId) {
		return super.delete(SaleLineItem.DATABASE_TABLE, sliId);
	}

	@Override
	public int update(SaleLineItem sli) {
		delete(sli);
		return (int)insert(sli);
	}

	@Override
	public SaleLineItem[] findAll() {
		String[] columns = new String[]{ GenericDao.KEY_ID , SaleLineItem.COL_ITEM_ID };
		Cursor cursor = super.get(ItemDescription.DATABASE_TABLE, columns);
		HashMap<Integer, SaleLineItem> slis = new HashMap<Integer, SaleLineItem>();
		if(cursor != null){
			int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
			int itemsId = cursor.getColumnIndex(SaleLineItem.COL_ITEM_ID);
			if(cursor.moveToFirst()){
				int count = cursor.getColumnCount();
				for(int i = 0 ; i< count ; i++) {
					if(!slis.containsKey(cursor.getInt(_id))){
						slis.put(cursor.getInt(_id) , new SaleLineItem());
						slis.get(cursor.getInt(_id)).setId(cursor.getInt(_id));
					}
					slis.get(cursor.getInt(_id)).addItem( new InventoryDB( getContext() ).find( cursor.getInt(itemsId)) );
					cursor.moveToNext();
				}
			}
		}
		ArrayList<SaleLineItem> slisReturn = new ArrayList<SaleLineItem>();
		for(Entry<Integer, SaleLineItem> n : slis.entrySet()){
			slisReturn.add(n.getValue());
		}
		return slisReturn.toArray(  new SaleLineItem[slis.size()] );
	}

	@Override
	public SaleLineItem findBy(int id) {
		String[] columns = new String[]{ GenericDao.KEY_ID , SaleLineItem.COL_ITEM_ID };
		Cursor cursor = super.get(ItemDescription.DATABASE_TABLE, columns , id);
		SaleLineItem sli = null;
		if(cursor != null){
			int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
			int itemsId = cursor.getColumnIndex(SaleLineItem.COL_ITEM_ID);
			if(cursor.moveToFirst()){
				int count = cursor.getColumnCount();
				sli = new SaleLineItem();
				sli.setId(cursor.getInt(_id));
				for(int i = 0 ; i< count ; i++) {
					sli.addItem( new InventoryDB( getContext() ).find( cursor.getInt(itemsId)) );
					cursor.moveToNext();
				}
			}
		}
		return sli;
	}

}
