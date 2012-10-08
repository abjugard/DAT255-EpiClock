/*
 * Copyright (C) 2012 Joakim Persson, Daniel Augurell, Adrian Bjugård, Andreas Rolén
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
package edu.chalmers.dat255.group09.Alarmed.test.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.test.AndroidTestCase;
import edu.chalmers.dat255.group09.Alarmed.database.AlarmHandler;
import edu.chalmers.dat255.group09.Alarmed.model.Alarm;

public class AlarmControllerTest extends AndroidTestCase {
	
	
	

	private class MockAlarmHandler implements AlarmHandler {
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
				if (alarm.getId() == alarmID) {
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
				if (alarm.isEnabled()) {
					return alarm;
				}
			}
			return null;
		}

		public List<Alarm> fetchAllAlarms() {
			return alarms;
		}

		public Alarm fetchAlarm(int alarmID) {
			for (Alarm alarm : alarms) {
				if (alarm.getId() == alarmID) {
					return alarm;
				}
			}
			return null;
		}

		public int getNumberOfAlarms() {
			return alarms.size();
		}

		public boolean isEnabled(int id) {
			for (Alarm alarm : alarms) {
				if (alarm.getId() == id) {
					return alarm.isEnabled();
				}
			}
			return false;
		}

		public boolean setAlarmEnabled(int id, boolean enable) {

			for (Alarm alarm : alarms) {
				if (alarm.getId() == id) {
					boolean change = alarm.isEnabled() == enable;
					alarm.setEnabled(enable);
					return change;
				}
			}
			return false;
		}

	}
}
