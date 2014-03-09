package com.example.taskreminder;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder_list);
        
        String[] items = new String[] { "Foo", "Bar", "Fizz", "Bin" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.reminder_row, R.id.text1, items);
        super.setListAdapter(adapter);
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
					Log.d("TaskReminder", "Handling the activity create result");
					ReminderTask reminderTask = ReminderIntentAdapter.Read(data);
					Log.d("TaskReminder", "Reminder title is " + reminderTask.getTitle());
					RemindersDbAdapter adapter = new RemindersDbAdapter(this);
					adapter.open();
					reminderTask = adapter.create(reminderTask);
					adapter.close();
					
				default:
					
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
    
	
    
    
}
