package com.ui.extended;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.core.Item;
import com.core.ItemDescription;
import com.database.InventoryDB;
import com.database.InventoryDao;
import com.database.ItemDescriptionBookDB;
import com.database.ItemDescriptionBookDao;
import com.example.android.navigationdrawerexample.R;

public class InventoryManagerActivity extends Activity {
	
	EditText txt_barcode ;
	EditText txt_quantity ;
	TextView txt_res;
	ItemDescriptionBookDao itdDB;
	InventoryDao db;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_manager);        
        
		txt_barcode = (EditText)findViewById(R.id.txt_barcode_inv);
		txt_quantity = (EditText)findViewById(R.id.txt_quantity_inv);
		txt_res = (TextView)findViewById(R.id.txt_res_inv);
		
		db = new InventoryDB(this);
		showdata();
    }

    public void btn_add_clicked(View v){
    	String barcode = txt_barcode.getText().toString();
    	String quantity = txt_quantity.getText().toString();
    	
    	if(barcode.equals("") || quantity.equals("")){
    		Toast.makeText(getBaseContext(), "Error, Not Enough Information",Toast.LENGTH_SHORT).show();  
			return;
    	}
    	itdDB = new ItemDescriptionBookDB(this);
    	ItemDescription itd = itdDB.findByBarcode(Integer.parseInt(barcode));
    	itdDB.close();
    	if(itd == null) return;
    	for(int i = 0 ; i< Integer.parseInt(quantity); i++){
    		db.insert(new Item( 0, itd));
    	}
    	showdata();
    }
    
    public void btn_remove_clicked(View v){
    	String barcode = txt_barcode.getText().toString();
    	String quantity = txt_quantity.getText().toString();
    	
    	
    	if(barcode.equals("") || quantity.equals("")){
    		Toast.makeText(getBaseContext(), "Error, Not Enough Information",Toast.LENGTH_SHORT).show();  
			return;
    	}
    	itdDB = new ItemDescriptionBookDB(this);
    	ItemDescription itd = itdDB.findByBarcode(Integer.parseInt(barcode));
    	itdDB.close();
    	if(itd == null) return;
    	Item[] items = db.findStatus(itd._id, Item.STATUS_STOCK);
    	if(items.length < Integer.parseInt(quantity) || items == null) {
    		Toast.makeText(getBaseContext(), "Error, Not Enough Stock",Toast.LENGTH_SHORT).show();
    		return;
    	}
    	for(int i = 0 ; i < Integer.parseInt(quantity) ; i++){
    		db.delete(items[i].get_id());
    	}
    	showdata();
    }
    
    public void btn_sale_clicked(View v){
    	String barcode = txt_barcode.getText().toString();
    	String quantity = txt_quantity.getText().toString();
    	
    	if(barcode.equals("") || quantity.equals("")){
    		Toast.makeText(getBaseContext(), "Error, Not Enough Information",Toast.LENGTH_SHORT).show();  
			return;
    	}
    	itdDB = new ItemDescriptionBookDB(this);
    	ItemDescription itd = itdDB.findByBarcode(Integer.parseInt(barcode));
    	itdDB.close();
    	if(itd == null) return;
    	Item[] items = db.findStatus(itd._id, Item.STATUS_STOCK);
		Log.i("TAG",items.length+"");
    	if(items.length < Integer.parseInt(quantity) || items==null) {
    		Log.i("TAG3",items.length+"");
    		Toast.makeText(getBaseContext(), "Error, Not Enough Stock",Toast.LENGTH_SHORT).show();
    		return;
    	}
    	for(int i = 0 ; i < Integer.parseInt(quantity) ; i++){
    		items[i].setStatus(Item.STATUS_SOLD);
    		db.update(items[i]);
    	}
    	showdata();
    }
    
    public void showdata(){
    	
    	itdDB = new ItemDescriptionBookDB(this);
    	ItemDescription[] itds = itdDB.findAll();
    	itdDB.close();
    	txt_res.setText("");
    	if(db.findStatus(Item.STATUS_STOCK) == null) Log.i("TAG", "xxxxxx");
    	for(int i = 0 ; i < itds.length ; i++){
    		txt_res.append("name : " + itds[i].getName() + "    barcode : " + itds[i].getBarcode() + "\nSTOCK : " );
    		Item[] items = db.findStatus(itds[i]._id,Item.STATUS_STOCK);
    		int len = 0;
    		if(items == null) len = 0; else len = items.length;
    		txt_res.append(len + "     SOLD : ");
    		items = db.findStatus(itds[i]._id,Item.STATUS_SOLD);
    		if(items == null) len = 0; else len = items.length;
    		txt_res.append(len + "\n\n");
    	}
    	
    }
    
    @Override
    protected void onDestroy(){    	
       super.onDestroy();
       db.close();
    }    
    
}