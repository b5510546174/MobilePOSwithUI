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
        "create table if not exists SaleLadger (_id integer primary key autoincrement , customer_id integer, payment text not null, date long not null, sale_line_items text not null);";
   
    public static final String COL_CUSTOMER_ID = "customer_id";
    public static final String COL_DATE = "date";
    public static final String COL_SALE_LINE_ITEMS = "sale_line_items";
    public static final String COL_PAYMENT = "payment";
    
    private int id;
    private Customer customer;
    private long date;
    private ArrayList<SaleLineItem> saleLineItems;
    private Payment payment = new Payment(0, 0);
    
    public Sale(Customer customer){
    	setSaleLineItems(new ArrayList<SaleLineItem>());
    	this.setCustomer(customer);
    }
    
    public Sale(){
    	setSaleLineItems(new ArrayList<SaleLineItem>());
    }
    
    public boolean addSaleLineItem(SaleLineItem sli){
    	return getSaleLineItems().add(sli);
    }

    public String getSaleLineItemString(){
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0 ; i < getSaleLineItems().size() ; i++)
    		sb.append(getSaleLineItems().get(i).getId()).append(" ");
    	return sb.toString();
    }

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public double getTotalPrice()
	{
		double total = 0;
		for(SaleLineItem s : saleLineItems)
		{
			Item[] items = s.getItems();
			for(int i=0;i<items.length;i++)
			{
				total+= items[i].getDescription().getPrice();
			}
		}
		return total;
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

	public ArrayList<SaleLineItem> getSaleLineItems() {
		return saleLineItems;
	}

	public void setSaleLineItems(ArrayList<SaleLineItem> saleLineItems) {
		this.saleLineItems = saleLineItems;
	}
}
