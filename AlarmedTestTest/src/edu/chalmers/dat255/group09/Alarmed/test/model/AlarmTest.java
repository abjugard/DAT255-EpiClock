/*
 * Copyright (C) 2012 Joakim Persson, Daniel Augurell, Adrian Bjugard, Andreas Rolen
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

import java.util.Calendar;

import junit.framework.TestCase;
import edu.chalmers.dat255.group09.Alarmed.model.Alarm;

/**
 * Class for testing the Alarm class.
 * 
 * @author Daniel Augurell
 * @author Joakim Persson
 */
public class AlarmTest extends TestCase {

	private static final int HOURS_OF_DAY = 24;
	private static final int MINUTES_OF_HOUR = 60;
	private static final int MILLISECONDS_OF_MINUTE = 60000;
	private static final long ONE_HOUR_IN_MILLI_SECONDS = 3600000;

	/*
	 * Since the alarm will triggered at hh:mm:00, and we are testing that only
	 * the hh and mm will be equal. Since we are using milliseconds the delta
	 * will be 60s = 60ms == 60000.
	 */
	public void testAlarmTimeAfterFourHours() {
		final int hoursToAdd = 4;
		final int minutesToAdd = 0;
		long currentTime = System.currentTimeMillis();
		long expectedTime = getExpectedTime(hoursToAdd, minutesToAdd)
				- currentTime;
		long actualTime = getActualTime(hoursToAdd, minutesToAdd) - currentTime;
		assertEquals(expectedTime, actualTime, AlarmTest.MILLISECONDS_OF_MINUTE);
	}

	public void testAlarmTimeAfterTwelveHours() {
		final int hoursToAdd = 12;
		final int minutesToAdd = 0;
		long currentTime = System.currentTimeMillis();
		long expectedTime = getExpectedTime(hoursToAdd, minutesToAdd)
				- currentTime;
		long actualTime = getActualTime(hoursToAdd, minutesToAdd) - currentTime;
		assertEquals(expectedTime, actualTime, AlarmTest.MILLISECONDS_OF_MINUTE);
	}

	public void testAlarmTimeAfterTenHoursThrityMinutes() {
		final int hoursToAdd = 10;
		final int minutesToAdd = 30;
		long currentTime = System.currentTimeMillis();
		long expectedTime = getExpectedTime(hoursToAdd, minutesToAdd)
				- currentTime;
		long actualTime = getActualTime(hoursToAdd, minutesToAdd) - currentTime;

		assertEquals(expectedTime, actualTime, AlarmTest.MILLISECONDS_OF_MINUTE);
	}

	public void testAlarmTimeAfterTwentyHoursFiftyMinutes() {

		final int minutesToAdd = 52;
		final int hoursToAdd = 20;
		long currentTime = System.currentTimeMillis();
		long expectedTime = getExpectedTime(hoursToAdd, minutesToAdd)
				- currentTime;
		long actualTime = getActualTime(hoursToAdd, minutesToAdd) - currentTime;
		assertEquals(expectedTime, actualTime, AlarmTest.MILLISECONDS_OF_MINUTE);
	}

	/**
	 * Generates an alarm with the specified time to add and returns how long
	 * time it's left.
	 * 
	 * @param hoursToAdd
	 *            How many hours to add
	 * @param minutesToAdd
	 *            How many minutes to add
	 * @return The time in milliseconds
	 */

	private long getActualTime(int hoursToAdd, int minutesToAdd) {

		Calendar cal = Calendar.getInstance();

		int currentTime = cal.get(Calendar.HOUR_OF_DAY);
		int currentMinute = cal.get(Calendar.MINUTE);

		if (minutesToAdd + currentMinute >= AlarmTest.MINUTES_OF_HOUR) {
			hoursToAdd++;
		}

		int hours = (currentTime + hoursToAdd) % AlarmTest.HOURS_OF_DAY;
		int minutes = (currentMinute + minutesToAdd)
				% AlarmTest.MINUTES_OF_HOUR;

		return new Alarm(hours, minutes, 0).getTimeInMilliSeconds();
	}

	/**
	 * Calculates the time that the alarm should activate on.
	 * 
	 * @param hoursToAdd
	 *            How many hours to add
	 * @param minutesToAdd
	 *            How many minutes to add
	 * @return The time in milliseconds
	 */
	private long getExpectedTime(int hoursToAdd, int minutesToAdd) {
		long hoursToAlarm = hoursToAdd * ONE_HOUR_IN_MILLI_SECONDS;
		long minutesToAlarm = minutesToAdd * MILLISECONDS_OF_MINUTE;

		long expectedTime = System.currentTimeMillis();
		expectedTime += hoursToAlarm;
		expectedTime += minutesToAlarm;

		return expectedTime;
	}

