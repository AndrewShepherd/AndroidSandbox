package com.example.taskreminder;

import java.lang.String;
import java.util.Calendar;

public final class ReminderTask {
	
	private String mTitle = "";
	private String mBody = "";
	private Calendar mDateTime = Calendar.getInstance();		
			
			

	private long mId = -1;
	
	public ReminderTask() {
	}
	
	
	public void setId(long id) {
		id = mId;
	}
	
	public long getId() {
		return mId;
	}
	
	public void setTitle(String title) {
		mTitle = title;
	}
	
	public String getTitle() {
		return mTitle;
	}
	
	public String getBody() {
		return mBody;
	}
	
	public void setBody(String body) {
		mBody = body;
	}
	
	public Calendar getDate() {
		return mDateTime;
	}
	
	public void setDate(Calendar date) {
		mDateTime = date;
	}
	
	

}
