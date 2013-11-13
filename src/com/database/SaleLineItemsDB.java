package com.database;

import java.util.Date;

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
		ContentValues cv = new ContentValues();
        cv.put(SaleLineItem.COL_ITEMS , sli.getItemsString());
        return super.insert(SaleLineItem.DATABASE_TABLE, cv);
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
		ContentValues cv = new ContentValues();
        cv.put(SaleLineItem.COL_ITEMS , sli.getItemsString());
        return super.update(SaleLineItem.DATABASE_TABLE, sli.getId() ,cv);
	}

	@Override
	public SaleLineItem[] findAll() {
		String[] columns = new String[]{ GenericDao.KEY_ID , SaleLineItem.COL_ITEMS };
		Cursor cursor = super.get(ItemDescription.DATABASE_TABLE, columns);
		SaleLineItem[] slis = null;
		if(cursor != null){
			if(cursor.moveToFirst()){
				int count = cursor.getColumnCount(); 
				slis = new SaleLineItem[count];
				for(int i = 0 ; i< count ; i++) {
					int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
					int itemsId = cursor.getColumnIndex(SaleLineItem.COL_ITEMS);
					String[] strs = cursor.getString(itemsId).split(" ");
					slis[i] = new SaleLineItem();
					slis[i].setId( cursor.getInt(_id) );
					for(int j = 0 ; j < strs.length ; j++){
						slis[i].addItem( new InventoryDB( getContext() ).find( Integer.parseInt(strs[j])  ) );
					}
					cursor.moveToNext();
				}
			}
		}
		return slis;
	}

	@Override
	public SaleLineItem findBy(int id) {
		String[] columns = new String[]{ GenericDao.KEY_ID , SaleLineItem.COL_ITEMS };
		Cursor cursor = super.get(ItemDescription.DATABASE_TABLE, columns , id);
		SaleLineItem sli = null;
		if(cursor != null){
			if(cursor.moveToFirst()){
				sli = new SaleLineItem();
				int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
				int itemsId = cursor.getColumnIndex(SaleLineItem.COL_ITEMS);
				String[] strs = cursor.getString(itemsId).split(" ");
				sli = new SaleLineItem();
				sli.setId( cursor.getInt(_id) );
				for(int j = 0 ; j < strs.length ; j++){
					sli.addItem( new InventoryDB( getContext() ).find( Integer.parseInt(strs[j])  ) );
				}
			}
		}
		return sli;
	}

}
