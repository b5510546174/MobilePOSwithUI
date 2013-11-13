package com.core;

import com.core.database.SaleLadger;

public class Store {
	private static Store instance;
	private SaleLadger saleLedger;
	
	private Store() {
		saleLedger = new SaleLadger();
		
	}
	
	public static Store getInstance(){
		if(instance == null) instance = new Store();
		return instance;
	}
	
	public SaleLadger getSaleLedger()
	{
		return this.saleLedger;
	}
}
