package com.example.taskreminder;



import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Toast;



public class MainActivity extends ListActivity {

	RemindersDbAdapter mDbHelper;
	
    private static class RequestCodes
    {
    	final static int ACTIVITY_CREATE = 0;
    }
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder_list);
        registerForContextMenu(getListView());
        

    }

    

    public RemindersDbAdapter getDbHelper() {
    	if(mDbHelper == null) {
    		mDbHelper = new RemindersDbAdapter(this.getBaseContext());
    		mDbHelper.open();
    	}
    	return mDbHelper;
    }
    

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId())
		{
			case R.id.menu_insert:
				Intent startActivityIntent = new Intent(this.getBaseContext(), ReminderEditActivity.class);
				startActivityForResult(startActivityIntent, RequestCodes.ACTIVITY_CREATE);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}


	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.list_menu_item_longpress, menu);
		
	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == Activity.RESULT_OK) {
			switch(requestCode) {
				case RequestCodes.ACTIVITY_CREATE :
					ReminderTask reminderTask = ReminderIntentAdapter.Read(data);
					reminderTask = getDbHelper().save(reminderTask);

				default:
					
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
    
    
}
