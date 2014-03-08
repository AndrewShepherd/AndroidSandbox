package com.example.taskreminder;

import android.content.Context;
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

	public RemindersDbAdapter open() throws android.database.SQLException {
		mDbHelper = new DatabaseHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		mDbHelper.close();
	}
	}
	
	
}
