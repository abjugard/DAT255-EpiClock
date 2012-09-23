package edu.chalmers.dat255.group09.Alarmed.test.model;

import java.util.Calendar;

import android.test.AndroidTestCase;
import edu.chalmers.dat255.group09.Alarmed.model.AlarmTime;

public class AlarmTimeTest extends AndroidTestCase {
	
	private AlarmTime alarm;
	

	public void testGetAlarmTimeInMilliSeconds() {

		int alarmHour = 20;
		int alarmMinute = 20;

		alarm = new AlarmTime(alarmHour, alarmMinute);

		long expectedAlarmTime = getExpectedAlarmTime(alarmHour, alarmMinute);
		long actualAlarmTime = alarm.getAlarmTimeInMilliSeconds();
		assertEquals(expectedAlarmTime, actualAlarmTime);

		alarmHour = 10;
		alarmMinute = 10;

		alarm = new AlarmTime(alarmHour, alarmMinute);

		expectedAlarmTime = getExpectedAlarmTime(alarmHour, alarmMinute);
		actualAlarmTime = alarm.getAlarmTimeInMilliSeconds();
		assertEquals(expectedAlarmTime, actualAlarmTime);

		alarmHour = 0;
		alarmMinute = 0;

		alarm = new AlarmTime(alarmHour, alarmMinute);

		expectedAlarmTime = getExpectedAlarmTime(alarmHour, alarmMinute);
		actualAlarmTime = alarm.getAlarmTimeInMilliSeconds();
		assertEquals(expectedAlarmTime, actualAlarmTime);

	}

	private long getExpectedAlarmTime(int alarmHour, int alarmMinute) {
		long currentTime = getCurrentTimeInMilliSeconds();
		long alarmTime = getTimeInMilliSeconds(alarmHour, alarmMinute, 0);
		long timeDifference = Math.abs((currentTime - alarmTime));

		return System.currentTimeMillis() + timeDifference;
	}

	private long getCurrentTimeInMilliSeconds() {
		Calendar cal = Calendar.getInstance();
		int currentHour = cal.get(Calendar.HOUR_OF_DAY);
		int currentMinute = cal.get(Calendar.MINUTE);
		int currentSeconds = cal.get(Calendar.SECOND);

		return getTimeInMilliSeconds(currentHour, currentMinute, currentSeconds);

	}

	private long getTimeInMilliSeconds(int hours, int minutes, int seconds) {
		long currentTime = hours * 3600000;
		currentTime += minutes * 60000;
		currentTime += seconds * 1000;
		return currentTime;
	}
}
