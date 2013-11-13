package com.database;

import com.core.SaleLineItem;

public interface SaleLineItemDao {
	public long insert(SaleLineItem sli);
	public int delete(SaleLineItem sli);
	public int delete(int sliId);
	public int update(SaleLineItem sli);
	public SaleLineItem[] findAll();
	public SaleLineItem findBy(int id);
}
