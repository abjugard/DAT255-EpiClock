/*
 * Copyright (C) 2012 Joakim Persson, Daniel Augurell, Adrian BjugŒrd, Andreas RolŽn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.chalmers.dat255.group09.Alarmed.controller;

import java.util.List;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import edu.chalmers.dat255.group09.Alarmed.database.AlarmHandler;
import edu.chalmers.dat255.group09.Alarmed.database.DatabaseHandler;
import edu.chalmers.dat255.group09.Alarmed.model.Alarm;
import edu.chalmers.dat255.group09.Alarmed.receiver.AlarmReceiver;

public class AlarmController {

	private static AlarmController instance;
	private AlarmHandler alarmHandler;

	private Context context;

	public static AlarmController getInstance() {
		if (instance == null) {
			instance = new AlarmController();
		}
		return instance;
	}

	public void init(Context context) {
		this.context = context;
		alarmHandler = new DatabaseHandler(context).openCon();
	}

	public void init(Context context, AlarmHandler handler) {
		this.context = context;
		alarmHandler = handler.openCon();
	}

	public void createAlarm(int hour, int minute, int dayOfWeek, String module) {
		alarmHandler.createAlarm(hour, minute, dayOfWeek, module);
		setAlarm();
	}

	private void setAlarm() {
		Alarm nextAlarm = alarmHandler.fetchFirstEnabledAlarm();
		if (nextAlarm != null) {
			addAlarmToAlarmManager(nextAlarm);
		}

	}

	private void addAlarmToAlarmManager(Alarm alarm) {
		PendingIntent sender = createAlarmPendingIntent(alarm);
		AlarmManager alarmManager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC_WAKEUP,
				alarm.getTimeInMilliSeconds(), sender);
	}

	private void removeAlarmFromAlarmManager(Alarm alarm) {
		PendingIntent sender = createAlarmPendingIntent(alarm);
		AlarmManager alarmManager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		alarmManager.cancel(sender);
	}

	private PendingIntent createAlarmPendingIntent(Alarm nextAlarm) {
		Intent intent = new Intent(context, AlarmReceiver.class);
		intent.setData(Uri.parse("" + nextAlarm.getId()));
		intent.putExtra("module", nextAlarm.getModule());
		PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent,
				Intent.FILL_IN_DATA);
		return sender;
	}

	public boolean alarmReceived(int id) {
		Alarm alarm = alarmHandler.fetchAlarm(id);
		if (alarm != null && alarm.isEnabled() && alarm.getDaysOfWeek() == 0) {
			alarmHandler.setAlarmEnabled(id, false);
		}
		if (alarmHandler.getNumberOfAlarms() > 0) {
			setAlarm();
		}
		if (alarm == null) {
			return false;
		}
		return true;
	}

	public boolean deleteAlarm(int id) {
		removeAlarmFromAlarmManager(alarmHandler.fetchAlarm(id));
		boolean removed = alarmHandler.deleteAlarm(id);
		setAlarm();
		return removed;
	}

	public boolean isAlarmEnabled(int id) {
		return alarmHandler.isEnabled(id);
	}

	public boolean enableAlarm(int id, boolean enable) {
		removeAlarmFromAlarmManager(alarmHandler.fetchAlarm(id));
		boolean enabled = alarmHandler.setAlarmEnabled(id, enable);
		setAlarm();
		return enabled;
	}

	public List<Alarm> getAllAlarms() {
		return alarmHandler.fetchAllAlarms();
	}

	public void destroy() {
		alarmHandler.closeCon();
	}

}
