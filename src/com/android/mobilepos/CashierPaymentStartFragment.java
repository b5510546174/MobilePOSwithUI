package com.android.mobilepos;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.controller.SaleController;
import com.core.Item;
import com.core.ItemDescription;
import com.core.Payment;
import com.core.database.ItemDescriptionBook;
import com.database.InventoryDB;
import com.database.ItemDescriptionBookDB;
import com.example.android.navigationdrawerexample.R;

public class CashierPaymentStartFragment extends Fragment {
	private Button buttonContinue;
	private TextView txtWishList;
	private List<String> wishList;
	private String wishListText = "";
	private SaleController saleController;
	private Payment payment;
	private EditText txtPaymentInput;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.activity_cashier_continue2_payment, container, false);

		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		saleController = SaleController.getInstance();
		saleController.createNewSale();

		super.onViewCreated(view, savedInstanceState);

		wishList = getActivity().getIntent().getStringArrayListExtra(
				"orderList");
		String displayTextAdded = "";

		buttonContinue = (Button) getView().findViewById(R.id.btContinue);
		txtWishList = (TextView) getView().findViewById(R.id.txtWishListShow);
		txtPaymentInput = (EditText) getView().findViewById(
				R.id.txtPaymentInput);
		InventoryDB inventoryDB = new InventoryDB(getActivity());
		for (String s : wishList) {
			wishListText += s + "\n";
			ItemDescriptionBookDB idbDB = new ItemDescriptionBookDB(
					getActivity().getApplicationContext());
			ItemDescription itemDes = idbDB.findBy(s);

			Item item = inventoryDB.findStatus(itemDes._id, Item.STATUS_STOCK)[0];
			saleController.addItemToSale(item);
			idbDB.close();
			if (item != null) {
				Log.i("TAG", item.getDescription().getId() + "");
				saleController.addItemToSale(item);
			} else
				Toast.makeText(getActivity().getApplicationContext(), "NULL",
						Toast.LENGTH_SHORT).show();
			if (item.getDescription() == null)
				Toast.makeText(getActivity().getApplicationContext(),
						"description is null", Toast.LENGTH_SHORT).show();

		}
		Toast.makeText(getActivity().getApplicationContext(), displayTextAdded,
				Toast.LENGTH_SHORT).show();
		txtWishList.setText(wishListText);

		buttonContinue.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ViewPager viewPager = (ViewPager) getActivity().findViewById(
						R.id.pager);
				payment = new Payment(Double.parseDouble(txtPaymentInput
						.getText().toString()), saleController
						.getTotalSalePrice());
				saleController.getSale().setPayment(payment);
				viewPager.setCurrentItem((viewPager.getCurrentItem() + 1));

			}
		});
	}

}
