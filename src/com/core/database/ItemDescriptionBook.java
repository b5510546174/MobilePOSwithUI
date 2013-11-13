package com.core.database;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.core.ItemDescription;
import com.database.ItemDescriptionBookDB;

public class ItemDescriptionBook {
	private ItemDescriptionBookDB db;
	private List<ItemDescription> itemDescription;
	public ItemDescriptionBook() {
		itemDescription = new ArrayList<ItemDescription>();
	}

	public ItemDescription get(Context con,int id) {
		db = new ItemDescriptionBookDB(con);
		ItemDescription x = db.findBy(id);
		db.close();
		return x;
	}

	public void add(Context con,ItemDescription item) {
		db = new ItemDescriptionBookDB(con);
		db.insert(item);
		db.close();
		itemDescription.add(item);
	}

	public int getAmount(Context con) {
		db = new ItemDescriptionBookDB(con);
		int x = db.findAll().length;
		db.close();
		return x;
	}

	public boolean remove(Context con,int id) {
		db = new ItemDescriptionBookDB(con);
		db.delete(id);
		db.close();
		for (ItemDescription i : itemDescription) {
			if (i.getId() == id)
				return itemDescription.remove(i);
		}
		return false;
	}

}
