package com.database;

import android.content.Context;

import com.core.Cashier;

public class CashierBookDB extends GenericDao implements CashierBookDao {

	public CashierBookDB(Context ctx){
		super(ctx, null, null, null , 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public long insert(Cashier cashier) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Cashier findBy(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cashier deleteBy(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cashier[] findByContains(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cashier delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cashier[] findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cashier findBy(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Cashier cashier) {
		// TODO Auto-generated method stub
		return 1;
	}

}
