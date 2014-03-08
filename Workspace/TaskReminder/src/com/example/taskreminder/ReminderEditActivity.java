package com.example.taskreminder;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ReminderEditActivity extends Activity {

	
	private Button getConfirmButton() {
		return (Button)this.findViewById(R.id.confirm);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.reminder_edit);
		
		this.getConfirmButton().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				ReminderEditActivity.this.finish();
				
			}
			
		});
		
	}

	
}
