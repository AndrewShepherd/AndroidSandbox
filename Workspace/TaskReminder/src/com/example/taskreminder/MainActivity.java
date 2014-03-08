package com.example.taskreminder;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder_list);
    }

    
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    
    final static int ACTIVITY_CREATE = 0;

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId())
		{
			case R.id.menu_insert:
				Intent startActivityIntent = new Intent(this, ReminderEditActivity.class);
				startActivityForResult(startActivityIntent, ACTIVITY_CREATE);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
    
    
    
}
