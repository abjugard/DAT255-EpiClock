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
import java.util.Map;

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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TimePicker;
import edu.chalmers.dat255.group09.Alarmed.R;
import edu.chalmers.dat255.group09.Alarmed.modules.factory.ModuleFactory;

/**
 * Activity to create a new alarm.
 * 
 * @author Daniel Augurell
 * @author Joakim Persson
 * 
 */
public class CreateAlarm extends Activity {
	private View volumeDialogView;
	private AlertDialog volumeDialog;
	private Map<Uri, String> alarmTones;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			enableActionbarBackButton();
		}

		setContentView(R.layout.activity_create_alarm);
		initTimePicker();
		initTaskSpinner();
		initAlarmTones();
		initVolumeDialog();
	}

	private void initVolumeDialog() {
		LayoutInflater inflater = getLayoutInflater();
		volumeDialogView = inflater
				.inflate(R.layout.custom_volume_dialog, null);
		Intent intent = this.getIntent();
		((SeekBar) volumeDialogView.findViewById(R.id.volume_dialog_seekbar))
				.setMax(7);
		((SeekBar) volumeDialogView.findViewById(R.id.volume_dialog_seekbar))
				.setProgress(intent.getIntExtra("volume", 6));
		((CheckBox) volumeDialogView.findViewById(R.id.volume_dialog_checkbox))
				.setChecked(intent.getBooleanExtra("vibration", true));

		volumeDialog = new AlertDialog.Builder(this)
				.setTitle("Set volume options")
				.setView(volumeDialogView)
				.setPositiveButton(android.R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int i) {
								Intent intent = getIntent();
								intent.putExtra(
										"vibration",
										((CheckBox) volumeDialogView
												.findViewById(R.id.volume_dialog_checkbox))
												.isChecked());
								intent.putExtra(
										"volume",
										((SeekBar) volumeDialogView
												.findViewById(R.id.volume_dialog_seekbar))
												.getProgress());
							}
						}).create();
	}

	private void initAlarmTones() {
		alarmTones = getAlarmTones();
	}

	/**
	 * If the platform is Honeycomb or greater the ActionBarBackButton is
	 * enabled.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void enableActionbarBackButton() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	/**
	 * Initiates the TimePicker as an 24h view and sets it to the current time.
	 */
	private void initTimePicker() {
		TimePicker timePicker = (TimePicker) findViewById(R.id.createAlarmTimePicker);

		timePicker.setIs24HourView(true);
		setTimepickerToCurrentTime(timePicker);
	}

	/**
	 * Sets the time of a TimePicker to the current time or the time of the
	 * alarm to be edited.
	 * 
	 * @param timePicker
	 *            The TimePicker to be changed
	 */
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

	/**
	 * Initiates the TaskSpinner with all the modulenames.
	 */
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
		default:
			return super.onOptionsItemSelected(item);
		}

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overrideTransition();
	}

	public void onAlarmToneBtnPressed(View view) {
		String[] array = new String[alarmTones.size()];
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, alarmTones.values()
						.toArray(array));
		onVolumeBtnPressed(view);
		new AlertDialog.Builder(this).setTitle("Pick alarm tone")
				.setAdapter(adapter, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int index) {
						getIntent()
								.putExtra(
										"toneuri",
										alarmTones.keySet().toArray()[index]
												.toString());
					}
				}).create().show();
	}

	private Map<Uri, String> getAlarmTones() {
		RingtoneManager ringMan = new RingtoneManager(this);
		ringMan.setType(RingtoneManager.TYPE_ALARM);

		Cursor cur = ringMan.getCursor();

		int tonesAvailable = cur.getCount();
		if (tonesAvailable == 0) {
			return new HashMap<Uri, String>();
		}

		Map<Uri, String> tones = new HashMap<Uri, String>();
		while (!cur.isAfterLast() && cur.moveToNext()) {
			int pos = cur.getPosition();
			tones.put(ringMan.getRingtoneUri(pos), ringMan.getRingtone(pos)
					.getTitle(this));
		}
		cur.close();

		return tones;
	}

	public void onVolumeBtnPressed(View view) {
		volumeDialog.show();
	}

	/**
	 * Finishes the activity and return to the previous activity with the given
	 * result.
	 * 
	 * @param view
	 *            The button that was pressed.
	 */
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
		intent.putExtra("days", getIntegerFromBooleanArray(intent
				.getBooleanArrayExtra("daysOfWeek")));

		this.setResult(RESULT_OK, intent);
		finish();
		overrideTransition();

	}

	/**
	 * Returns a integer representation of the days that the alarm should be
	 * recurring on.
	 * 
	 * @param daysOfWeek
	 *            The boolean array that represents the days that the alarms
	 *            should be recurring on
	 * @return The days of the week that should be recurring as bits in a
	 *         integer
	 */
	private int getIntegerFromBooleanArray(boolean[] daysOfWeek) {
		StringBuilder days = new StringBuilder();
		for (int i = daysOfWeek.length - 1; i >= 0; i--) {
			if (daysOfWeek[i]) {
				days.append("1");
			} else {
				days.append("0");
			}
		}
		return Integer.valueOf(days.toString(), 2);

	}

	/**
	 * Flashy transition between views.
	 */
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

	/**
	 * Creates a dialog with a list of the days of the week. You can enable and
	 * disable every day and the selected days will be the days that is
	 * recurring.
	 * 
	 * @param view
	 *            The button that is pressed.
	 */
	public void onDayOfWeekClick(View view) {
		new AlertDialog.Builder(this)
				.setTitle(R.string.recurring_alarms)
				.setMultiChoiceItems(R.array.days_of_week,
						getIntent().getBooleanArrayExtra("daysOfWeek"),
						new MultiClickListener())
				.setPositiveButton(R.string.ok_button,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
							}
						}).create().show();

	}

	/**
	 * A listener that changes the state of the selected days of the week.
	 * 
	 * @author Daniel Augurell
	 * 
	 */
	private class MultiClickListener implements
			DialogInterface.OnMultiChoiceClickListener {

		@Override
		public void onClick(DialogInterface dialog, int which, boolean isChecked) {
			Intent intent = getIntent();
			boolean[] daysOfWeek = intent.getBooleanArrayExtra("daysOfWeek");
			daysOfWeek[which] = isChecked;

		}

	}

}
