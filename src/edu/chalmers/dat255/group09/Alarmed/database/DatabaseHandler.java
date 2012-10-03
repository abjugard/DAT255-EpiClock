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
public class DatabaseHandler implements AlarmHandlerInterface {
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

	public static final String[] KEYS = { KEY_ROWID, KEY_TIME, KEY_RECURRING,
			KEY_ENABLED };

	private static final String DB_CREATE = "CREATE TABLE " + DB_TABLE + " ("
			+ KEY_ROWID + " INTEGER PRIMARY KEY , " + KEY_TIME + " DATETIME, "
			+ KEY_RECURRING + " BOOLEAN," + KEY_ENABLED + " BOOLEAN);";

	private static final int DB_VERSION = 4;

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

	public AlarmHandlerInterface openCon() {
		aDbHelper = new DatabaseHelper(aCtx);
		aDb = aDbHelper.getWritableDatabase();
		return this;
	}

	public void closeCon() {
		aDbHelper.close();
	}

	public long createAlarm(int hour, int minute, boolean recurring) {
		ContentValues alarmTime = new ContentValues();
		alarmTime.putNull(KEY_ROWID);
		alarmTime.put(KEY_RECURRING, recurring);
		alarmTime.put(KEY_ENABLED, true);
		alarmTime.put(KEY_TIME, hour + ":" + minute);
		return aDb.insert(DB_TABLE, null, alarmTime);
	}

	public boolean deleteAlarm(int alarmID) {
		return aDb.delete(DB_TABLE, KEY_ROWID + "=" + alarmID, null) > 0;
	}

	public boolean deleteAlarm(String time) {
		return aDb.delete(DB_TABLE, KEY_TIME + "=" + time, null) > 0;
	}

	public Alarm fetchFirstEnabledAlarm() {
		List<Alarm> list = getAllAlarms();
		Collections.sort(list);
		for(Alarm alarm: list){
			if(alarm.isEnabled()){
				return alarm;
			}
		}
		return null;
	}

	public Cursor fetchAlarms() {
		return aDb.query(true, DB_TABLE, KEYS, null, null, null, null, null,
				null);
	}

	public Alarm fetchAlarm(int alarmID) {
		Log.d("Database", "Fetch alarmID:" + alarmID);
		List<Alarm> list = getAllAlarms();
		for (Alarm alarm : list) {
			if (alarmID == alarm.getId()) {
				return alarm;
			}
		}
		return null;
	}

	public int getNumberOfAlarms() {
		return fetchAlarms().getCount();
	}

	/**
	 * Fetches all the alarm data and makes a list of Alarms
	 * 
	 * @return A list of all the alarms
	 */
	private List<Alarm> getAllAlarms() {
		Cursor aCursor = fetchAlarms();
		ArrayList<Alarm> list = new ArrayList<Alarm>();
		if (aCursor != null) {
			if (aCursor.moveToFirst()) {
				do {
					String[] time = aCursor.getString(
							aCursor.getColumnIndex(KEY_TIME)).split(":");
					Alarm a = new Alarm(Integer.parseInt(time[0]), Integer
							.parseInt(time[1]), aCursor.getInt(aCursor
							.getColumnIndex(KEY_ROWID)));
					a.setEnabled(aCursor.getInt(aCursor.getColumnIndex(KEY_ENABLED)) > 0);
					list.add(a);
				} while (aCursor.moveToNext());
			}
		}
		return list;
	}

	public boolean isEnabled(int id) {
		Cursor cursor = aDb.query(true, DB_TABLE, KEYS, KEY_ROWID + "=" + id,
				null, null, null, null, null);
		return cursor.getInt(cursor.getColumnIndex(KEY_ENABLED)) > 0;
	}

	public boolean enableAlarm(int id, boolean enable) {
		ContentValues values = new ContentValues();
		values.put(KEY_ENABLED, enable);
		return aDb.update(DB_TABLE, values, KEY_ROWID + "=" + id, null) > 0;
	}

}
