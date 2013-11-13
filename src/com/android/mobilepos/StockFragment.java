package com.android.mobilepos;
import com.example.android.navigationdrawerexample.R;
import com.ui.extended.StoreManagerActivity;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class StockFragment extends Fragment{
	
	private Button btGoManagement;

	 @Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		btGoManagement = (Button) getView().findViewById(R.id.btStoreManagement);
		btGoManagement.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent  = new Intent(getActivity().getApplicationContext(), StoreManagerActivity.class );
				startActivity(intent);
				
			}
		});
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        View rootView = inflater.inflate(R.layout.activity_stock_fragment, container, false);
	        return rootView;
	    }
    
}
