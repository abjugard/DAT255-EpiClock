package edu.chalmers.dat255.group09.Alarmed.controller;

import java.util.LinkedList;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import edu.chalmers.dat255.group09.Alarmed.R;
import edu.chalmers.dat255.group09.Alarmed.activity.MainActivity;
import edu.chalmers.dat255.group09.Alarmed.model.Alarm;

public class NotificationController {

	private NotificationManager notificationManager;
	private Context context;
	private Alarm currentNotification;

	public NotificationController(Context context) {
		this.context = context;
		this.notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);

	}

	public void addNotification(Alarm alarm) {
		if(currentNotification != null) {
			notificationManager.cancel(currentNotification.getId());
		}
		this.notificationManager
				.notify(alarm.getId(), buildNotification(alarm));
		currentNotification = alarm;
	}

	private Notification buildNotification(Alarm alarm) {
		Intent notificationIntent = new Intent(context, MainActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
				notificationIntent, 0);
		String contentText = "Your next alarm is set at: "
				+ alarm.getAlarmHours() + ":" + alarm.getAlarmMinutes();
		String contentTitle = "An alarm is scheduled!";
		String tickerText = "A New Alarm is Scheduled!";

		Notification notification = new NotificationCompat.Builder(context)
				.setContentIntent(pendingIntent)
				.setSmallIcon(R.drawable.ic_launcher)
				.setAutoCancel(true)
				.setTicker(tickerText)
				.setContentTitle(contentTitle)
				.setContentText(contentText)
				.setOngoing(true)
				.setOnlyAlertOnce(true).build();

		return notification;
	}

	private void deleteOldNotification(Alarm alarm) {
		notificationManager.cancel(alarm.getId());
	}
	
}
