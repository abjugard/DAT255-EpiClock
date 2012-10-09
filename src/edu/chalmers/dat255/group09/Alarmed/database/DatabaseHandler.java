/*
 * Copyright (C) 2012 Joakim Persson, Daniel Augurell, Adrian BjugŒrd, Andreas RolŽn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.chalmers.dat255.group09.Alarmed.database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import edu.chalmers.dat255.group09.Alarmed.model.Alarm;
import android.util.Log;

/**
 * A class to help the creation and accessing of alarms in a database. Gives the
 * ability to create and delete alarms, and fetching one or all alarms.
 * 
 * @author Daniel Augurell
 * 
 */
public class DatabaseHandler implements AlarmHandler {
	private Context aCtx;

	private DatabaseHelper aDbHelper;
	private SQLiteDatabase aDb;

	/**
	 * Database SQL statements
	 */

	private static final String DB_NAME = "data";
	private static final String DB_TABLE = "alarms";

	public static final String KEY_TIME = "time";
	public static final String KEY_RECURRING = "recurring";
	public static final String KEY_ROWID = "_id";
	public static final String KEY_ENABLED = "enabled";
	public static final String KEY_MODULE = "module";

	public static final String[] KEYS = { KEY_ROWID, KEY_TIME, KEY_RECURRING,
			KEY_ENABLED, KEY_MODULE };

	private static final String DB_CREATE = "CREATE TABLE " + DB_TABLE + " ("
			+ KEY_ROWID + " INTEGER PRIMARY KEY , " + KEY_TIME + " DATETIME, "
			+ KEY_RECURRING + " BOOLEAN," + KEY_ENABLED + " BOOLEAN,"+KEY_MODULE+" STRING);";

	private static final int DB_VERSION = 5;

	/**
	 * 
	 * A help class to open or create the database.
	 * 
	 * @author Daniel Augurell
	 * 
	 */
	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, DB_NAME, null, DB_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DB_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
			onCreate(db);
			Log.w("DATABASE:", "The database is changed from version "
					+ oldVersion + " to version " + newVersion);
		}

		@Override
		public void onDowngrade(SQLiteDatabase db, int oldVersion,
				int newVersion) {
			onUpgrade(db, oldVersion, newVersion);
		}
	}

	public DatabaseHandler(Context ctx) {
		aCtx = ctx;
	}

	public AlarmHandler openCon() {
		aDbHelper = new DatabaseHelper(aCtx);
		aDb = aDbHelper.getWritableDatabase();
		return this;
	}

	public void closeCon() {
		aDbHelper.close();
	}

	public long createAlarm(int hour, int minute, boolean recurring,
			String module) {
		ContentValues alarmTime = new ContentValues();
		alarmTime.putNull(KEY_ROWID);
		alarmTime.put(KEY_RECURRING, recurring);
		alarmTime.put(KEY_ENABLED, true);
		alarmTime.put(KEY_TIME, hour + ":" + minute);
		alarmTime.put(KEY_MODULE, module);
		return aDb.insert(DB_TABLE, null, alarmTime);
	}

	public boolean deleteAlarm(int alarmID) {
		return aDb.delete(DB_TABLE, KEY_ROWID + "=" + alarmID, null) > 0;
	}

	public Alarm fetchFirstEnabledAlarm() {
		List<Alarm> list = fetchAllAlarms();
		Collections.sort(list);
		for (Alarm alarm : list) {
			if (alarm.isEnabled()) {
				return alarm;
			}
		}
		return null;
	}

	/**
	 * Fetches all alarms as Cursor to the database
	 * 
	 * @return Cursor with all the alarmdata
	 */

	private Cursor getAlarms() {
		return aDb.query(true, DB_TABLE, KEYS, null, null, null, null, null,
				null);
	}

	public Alarm fetchAlarm(int alarmID) {
		Cursor cursor = aDb.query(true, DB_TABLE, KEYS, KEY_ROWID + "="
				+ alarmID, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			return getAlarmFromCursor(cursor);
		}
		return null;
	}

	public int getNumberOfAlarms() {
		return getAlarms().getCount();
	}

	public List<Alarm> fetchAllAlarms() {
		Cursor cursor = getAlarms();
		ArrayList<Alarm> list = new ArrayList<Alarm>();
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				do {
					list.add(getAlarmFromCursor(cursor));
				} while (cursor.moveToNext());
			}
		}
		return list;
	}

	/**
	 * Gets the alarm from the data at the cursors current position.
	 * 
	 * @param cursor
	 *            The cursor set at the specified position.
	 * @return An Alarm with the specified data.
	 */

	private Alarm getAlarmFromCursor(Cursor cursor) {
		String[] time = cursor.getString(cursor.getColumnIndex(KEY_TIME))
				.split(":");
		Alarm a = new Alarm(Integer.parseInt(time[0]),
				Integer.parseInt(time[1]), cursor.getInt(cursor
						.getColumnIndex(KEY_ROWID)), cursor.getString(cursor
						.getColumnIndex(KEY_MODULE)));
		a.setEnabled(cursor.getInt(cursor.getColumnIndex(KEY_ENABLED)) > 0);
		return a;
	}

	public boolean isEnabled(int id) {
		Cursor cursor = aDb.query(true, DB_TABLE, KEYS, KEY_ROWID + "=" + id,
				null, null, null, null, null);
		return cursor.getInt(cursor.getColumnIndex(KEY_ENABLED)) > 0;
	}

	public boolean setAlarmEnabled(int id, boolean enable) {
		ContentValues values = new ContentValues();
		values.put(KEY_ENABLED, enable);
		return aDb.update(DB_TABLE, values, KEY_ROWID + "=" + id, null) > 0;
	}

}
