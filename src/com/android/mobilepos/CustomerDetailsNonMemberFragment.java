package com.android.mobilepos;

import com.controller.SaleController;
import com.core.Customer;
import com.database.CustomerBookDB;
import com.example.android.navigationdrawerexample.R;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CustomerDetailsNonMemberFragment extends Fragment {

	private EditText txtNonMemberName;
	private EditText txtNonMemberEmail;
	private Button btNonMemberOK;
	private Customer customer;

	/**
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.activity_cashier_continue5_customer_nonmember,
				container, false);

		return rootView;
	}
	
	/**
	 * @see android.support.v4.app.Fragment#onViewCreated(android.view.View, android.os.Bundle)
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {

		super.onViewCreated(view, savedInstanceState);
		txtNonMemberEmail = (EditText) getView().findViewById(
				R.id.txtNonMemberEmail);
		txtNonMemberName = (EditText) getView().findViewById(
				R.id.txtNonMemberName);
		btNonMemberOK = (Button) getView().findViewById(R.id.btNonMemberOK);

		btNonMemberOK.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				AlertDialog alertDialog1 = new AlertDialog.Builder(getActivity()).create();

				alertDialog1.setTitle("POS Mobile");
				
				new CustomerBookDB(getActivity().getApplicationContext()).close();
				
				alertDialog1.setMessage("E-mail Sent");
				customer = new Customer(txtNonMemberName.getText().toString());
				SaleController saleController = SaleController.getInstance();
				saleController.getSale().setCustomer(customer);
				saleController.saveSaleToSaleLedger(getActivity().getApplicationContext());
				saleController.removeFromDatabase(getActivity().getApplicationContext());

				alertDialog1.setButton("OK",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								getActivity().finish();
							}
						});

				alertDialog1.show();
			}
		});

	}

}
