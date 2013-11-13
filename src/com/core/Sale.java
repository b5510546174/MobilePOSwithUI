package com.core;

import java.util.ArrayList;
import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Sale {
	public static final String DATABASE_TABLE = "SaleLadger";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
        "create table if not exists SaleLadger (_id integer primary key autoincrement , customer_id integer, payment text not null, date long not null, sale_line_itemsId integer not null);";
   
    public static final String COL_CUSTOMER_ID = "customer_id";
    public static final String COL_DATE = "date";
    public static final String COL_SALE_LINE_ITEM_ID = "sale_line_itemsId";
    public static final String COL_PAYMENT = "payment";
    
    private int id;
    private Customer customer;
    private long date;
    private SaleLineItem saleLineItem;
    private Payment payment = new Payment(0, 0);
    
    public Sale(Customer customer){
    	//setSaleLineItems(new ArrayList<SaleLineItem>());
    	this.setCustomer(customer);
    }
    
    public Sale(){
    	//setSaleLineItems(new ArrayList<SaleLineItem>());
    }

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public double getTotalPrice()
	{
		return saleLineItem.getTotal();
	}

	public long getDateAsLong() {
		return date;
	}
	
	public Date getDate() {
		Date d = new Date();
		d.setTime(date);
		return d;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Payment getPayment() {
		return payment;
	}


	
	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public SaleLineItem getSaleLineItem() {
		return saleLineItem;
	}

	public void setSaleLineItem(SaleLineItem saleLineItem) {
		this.saleLineItem = saleLineItem;
	}
}
