package com.android.mobilepos;


import com.example.android.navigationdrawerexample.R;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public class HistoryFragment extends Fragment {


	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_history_fragment, container, false);
        return rootView;
    }
	
	
	
    
}
