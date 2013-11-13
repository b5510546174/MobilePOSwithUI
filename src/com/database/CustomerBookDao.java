package com.database;

import com.core.Customer;

public interface CustomerBookDao {
	public long insert(Customer customer);
	public int update(Customer customer);
	public Customer findBy(String name);
	public Customer deleteBy(String name);
	public Customer[] findByContains(String name);
	public Customer delete(int id);
	public Customer[] findAll();
	public void close();
	public Customer findBy(int id);
}