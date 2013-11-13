package com.android.mobilepos;


import com.controller.SaleController;
import com.example.android.navigationdrawerexample.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class CashierPaymentFinishedFragment extends Fragment
	{
	private Button btMember;
	private Button btNonMember;
	private ViewPager viewPager;
	private SaleController saleController;
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.activity_cashier_continue3_finished_payment, container, false);
			saleController = SaleController.getInstance();
			
			return rootView;
		}

		@Override
		public void onViewCreated(View view, Bundle savedInstanceState) {
			
			super.onViewCreated(view, savedInstanceState);
			viewPager = (ViewPager) getActivity().findViewById(R.id.pager);
			
			
			
			btMember = (Button) getView().findViewById(R.id.btMemberSendEmail);
			btNonMember = (Button) getView().findViewById(R.id.btNonMember);
			btMember.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					viewPager.setCurrentItem(2);
				}
			});
			btNonMember.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					viewPager.setCurrentItem(3);
				}
			});
			
		}
	}