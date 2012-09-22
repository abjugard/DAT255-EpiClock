package edu.chalmers.dat255.group09.Alarmed;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TimePicker;

public class CreateAlarm extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_alarm);

		TimePicker timePicker = (TimePicker) findViewById(R.id.createAlarmTimePicker);
		timePicker.setIs24HourView(true);
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
	}
}
