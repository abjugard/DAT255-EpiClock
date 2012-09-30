package edu.chalmers.dat255.group09.Alarmed.controller;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;
import edu.chalmers.dat255.group09.Alarmed.database.DatabaseHandler;
import edu.chalmers.dat255.group09.Alarmed.model.Alarm;
import edu.chalmers.dat255.group09.Alarmed.receiver.AlarmReceiver;

public class AlarmController {

	private static AlarmController instance;
	private DatabaseHandler dbHelper;

	private Context context;

	public static AlarmController getInstance() {
		if (instance == null) {
			instance = new AlarmController();
		}
		return instance;
	}

	public void init(Context context) {
		this.context = context;
		dbHelper = new DatabaseHandler(context);
	}

	public void createAlarm(int hour, int minute) {
		dbHelper.createAlarm(hour, minute, false);
		Alarm nextAlarm = dbHelper.fetchFirstAlarm();
		PendingIntent sender = PendingIntent.getBroadcast(context, 0,
				new Intent(context, AlarmReceiver.class).putExtra("ID",
						nextAlarm.getId()), 0);

		AlarmManager alarmManager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC_WAKEUP,
				nextAlarm.getTimeInMilliSeconds(), sender);

		Alarm newAlarm = new Alarm(hour, minute, 0);
		Toast.makeText(context, newAlarm.toString(), Toast.LENGTH_LONG).show();

		Log.d("CreateAlarm", hour + ":" + minute);
	}

	public void alarmRecived(int id) {
		Alarm alarm = dbHelper.fetchAlarm(id);
		if (alarm != null) {
			Toast.makeText(context, "Activated", Toast.LENGTH_SHORT).show();
			Log.d("CreateAlarm: ", "Alarm Activated");
			dbHelper.deleteAlarm(alarm.getId());
		}
	}

	public Cursor getAllAlarms() {
		return dbHelper.fetchAlarms();
	}

	public void destroy() {
		dbHelper.closeDb();
	}

}
