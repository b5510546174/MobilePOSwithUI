package com.database;

import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.core.Customer;

public class CustomerBookDB extends GenericDao implements CustomerBookDao {
	Context context;

	public CustomerBookDB(Context context) {
		super(context, GenericDao.dName, Customer.TABLE_CREATE, Customer.DATABASE_TABLE, Customer.DATABASE_VERSION);
		this.context = context;
	}

	@Override
	public long insert(Customer customer) {
		ContentValues cv = new ContentValues();
        cv.put(Customer.COL_NAME , customer.getName());
        cv.put(Customer.COL_DATE , new Date().getTime());
        return super.insert(Customer.DATABASE_TABLE, cv);
	}

	@Override
	public Customer findBy(String name) {
		String[] columns = new String[]{GenericDao.KEY_ID , Customer.COL_NAME , Customer.COL_DATE};
		Cursor cursor = super.get(Customer.DATABASE_TABLE, columns , Customer.COL_NAME + "=" + name);
		Customer customer = null;
		if(cursor != null){
			if(cursor.moveToFirst()){
				int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
				int cus_name = cursor.getColumnIndex(Customer.COL_NAME);
				int invId = cursor.getColumnIndex(Customer.COL_DATE);
				customer = new Customer(cursor.getInt(_id), cursor.getString(cus_name), cursor.getLong(invId));
			}
		} 
		return customer;
	}
	
	@Override
	public Customer findBy(int id) {
		String[] columns = new String[]{GenericDao.KEY_ID , Customer.COL_NAME , Customer.COL_DATE};
		Cursor cursor = super.get(Customer.DATABASE_TABLE, columns , GenericDao.KEY_ID + "=" + id);
		Customer customer = null;
		if(cursor != null){
			if(cursor.moveToFirst()){
				int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
				int cus_name = cursor.getColumnIndex(Customer.COL_NAME);
				int invId = cursor.getColumnIndex(Customer.COL_DATE);
				customer = new Customer(cursor.getInt(_id), cursor.getString(cus_name), cursor.getLong(invId));
			}
		} 
		return customer;
	}

	@Override
	public Customer deleteBy(String name) {
		Customer customer = this.findBy(name);
		super.delete(Customer.DATABASE_TABLE , Customer.COL_NAME + "=" + name, null);
		return customer;
	}

	@Override
	public Customer[] findByContains(String name) {
		String[] columns = new String[]{GenericDao.KEY_ID , Customer.COL_NAME , Customer.COL_DATE};
		Cursor cursor = super.get(Customer.TABLE_CREATE ,columns ,Customer.COL_NAME + " like " + "'%" + name + "%'");
		Customer[] customers = null;
		if(cursor != null){
			if(cursor.moveToFirst()){
				int count = cursor.getCount();
				customers = new Customer[count];
				int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
				int cus_name = cursor.getColumnIndex(Customer.COL_NAME);
				int invId = cursor.getColumnIndex(Customer.COL_DATE);
				
				for(int i = 0 ; i < count ; i++){
					customers[i] = new Customer(cursor.getInt(_id), cursor.getString(cus_name), cursor.getLong(invId));
					cursor.moveToNext();
				}
			}
		} 
		return customers;
	}

	@Override
	public Customer delete(int id) {
		Customer customer = this.findBy(id);
		super.delete(Customer.DATABASE_TABLE , GenericDao.KEY_ID + "=" + id, null);
		return customer;
	}

	@Override
	public Customer[] findAll() {
		String[] columns = new String[]{GenericDao.KEY_ID , Customer.COL_NAME , Customer.COL_DATE};
		Cursor cursor = super.get(Customer.DATABASE_TABLE, columns);
		Customer[] customers = null;
		if(cursor != null){
			if(cursor.moveToFirst()){
				int count = cursor.getColumnCount(); 
				customers = new Customer[count];
				int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
				int cus_name = cursor.getColumnIndex(Customer.COL_NAME);
				int invId = cursor.getColumnIndex(Customer.COL_DATE);
				for(int i = 0 ; i< count ; i++){
					customers[i] = new Customer(cursor.getInt(_id), cursor.getString(cus_name), cursor.getLong(invId));
					cursor.moveToNext();
				}
			}
		} 
		return customers;
	}

	@Override
	public int update(Customer customer) {
		ContentValues cv = new ContentValues();
        cv.put(Customer.COL_NAME , customer.getName());
        cv.put(Customer.COL_DATE , new Date().getTime());
        return super.update(Customer.DATABASE_TABLE, customer.getId(), cv);
	}

}
