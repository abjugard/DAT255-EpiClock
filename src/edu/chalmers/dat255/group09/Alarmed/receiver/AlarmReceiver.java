package edu.chalmers.dat255.group09.Alarmed.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import edu.chalmers.dat255.group09.Alarmed.activity.ActivationActivity;
import edu.chalmers.dat255.group09.Alarmed.controller.AlarmController;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		AlarmController aControll = AlarmController.getInstance();
		aControll.init(context);
		aControll.alarmRecived(Integer.parseInt(intent.getData().toString()));
		aControll.destroy();
		Intent activateIntent = new Intent(context, ActivationActivity.class);
		activateIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(activateIntent);
	}

}
