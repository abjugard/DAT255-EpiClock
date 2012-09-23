package edu.chalmers.dat255.group09.Alarmed.test.model;


import java.util.Calendar;

import android.test.AndroidTestCase;
import edu.chalmers.dat255.group09.Alarmed.model.AlarmTime;

public class AlarmTimeTest extends AndroidTestCase {

	private final long ONE_HOUR_IN_MILLI_SECONDS = 3600000;
	private final long ONE_MINUTE_IN_MILLI_SECONDS = 60000;

	/*
	 * Since the alarm will triggerd at hh:mm:00, and we are testing that only
	 * the hh and mm will be equal. Since we are using milliseconds the delta
	 * will be 60s = 60ms == 60000
	 */
	
	public void testAlarmTimeAfterFourHours() {
		int hoursToAdd = 4;
		int minutesToAdd = 0;
		long expectedTime = getExpectedTime(hoursToAdd, minutesToAdd);
		long actualTime = getActualTime(hoursToAdd, minutesToAdd);
		assertEquals(expectedTime, actualTime, 60000);
	}

	public void testAlarmTimeAfterTwelveHours() {
		int hoursToAdd = 12;
		int minutesToAdd = 0;
		long expectedTime = getExpectedTime(hoursToAdd, minutesToAdd);
		long actualTime = getActualTime(hoursToAdd, minutesToAdd);
		assertEquals(expectedTime, actualTime, 60000);
	}

	public void testAlarmTimeAfterTenHoursThrityMinutes() {
		int hoursToAdd = 10;
		int minutesToAdd = 30;
		long expectedTime = getExpectedTime(hoursToAdd, minutesToAdd);
		long actualTime = getActualTime(hoursToAdd, minutesToAdd);
		assertEquals(expectedTime, actualTime, 60000);
	}

	public void testAlarmTimeAfterTwentyHoursFiftyMinutes() {

		int minutesToAdd = 52;
		int hoursToAdd = 20;

		long expectedTime = getExpectedTime(hoursToAdd, minutesToAdd);
		long actualTime = getActualTime(hoursToAdd, minutesToAdd);
		assertEquals(expectedTime, actualTime, 60000);
	}

	private long getActualTime(int hoursToAdd, int minutesToAdd) {

		Calendar cal = Calendar.getInstance();

		int currentTime = cal.get(Calendar.HOUR_OF_DAY);
		int currentMinute = cal.get(Calendar.MINUTE);

		if (minutesToAdd + currentMinute >= 60) {
			hoursToAdd++;
		}

		int hours = (currentTime + hoursToAdd) % 24;
		int minutes = (currentMinute + minutesToAdd) % 60;

		return new AlarmTime(hours, minutes).getTimeInMilliSeconds();
	}

	private long getExpectedTime(int hoursToAdd, int minutesToAdd) {
		long hoursToAlarm = hoursToAdd * ONE_HOUR_IN_MILLI_SECONDS;
		long minutesToAlarm = minutesToAdd * ONE_MINUTE_IN_MILLI_SECONDS;

		long expectedTime = System.currentTimeMillis();
		expectedTime += hoursToAlarm;
		expectedTime += minutesToAlarm;

		return expectedTime;
	}

	/*
	 * The toString() will return a different format depending on how long it's
	 * left. The format has 8 different formats.
	 */

	public void testOneMinuteToString() {
		Calendar cal = Calendar.getInstance();
		AlarmTime at = new AlarmTime(cal.get(Calendar.HOUR_OF_DAY),
				(cal.get(Calendar.MINUTE) + 1) % 60);
		String toMatch = "Alarm is set for 1 minute from now.";
		assertEquals(at.toString(), toMatch);
	}

	public void testThirtyMinutesToString() {
		Calendar cal = Calendar.getInstance();
		AlarmTime at = new AlarmTime(cal.get(Calendar.HOUR_OF_DAY),
				(cal.get(Calendar.MINUTE) + 30) % 60);
		String toMatch = "Alarm is set for 30 minutes from now.";
		assertEquals(at.toString(), toMatch);
	}

	public void testOneHourToString() {
		Calendar cal = Calendar.getInstance();
		AlarmTime at = new AlarmTime((cal.get(Calendar.HOUR_OF_DAY) + 1) % 24,
				cal.get(Calendar.MINUTE));
		String toMatch = "Alarm is set for 1 hour from now.";
		assertEquals(at.toString(), toMatch);
	}

	public void testFiveHoursToString() {
		Calendar cal = Calendar.getInstance();
		AlarmTime at = new AlarmTime((cal.get(Calendar.HOUR_OF_DAY) + 5) % 24,
				cal.get(Calendar.MINUTE));
		String toMatch = "Alarm is set for 5 hours from now.";
		assertEquals(at.toString(), toMatch);
	}

	public void testOneHourOneMinuteToString() {
		Calendar cal = Calendar.getInstance();
		AlarmTime at = new AlarmTime((cal.get(Calendar.HOUR_OF_DAY) + 1) % 24,
				(cal.get(Calendar.MINUTE) + 1) % 60);
		String toMatch = "Alarm is set for 1 hour and 1 minute from now.";
		assertEquals(at.toString(), toMatch);
	}

	public void testOneHourThirtyMinuteToString() {
		Calendar cal = Calendar.getInstance();
		AlarmTime at = new AlarmTime(
				((cal.get(Calendar.HOUR_OF_DAY) + 1) % 24),
				(cal.get(Calendar.MINUTE) + 30) % 60);
		String toMatch = "Alarm is set for 1 hour and 30 minutes from now.";
		assertEquals(at.toString(), toMatch);
	}

	public void testFiveHourOneMinuteToString() {
		Calendar cal = Calendar.getInstance();
		AlarmTime at = new AlarmTime(
				((cal.get(Calendar.HOUR_OF_DAY) + 5) % 24),
				(cal.get(Calendar.MINUTE) + 1) % 60);
		String toMatch = "Alarm is set for 5 hours and 1 minute from now.";
		assertEquals(at.toString(), toMatch);
	}

	public void testFiveHourThirtyMinuteToString() {
		Calendar cal = Calendar.getInstance();
		AlarmTime at = new AlarmTime(
				((cal.get(Calendar.HOUR_OF_DAY) + 5) % 24),
				(cal.get(Calendar.MINUTE) + 30) % 60);
		String toMatch = "Alarm is set for 5 hours and 30 minutes from now.";
		assertEquals(at.toString(), toMatch);
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
