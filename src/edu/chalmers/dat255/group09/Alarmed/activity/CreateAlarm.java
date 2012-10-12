/*
 * Copyright (C) 2012 Joakim Persson, Daniel Augurell, Adrian Bjugård, Andreas Rolén
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
import java.util.HashMap;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TimePicker;
import edu.chalmers.dat255.group09.Alarmed.R;
import edu.chalmers.dat255.group09.Alarmed.factory.ModuleFactory;

public class CreateAlarm extends Activity {

	private HashMap<String, Uri> alarmTones;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			enableActionbarBackButton();
		}

		setContentView(R.layout.activity_create_alarm);
		initTimePicker();
		initTaskSpinner();
		long begin = System.currentTimeMillis();

		initAlarmTones();

		long end = System.currentTimeMillis();
		Log.d("DEBUG", end - begin + "ms");
	}

	private void initAlarmTones() {
		alarmTones = getAlarmTones();
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

	private void initTaskSpinner() {
		Spinner spinner = (Spinner) findViewById(R.id.activity_create_alarm_task_spinner);
		ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
				this, android.R.layout.simple_spinner_item,
				ModuleFactory.getModuleNames());
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner.setAdapter(adapter);

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
		Spinner spinner = (Spinner) findViewById(R.id.activity_create_alarm_task_spinner);

		int hours = timePicker.getCurrentHour();
		int minutes = timePicker.getCurrentMinute();

		String module = (String) spinner.getSelectedItem();

		Intent intent = getIntent();
		intent.putExtra("hours", hours);
		intent.putExtra("minutes", minutes);
		intent.putExtra("module", module);

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

	public void onAlarmToneBtnPressed(View view) {
		String[] array = new String[alarmTones.size()];
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, (String[]) alarmTones
						.keySet().toArray(array));
		new AlertDialog.Builder(this).setTitle("Pick alarm tone")
				.setAdapter(adapter, new AlarmToneClickListener()).create()
				.show();
	}

	private class AlarmToneClickListener implements
			DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int index) {
			Log.d("DEBUG", alarmTones.get(alarmTones.keySet().toArray()[index])
					.toString());
			Log.d("DEBUG", alarmTones.keySet().toArray()[index].toString());
		}
	}

	private HashMap<String, Uri> getAlarmTones() {
		RingtoneManager ringMan = new RingtoneManager(this);
		ringMan.setType(RingtoneManager.TYPE_ALARM);

		Cursor cur = ringMan.getCursor();

		int tonesAvailable = cur.getCount();
		if (tonesAvailable == 0) {
			return new HashMap<String, Uri>();
		}

		HashMap<String, Uri> alarmTones = new HashMap<String, Uri>();
		while (!cur.isAfterLast() && cur.moveToNext()) {
			int pos = cur.getPosition();
			alarmTones.put(ringMan.getRingtone(pos).getTitle(this),
					ringMan.getRingtoneUri(pos));
		}
		cur.close();

		return alarmTones;
	}
}