	/**
	 * Creates an alarm to be activated in the added time.
	 * 
	 * @param minute
	 *            Minutes till the next alarm
	 * @param hour
	 *            Hours till the next alarm
	 * @return An alarm set to be triggered at the added time
	 */
	private Alarm alarmTimeFromNow(int minute, int hour) {
		Calendar cal = Calendar.getInstance();
		int minuteToTrigger = cal.get(Calendar.MINUTE) + minute;
		int hourToTrigger = cal.get(Calendar.HOUR_OF_DAY) + hour;
		if (minuteToTrigger >= AlarmTest.MINUTES_OF_HOUR) {
			hourToTrigger++;
		}
		return new Alarm((hourToTrigger % AlarmTest.HOURS_OF_DAY),
				(minuteToTrigger % AlarmTest.MINUTES_OF_HOUR), 0);
	}

	/*
	 * The toString() will return a different format depending on how long it's
	 * left. The string has 8 different formats.
	 */
	public void testOneMinuteToString() {
		Alarm at = alarmTimeFromNow(1, 0);
		String toMatch = "Alarm is set for 1 minute from now.";
		assertEquals(at.getTimeToNextAlarmString(), toMatch);
	}

	public void testThirtyMinuteToString() {
		Alarm at = alarmTimeFromNow(30, 0);
		String toMatch = "Alarm is set for 30 minutes from now.";
		assertEquals(at.getTimeToNextAlarmString(), toMatch);
	}

	public void testOneHourToString() {
		Alarm at = alarmTimeFromNow(0, 1);
		String toMatch = "Alarm is set for 1 hour from now.";
		assertEquals(at.getTimeToNextAlarmString(), toMatch);
	}

	public void testFiveHoursToString() {
		Alarm at = alarmTimeFromNow(0, 5);
		String toMatch = "Alarm is set for 5 hours from now.";
		assertEquals(at.getTimeToNextAlarmString(), toMatch);
	}

	public void testOneHourOneMinuteToString() {
		Alarm at = alarmTimeFromNow(1, 1);
		String toMatch = "Alarm is set for 1 hour and 1 minute from now.";
		assertEquals(at.getTimeToNextAlarmString(), toMatch);
	}

	public void testOneHourThirtyMinuteToString() {
		Alarm at = alarmTimeFromNow(30, 1);
		String toMatch = "Alarm is set for 1 hour and 30 minutes from now.";
		assertEquals(at.getTimeToNextAlarmString(), toMatch);
	}

	public void testFiveHourOneMinuteToString() {
		Alarm at = alarmTimeFromNow(1, 5);
		String toMatch = "Alarm is set for 5 hours and 1 minute from now.";
		assertEquals(at.getTimeToNextAlarmString(), toMatch);
	}

	public void testFiveHourThirtyMinuteToString() {
		Alarm at = alarmTimeFromNow(30, 5);
		String toMatch = "Alarm is set for 5 hours and 30 minutes from now.";
		assertEquals(at.getTimeToNextAlarmString(), toMatch);
	}

	public void testTwentyThreeHourThirtyMinuteToString() {
		Alarm at = alarmTimeFromNow(30, 23);
		String toMatch = "Alarm is set for 23 hours and 30 minutes from now.";
		assertEquals(at.getTimeToNextAlarmString(), toMatch);
	}

	public void testTwentyFourHourToString() {
		Alarm at = alarmTimeFromNow(0, AlarmTest.HOURS_OF_DAY);
		String toMatch = "Alarm is set for 24 hours from now.";
		assertEquals(at.getTimeToNextAlarmString(), toMatch);
	}

	public void testToString() {
		final int hour = 23;
		final int minute = 23;
		Alarm at = new Alarm(hour, minute, 0);
		assertEquals(at.toString(), "23:23");
		final int otherHour = 3;
		final int otherMinute = 3;
		Alarm at2 = new Alarm(otherHour, otherMinute, 0);
		assertEquals(at2.toString(), "03:03");
	}

