package com.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.core.ItemDescription;

public class ItemDescriptionBookDB extends GenericDao implements ItemDescriptionBookDao {
	
	public ItemDescriptionBookDB(Context context) {
		super(context, GenericDao.dName, ItemDescription.TABLE_CREATE, ItemDescription.DATABASE_TABLE, ItemDescription.DATABASE_VERSION);
	}

	@Override
	public long insert(ItemDescription itd) {
		ContentValues cv = new ContentValues();
		cv.put(ItemDescription.COL_NAME, itd.getName());
		cv.put(ItemDescription.COL_BARCODE, itd.getBarcode());
		cv.put(ItemDescription.COL_DESCRIPTION, itd.getItemDescription());
		cv.put(ItemDescription.COL_PRICE, itd.getPrice());
		long tmp = super.insert(ItemDescription.DATABASE_TABLE, cv);
		itd.setId((int)tmp);
		return tmp;
	}

	@Override
	public int update(int id, ItemDescription itd) {
		ContentValues cv = new ContentValues();
		cv.put(ItemDescription.COL_NAME, itd.getName());
		cv.put(ItemDescription.COL_BARCODE, itd.getBarcode());
		cv.put(ItemDescription.COL_DESCRIPTION, itd.getItemDescription());
		cv.put(ItemDescription.COL_PRICE, itd.getPrice());
        return super.update(ItemDescription.DATABASE_TABLE, GenericDao.KEY_ID + "=" + id, cv);
	}

	@Override
	public ItemDescription[] findAll() {
		String[] columns = new String[]{ GenericDao.KEY_ID , ItemDescription.COL_DESCRIPTION ,ItemDescription.COL_BARCODE, ItemDescription.COL_NAME , ItemDescription.COL_PRICE};
		return getItemDescriptionsFromCursor(super.get(ItemDescription.DATABASE_TABLE, columns));
	}
	
	private ItemDescription[] getItemDescriptionsFromCursor(Cursor cursor){
		ItemDescription[] itds = null;
		if(cursor != null){
			if(cursor.moveToFirst()){
				int count = cursor.getCount();
				itds = new ItemDescription[count];
				for(int i = 0 ; i < count ; i++){
					int barcode = cursor.getColumnIndex(ItemDescription.COL_BARCODE);
					int desc = cursor.getColumnIndex(ItemDescription.COL_DESCRIPTION);
					int _name = cursor.getColumnIndex(ItemDescription.COL_NAME);
					int price = cursor.getColumnIndex(ItemDescription.COL_PRICE);
					int _id = cursor.getColumnIndex(GenericDao.KEY_ID); 
					itds[i] = new ItemDescription(cursor.getString(_name) , cursor.getString(desc) , cursor.getFloat(price) , cursor.getInt(barcode));
					itds[i]._id = cursor.getInt(_id);
					cursor.moveToNext();
				}
			}
		} else itds = new ItemDescription[0];
		return itds;
	}

	@Override
	public int delete(int id) {
		return super.delete(ItemDescription.DATABASE_TABLE,(long)id);
	}
	
	@Override
	public ItemDescription[] findByContains(String name) {
		String[] cols = new String[]{ GenericDao.KEY_ID , ItemDescription.COL_DESCRIPTION ,ItemDescription.COL_BARCODE, ItemDescription.COL_NAME , ItemDescription.COL_PRICE};
		ItemDescription[] itds = null;
		return getItemDescriptionsFromCursor(super.get(ItemDescription.DATABASE_TABLE, cols , ItemDescription.COL_NAME + " like " + "'%" + name + "%'" ));
	}

	@Override
	public int[] findIdByAll(String name) {
		String[] columns = new String[]{GenericDao.KEY_ID};
		Cursor cursor = super.get(ItemDescription.DATABASE_TABLE, columns);
		int[] itds = new int[0];
		if(cursor != null){
			if(cursor.moveToFirst()){
				int count = cursor.getCount();
				itds = new int[count];
				for(int i = 0 ; i < count ; i++){
					int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
					itds[i] = cursor.getInt(_id);
					cursor.moveToNext();
				}
			}
		} 
		return itds;
	}

	@Override
	public int findIdBy(String name) {
		String[] columns = new String[]{GenericDao.KEY_ID};
		Cursor cursor = super.get(ItemDescription.DATABASE_TABLE, columns);
		cursor = super.get(ItemDescription.DATABASE_TABLE , columns , ItemDescription.COL_NAME + " like " + "'" +name +"'");
		int itds = 0;
		if(cursor != null){
			if(cursor.moveToFirst()){
				int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
				itds = cursor.getInt(_id);
				
			}
		}
		return itds;
	}

