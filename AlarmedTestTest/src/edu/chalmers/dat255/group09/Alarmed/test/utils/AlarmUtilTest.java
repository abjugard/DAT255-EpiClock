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
package edu.chalmers.dat255.group09.Alarmed.test.utils;

import java.util.Calendar;

import junit.framework.TestCase;
import android.util.Log;
import edu.chalmers.dat255.group09.Alarmed.utils.AlarmUtils;

/**
 * Test class for AlarmUtil.
 * 
 * @author Daniel Augurell
 * 
 */
public class AlarmUtilTest extends TestCase {

	public void testGetBooleanArray() {
		String bits = "1110110";
		boolean[] array = AlarmUtils.getBooleanArray(Integer.parseInt(bits, 2));
		for (int i = 0; i < array.length; i++) {
			if (bits.charAt(array.length - 1 - i) == '1') {
				assertEquals(true, array[i]);
			} else {
				assertEquals(false, array[i]);
			}

		}
	}

	public void testGetMinutesToTime() {
		int minute = Calendar.getInstance().get(Calendar.MINUTE);
		final int minuteToAdd = 10;
		int minuteToTime = (minute + minuteToAdd) % AlarmUtils.MINUTES_OF_HOUR;
		assertEquals(minuteToAdd, AlarmUtils.getMinutesToTime(minuteToTime));
	}

	public void testGetHoursToTime() {
		int minute = Calendar.getInstance().get(Calendar.MINUTE);
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		final int minuteToAdd = 10;
		final int hoursToAdd = 10;
		int minuteToTime = (minute + minuteToAdd) % AlarmUtils.MINUTES_OF_HOUR;
		int hourToTime = (hour + hoursToAdd) % AlarmUtils.HOUR_OF_DAY;
		if (minuteToTime < minute) {
			hourToTime = (hourToTime + 1) % AlarmUtils.HOUR_OF_DAY;
		}
		assertEquals(hoursToAdd,
				AlarmUtils.getHoursToTime(hourToTime, minuteToTime));
	}

	public void testChangeToCalendar() {
		boolean[] days = { true, false, false, false, false, false, true };
		boolean[] calDays = AlarmUtils.changeToCalendar(days);
		assertEquals(days[days.length - 1], calDays[0]);
		for (int i = 0; i < calDays.length - 1; i++) {
			assertEquals(days[i], calDays[i + 1]);
		}
	}

	public void testGetDaysToNextDay() {
		final int currentDay = 6;
		String bits = "1000101";
		final int days = AlarmUtils.getDaysToNextDay(currentDay,
				Integer.parseInt(bits, 2));
		assertEquals(2, days);
	}

	public void testGetIntegerFromBooleanArray() {
		boolean[] days = { true, false, false, false, false, false, true };
		final int intDays = AlarmUtils.getIntegerFromBooleanArray(days);
		final int expextedDays = Integer.parseInt("1000001", 2);
		assertEquals(expextedDays, intDays);

	}

	public void testAddDaysNone() {
		Calendar cal = Calendar.getInstance();
		final int currentDay = cal.get(Calendar.DAY_OF_YEAR);
		String bits1 = "0000000";
		AlarmUtils.addDays(cal, Integer.parseInt(bits1, 2));
		assertEquals(currentDay, cal.get(Calendar.DAY_OF_YEAR));
	}

	public void testAddDays() {
		Calendar cal = Calendar.getInstance();
		final int currentDay = cal.get(Calendar.DAY_OF_YEAR);
		StringBuilder bits = new StringBuilder("0000000");
		int currDay = cal.get(Calendar.DAY_OF_WEEK) - 2;
		if (currDay == -1) {
			currDay = AlarmUtils.DAYS_OF_WEEK - 1;
		}
		bits.setCharAt(AlarmUtils.DAYS_OF_WEEK - 1 - (currDay + 1)
				% AlarmUtils.DAYS_OF_WEEK, '1');
		AlarmUtils.addDays(cal, Integer.parseInt(bits.toString(), 2));
		Log.w("Bits", bits.toString());
		Log.w("day", "" + (currDay + 1));
		assertEquals(currentDay + 1, cal.get(Calendar.DAY_OF_YEAR));
	}
}
