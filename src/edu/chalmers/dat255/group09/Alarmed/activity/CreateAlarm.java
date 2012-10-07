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
package edu.chalmers.dat255.group09.Alarmed.activity;

import java.util.Calendar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TimePicker;
import edu.chalmers.dat255.group09.Alarmed.R;

public class CreateAlarm extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			enableActionbarBackButton();
		}

		setContentView(R.layout.activity_create_alarm);
		initTimePicker();
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void enableActionbarBackButton() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	private void initTimePicker() {
		TimePicker timePicker = (TimePicker) findViewById(R.id.createAlarmTimePicker);

		timePicker.setIs24HourView(true);
		setTimepickerToCurrentTime(timePicker);
	}

	private void setTimepickerToCurrentTime(TimePicker timePicker) {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		Intent intent = this.getIntent();
		if (intent.getIntExtra("requestCode", -1) == MainActivity.EDIT_ALARM_REQUEST_CODE) {
			String[] times = intent.getStringExtra("time").split(":");
			hour = Integer.parseInt(times[0]);
			minute = Integer.parseInt(times[1]);
		}
		timePicker.setCurrentHour(hour);
		timePicker.setCurrentMinute(minute);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_create_alarm, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overrideTransition();
	}

	public void onSetAlarmBtnPressed(View view) {

		TimePicker timePicker = (TimePicker) findViewById(R.id.createAlarmTimePicker);

		int hours = timePicker.getCurrentHour();
		int minutes = timePicker.getCurrentMinute();

		Intent intent = getIntent();
		intent.putExtra("hours", hours);
		intent.putExtra("minutes", minutes);

		this.setResult(RESULT_OK, intent);
		finish();
		overrideTransition();

	}

	private void overrideTransition() {
		int fadeIn = android.R.anim.fade_in;
		int fadeOut = android.R.anim.fade_out;
		overridePendingTransition(fadeIn, fadeOut);
	}

	@Override
	protected void onResume() {
		super.onResume();
		TimePicker timePicker = (TimePicker) findViewById(R.id.createAlarmTimePicker);
		setTimepickerToCurrentTime(timePicker);
	}
}
