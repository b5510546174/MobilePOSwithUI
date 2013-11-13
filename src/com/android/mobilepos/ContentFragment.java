package com.android.mobilepos;

import java.util.Locale;

import android.os.Bundle;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class ContentFragment extends  Fragment {
	
	
	private ActionBar actionBar;
	private String[] navigationText = new  String[]{"History","Cashier","Stock","Search"};
	@Override
	public void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		
		actionBar = getActivity().getActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
	    
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

	}

	

}
