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
import java.util.List;

import android.test.AndroidTestCase;
import android.test.mock.MockContext;
import edu.chalmers.dat255.group09.Alarmed.controller.AlarmController;
import edu.chalmers.dat255.group09.Alarmed.database.AlarmHandler;
import edu.chalmers.dat255.group09.Alarmed.model.Alarm;

public class AlarmControllerTest extends AndroidTestCase {
	AlarmController ac;
	MockContext context;
	AlarmHandler handler;
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		ac = AlarmController.getInstance();
		context = new MockContext();
		handler = new MockAlarmHandler().openCon();
		ac.init(context, handler);
	}
	
	public void testDestroy() {
		setUpAlarms();
		ac.destroy();
		assertNull(handler.fetchAllAlarms());
	}

	public void testDeleteAlarm() {
		setUpAlarms();
		assertTrue(ac.deleteAlarm(2));
		assertNull(handler.fetchAlarm(2));
		assertFalse(ac.deleteAlarm(2));
	}

	public void testGetAllAlarms() {
		setUpAlarms();
		List<Alarm> list = ac.getAllAlarms();
		assertEquals(2, list.size());
		for (Alarm alarm : list) {
			assertNotNull(alarm);
		}
	}

	public void testAlarmReceived() {
		setUpAlarms();
		assertFalse(ac.alarmReceived(3));
		ac.alarmReceived(2);
		assertFalse(handler.isEnabled(2));
	}
	public void testAlarmEnabled() {
		setUpAlarms();
		assertTrue(ac.enableAlarm(1, false));
		assertFalse(ac.enableAlarm(2, true));
		assertFalse(handler.isEnabled(1));
	}
	public void testCreateAlarm() {
		setUpAlarms();
		assertNotNull(handler.fetchAlarm(1));
		assertNotNull(handler.fetchAlarm(2));
	}
	private void setUpAlarms(){
		ac.createAlarm(10, 10);
		ac.createAlarm(20, 20);
	}



	private class MockAlarmHandler implements AlarmHandler {
		private int nbrID;
		private List<Alarm> alarms;

		public AlarmHandler openCon() {
			alarms = new ArrayList<Alarm>();
			return this;
		}

		public void closeCon() {
			alarms = null;
			nbrID = 0;
		}

		public long createAlarm(int hour, int minute, boolean recurring) {
			Alarm alarm = new Alarm(hour, minute, ++nbrID);
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
					boolean change = alarm.isEnabled() != enable;
					alarm.setEnabled(enable);
					return change;
				}
			}
			return false;
		}

	}
}
