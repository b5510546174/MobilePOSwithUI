package com.database;

import com.core.Sale;

public interface SaleLadgerDao {
	public long insert(Sale sale);
	public int delete(Sale sale);
	public int delete(int saleId);
	public int update(Sale item);
	public Sale[] findAll();
	public Sale findBy(int id);
}
