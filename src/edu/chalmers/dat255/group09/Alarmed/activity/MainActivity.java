package edu.chalmers.dat255.group09.Alarmed.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import edu.chalmers.dat255.group09.Alarmed.R;
import edu.chalmers.dat255.group09.Alarmed.adapter.BrowseAlarmAdapter;
import edu.chalmers.dat255.group09.Alarmed.controller.AlarmController;

public class MainActivity extends Activity {

	public final static int ADD_ALARM_REQUEST_CODE = 1;
	private BrowseAlarmAdapter alarmAdapter;
	private AlarmController aControll;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		initController();
		setAdapter();
	}

	private void initController() {
		aControll = AlarmController.getInstance();
		aControll.init(this);
	}

	private void setAdapter() {
		ListView listView = (ListView) findViewById(R.id.alarms_list);
		alarmAdapter = new BrowseAlarmAdapter(this, aControll.getAllAlarms());
		listView.setAdapter(alarmAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == R.id.menu_add_alarm) {
			startActivityForResult(new Intent(this, CreateAlarm.class),
					ADD_ALARM_REQUEST_CODE);
			overrrideTransition();

			return true;
		}

		return super.onOptionsItemSelected(item);

	}

	private void overrrideTransition() {
		int fadeIn = android.R.anim.fade_in;
		int fadeOut = android.R.anim.fade_out;
		overridePendingTransition(fadeIn, fadeOut);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (isResonseValid(requestCode, resultCode)) {
			createAlarm(data.getIntExtra("hours", -1),
					data.getIntExtra("minutes", -1));
		}
	}

	private boolean isResonseValid(int requestCode, int resultCode) {
		return requestCode == ADD_ALARM_REQUEST_CODE && resultCode == RESULT_OK;
	}

	private void createAlarm(int hour, int minute) {
		aControll.createAlarm(hour, minute);
		alarmAdapter.changeCursor(aControll.getAllAlarms());
	}

	@Override
	protected void onResume() {
		super.onResume();
		alarmAdapter.changeCursor(aControll.getAllAlarms());
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		aControll.destroy();
	}
}
