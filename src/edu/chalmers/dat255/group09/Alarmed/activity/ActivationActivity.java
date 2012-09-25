package edu.chalmers.dat255.group09.Alarmed.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import edu.chalmers.dat255.group09.Alarmed.R;

public class ActivationActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activation);

		initVibration();
	}

	private void initVibration() {
		// get os vibration service
		Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

		// create vibration pattern
		long[] pattern = { 0, 200, 500 };
		
		// start vibrating
		v.vibrate(pattern, 0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_activation, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
