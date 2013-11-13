package com.android.mobilepos;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class CashierPagerAdapter extends FragmentStatePagerAdapter {
	private List<Fragment> fragmentList = new ArrayList<Fragment>();
	private Fragment startPaymentFragment = new CashierPaymentStartFragment();
	private Fragment finishedPaymentFragment = new CashierPaymentFinishedFragment();
	private Fragment memberDetailsFragment = new CustomerDetailsMemberedFragment();
	private Fragment nonMemberDetailsFragment = new CustomerDetailsNonMemberFragment();
	
	private List<String> fragmentName = new ArrayList<String>();
	
	


	public CashierPagerAdapter(FragmentManager fm) {
		super(fm);
		fragmentList.add(startPaymentFragment);
		fragmentList.add(finishedPaymentFragment);
		fragmentList.add(memberDetailsFragment);
		fragmentList.add(nonMemberDetailsFragment);
		
		fragmentName.add("Payment");
		fragmentName.add("Finished");
		fragmentName.add("Member details");
		fragmentName.add("Non member details");
		
	}

	@Override
	public Fragment getItem(int i) {

		return fragmentList.get(i);
	}

	@Override
	public int getCount() {
		return fragmentList.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {

		return fragmentName.get(position);
	}
}
