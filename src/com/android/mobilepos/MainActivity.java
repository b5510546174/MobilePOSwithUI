
package com.android.mobilepos;

import java.util.Locale;

import com.example.android.navigationdrawerexample.R;



import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.drm.DrmStore.Action;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mPlanetTitles;
    ActionBar.Tab[] tabs;
    HistoryFragment historyFragment = new HistoryFragment();
    CashierFragment cashierFragment = new CashierFragment();
	SearchFragment searchFragment = new SearchFragment();
	StockFragment stockFragment = new StockFragment();
	ActionBar actionBar;
	private static ListView listViews;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	//a = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabs = new ActionBar.Tab[4];
        listViews = (ListView)findViewById(R.id.cashierListView);

        mTitle = mDrawerTitle = "Mobile POS";
         mPlanetTitles = new String[]{"Profile","Sign out"};
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        //mDrawerList.setHeaderDividersEnabled(true);
        View headerView = ((LayoutInflater)getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE)).inflate(R.layout.header, null, false);
       // mDrawerList.addHeaderView(headerView);
        mDrawerList.addHeaderView(headerView, "HEY", false);
        //mDrawerList.setHeaderDividersEnabled(true);
        mDrawerList.setSelected(false);
        //mDrawerList.setFooterDividersEnabled(true);
        

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mPlanetTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        
        //ContentFragment fragment = new  ContentFragment();
        //getFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        
        actionBar = getActionBar();
        // Hide Actionbar Icon
     	actionBar.setDisplayShowHomeEnabled(true);

     	// Create Actionbar Tabs
     	actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
     // Set Tab Icon and Titles
     		tabs[0]= actionBar.newTab().setText("History");
     		tabs[1] = actionBar.newTab().setText("Cashier");
     		tabs[2] = actionBar.newTab().setText("Stock");
     		tabs[3] = actionBar.newTab().setText("Search");
     		

     		// Set Tab Listeners
     		tabs[0].setTabListener(new TabListener(historyFragment));
     		tabs[1].setTabListener(new TabListener(cashierFragment));
     		tabs[2].setTabListener(new TabListener(stockFragment));
     		tabs[3].setTabListener(new TabListener(searchFragment));
				
     		// Add tabs to actionbar
     		for(int i=0;i<tabs.length;i++)
     		{
     			actionBar.addTab(tabs[i]);
     		}
     		
     		

        if (savedInstanceState == null) {
        	HistoryFragment fragment = new HistoryFragment();
             getFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
           
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.btMemberOK).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		final Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
		// Handle action buttons
		switch (item.getItemId()) {
		case R.id.btMemberOK:

			AlertDialog.Builder alert = new AlertDialog.Builder(this);

			alert.setTitle("Google");
			alert.setMessage("Input search");

			// Set an EditText view to get user input
			final EditText input = new EditText(this);
			alert.setView(input);

			alert.setPositiveButton("Search",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							String value = input.getText().toString();

							intent.putExtra(SearchManager.QUERY, value);
							
							if (intent.resolveActivity(getPackageManager()) != null) {
								startActivity(intent);
							} else {
								Toast bread = Toast.makeText(getApplicationContext(),"Browser not avilable", Toast.LENGTH_LONG);
								bread.show();
							}
							
						}
					});

			alert.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
						}
					});

			alert.show();

			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //selectItem(position);
        	Toast.makeText(getApplicationContext(), "Press side!",Toast.LENGTH_SHORT).show();
        	mDrawerLayout.closeDrawers();
        }
    }

    

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    
}