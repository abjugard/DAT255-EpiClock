package edu.chalmers.dat255.group09.Alarmed.activity;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;
import edu.chalmers.dat255.group09.Alarmed.R;
import edu.chalmers.dat255.group09.Alarmed.model.AlarmTime;
import edu.chalmers.dat255.group09.Alarmed.receiver.AlarmReceiver;

public class CreateAlarm extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_alarm);

		initTimePicker();

	}

	private void initTimePicker() {
		TimePicker timePicker = (TimePicker) findViewById(R.id.createAlarmTimePicker);
		Calendar calendar = Calendar.getInstance();
		timePicker.setIs24HourView(true);
		timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
		timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_create_alarm, menu);
		return true;
	}

	public void onSetAlarmBtnPressed(View view) {
		TimePicker timePicker = (TimePicker) findViewById(R.id.createAlarmTimePicker);
		int hour = timePicker.getCurrentHour();
		int minute = timePicker.getCurrentMinute();

		Log.d("CreateAlarm", hour + ":" + minute);

		createAlarm(hour, minute);

	}

	private void createAlarm(int hour, int minute) {
		AlarmTime alarm = new AlarmTime(hour, minute);

		Intent intent = new Intent(this, AlarmReceiver.class);
		PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, 0);

		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC_WAKEUP,
				alarm.getAlarmTimeInMilliSeconds(), sender);

		Toast.makeText(this, "Alarm Scheduled", Toast.LENGTH_LONG).show();
	}
}
