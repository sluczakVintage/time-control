package com.nfc.timecontrol.cl;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.Log;

/**
 * @author Sebastian
 *
 */
public class TagEventProvider extends ContentProvider {

	/**
	 * Tag used for logging purposes
	 */
	private static final String TAG = "NFCTimeControl_TagEventProvider";
		
	/**
	 * Database name
	 */
	private static final String DATABASE_NAME = "tagevents.db";
	/**
	 * Events table name
	 */
	private static final String EVENTS_TABLE_NAME = "events";
	/**
	 * Database version
	 */
	private static final int DATABASE_VERSION = 3;
	
	/**
	 * 
	 */
	private static HashMap<String, String> sEventsProjectionMap;
	
	/**
	 * Content provider uri matcher
	 */
	private static final UriMatcher sUriMatcher;

	
	/**
	 * 
	 */
	private static final int EVENTS = 2;


	private static class DatabaseHelper extends SQLiteOpenHelper {

		
		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase database) {
			database.execSQL("CREATE TABLE " + EVENTS_TABLE_NAME + " ("
                    + EventsColumns._ID + " INTEGER PRIMARY KEY,"
                    + EventsColumns.USER + " TEXT,"
                    + EventsColumns.TAG_ID + " TEXT,"
                    + EventsColumns.TOKEN + " TEXT, "
                    + EventsColumns.COMMENT + " TEXT, "
                    + EventsColumns.CREATED_DATE + " INTEGER, "
                    + EventsColumns.STATE + " INTEGER"
                    + ");");
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            database.execSQL("DROP TABLE IF EXISTS " + EVENTS_TABLE_NAME);
            onCreate(database);
		}
	}
	
	/**
	 * 
	 */
	private DatabaseHelper mOpenHelper;

	public static final String AUTHORITY = "com.nfc.timecontrol.cl.NFCTimeControl";


	@Override
	public boolean onCreate() {
		mOpenHelper = new DatabaseHelper(getContext());
		return true;
	}


	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		qb.setTables(EVENTS_TABLE_NAME);
		
		switch (sUriMatcher.match(uri)) {
			case EVENTS:
				qb.setProjectionMap(sEventsProjectionMap);
				break;
				
			default:
				throw new IllegalArgumentException("Unknown URI" + uri);
		}
		
		String orderBy;
		if (TextUtils.isEmpty(sortOrder)) {
			orderBy = EventsColumns.DEFAULT_SORT_ORDER;
		}
			else {
				orderBy = sortOrder;
		}
		
		SQLiteDatabase database = mOpenHelper.getReadableDatabase();
		Cursor c = qb.query(database, projection, selection, selectionArgs, null, null, orderBy);
		
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}
	
	@Override
	public int delete(Uri uri, String where, String[] whereArgs) {	
		SQLiteDatabase database = mOpenHelper.getWritableDatabase();
		int count;
		switch(sUriMatcher.match(uri)) {	
			case EVENTS:
				count = database.delete(EVENTS_TABLE_NAME, where, whereArgs);
				break;
				
			default: 
				throw new IllegalArgumentException("Unknown URI " + uri);
				
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

	@Override
	public String getType(Uri uri) {
		switch (sUriMatcher.match(uri)) {
			case EVENTS:
			
			default:
		        throw new IllegalArgumentException("Unknown URI " + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues initialValues) {
		if (sUriMatcher.match(uri) != EVENTS) {
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		
		ContentValues values;
		if (initialValues != null) {
			values = new ContentValues(initialValues);
		}
		else	{
			values = new ContentValues();
		}
		
		Time nowTime = new Time();
		nowTime.setToNow();
		Long now = nowTime.toMillis(true);
		
		if (values.containsKey(EventsColumns.USER) == false) {
            values.put(EventsColumns.USER, "");
        }

        if (values.containsKey(EventsColumns.TAG_ID) == false) {
            values.put(EventsColumns.TAG_ID, "");
        }
        
        if (values.containsKey(EventsColumns.TOKEN) == false) {
            values.put(EventsColumns.TOKEN, "");
        }
        if (values.containsKey(EventsColumns.COMMENT) == false) {
            values.put(EventsColumns.COMMENT, "");
        }
        if (values.containsKey(EventsColumns.CREATED_DATE) == false) {
            values.put(EventsColumns.CREATED_DATE, now);
        }
        
        if (values.containsKey(EventsColumns.STATE) == false) {
            values.put(EventsColumns.STATE, 1);
        }
        
        SQLiteDatabase database = mOpenHelper.getWritableDatabase();
        long rowId = database.insert(EVENTS_TABLE_NAME, EventsColumns.USER, values);
        
        if (rowId > 0) {
            Uri eventUri = ContentUris.withAppendedId(EventsColumns.CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(eventUri, null);
            return eventUri;
        }

        throw new SQLException("Failed to insert row into " + uri);
	}


	@Override
	public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
		SQLiteDatabase database = mOpenHelper.getWritableDatabase();
		int count;
		switch(sUriMatcher.match(uri)) {
			case EVENTS:
				count = database.update(EVENTS_TABLE_NAME, values, where, whereArgs);
				break;
			
			default:
				throw new IllegalArgumentException("Unknown URI " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}
	
	static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(TagEventProvider.AUTHORITY, EVENTS_TABLE_NAME, EVENTS);

        sEventsProjectionMap = new HashMap<String, String>();
        sEventsProjectionMap.put(EventsColumns._ID, EventsColumns._ID);
        sEventsProjectionMap.put(EventsColumns.USER, EventsColumns.USER);
        sEventsProjectionMap.put(EventsColumns.TAG_ID, EventsColumns.TAG_ID);
        sEventsProjectionMap.put(EventsColumns.TOKEN, EventsColumns.TOKEN);
        sEventsProjectionMap.put(EventsColumns.COMMENT, EventsColumns.COMMENT);
        sEventsProjectionMap.put(EventsColumns.CREATED_DATE, EventsColumns.CREATED_DATE);
        sEventsProjectionMap.put(EventsColumns.STATE, EventsColumns.STATE);
        

    }
}
