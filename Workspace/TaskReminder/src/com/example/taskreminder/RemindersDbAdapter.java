package com.example.taskreminder;

import java.util.ArrayList;
import java.util.Iterator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RemindersDbAdapter {
	private static final String DATABASE_NAME = "data";
	private static final String DATABASE_TABLE = "reminders";
	private static final int DATABASE_VERSION = 1;
	
	public static final String KEY_TITLE = "title";
	public static final String KEY_BODY = "body";
	public static final String KEY_DATE_TIME = "reminder_date_time";
	public static final String KEY_ROWID = "_id";
	
	
	private final ArrayList<RemindersDbAdapterListener> mListeners = new ArrayList<RemindersDbAdapterListener>();
	
	
	private class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			
		}
	}
	
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;
	
	private static final String DATABASE_CREATE = 
			"create table " + DATABASE_TABLE + "("
			+ KEY_ROWID + " integer primary key autoincrement,"
			+ KEY_TITLE + " text not null, "
			+ KEY_BODY + " text not null, "
			+ KEY_DATE_TIME + " text not null);";
	
	private final Context mCtx;
	
	public RemindersDbAdapter(Context ctx) {
		mCtx = ctx;
	}
	
	public void addListener(RemindersDbAdapterListener listener) {
		mListeners.add(listener);
	}
	
	public Context getContext() {
		return mCtx;
	}

	public RemindersDbAdapter open() throws android.database.SQLException {
		mDbHelper = new DatabaseHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}
	
	void fireChangeEvent() {
		Iterator<RemindersDbAdapterListener> iterator = mListeners.iterator();
		while(iterator.hasNext()) {
			RemindersDbAdapterListener listener = iterator.next();
			listener.onDataChange();
		}
	}
	
	public ReminderTask save(ReminderTask reminderTask) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_TITLE, reminderTask.getTitle());
		initialValues.put(KEY_BODY, reminderTask.getBody());
		initialValues.put(KEY_DATE_TIME, reminderTask.getDate().toString());
		
		long id = mDb.insert(DATABASE_TABLE, null, initialValues);
		reminderTask.setId(id);
		fireChangeEvent();
		return reminderTask;
	}
	
	public boolean deleteReminder(long rowId) {
		boolean success = mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
		if(success) {
			fireChangeEvent();
		}
		return success;
	}
	
	public void close() {
		mDbHelper.close();
	}
	
	
	public Cursor allItemsCursor() {
		return mDb.query(true, 
							DATABASE_TABLE, 
							new String[] { 
								KEY_ROWID, 
								KEY_TITLE, 
								KEY_BODY, 
								KEY_DATE_TIME
								}, 
								null, null, null, null, null, null
						);
	}
	
	
}
