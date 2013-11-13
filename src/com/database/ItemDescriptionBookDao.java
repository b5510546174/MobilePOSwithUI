package com.database;

import com.core.ItemDescription;

public interface ItemDescriptionBookDao {
	public long insert(ItemDescription itd);
	public int update(int id, ItemDescription itd);
	public ItemDescription[] findAll();
	public ItemDescription findByBarcode(int barcode);
	public int delete(int id);
	public int delete(String name);
	public ItemDescription[] findByContains(String name);
	public int[] findIdByAll(String name);
	public int[] findIdByContains(String name);
	public int findIdBy(String name);
	public ItemDescription findBy(int id);
	public ItemDescription findBy(String name);
	public void close();
}
