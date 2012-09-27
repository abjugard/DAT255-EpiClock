package edu.chalmers.dat255.group09.Alarmed.database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.chalmers.dat255.group09.Alarmed.model.Alarm;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * A class to help the creation and accessing of alarms in a database. Gives the
 * ability to create and delete alarms, and fetching one or all alarms.
 * 
 * @author Daniel Augurell
 * 
 */
public class DatabaseHandler {
	private static DatabaseHandler instance;
	private static Context aCtx;

	private DatabaseHelper aDbHelper;
	private SQLiteDatabase aDb;

	/**
	 * Database SQL statements
	 */
	public static final String KEY_TIME = "time";
	public static final String KEY_RECURRING = "recurring";
	public static final String KEY_ROWID = "_id";

	public static final String[] KEYS = { KEY_ROWID, KEY_TIME, KEY_RECURRING };

	private static final String DB_NAME = "data";
	private static final String DB_TABLE = "alarms";
	private static final String DB_CREATE = "create table " + DB_TABLE + " ("
			+ KEY_ROWID + " integer primary key autoincrement, " + KEY_TIME
			+ " datetime, " + KEY_RECURRING + " boolean);";

	private static final int DB_VERSION = 2;

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
		}
	}

	public static DatabaseHandler getInstance() {
		if (instance == null) {
			instance = new DatabaseHandler();
		}
		return instance;
	}

	public void setContext(Context ctx) {
		aCtx = ctx;
	}

	/**
	 * Open the database or create a new if it hasn't been created yet.
	 * 
	 * @return this, a self reference
	 */

	public DatabaseHandler openDb() {
		aDbHelper = new DatabaseHelper(aCtx);
		aDb = aDbHelper.getWritableDatabase();
		return this;
	}

	/**
	 * Closes the connection to the database
	 */
	public void closeDb() {
		aDbHelper.close();
	}

	/**
	 * Inserts a alarm to the database given the time and if it should be
	 * recurring.
	 * 
	 * @param hour
	 *            The hour which the alarm has been set to trigger on
	 * @param minute
	 *            The minute which the alarm has been set to trigger on
	 * @param recurring
	 *            True if the alarm is set to be recurring.
	 * @return the id of the created Alarm in the database, or -1 if an error
	 *         occurred
	 */

	public long createAlarm(int hour, int minute, boolean recurring) {
		ContentValues alarmTime = new ContentValues();
		alarmTime.put(KEY_RECURRING, recurring);
		alarmTime.put(KEY_TIME, hour + ":" + minute);
		return aDb.insert(DB_TABLE, null, alarmTime);
	}

	/**
	 * Deletes an Alarm with a specified id.
	 * 
	 * @param alarmID
	 *            id of the alarm to be deleted.
	 * @return true if deleted else false
	 */

	public boolean deleteAlarm(int alarmID) {
		return aDb.delete(DB_TABLE, KEY_ROWID + "=" + alarmID, null) > 0;
	}

	/**
	 * Returns a Cursor over the list of all set alarms
	 * 
	 * @return Cursor with all alarms
	 */

	public Alarm fetchFirstAlarm() {
		Cursor aCursor = aDb.query(true, DB_TABLE, new String[] { KEY_ROWID,
				KEY_TIME, KEY_RECURRING }, null, null, null, null, null, null);
		ArrayList<Alarm> list = new ArrayList<Alarm>();
		if (aCursor != null) {
			if (aCursor.moveToFirst()) {
				do {
					String[] time = aCursor.getString(
							aCursor.getColumnIndex(KEY_TIME)).split(":");
					list.add(new Alarm(Integer.parseInt(time[0]), Integer
							.parseInt(time[1]), aCursor.getInt(aCursor
							.getColumnIndex(KEY_ROWID))));
				} while (aCursor.moveToNext());
			}
		}
		Collections.sort(list);
		return list.get(0);
	}

	public Cursor fetchAlarms() {
		return aDb.query(true, DB_TABLE, KEYS, null, null, null, null, null,
				null);
	}

	/**
	 * Fetches an alarm from the database given an specified id.
	 * 
	 * @param alarmID
	 *            the id of the alarm to retrieve
	 * @return A cursor with the position set to the matching alarm, if found
	 */
	public Alarm fetchAlarm(int alarmID) {
		Cursor aCursor = aDb.query(true, DB_TABLE, new String[] { KEY_ROWID,
				KEY_TIME, KEY_RECURRING }, KEY_ROWID + "=" + alarmID, null,
				null, null, null, null);
		if (aCursor != null) {
			aCursor.moveToFirst();

			String[] time = aCursor.getString(aCursor.getColumnIndex(KEY_TIME))
					.split(":");
			return new Alarm(Integer.parseInt(time[0]),
					Integer.parseInt(time[1]), aCursor.getInt(aCursor
							.getColumnIndex(KEY_ROWID)));
		}
		return null;
	}

}
