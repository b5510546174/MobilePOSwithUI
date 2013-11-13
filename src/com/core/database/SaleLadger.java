package com.core.database;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.core.Sale;
import com.database.CustomerBookDB;
import com.database.SaleLadgerDB;

public class SaleLadger {
	private SaleLadgerDB db;
	private List<Sale> sales;
	public SaleLadger(){
		sales = new ArrayList<Sale>();
	}
	
	public void add(Context con,Sale sale){
		Log.i("TAG SALLADGER", sale.getCustomer().getName() + "   " + sale.getDate() + "  " + sale.getPayment().getCash());
		db = new SaleLadgerDB(con);
		db.insert(sale);
		db.close();
		sales.add(sale);
	}
	
	public boolean remove(Context con,Sale sale){
		db = new SaleLadgerDB(con);
		db.delete(sale);
		db.close();
		return sales.remove(sale);
	}
	
	public Sale[] getAllSale(Context con){
		db = new SaleLadgerDB(con);
		Sale[] x = db.findAll();
		db.close();
		return x;
	}
}
