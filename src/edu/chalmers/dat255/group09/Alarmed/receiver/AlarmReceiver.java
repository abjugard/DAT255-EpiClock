package edu.chalmers.dat255.group09.Alarmed.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import edu.chalmers.dat255.group09.Alarmed.controller.AlarmController;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		AlarmController aControll = AlarmController.getInstance();
		aControll.init(context);
		aControll.alarmRecived(Integer.parseInt(intent.getData().toString()));
		aControll.destroy();
	}

}
