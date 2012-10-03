package edu.chalmers.dat255.group09.Alarmed.controller;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;
import edu.chalmers.dat255.group09.Alarmed.database.DatabaseHandler;
import edu.chalmers.dat255.group09.Alarmed.database.AlarmHandlerInterface;
import edu.chalmers.dat255.group09.Alarmed.model.Alarm;
import edu.chalmers.dat255.group09.Alarmed.receiver.AlarmReceiver;

public class AlarmController {

	private static AlarmController instance;
	private AlarmHandlerInterface alarmHandler;

	private Context context;

	public static AlarmController getInstance() {
		if (instance == null) {
			instance = new AlarmController();
		}
		return instance;
	}

	public void init(Context context) {
		this.context = context;
		alarmHandler = new DatabaseHandler(context).openCon();
	}

	public void createAlarm(int hour, int minute) {
		alarmHandler.createAlarm(hour, minute, false);
		setAlarm();
		Log.d("CreateAlarm", hour + ":" + minute);
		Toast.makeText(context, new Alarm(hour, minute, 0).toString(),
				Toast.LENGTH_LONG).show();
	}

	private void setAlarm() {

		Alarm nextAlarm = alarmHandler.fetchFirstEnabledAlarm();
		if (nextAlarm != null) {
			
			Intent intent = new Intent(context, AlarmReceiver.class);
			intent.setData(Uri.parse(""+nextAlarm.getId()));
			PendingIntent sender = PendingIntent.getBroadcast(context, 0,
					intent, Intent.FILL_IN_DATA);
			
			AlarmManager alarmManager = (AlarmManager) context
					.getSystemService(Context.ALARM_SERVICE);
			alarmManager.set(AlarmManager.RTC_WAKEUP,
					nextAlarm.getTimeInMilliSeconds(), sender);

			Log.d("NextAlarm",
					nextAlarm.getAlarmHours() + ":"
							+ nextAlarm.getAlarmMinutes());
		}

	}

	public boolean alarmRecived(int id) {
		Alarm alarm = alarmHandler.fetchAlarm(id);
		if (alarm != null && alarm.isEnabled()) {
			Toast.makeText(context, "Activated", Toast.LENGTH_SHORT).show();
			Log.d("AlarmRecived: ",
					"Alarm Activated, " + alarmHandler.deleteAlarm(id));
		}
		if (alarmHandler.getNumberOfAlarms() > 0) {
			setAlarm();
		}
		return alarm.isEnabled();
	}
	public boolean deleteAlarm(int id){
		return alarmHandler.deleteAlarm(id);
	}
	public boolean isAlarmEnabled(int id){
		return alarmHandler.isEnabled(id);
	}
	public boolean enableAlarm(int id, boolean enable){
		return alarmHandler.enableAlarm(id, enable);
	}

	public Cursor getAllAlarms() {
		return alarmHandler.fetchAlarms();
	}

	public void destroy() {
		alarmHandler.closeCon();
	}

}
