package com.core.database;

import java.util.ArrayList;
import java.util.List;

import com.core.Customer;
import com.database.CustomerBookDB;

import android.content.Context;

public class CustomerBook {
	private CustomerBookDB db;
	public CustomerBook() {
	}

	public Customer getCustomer(int id) {
		return db.findBy(id);
	}

	public void addCutomer(Context con,Customer customer) {
		db = new CustomerBookDB(con);
		db.insert(customer);
		db.close();
	}

	public int getAmount(Context con) {
		db = new CustomerBookDB(con);
		int i = db.findAll().length;
		db.close();
		return i;
	}

	public boolean remove(Context con,Customer customer) {
		db = new CustomerBookDB(con);
		Customer c = db.delete(customer.getId());
		db.close();
		return c != null;
	}

	public boolean remove(Context con,int id) {
		db = new CustomerBookDB(con);
		db.delete(id);
		db.close();
		// TODO
		/*for(Customer c: customers){
			if(c.getId() == id)
				return customers.remove(c);
		}*/
		return false;
	}

	public boolean isContains(Context con,Customer customer) {
		db = new CustomerBookDB(con);
		Customer[] x = db.findAll();
		db.close();
		for (Customer c : x) {
			if (c.getId() == customer.getId())
				{
				return true;
				}
		}

		return false;
	}

}
