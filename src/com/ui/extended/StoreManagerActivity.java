package com.ui.extended;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.core.ItemDescription;
import com.database.ItemDescriptionBookDB;
import com.database.ItemDescriptionBookDao;
import com.example.android.navigationdrawerexample.R;

public class StoreManagerActivity extends Activity {
	
	EditText txt_id ;
	EditText txt_name ;
	EditText txt_price ;
	EditText txt_barcode ;
	EditText txt_desc ;
	TextView txt_res;
	ItemDescriptionBookDao db;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_manager);        
               
        txt_name = (EditText)findViewById(R.id.txt_name);
        txt_id = (EditText)findViewById(R.id.txt_id);
		txt_price = (EditText)findViewById(R.id.txt_price);
		txt_barcode = (EditText)findViewById(R.id.txt_barcode);
		txt_desc = (EditText)findViewById(R.id.txt_description);
		txt_res = (TextView)findViewById(R.id.txt_res);
		
		db = new ItemDescriptionBookDB(this);
		showdata();
    }
    
    public void btn_insert_clicked(View v){
    	Log.d("Clicked","btn_insert");
    	String name = txt_name.getText().toString();
    	String price = txt_price.getText().toString();
    	String barcode = txt_barcode.getText().toString();
    	String desc = txt_desc.getText().toString();
		
		if(name.equals("") || price.equals("") || barcode.equals("") || desc.equals("")){
			Toast.makeText(getBaseContext(), "Error, Not Enough Information",Toast.LENGTH_SHORT).show();  
			return;
		}
    	
		ItemDescription itd = new ItemDescription(name, desc, Float.parseFloat(price), Integer.parseInt(barcode));
    	try{
    		long _id = db.insert(itd);
    		Toast.makeText(getBaseContext(), String.format("Add id : %d\nName : %s\nDescription : %s\nBarcode : %d\nPrice : %.2f", _id , itd.getName() ,itd.getItemDescription() , itd.getBarcode() , itd.getPrice()),Toast.LENGTH_SHORT).show();
    	}catch(Exception e){
    		Toast.makeText(getBaseContext(), "Error,"+e.getMessage(),Toast.LENGTH_SHORT).show();  
    	}finally{
    		clearTextField();
    		showdata();
    	}
    }  
    
    public void btn_update_clicked(View v){
    	String id = txt_id.getText().toString();
    	String name = txt_name.getText().toString();
    	String price = txt_price.getText().toString();
    	String barcode = txt_barcode.getText().toString();
    	String desc = txt_desc.getText().toString();
		
		if(name.equals("") || price.equals("") || barcode.equals("") || desc.equals("") || id.equals("")){
			Toast.makeText(getBaseContext(), "Error, Not Enough Information",Toast.LENGTH_SHORT).show();  
			return;
		}
    	
		ItemDescription itd = new ItemDescription(name, desc, Float.parseFloat(price), Integer.parseInt(barcode));
		db.update(Integer.parseInt(id), itd);
		showdata();
		Toast.makeText(getBaseContext(), String.format("Updated id : %s to\nName : %s\nDescription : %s\nBarcode : %d\nPrice : %.2f", id.toString() , itd.getName() ,itd.getItemDescription() , itd.getBarcode() , itd.getPrice()),Toast.LENGTH_SHORT).show();
		clearTextField();
    }

	/**
	 * 
	 */
	private void clearTextField() {
		txt_name.setText("");
		txt_price.setText("");
		txt_desc.setText("");
		txt_barcode.setText("");
		txt_id.setText("");
	}
    
    public void btn_delete_clicked(View v){
    	String str = txt_id.getText().toString();
    	
    	if(str.equals("")) return;
    	int id = Integer.parseInt(str);
    	
    	db.delete(id);
    	showdata();
    	Toast.makeText(getBaseContext(), "Delete id : " + id,Toast.LENGTH_SHORT).show();
		clearTextField();
    }
    
    public void btn_select_clicked(View v){
    	String id = txt_id.getText().toString();
    	String name = txt_name.getText().toString();
    	String barcode = txt_barcode.getText().toString();
    	
    	if(!id.equals("")){
	    	ItemDescription itd = db.findBy(Integer.parseInt(id));
	    	if(itd != null){
		    	Toast.makeText(getBaseContext(), String.format("Found id : %s\nName : %s\nDescription : %s\nBarcode : %d\nPrice : %.2f", id , itd.getName() ,itd.getItemDescription() , itd.getBarcode() , itd.getPrice()),Toast.LENGTH_SHORT).show();
				clearTextField();
				showdata(new ItemDescription[]{itd});
	    	}else Toast.makeText(getBaseContext(), "Not found",Toast.LENGTH_SHORT).show();
    	}else if(!name.equals("")){
    		ItemDescription[] itds = db.findByContains(name);
    		if(itds != null){
		    	Toast.makeText(getBaseContext(), "Search for " + name,Toast.LENGTH_SHORT).show();
				clearTextField();
				showdata(itds);
    		}else Toast.makeText(getBaseContext(), "Not found",Toast.LENGTH_SHORT).show();
    	}else if(!barcode.equals("")){
    		ItemDescription itd = db.findByBarcode(Integer.parseInt(barcode));
    		if(itd != null){
		    	Toast.makeText(getBaseContext(), String.format("Found id : %s\nName : %s\nDescription : %s\nBarcode : %d\nPrice : %.2f", id , itd.getName() ,itd.getItemDescription() , itd.getBarcode() , itd.getPrice()),Toast.LENGTH_SHORT).show();
				clearTextField();
				showdata(new ItemDescription[]{itd});
    		}else Toast.makeText(getBaseContext(), "Not found",Toast.LENGTH_SHORT).show();
        }else showdata();
    }
    
    public void btn_intent_clicked(View v){
    	Intent intent = new Intent(getApplicationContext(), InventoryManagerActivity.class);
    	startActivity(intent);
    }
    

    public void showdata(ItemDescription[] itds){
    	txt_res.setText("");
        for(int i = 0 ; i < itds.length ; i++){
        	txt_res.append("_id : " + itds[i]._id + "    name : " + itds[i].getName() + "    barcode : " + itds[i].getBarcode() + "    price : " + itds[i].getPrice() + "\nDescription : " + itds[i].getItemDescription() + "\n\n");
        }
    }
    
    public void showdata(){
    	txt_res.setText("");
    	ItemDescription[] itds = db.findAll();
    	if(itds != null)
        for(int i = 0 ; i < itds.length ; i++){
        	txt_res.append("_id : " + itds[i]._id + "    name : " + itds[i].getName() + "    barcode : " + itds[i].getBarcode() + "    price : " + itds[i].getPrice() + "\nDescription : " + itds[i].getItemDescription() + "\n\n");
        }
    }
    
    @Override
    protected void onDestroy(){    	
       super.onDestroy();
       db.close();
    }    
    
}