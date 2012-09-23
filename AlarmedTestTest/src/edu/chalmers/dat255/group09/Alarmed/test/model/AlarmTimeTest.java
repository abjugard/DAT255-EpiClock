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

	public void testIllegalHourInput() {
		try {
			new AlarmTime(24, 23);
			fail("Should generate an illegalargumentexpection");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	public void testNegativeHourInput() {
		try {
			new AlarmTime(-3, 23);
			fail("Should generate an illegalargumentexpection");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}

	}

	public void testIllegalMinuteInput() {
		try {
			new AlarmTime(0, 60);
			fail("Should generate an illegalargumentexpection");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}

	}

	public void testNegativeMinuteInput() {
		try {
			new AlarmTime(22, -60);
			fail("Should generate an illegalargumentexpection");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}

	}

	public void testNegativeHourMinuteInput() {
		try {
			new AlarmTime(-1, -2);
			fail("Should generate an illegalargumentexpection");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}

	}

	public void testIllegalHourMinuteInput() {
		try {
			new AlarmTime(24, 60);
			fail("Should generate an illegalargumentexpection");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}
}
