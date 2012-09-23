package edu.chalmers.dat255.group09.Alarmed.test.model;

import java.util.Calendar;

import android.test.AndroidTestCase;
import edu.chalmers.dat255.group09.Alarmed.model.AlarmTime;

public class AlarmTimeTest extends AndroidTestCase {

	public void testAlarmTimeAfterFourHours() {

		int hoursToAlarmShouldTrigger = 4;
		int minutesToAlarmShuoldTrigger = 0;

		int alarmHours = getAlarmHours(hoursToAlarmShouldTrigger);
		int alarmMinutes = getAlarmMinutes(minutesToAlarmShuoldTrigger);

		long expectedAlarmTime = getExpectedAlarmTime(alarmHours, alarmMinutes);

		long actualAlarmTime = new AlarmTime(alarmHours, alarmMinutes)
				.getTimeInMilliSeconds();

		assertEquals(expectedAlarmTime, actualAlarmTime);
	}

	public void testAlarmTimeAfterTwelveHours() {

		int hoursToAlarmShouldTrigger = 12;
		int minutesToAlarmShuoldTrigger = 0;

		int alarmHours = getAlarmHours(hoursToAlarmShouldTrigger);
		int alarmMinutes = getAlarmMinutes(minutesToAlarmShuoldTrigger);

		long expectedAlarmTime = getExpectedAlarmTime(alarmHours, alarmMinutes);

		long actualAlarmTime = new AlarmTime(alarmHours, alarmMinutes)
				.getTimeInMilliSeconds();
		assertEquals(expectedAlarmTime, actualAlarmTime);
	}

	public void testAlarmTimeAfterTenHoursThrityMinutes() {
		int hoursToAlarmShouldTrigger = 10;
		int minutesToAlarmShuoldTrigger = 30;

		int alarmHours = getAlarmHours(hoursToAlarmShouldTrigger);
		int alarmMinutes = getAlarmMinutes(minutesToAlarmShuoldTrigger);

		long expectedAlarmTime = getExpectedAlarmTime(alarmHours, alarmMinutes);

		long actualAlarmTime = new AlarmTime(alarmHours, alarmMinutes)
				.getTimeInMilliSeconds();
		assertEquals(expectedAlarmTime, actualAlarmTime);
	}

	private int getAlarmMinutes(int minutesToAlarmShuoldTrigger) {
		Calendar cal = Calendar.getInstance();
		return (cal.get(Calendar.MINUTE) + minutesToAlarmShuoldTrigger) % 60;
	}

	private int getAlarmHours(int hoursToAlarmShouldTrigger) {
		Calendar cal = Calendar.getInstance();
		return (cal.get(Calendar.HOUR_OF_DAY) + hoursToAlarmShouldTrigger) % 24;
	}

	private long getExpectedAlarmTime(int alarmHours, int alarmMinutes) {

		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.HOUR_OF_DAY, alarmHours);
		cal.set(Calendar.MINUTE, alarmMinutes);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTimeInMillis();
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
