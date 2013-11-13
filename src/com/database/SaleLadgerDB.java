package com.database;

import java.util.ArrayList;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.core.Customer;
import com.core.Item;
import com.core.Payment;
import com.core.Sale;

public class SaleLadgerDB extends GenericDao implements SaleLadgerDao{

	public SaleLadgerDB(Context context){
		super(context, GenericDao.dName, Sale.TABLE_CREATE, Sale.DATABASE_TABLE, Sale.DATABASE_VERSION);
	}
	
	@Override
	public long insert(Sale sale) {
		Log.i("TAG SALLADGER" , "1");
		ContentValues cv = new ContentValues();
        cv.put(Sale.COL_CUSTOMER_ID , sale.getCustomer().getId());
        cv.put(Sale.COL_DATE , sale.getDateAsLong()); 
        cv.put(Sale.COL_SALE_LINE_ITEM_ID , sale.getSaleLineItem().getId()); 
        cv.put(Sale.COL_PAYMENT , sale.getPayment().toString());
        int tmp = (int)super.insert(Sale.DATABASE_TABLE, cv);
        sale.setId(tmp);
        return tmp;
	}

	@Override
	public int delete(Sale sale) {
		return super.delete(Sale.DATABASE_TABLE, sale.getId());
	}

	@Override
	public int delete(int saleId) {
		return super.delete(Sale.DATABASE_TABLE, saleId);
	}

	@Override
	public int update(Sale sale) {
		ContentValues cv = new ContentValues();
        cv.put(Sale.COL_CUSTOMER_ID , sale.getCustomer().getId());
        cv.put(Sale.COL_DATE , sale.getDateAsLong());
        cv.put(Sale.COL_SALE_LINE_ITEM_ID , sale.getSaleLineItem().getId());
        cv.put(Sale.COL_PAYMENT , sale.getPayment().toString());
		return super.update(Sale.DATABASE_TABLE, sale.getId(), cv);
	}

	@Override
	public Sale[] findAll() {
		String[] columns = new String[]{GenericDao.KEY_ID, Sale.COL_CUSTOMER_ID ,  Sale.COL_DATE , Sale.COL_SALE_LINE_ITEM_ID , Sale.COL_PAYMENT};
		Cursor cursor = super.get(Item.DATABASE_TABLE, columns);
		Sale[] items = null;
		if(cursor != null){
			if(cursor.moveToFirst()){
				int count = cursor.getColumnCount(); 
				items = new Sale[count];
				for(int i = 0 ; i< count ; i++) {
					int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
					int cusId = cursor.getColumnIndex(Sale.COL_CUSTOMER_ID );
					int date = cursor.getColumnIndex(Sale.COL_DATE);
					int sliID = cursor.getColumnIndex(Sale.COL_SALE_LINE_ITEM_ID);
					int pay = cursor.getColumnIndex(Sale.COL_PAYMENT);
					items[i] = new Sale( new CustomerBookDB(getContext()).findBy(cursor.getInt(cusId)));
					items[i].setId(cursor.getInt(_id));
					items[i].setDate(cursor.getLong(date));
					items[i].setPayment(new Payment(cursor.getString(pay)));
					int saleLineItems = cursor.getInt(sliID);

						items[i].setSaleLineItem( new SaleLineItemsDB(getContext()).findBy( saleLineItems) );
					
					cursor.moveToNext();
				}
			}
		}
		return items;
	}

	@Override
	public Sale findBy(int id) {
		String[] columns = new String[]{GenericDao.KEY_ID, Sale.COL_CUSTOMER_ID ,  Sale.COL_DATE , Sale.COL_SALE_LINE_ITEM_ID , Sale.COL_PAYMENT};
		Cursor cursor = super.get(Sale.DATABASE_TABLE, columns , GenericDao.KEY_ID + "=" + id);
		Sale sale = null;
		if(cursor != null){
			if(cursor.moveToFirst()){
				int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
				int cusId = cursor.getColumnIndex(Sale.COL_CUSTOMER_ID );
				int date = cursor.getColumnIndex(Sale.COL_DATE);
				int slis = cursor.getColumnIndex(Sale.COL_SALE_LINE_ITEM_ID);
				int pay = cursor.getColumnIndex(Sale.COL_PAYMENT);
				sale = new Sale( new CustomerBookDB(getContext()).findBy(cursor.getInt(cusId)));
				sale.setId(cursor.getInt(_id));
				sale.setDate(cursor.getLong(date));
				sale.setPayment(new Payment(cursor.getString(pay)));
				int saleLineItems = cursor.getInt(slis);
					sale.setSaleLineItem( new SaleLineItemsDB(getContext()).findBy( saleLineItems) );
				cursor.moveToNext();
			}
		} 
		return sale;
	}

}
