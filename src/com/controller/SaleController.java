package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;

import com.core.Item;
import com.core.Sale;
import com.core.SaleLineItem;
import com.core.Store;
import com.core.database.ItemDescriptionBook;
import com.database.InventoryDB;
import com.database.ItemDescriptionBookDB;

public class SaleController {
	private Store store;
	private Sale sale = new Sale();
	private List<SaleLineItem> saleLineItems;
	private List<Item> saleItems;
	private InventoryDB inventoryDB;
	private static SaleController saleController;

	public SaleController() {
		this.store = Store.getInstance();
		saleItems = new ArrayList<Item>();
		saleLineItems = new ArrayList<SaleLineItem>();

	}

	public static SaleController getInstance() {
		if (saleController == null)
			saleController = new SaleController();
		return saleController;
	}

	public Sale getSale() {
		return this.sale;
	}

	public String getLineItemsString() {
		return saleLineItems.toString();
	}

	public List<Item> getItems() {
		List<Item> ret = new ArrayList<Item>();

		List<SaleLineItem> saleLine = sale.getSaleLineItems();
		for (SaleLineItem s : saleLine) {
			Item[] i = s.getItems();
			for (Item a : i) {
				ret.add(a);
			}
		}
		return ret;
	}

	public void addItemToSale(Item item) {
		for (SaleLineItem s : saleLineItems) {
			if (s.getId() == item.get_id())
				s.addItem(item);
			return;
		}
		SaleLineItem add = new SaleLineItem();
		add.addItem(item);
		saleLineItems.add(add);
	}

	public int checkAmount(Context context, int itemID) {
		inventoryDB = new InventoryDB(context);
		Item[] items = inventoryDB.findStatus(
				new ItemDescriptionBookDB(context).findBy(itemID).getId(),
				Item.STATUS_STOCK);

		inventoryDB.close();
		return items.length;
	}

	public void createNewSale() {
		this.sale = new Sale();
	}

	public void saveSaleToSaleLedger(Context con) {
		store.getSaleLedger().add(con, sale);
	}

	public List<Item> getAllSaleItems() {
		return saleItems;
	}

	public Item[] getAllSaleLineItems() {
		return (Item[]) sale.getSaleLineItems().toArray();
	}

	public double getTotalSalePrice() {
		return sale.getTotalPrice();
	}

	public void removeFromDatabase(Context context) {
		inventoryDB = new InventoryDB(context);
		for (SaleLineItem s : saleLineItems) {
			Item[] items = s.getItems();
			int[] itemsID = new int[items.length];

			for (int i = 0; i < items.length; i++) {
				itemsID[i] = items[i].get_id();
			}
			inventoryDB.deleteAll(itemsID);
		}
		inventoryDB.close();

	}

}
