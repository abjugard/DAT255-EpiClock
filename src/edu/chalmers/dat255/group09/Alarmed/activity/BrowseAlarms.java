package edu.chalmers.dat255.group09.Alarmed.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import edu.chalmers.dat255.group09.Alarmed.R;

public class BrowseAlarms extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browse_alarms);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_browse_alarms, menu);
		return true;
	}
		
	public void onCreateAlarmBtnPressed(View view) {
		Intent intent = new Intent(this, CreateAlarm.class);
		startActivity(intent);
		overridePendingTransition(0, 0);
	}
}
