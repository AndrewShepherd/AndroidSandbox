package com.example.taskreminder;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class RemindersListFragment extends ListFragment {

	RemindersDbAdapter getDbHelper() {
		MainActivity mainActivity = (MainActivity)getActivity();
		return mainActivity.getDbHelper();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = super.onCreateView(inflater, container, savedInstanceState);
        fillData();
        getDbHelper().addListener(new RemindersDbAdapterListener() {

			@Override
			public void onDataChange() {
				RemindersListFragment.this.fillData();
			}
        	
        });
		return view;
	}
		
	private void fillData() {
		Cursor cursor = getDbHelper().allItemsCursor();
        
        SimpleCursorAdapter reminders = new SimpleCursorAdapter(
        				getActivity().getBaseContext(), 
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
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) {
		case R.id.menu_delete:
			AdapterContextMenuInfo info = 
				(AdapterContextMenuInfo)item.getMenuInfo();
			getDbHelper().deleteReminder(info.id);
			return true;
		}
		return super.onContextItemSelected(item);
	}
}