	public void testIllegalHourInput() {
		final int minute = 23;
		try {
			new Alarm(AlarmTest.HOURS_OF_DAY, minute, 0);
			fail("Should generate an illegalargumentexpection");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	public void testNegativeHourInput() {
		final int minutes = 23;
		final int hours = -3;
		try {
			new Alarm(hours, minutes, 0);
			fail("Should generate an illegalargumentexpection");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}

	}

	public void testIllegalMinuteInput() {
		try {
			new Alarm(0, AlarmTest.MINUTES_OF_HOUR, 0);
			fail("Should generate an illegalargumentexpection");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}

	}

	public void testNegativeMinuteInput() {
		final int hours = 22;
		try {
			new Alarm(hours, -AlarmTest.MINUTES_OF_HOUR, 0);
			fail("Should generate an illegalargumentexpection");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}

	}

	public void testNegativeHourMinuteInput() {
		final int minutes = -2;
		final int hours = -1;
		try {
			new Alarm(hours, minutes, 0);
			fail("Should generate an illegalargumentexpection");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}

	}

	public void testIllegalHourMinuteInput() {
		try {
			new Alarm(AlarmTest.HOURS_OF_DAY, AlarmTest.MINUTES_OF_HOUR, 0);
			fail("Should generate an illegalargumentexpection");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	public void testGetID() {
		final int minutes = 20;
		final int hours = 20;
		Alarm alarm = new Alarm(hours, minutes, 1);
		int actual = 1;
		assertEquals(alarm.getId(), actual);
	}

	public void testGetAlarmHours() {
		final int minutes = 20;
		final int hours = 20;
		Alarm alarm = new Alarm(hours, minutes, 1);
		assertEquals(alarm.getAlarmHours(), hours);
	}

	public void testGetAlarmMinutes() {
		final int minutes = 20;
		final int hours = 20;
		Alarm alarm = new Alarm(hours, minutes, 1);
		assertEquals(alarm.getAlarmMinutes(), minutes);
	}

	public void testHashCode() {
		final int minutes = 20;
		final int hours = 20;
		final int id = 2;
		Alarm alarm = new Alarm(hours, minutes, 1);
		Alarm otherAlarm = new Alarm(hours + 1, minutes, 1);

		assertFalse(alarm.hashCode() == otherAlarm.hashCode());

		otherAlarm = new Alarm(hours, minutes, id);
		assertTrue(alarm.hashCode() == otherAlarm.hashCode());
	}

	public void testEquals() {
		final int minutes = 20;
		final int hours = 20;
		final int id = 2;
		Alarm alarm = new Alarm(hours, minutes, 1);
		Alarm otherAlarm = new Alarm(hours + 1, minutes, id);

		// // Testing for self refrence and null
		assertTrue(alarm.equals(alarm));
		assertFalse(alarm.equals(null));

		assertFalse(alarm.equals(otherAlarm));

		otherAlarm = new Alarm(hours, minutes, id);
		assertTrue(alarm.equals(otherAlarm));
	}

	public void testCompareTo() {
		int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

		// Sets the test to be set on different times depending on the current
		// time
		int hour = 0;
		if (currentHour > 12) {
			hour = 6;
		} else {
			hour = 18;
		}

		final int minute = 20;
		Alarm alarm = new Alarm(hour, minute, 1);
		Alarm otherAlarm = new Alarm(hour + 1, minute, 1);

		assertTrue(alarm.compareTo(otherAlarm) < 0);

		otherAlarm = new Alarm(hour, minute, 2);

		assertTrue(alarm.compareTo(otherAlarm) == 0);

		otherAlarm = new Alarm(hour - 1, minute - 1, 2);

		assertTrue(alarm.compareTo(otherAlarm) > 0);
	}

	public void testEnabled() {

		final int minutes = 20;
		final int hours = 20;
		Alarm alarm = new Alarm(hours, minutes, 1);

		assertTrue(alarm.isEnabled());

		alarm.setEnabled(false);

		assertFalse(alarm.isEnabled());

		alarm.setEnabled(true);

		assertTrue(alarm.isEnabled());
	}

	public void testModule() {
		final int minutes = 20;
		final int hours = 20;
		String moduleName = "module";
		Alarm alarm = new Alarm(hours, minutes, 1, moduleName, 0);

		assertEquals(alarm.getModule(), moduleName);

		alarm.setModule("module2");

		assertFalse(alarm.getModule().equals(moduleName));
	}

	public void testVolume() {
		final int volume = 6;
		String moduleName = "module";
		final int otherVolume = 5;
		final int minutes = 20;
		final int hours = 20;
		Alarm alarm = new Alarm(hours, minutes, 1, moduleName, volume);
		assertTrue(volume == alarm.getVolume());
		alarm.setVolume(otherVolume);
		assertTrue(volume != alarm.getVolume());

	}

	public void testVibration() {
		Alarm alarm = new Alarm(20, 20, 1);
		assertFalse(alarm.isVibrationEnabled());
		alarm.setVibrationEnabled(true);
		assertTrue(alarm.isVibrationEnabled());
	}

	public void testAlarmTone() {
		Alarm alarm = new Alarm(20, 20, 1);
		String toneUri = "super awesome ringtone";
		alarm.setToneUri(toneUri);
		assertEquals(alarm.getToneUri(), toneUri);
	}
}
