package edu.chalmers.dat255.group09.Alarmed.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "Activated", Toast.LENGTH_SHORT).show();
		Log.d("CreateAlarm: ", "Alarm Activated");
	}
}
