package com.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.core.Item;
import com.core.ItemDescription;

public class InventoryDB extends GenericDao implements InventoryDao{
	
	private Context context;
	
	public InventoryDB(Context context){
		super(context, GenericDao.dName, Item.TABLE_CREATE, Item.DATABASE_TABLE, Item.DATABASE_VERSION);
		this.context = context;
	}

	@Override
	public long insert(Item item) {
		ContentValues cv = new ContentValues();
        cv.put(Item.COL_STATUS, item.getStatus());
        cv.put(Item.COL_INVENTORYLINEITEM_ID, item.getInventoryLineItemId());

        cv.put(Item.COL_DESCRIPTION_ID, item.getDescription().getId());
		return super.insert(Item.DATABASE_TABLE, cv);
	}

	@Override
	public int delete(int itemId) {
		return super.delete(Item.DATABASE_TABLE,GenericDao.KEY_ID + " = " + itemId, null);
	}

	@Override
	public int update(Item item) {
		ContentValues cv = new ContentValues();
        cv.put(GenericDao.KEY_ID, item.get_id());
        cv.put(Item.COL_INVENTORYLINEITEM_ID, item.getInventoryLineItemId());
        cv.put(Item.COL_DESCRIPTION_ID, item.getDescription().getId());
        cv.put(Item.COL_STATUS, item.getStatus());
        return super.update(Item.DATABASE_TABLE, GenericDao.KEY_ID + " = " + item.get_id(), cv);
	}

	@Override
	public Item[] findAll() {
		String[] columns = new String[]{GenericDao.KEY_ID, Item.COL_STATUS , Item.COL_DESCRIPTION_ID, Item.COL_INVENTORYLINEITEM_ID};
		return getItemFromCursor(super.get(Item.DATABASE_TABLE, columns));
	}
	
	private Item[] getItemFromCursor(Cursor cursor){
		Item[] itds = null;
		if(cursor != null){
			if(cursor.moveToFirst()){
				int count = cursor.getCount();
				itds = new Item[count];
				int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
				int desId = cursor.getColumnIndex(Item.COL_DESCRIPTION_ID);
				int invId = cursor.getColumnIndex(Item.COL_INVENTORYLINEITEM_ID);
				int statusId = cursor.getColumnIndex(Item.COL_STATUS);
				for(int i = 0 ; i < count ; i++){
					itds[i] = new Item( cursor.getInt(invId) , new ItemDescriptionBookDB(context).findBy(  cursor.getInt(desId)  ));
					itds[i].setStatus(statusId);
					itds[i].set_id(cursor.getInt(_id));
					cursor.moveToNext();
				}
			}
		} 
		return itds;
	}

	@Override
	public Item[] findContainsBy(String name) {
		ItemDescriptionBookDB itdDB = new ItemDescriptionBookDB(context);
		int[] array = itdDB.findIdByContains(name);
		String[] columns = new String[]{GenericDao.KEY_ID, Item.COL_STATUS , Item.COL_DESCRIPTION_ID, Item.COL_INVENTORYLINEITEM_ID};
		Item[] items = null;
		for(int x = 0 ; x < array.length ; x++){
			Cursor cursor = super.get(Item.DATABASE_TABLE, columns , GenericDao.KEY_ID + " = " + array[x]);
			if(cursor != null){
				if(cursor.moveToFirst()){
					int count = cursor.getColumnCount(); 
					items = new Item[count];
					for(int i = 0 ; i< count ; i++){
						int desId = cursor.getColumnIndex(Item.COL_DESCRIPTION_ID);
						int invId = cursor.getColumnIndex(Item.COL_INVENTORYLINEITEM_ID);
						int statusId = cursor.getColumnIndex(Item.COL_STATUS);
						int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
						items[i].set_id(cursor.getInt(_id));
						
						items[i].setDescription( new ItemDescriptionBookDB(context).findBy(  cursor.getInt(desId)  ));
						items[i].setInventoryLineItemId(cursor.getInt(invId));
						items[i].setStatus(cursor.getInt(statusId));
						cursor.moveToNext();
					}
				}
			}
		}
		if(items == null) items = new Item[0];
		return items;
	}

	@Override
	public Item findBy(String name) {
		String[] columns = new String[]{GenericDao.KEY_ID, Item.COL_STATUS , Item.COL_DESCRIPTION_ID, Item.COL_INVENTORYLINEITEM_ID};
		Cursor cursor = super.get(Item.DATABASE_TABLE, columns);
		Item item = null;
		if(cursor != null){
			if(cursor.moveToFirst()){
				int desId = cursor.getColumnIndex(Item.COL_DESCRIPTION_ID);
				int invId = cursor.getColumnIndex(Item.COL_INVENTORYLINEITEM_ID);
				int itcId = cursor.getColumnIndex(Item.COL_STATUS);
				int _id = cursor.getColumnIndex(GenericDao.KEY_ID); 
				item = new Item( cursor.getInt(invId)  , new ItemDescriptionBookDB(context).findBy(  cursor.getInt(desId)  ));
				item.setStatus(itcId);
				item.set_id(cursor.getInt(_id));
			}
		}
		return item;
	}

	@Override
	public void close() {
		super.close();
	}

	@Override
	public int findQuantity(int descId) {
		String[] columns = new String[]{GenericDao.KEY_ID ,Item.COL_INVENTORYLINEITEM_ID};
		Cursor cursor = super.get(Item.DATABASE_TABLE, columns , Item.COL_DESCRIPTION_ID + "=" + descId);
		if(cursor != null){
			cursor.moveToFirst();
			Log.i ("TAG" , "");
			return cursor.getCount();
		}
		return 0;
	}

	@Override
	public Item[] findByBarcode(int barcode) {
		ItemDescriptionBookDao itdDB = new ItemDescriptionBookDB(context);
		ItemDescription itd = itdDB.findByBarcode(barcode);
		itdDB.close();
		String[] columns = new String[]{GenericDao.KEY_ID, Item.COL_STATUS , Item.COL_DESCRIPTION_ID, Item.COL_INVENTORYLINEITEM_ID};
		return getItemFromCursor(super.get(Item.DATABASE_TABLE, columns , Item.COL_DESCRIPTION_ID + "=" + itd._id));
	}

	@Override
	public Item[] findStatus(int status) {
		String[] columns = new String[]{GenericDao.KEY_ID, Item.COL_STATUS , Item.COL_DESCRIPTION_ID, Item.COL_INVENTORYLINEITEM_ID};
		return getItemFromCursor(super.get(Item.DATABASE_TABLE, columns , Item.COL_STATUS + "=" + status));
	}

	@Override
	public Item[] findStatus(int descId, int status) {
		return getItemFromCursor(super.getDB().rawQuery("select " + GenericDao.KEY_ID +"," + Item.COL_STATUS +"," + Item.COL_DESCRIPTION_ID + "," + Item.COL_INVENTORYLINEITEM_ID + " from " + Item.DATABASE_TABLE + " where " +  Item.COL_STATUS + " = ? AND " + Item.COL_DESCRIPTION_ID + " = ? " ,new String[]{status+"" , descId +""} ));
	}

	@Override
	public void deleteAll(int[] itemCode) {
		for(int i = 0 ; i < itemCode.length ; i++){
			delete(itemCode[i]);
		}
	}

	@Override
	public Item find(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
