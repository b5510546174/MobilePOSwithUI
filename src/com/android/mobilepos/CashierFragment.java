package com.android.mobilepos;

import java.util.ArrayList;
import java.util.ResourceBundle.Control;

import com.controller.SaleController;
import com.core.Item;
import com.core.ItemDescription;
import com.core.database.Inventory;
import com.database.GenericDao;
import com.database.InventoryDB;
import com.database.ItemDescriptionBookDB;
import com.example.android.navigationdrawerexample.R;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class CashierFragment extends Fragment {
	private ListView itemListView;
	private Button nextButton;
	private Button scanWithBarcodeButton;
	private EditText txtID;
	private Button buttonOK;
	private ArrayList<String> list = new ArrayList<String>();
	private ArrayList<String> sendList = new ArrayList<String>();
	private SaleController saleController;
	ArrayAdapter<String> adapter;
	private ArrayList<String> displayNames = new ArrayList<String>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = inflater.inflate(
				R.layout.activity_cashier_fragment, container, false);
		list = new ArrayList<String>();
		saleController = SaleController.getInstance();

		return rootView;

	}
	
	public void addDataToDB()
	{
		new GenericDao(getActivity().getApplicationContext(), GenericDao.dName, ItemDescription.TABLE_CREATE, ItemDescription.DATABASE_TABLE, 1).delete(ItemDescription.DATABASE_TABLE);
		new GenericDao(getActivity().getApplicationContext(), GenericDao.dName, Item.TABLE_CREATE, Item.DATABASE_TABLE, 1).delete(Item.DATABASE_TABLE);
		InventoryDB inventoryDB = new InventoryDB(getActivity().getApplicationContext());
		ItemDescriptionBookDB dd = new ItemDescriptionBookDB(getActivity().getApplicationContext());
		ItemDescription i1 = new ItemDescription("Lay Seaweed", "newest Lay favor", 20.0f, 10000001);
		ItemDescription i2 = new ItemDescription("Testo Cheese", "newest Lay", 25.0f, 10000002);
		ItemDescription i3 = new ItemDescription("Testo Spicy", "ei", 25.0f, 10000003);
		ItemDescription i4 = new ItemDescription("Lay SourCream", "ei", 25.0f, 10000004);
		ItemDescription i5 = new ItemDescription("Nescafe green", "ei", 25.0f, 10000005);
		ItemDescription i6 = new ItemDescription("Nescafe back", "ei", 25.0f, 10000006);
		ItemDescription i7 = new ItemDescription("Nescafe back", "ei", 25.0f, 10000006);
		ItemDescription i8 = new ItemDescription("Nescafe back", "ei", 25.0f, 10000006);
		dd.insert(i1);
		dd.insert(i2);
		dd.insert(i3);
		dd.insert(i4);
		dd.insert(i5);
		dd.insert(i6);
		dd.insert(i7);
		dd.insert(i8);
		for( int i = 0 ; i < 6 ; i++){
			Log.i("TAG ID" , inventoryDB.insert(new Item(0, i1)) +"");
			Log.i("TAG DES ID" , i1.getId() + "");
		}
		Log.i("TAG AMOUNT" , inventoryDB.findQuantity(i1.getId()) +"");
		for( int i = 0 ; i < 6 ; i++){
			inventoryDB.insert(new Item(0, i2));
		}
		for( int i = 0 ; i < 6 ; i++){
			inventoryDB.insert(new Item(0, i3));
		}
		for( int i = 0 ; i < 6 ; i++){
			inventoryDB.insert(new Item(0, i4));
		}
		for( int i = 0 ; i < 6 ; i++){
			inventoryDB.insert(new Item(0, i5));
		}
		for( int i = 0 ; i < 6 ; i++){
			inventoryDB.insert(new Item(0, i6));
		}
		for( int i = 0 ; i < 6 ; i++){
			inventoryDB.insert(new Item(0, i7));
		}
		for( int i = 0 ; i < 6 ; i++){
			inventoryDB.insert(new Item(0, i8));
		}
		inventoryDB.close();
		dd.close();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		adapter = new ArrayAdapter<String>(
				getActivity(), R.layout.list_view, displayNames);
		//TODO add dummy data base
		//addDataToDB();

		itemListView = (ListView) getView().findViewById(R.id.cashierListView);
		nextButton = (Button) getView().findViewById(R.id.cashierButtonNext);
		buttonOK = (Button) getView().findViewById(R.id.cashierButtonOK);
		nextButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getActivity(),CashierContinueActivity.class);
				sendList = new ArrayList<String>();
				while(displayNames.size()>0){
					sendList.add(displayNames.get(0));
					displayNames.remove(0);
				}
				intent.putStringArrayListExtra("orderList", sendList);
				
				adapter.notifyDataSetChanged();
				txtID.setText("");
				startActivity(intent);

			}
		});
		scanWithBarcodeButton = (Button) getView().findViewById(
				R.id.btScanWithBarcode);
		txtID = (EditText) getView().findViewById(R.id.CashierTxtID);
		scanWithBarcodeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				try {
					Intent intent = new Intent(
							"com.google.zxing.client.android.SCAN");
					intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
					startActivityForResult(intent, 0);
				} catch (Exception e) {
					Toast.makeText(getActivity().getBaseContext(),
							"Please Install Barcode Scanner",
							Toast.LENGTH_SHORT).show();
				}

			}
		});

		itemListView.setAdapter(adapter);
		adapter.notifyDataSetChanged();

		itemListView.setTextFilterEnabled(true);
		itemListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				list.remove(arg2);
				adapter.notifyDataSetChanged();
			}

		});

		buttonOK.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ItemDescriptionBookDB itemDesDB = new ItemDescriptionBookDB(getActivity());
				String barcode = txtID.getText().toString();
				list.add(barcode);
				try {
					displayNames.add(itemDesDB.findByBarcode(Integer.parseInt(barcode)).getName());
					adapter.notifyDataSetChanged();
				} catch (Exception e) {
					Toast.makeText(getActivity(),"Not found", Toast.LENGTH_SHORT).show();
				}
				
				
				
			}
		});
	}


	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			if (resultCode == getActivity().RESULT_OK) {

				String contents = intent.getStringExtra("SCAN_RESULT");
				String format = intent.getStringExtra("SCAN_RESULT_FORMAT");

				txtID.setText(contents);
			}
		}
		super.onActivityResult(requestCode, resultCode, intent);
	}

}