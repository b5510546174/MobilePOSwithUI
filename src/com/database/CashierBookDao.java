package com.database;

import com.core.Cashier;

public interface CashierBookDao {
	public long insert(Cashier cashier);
	public int update(Cashier cashier);
	public Cashier findBy(String name);
	public Cashier deleteBy(String name);
	public Cashier[] findByContains(String name);
	public Cashier delete(int id);
	public Cashier[] findAll();
	public void close();
	public Cashier findBy(int id);
}
