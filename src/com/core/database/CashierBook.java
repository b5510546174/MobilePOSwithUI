package com.core.database;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.core.Cashier;
import com.database.CashierBookDB;

public class CashierBook {
	private List<Cashier> cashier;
	private CashierBookDB db;

	public CashierBook() {
		cashier = new ArrayList<Cashier>();
	}
	
	public Cashier getCashier(Context context , int id) {
		db = new CashierBookDB(context);
		Cashier c = db.findBy(id);
		db.close();
		return c;
	}
	
	public void addCashier(Context context , Cashier c){
		db = new CashierBookDB(context);
		db.insert(c);
		db.close();
		cashier.add(c);
	}
	
	public boolean remove(Context context , Cashier c){
		db = new CashierBookDB(context);
		db.delete(c.getId());
		db.close();
		return cashier.remove(c);
	}
	
	public boolean remove(Context context , int id){
		db = new CashierBookDB(context);
		db.delete(id);
		db.close();
		for(Cashier c: cashier){
			if(c.getId() == id){
				return cashier.remove(c);
			}
		}
		return false;
	}
	
	public boolean isContains(Context context , Cashier c){
		return cashier.contains(c);
	}
}
