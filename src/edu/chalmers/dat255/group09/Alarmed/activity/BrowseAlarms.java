package edu.chalmers.dat255.group09.Alarmed.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import edu.chalmers.dat255.group09.Alarmed.R;
import edu.chalmers.dat255.group09.Alarmed.adapter.BrowseAlarmAdapter;
import edu.chalmers.dat255.group09.Alarmed.database.DatabaseHandler;

public class BrowseAlarms extends Activity {

	private BrowseAlarmAdapter alarmAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_browse_alarms);
		
		ListView listView = (ListView) findViewById(R.id.alarms_list);
		DatabaseHandler handler = DatabaseHandler.getInstance();
		handler.setContext(this);
		handler.openDb();
		alarmAdapter = new BrowseAlarmAdapter(this, handler.fetchAlarms());
		listView.setAdapter(alarmAdapter);
		handler.closeDb();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_browse_alarms, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		Intent intent = new Intent(this, CreateAlarm.class);

		startActivity(intent);

		int fadeIn = android.R.anim.fade_in;
		int fadeOut = android.R.anim.fade_out;
		overridePendingTransition(fadeIn, fadeOut);

		return true;
	}

}
