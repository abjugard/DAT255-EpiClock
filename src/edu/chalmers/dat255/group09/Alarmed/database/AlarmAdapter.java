package edu.chalmers.dat255.group09.Alarmed.database;

import java.util.List;

import edu.chalmers.dat255.group09.Alarmed.model.Alarm;

public interface AlarmAdapter {
	public long createAlarm(int hour, int minute, boolean recurring);
	public boolean deleteAlarm(int alarmID);
	public List<Alarm> fetchAllAlarms();
	public Alarm fetchAlarm(int alarmID);
}
