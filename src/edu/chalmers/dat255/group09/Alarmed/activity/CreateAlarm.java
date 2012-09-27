package edu.chalmers.dat255.group09.Alarmed.activity;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

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
import edu.chalmers.dat255.group09.Alarmed.database.AlarmDbAdapter;
import edu.chalmers.dat255.group09.Alarmed.model.Alarm;
import edu.chalmers.dat255.group09.Alarmed.receiver.AlarmReceiver;

public class CreateAlarm extends Activity {
	private AlarmDbAdapter dbHelp;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dbHelp = AlarmDbAdapter.getInstance();
		dbHelp.setContext(getApplication());
		dbHelp.openDb();
		setContentView(R.layout.activity_create_alarm);
		initTimePicker();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		dbHelp.closeDb();
	}

	@Override
	protected void onResume() {
		super.onResume();
		TimePicker timePicker = (TimePicker) findViewById(R.id.createAlarmTimePicker);
		setTimepickerToCurrentTime(timePicker);
	}

	private void initTimePicker() {
		TimePicker timePicker = (TimePicker) findViewById(R.id.createAlarmTimePicker);

		timePicker.setIs24HourView(true);
		setTimepickerToCurrentTime(timePicker);
	}

	private void setTimepickerToCurrentTime(TimePicker timePicker) {
		Calendar calendar = Calendar.getInstance();
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

		dbHelp.createAlarm(hour, minute, false);
		List<Alarm> alarms = dbHelp.fetchAllAlarms();
		Collections.sort(alarms);
		Alarm alarm = alarms.get(0);

		Intent intent = new Intent(this, AlarmReceiver.class);
		intent.putExtra("ID", alarm.getId());
		PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, 0);

		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC_WAKEUP,
				alarm.getTimeInMilliSeconds(), sender);

		Toast.makeText(this, alarm.toString(), Toast.LENGTH_LONG).show();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		int fadeIn = android.R.anim.fade_in;
		int fadeOut = android.R.anim.fade_out;
		overridePendingTransition(fadeIn, fadeOut);
	}
}
