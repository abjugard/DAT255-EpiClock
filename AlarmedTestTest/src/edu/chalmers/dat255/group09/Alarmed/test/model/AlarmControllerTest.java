package edu.chalmers.dat255.group09.Alarmed.test.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.test.AndroidTestCase;
import edu.chalmers.dat255.group09.Alarmed.database.AlarmHandler;
import edu.chalmers.dat255.group09.Alarmed.model.Alarm;

public class AlarmControllerTest extends AndroidTestCase {

	
	private class MockAlarmHandler implements AlarmHandler{
		private int nbrID;
		List<Alarm> alarms;
		public AlarmHandler openCon() {
			alarms = new ArrayList<Alarm>();
			return this;
		}

		public void closeCon() {
			alarms = null;
		}

		public long createAlarm(int hour, int minute, boolean recurring) {
			Alarm alarm = new Alarm(hour, minute, nbrID++);
			alarms.add(alarm);
			return alarm.getId();
		}

		public boolean deleteAlarm(int alarmID) {
			for (Alarm alarm : alarms) {
				if(alarm.getId() == alarmID){
					alarms.remove(alarm);
					return true;
				}
			}
			return false;
		}

		public Alarm fetchFirstEnabledAlarm() {
			List<Alarm> sortedList = new ArrayList<Alarm>();
			Collections.copy(sortedList, alarms);
			Collections.sort(sortedList);
			for (Alarm alarm : sortedList) {
				if(alarm.isEnabled()){
					return alarm;
				}
			}
			return null;
		}

		public List<Alarm> fetchAllAlarms() {
			return null;
		}

		public Alarm fetchAlarm(int alarmID) {
			return null;
		}

		public int getNumberOfAlarms() {
			return 0;
		}

		public boolean isEnabled(int id) {
			return false;
		}

		public boolean enableAlarm(int id, boolean enable) {
			return false;
		}
		
	}
}
