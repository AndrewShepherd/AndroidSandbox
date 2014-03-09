package com.example.taskreminder;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;



public class MainActivity extends ListActivity {

	RemindersDbAdapter mDbHelper;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder_list);
        
        mDbHelper = new RemindersDbAdapter(this);
        mDbHelper.open();
        fillData();
    }




	private void fillData() {
		Cursor cursor = mDbHelper.allItemsCursor();
        
        SimpleCursorAdapter reminders = new SimpleCursorAdapter(this, R.layout.reminder_row, cursor, new String[] { RemindersDbAdapter.KEY_TITLE }, new int[] { R.id.text1 } );
        
     
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
