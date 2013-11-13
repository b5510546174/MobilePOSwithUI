package com.android.mobilepos;

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

public class CustomerDetailsMemberedFragment extends Fragment {
	private EditText txtMemberId;
	private Button btMemberOK;
	private Button btSendEmail;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.activity_cashier_continue4_customer_details_member,
				container, false);

		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {

		super.onViewCreated(view, savedInstanceState);
		txtMemberId = (EditText) getView().findViewById(R.id.txtMemberID);
		btMemberOK = (Button) getView().findViewById(R.id.btMemberOK);
		btSendEmail = (Button) getView().findViewById(R.id.btMemberSendEmail);
		btSendEmail.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				AlertDialog alertDialog1 = new AlertDialog.Builder(
						getActivity()).create();

				alertDialog1.setTitle("POS Mobile");

				alertDialog1.setMessage("E-mail Sent");

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
