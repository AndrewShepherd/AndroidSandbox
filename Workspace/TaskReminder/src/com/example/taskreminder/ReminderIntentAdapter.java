package com.example.taskreminder;

import android.content.Intent;

public class ReminderIntentAdapter {

	private static class KeyNames {
		static String TITLE = "Title";
	}
	
	public static ReminderTask Read(Intent intent) {
		ReminderTask reminderTask = new ReminderTask();
		reminderTask.setTitle(intent.getStringExtra(KeyNames.TITLE));
		return reminderTask;
	}
	
	public static void Write(ReminderTask reminderTask, Intent intent) {
		intent.putExtra(KeyNames.TITLE, reminderTask.getTitle());
	}
	
	
}
