package edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import edu.chalmers.dat255.group09.Alarmed.R;

public class MemoryActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_memory);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_memory, menu);
		return true;
	}

	public void onButtonPressed(View view) {
		Log.d("CreateAlarm", "" + view.getId());
		view.setBackgroundColor(Color.CYAN);
	}

}
