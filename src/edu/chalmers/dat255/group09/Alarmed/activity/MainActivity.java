package edu.chalmers.dat255.group09.Alarmed.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;
import edu.chalmers.dat255.group09.Alarmed.R;
import edu.chalmers.dat255.group09.Alarmed.adapter.BrowseAlarmAdapter;
import edu.chalmers.dat255.group09.Alarmed.database.DatabaseHandler;
import edu.chalmers.dat255.group09.Alarmed.model.Alarm;
import edu.chalmers.dat255.group09.Alarmed.receiver.AlarmReceiver;

public class MainActivity extends Activity {

	public final static int ADD_ALARM_REQUEST_CODE = 1;
	private BrowseAlarmAdapter alarmAdapter;
	private DatabaseHandler dbHelper;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		openDatabase();
		setAdapter();
	}

	private void setAdapter() {
		ListView listView = (ListView) findViewById(R.id.alarms_list);
		alarmAdapter = new BrowseAlarmAdapter(this, dbHelper.fetchAlarms());
		listView.setAdapter(alarmAdapter);
	}

	private void openDatabase() {
		dbHelper = DatabaseHandler.getInstance();
		dbHelper.setContext(this);
		dbHelper.openDb();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == R.id.menu_add_alarm) {
			Intent intent = new Intent(this, CreateAlarm.class);

			startActivityForResult(intent, ADD_ALARM_REQUEST_CODE);
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
			int hours = data.getIntExtra("hours", -1);
			int minutes = data.getIntExtra("minutes", -1);

			createAlarm(hours, minutes);
			Log.d("CreateAlarm", hours + ":" + minutes);

		}
	}

	private boolean isResonseValid(int requestCode, int resultCode) {
		return requestCode == ADD_ALARM_REQUEST_CODE && resultCode == RESULT_OK;
	}

	private void createAlarm(int hour, int minute) {

		dbHelper.createAlarm(hour, minute, false);

		Alarm nextAlarm = dbHelper.fetchFirstAlarm();

		Intent intent = new Intent(this, AlarmReceiver.class);
		intent.putExtra("ID", nextAlarm.getId());
		PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, 0);

		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC_WAKEUP,
				nextAlarm.getTimeInMilliSeconds(), sender);

		Alarm newAlarm = new Alarm(hour, minute, 0);
		Toast.makeText(this, newAlarm.toString(), Toast.LENGTH_LONG).show();

		alarmAdapter.changeCursor(dbHelper.fetchAlarms());
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		dbHelper.closeDb();
	}
}
