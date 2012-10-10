package edu.chalmers.dat255.group09.Alarmed.controller;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import edu.chalmers.dat255.group09.Alarmed.R;

public class NotificationController {

	private NotificationManager notificationManager;
	private Context context;
	private int UniqueID = 234;

	public NotificationController(Context context) {
		this.context = context;
		this.notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);

	}

	public void addNotification(int hour, int minute) {
		this.notificationManager.notify(UniqueID,
				buildNotification(hour, minute));
	}

	private Notification buildNotification(int hour, int minute) {
		Intent notificationIntent = new Intent(context, this.getClass());
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
				notificationIntent, 0);

		Notification notification = new NotificationCompat.Builder(context)
				.setContentIntent(pendingIntent)
				.setSmallIcon(R.drawable.ic_launcher)
				.setAutoCancel(true)
				.setContentTitle("New Alarm Scheduled!")
				.setContentText(
						"You got a new alarm set at: " + hour + ":" + minute)
				.build();

		return notification;
	}

}
