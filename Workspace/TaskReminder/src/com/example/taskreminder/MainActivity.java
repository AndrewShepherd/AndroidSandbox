package com.example.taskreminder;

import android.os.Bundle;
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
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder_list);
        registerForContextMenu(getListView());
        
        mDbHelper = new RemindersDbAdapter(this);
        mDbHelper.open();
        fillData();
    }

    


	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) {
		case R.id.menu_delete:
			AdapterContextMenuInfo info = 
				(AdapterContextMenuInfo)item.getMenuInfo();
			
			Toast toast = Toast.makeText(this, "You pressed delete for id " + info.id, 2000);
			toast.show();
			mDbHelper.deleteReminder(info.id);
			fillData();
			return true;
		}
		return super.onContextItemSelected(item);
	}




	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.list_menu_item_longpress, menu);
	}




	private void fillData() {
		Cursor cursor = mDbHelper.allItemsCursor();
        
        SimpleCursorAdapter reminders = new SimpleCursorAdapter(
        				this, 
        				R.layout.reminder_row, 
        				cursor, 
        				new String[] { 
        							RemindersDbAdapter.KEY_TITLE 
        				}, 
        				new int[] { R.id.text1 } 
        				);
        super.setListAdapter(reminders);
	}

    
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    
    private static class RequestCodes
    {
    	final static int ACTIVITY_CREATE = 0;
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId())
		{
			case R.id.menu_insert:
				Intent startActivityIntent = new Intent(this, ReminderEditActivity.class);
				startActivityForResult(startActivityIntent, RequestCodes.ACTIVITY_CREATE);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}



	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == RESULT_OK) {
			switch(requestCode) {
				case RequestCodes.ACTIVITY_CREATE :
					ReminderTask reminderTask = ReminderIntentAdapter.Read(data);

					reminderTask = mDbHelper.save(reminderTask);
					fillData();
				default:
					
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
    
	
    
    
}