	@Override
	public ItemDescription findBy(int id) {
		Cursor cursor;
		String[] cols = new String[]{ GenericDao.KEY_ID , ItemDescription.COL_DESCRIPTION ,ItemDescription.COL_BARCODE, ItemDescription.COL_NAME , ItemDescription.COL_PRICE};
		cursor = super.get(ItemDescription.DATABASE_TABLE , cols , GenericDao.KEY_ID + "=" + id);
		ItemDescription itd = null;
		if(cursor != null){
			if(cursor.moveToFirst()){
				int barcode = cursor.getColumnIndex(ItemDescription.COL_BARCODE);
				int desc = cursor.getColumnIndex(ItemDescription.COL_DESCRIPTION);
				int _name = cursor.getColumnIndex(ItemDescription.COL_NAME);
				int price = cursor.getColumnIndex(ItemDescription.COL_PRICE);
				int _id = cursor.getColumnIndex(GenericDao.KEY_ID); 
				
				itd= new ItemDescription(cursor.getString(_name) , cursor.getString(desc) , cursor.getFloat(price) , cursor.getInt(barcode));
				itd._id = cursor.getInt(_id);
			}
		}
		return itd;
	}

	@Override
	public ItemDescription findBy(String name) {
		Cursor cursor;
		String[] cols = new String[]{ GenericDao.KEY_ID , ItemDescription.COL_DESCRIPTION ,ItemDescription.COL_BARCODE, ItemDescription.COL_NAME , ItemDescription.COL_PRICE};
		cursor = super.get(ItemDescription.DATABASE_TABLE , cols , ItemDescription.COL_NAME + " like " + "'" +name +"'");
		ItemDescription itd = null;
		if(cursor != null){
			if(cursor.moveToFirst()){
				int barcode = cursor.getColumnIndex(ItemDescription.COL_BARCODE);
				int desc = cursor.getColumnIndex(ItemDescription.COL_DESCRIPTION);
				int _name = cursor.getColumnIndex(ItemDescription.COL_NAME);
				int price = cursor.getColumnIndex(ItemDescription.COL_PRICE);
				int _id = cursor.getColumnIndex(GenericDao.KEY_ID); 
				itd= new ItemDescription(cursor.getString(_name) , cursor.getString(desc) , cursor.getFloat(price) , cursor.getInt(barcode));
				Log.i("TAGGG" , cursor.getInt(_id)+"");
				itd._id = cursor.getInt(_id);
			}
		}
		return itd;
	}

	@Override
	public int delete(String name) {
		return super.delete(ItemDescription.DATABASE_TABLE,ItemDescription.COL_NAME + " like " + "'" + name + "'" , null);
	}

	public void close() {
		super.close();
	}

	@Override
	public int[] findIdByContains(String name) {
		String[] cols = new String[]{ GenericDao.KEY_ID };
		Cursor cursor =  super.get(ItemDescription.DATABASE_TABLE, cols , ItemDescription.COL_NAME + " like " + "'%" + name + "%'" );
		int[] arr = new int[0];
		if(cursor != null){
			if(cursor.moveToFirst()){
				int count = cursor.getCount();
				arr = new int[count];
				for(int i = 0 ; i < count ; i++){
					int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
					arr[i] = cursor.getInt(_id);
					cursor.moveToNext();
				}
			}
		}
		return arr;
	}

	@Override
	public ItemDescription findByBarcode(int barcode) {
		Cursor cursor;
		String[] cols = new String[]{ GenericDao.KEY_ID , ItemDescription.COL_DESCRIPTION ,ItemDescription.COL_BARCODE, ItemDescription.COL_NAME , ItemDescription.COL_PRICE};
		cursor = super.get(ItemDescription.DATABASE_TABLE , cols , ItemDescription.COL_BARCODE + "=" + barcode);
		ItemDescription itd = null;
		if(cursor != null){
			if(cursor.moveToFirst()){
				int _barcode = cursor.getColumnIndex(ItemDescription.COL_BARCODE);
				int desc = cursor.getColumnIndex(ItemDescription.COL_DESCRIPTION);
				int _name = cursor.getColumnIndex(ItemDescription.COL_NAME);
				int price = cursor.getColumnIndex(ItemDescription.COL_PRICE);
				int _id = cursor.getColumnIndex(GenericDao.KEY_ID); 
				
				itd= new ItemDescription(cursor.getString(_name) , cursor.getString(desc) , cursor.getFloat(price) , cursor.getInt(_barcode));
				itd._id = cursor.getInt(_id);
			}
		}
		return itd;
	}

}
