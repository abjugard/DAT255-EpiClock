package edu.chalmers.dat255.group09.Alarmed.receiver;

import edu.chalmers.dat255.group09.Alarmed.database.AlarmDbAdapter;
import edu.chalmers.dat255.group09.Alarmed.model.Alarm;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		AlarmDbAdapter db = AlarmDbAdapter.getInstance();
		db.setContext(context);
		db.openDb();
		Alarm alarm = db.fetchAlarm(intent.getIntExtra("ID", -1));
		if (alarm != null) {
			Toast.makeText(context, "Activated", Toast.LENGTH_SHORT).show();
			Log.d("CreateAlarm: ", "Alarm Activated");
			db.deleteAlarm(alarm.getId());
		}
		db.closeDb();
	}

}
