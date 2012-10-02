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
public class DatabaseHandler {
	private Context aCtx;

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
	private static final String DB_CREATE = "CREATE TABLE " + DB_TABLE + " ("
			+ KEY_ROWID + " INTEGER PRIMARY KEY , " + KEY_TIME
			+ " DATETIME, " + KEY_RECURRING + " BOOLEAN);";

	private static final int DB_VERSION = 3;

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
		openDb();
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
		alarmTime.putNull(KEY_ROWID);
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
	 * Deletes an Alarm with a specified time.
	 * 
	 * @param alarmID
	 *            time of the alarm to be deleted.
	 * @return true if deleted else false
	 */

	public boolean deleteAlarm(String time) {
		return aDb.delete(DB_TABLE, KEY_TIME + "=" + time, null) > 0;
	}

	/**
	 * Returns a Cursor over the list of all set alarms
	 * 
	 * @return Cursor with all alarms
	 */

	public Alarm fetchFirstAlarm() {
		List<Alarm> list = getAllAlarms();
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
	 * @return the alarm with the specified id, if not found null
	 */
	public Alarm fetchAlarm(int alarmID) {
		Log.d("Database", "Fetch alarmID:"+alarmID);
		List<Alarm> list = getAllAlarms();
		for(Alarm alarm: list){
			if(alarmID == alarm.getId()){
				return alarm;
			}
		}
		return null;
	}

	public int getNumberOfAlarms() {
		return fetchAlarms().getCount();
	}
	private List<Alarm> getAllAlarms(){
		Cursor aCursor = fetchAlarms();
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
		return list;
	}

}
