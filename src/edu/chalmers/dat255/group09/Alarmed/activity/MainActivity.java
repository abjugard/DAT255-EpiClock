package edu.chalmers.dat255.group09.Alarmed.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import edu.chalmers.dat255.group09.Alarmed.R;
import edu.chalmers.dat255.group09.Alarmed.adapter.BrowseAlarmAdapter;
import edu.chalmers.dat255.group09.Alarmed.controller.AlarmController;

public class MainActivity extends Activity {

	public static final int ADD_ALARM_REQUEST_CODE = 1;
	public static final int EDIT_ALARM_REQUEST_CODE = 2;
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

		registerForContextMenu(listView);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		if (v.getId() == R.id.alarms_list) {
			menu.setHeaderTitle("Menu");
			menu.add(Menu.NONE, 1, 1, "Delete");
			menu.add(Menu.NONE, 2, 2, "Edit");
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();
		ListView listView = (ListView) findViewById(R.id.alarms_list);
		Cursor cursor = (Cursor) listView.getAdapter().getItem(info.position);
		switch (item.getItemId()) {
		case 1:
			aControll.deleteAlarm(cursor.getInt(cursor.getColumnIndex("_id")));
			break;
		case 2:
			Intent intent = new Intent(this, CreateAlarm.class);
			intent.putExtra("ID", cursor.getInt(cursor.getColumnIndex("_id")));
			intent.putExtra("requestCode", EDIT_ALARM_REQUEST_CODE);   
			intent.putExtra("time", cursor.getString(cursor.getColumnIndex("time")));
			startActivityForResult(intent, EDIT_ALARM_REQUEST_CODE);
			overrrideTransition();
			break;
		default:
			break;
		}
		updateList();
		return true;
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
		if (isResonseValid(resultCode)) {
			if (requestCode == ADD_ALARM_REQUEST_CODE) {
				createAlarm(data.getIntExtra("hours", -1),
						data.getIntExtra("minutes", -1));
			} else if (requestCode == EDIT_ALARM_REQUEST_CODE) {
				aControll.deleteAlarm(data.getIntExtra("ID", -1));
				createAlarm(data.getIntExtra("hours", -1),
						data.getIntExtra("minutes", -1));
			}

		}
	}

	private boolean isResonseValid(int resultCode) {
		return resultCode == RESULT_OK;
	}

	private void createAlarm(int hour, int minute) {
		aControll.createAlarm(hour, minute);
		updateList();
	}

	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {

		return super.onKeyLongPress(keyCode, event);
	}

	@Override
	protected void onResume() {
		super.onResume();
		updateList();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		aControll.destroy();
	}

	private void updateList() {
		alarmAdapter.changeCursor(aControll.getAllAlarms());
	}
}
