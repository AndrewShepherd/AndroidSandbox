package com.example.taskreminder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ReminderEditActivity extends Activity {

	
	private Button getConfirmButton() {
		return (Button)this.findViewById(R.id.confirm);
	}
	
	private String getTitleValue() {
		EditText editText = (EditText)this.findViewById(R.id.title);
		Editable editable = editText.getText();
		return editable.toString();
	}
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.reminder_edit);
		
		this.getConfirmButton().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				ReminderTask reminderTask = new ReminderTask();
				reminderTask.setTitle(ReminderEditActivity.this.getTitleValue());
				
				Intent data = new Intent();
				
				ReminderIntentAdapter.Write(reminderTask, data);
				setResult(RESULT_OK, data);
				ReminderEditActivity.this.finish();
				
			}
			
		});
		
	}

	
}
