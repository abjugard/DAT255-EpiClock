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
package edu.chalmers.dat255.group09.Alarmed.model;

import java.util.Calendar;

/**
 * The model of the alarm.
 * 
 * @author Joakim Persson
 * @author Daniel Augurell
 * 
 */
public class Alarm implements Comparable<Alarm> {

	public static final int DAYS_OF_WEEK = 7;
	public static final int HOUR_OF_DAY = 24;
	public static final int MINUTES_OF_HOUR = 60;

	private final int alarmHours;
	private final int alarmMinutes;
	private final int alarmId;
	private boolean alarmEnabled;
	private String alarmModule;
	private int alarmVolume;
	private int daysOfWeek;

	/**
	 * Default constructor for an alarm.
	 * 
	 * @param hours
	 *            The hour of the alarm
	 * @param minutes
	 *            The minute of the alarm
	 * @param id
	 *            The id of the alarm
	 * @throws IllegalArgumentException
	 */
	public Alarm(int hours, int minutes, int id)
			throws IllegalArgumentException {
		if (isIllegalHour(hours) || isIllegalMinutes(minutes)) {
			throw new IllegalArgumentException("Illegal constructor argument!");
		}
		this.alarmHours = hours;
		this.alarmMinutes = minutes;
		this.alarmId = id;
	}

	/**
	 * Constructor for Alarm. Able to set the volume and module for the alarm.
	 * 
	 * @param hours
	 *            The hour of the alarm
	 * @param minutes
	 *            The minute of the alarm
	 * @param id
	 *            The id of the alarm
	 * @param module
	 *            The module of the alarm
	 * @param volume
	 *            The volume of the alarm
	 */
	public Alarm(int hours, int minutes, int id, String module, int volume) {
		this(hours, minutes, id);
		this.alarmModule = module;
		this.alarmEnabled = true;
		this.alarmVolume = volume;
		this.daysOfWeek = 0;
	}

	/**
	 * Get the time left to the alarm. If the alarm is recurring the time left
	 * is the time to the next alarm.
	 * 
	 * @return The time left to the alarm in milliseconds.
	 */
	public long getTimeInMilliSeconds() {
		Calendar cal = Calendar.getInstance();

		if (isHourTomorrow() || isMinuteThisHourTomorrow()) {
			cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + 1);
		}
		if (this.getDaysOfWeek() != 0) {
			setNextOccuringDay(cal);
		}

		cal.set(Calendar.HOUR_OF_DAY, alarmHours);
		cal.set(Calendar.MINUTE, alarmMinutes);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTimeInMillis();
	}

	/**
	 * Add days to the next occurring alarm.
	 * 
	 * @param cal
	 *            The calendar associated with the alarm.
	 */
	private void setNextOccuringDay(Calendar cal) {
		int currentDay = cal.get(Calendar.DAY_OF_WEEK);
		int nextDay = getDaysToNextAlarm(currentDay);
		if (nextDay == -1) {
			return;
		}
		cal.add(Calendar.DAY_OF_YEAR, nextDay);
	}

	/**
	 * Gets the number of days until the next alarm.
	 * 
	 * @param currentDay
	 *            The calendars integer representation of the current day
	 * @return days until next alarm
	 */
	private int getDaysToNextAlarm(int currentDay) {
		boolean[] days = changeToCalendar(getBooleanArrayDayOfWeek());
		for (int i = 0; i < Alarm.DAYS_OF_WEEK; i++) {
			if (days[(currentDay + i) % Alarm.DAYS_OF_WEEK]) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Changes an boolean array so the first day is Sunday instead of Monday.
	 * 
	 * @param dayOfWeek
	 *            The days of week which is set to be recurring
	 * @return The array but with the Sunday as the first day of the week.
	 */
	private boolean[] changeToCalendar(boolean[] dayOfWeek) {
		boolean[] calendarDays = new boolean[Alarm.DAYS_OF_WEEK];
		for (int i = 0; i < calendarDays.length; i++) {
			if (i == 0) {
				calendarDays[i] = dayOfWeek[calendarDays.length - 1];
			} else {
				calendarDays[i] = dayOfWeek[i - 1];
			}
		}
		return calendarDays;
	}

	/**
	 * Checks if the minute is tomorrow.
	 * 
	 * @return true if the minute is tomorrow
	 */
	private boolean isMinuteThisHourTomorrow() {
		Calendar cal = Calendar.getInstance();
		return (cal.get(Calendar.HOUR_OF_DAY) == alarmHours)
				&& cal.get(Calendar.MINUTE) >= alarmMinutes;
	}

	/**
	 * Checks if the hour is tomorrow.
	 * 
	 * @return true if the hour is tomorrow
	 */
	private boolean isHourTomorrow() {
		return Calendar.getInstance().get(Calendar.HOUR_OF_DAY) > alarmHours;
	}

	/**
	 * Checks if the minute is illegal.
	 * 
	 * @param minutes
	 *            the minute to be checked.
	 * @return true if the minute is illegal
	 */
	private boolean isIllegalMinutes(int minutes) {
		return minutes >= Alarm.MINUTES_OF_HOUR || minutes < 0;
	}

	/**
	 * Checks if the hour is illegal.
	 * 
	 * @param hours
	 *            the hour to be checked.
	 * @return true if the hour is illegal
	 */
	private boolean isIllegalHour(int hours) {
		return hours >= Alarm.HOUR_OF_DAY || hours < 0;
	}

	/**
	 * Gets the hour of the alarm.
	 * 
	 * @return The hour of the alarm
	 */
	public int getAlarmHours() {
		return alarmHours;
	}

	/**
	 * Gets the minute of the alarm.
	 * 
	 * @return The minute of the alarm
	 */
	public int getAlarmMinutes() {
		return alarmMinutes;
	}

	@Override
	public String toString() {
		// Format
		int daysLeft = getDaysToNextAlarm(Calendar.getInstance().get(
				Calendar.DAY_OF_WEEK));
		boolean day = daysLeft > 0;
		boolean days = daysLeft > 1;
		boolean hour = getHoursToAlarm() > 0;
		boolean hours = getHoursToAlarm() > 1;
		boolean minute = getMinutesToAlarm() > 0;
		boolean minutes = getMinutesToAlarm() > 1;

		StringBuilder strBuilder = new StringBuilder();

		strBuilder.append("Alarm is set for ");
		if (day) {
			strBuilder.append(daysLeft + " day");
			if (days) {
				strBuilder.append("s");
			}
		}
		if (day && hour) {
			strBuilder.append(" and ");
		}
		if (hour) {
			strBuilder.append(getHoursToAlarm() + " hour");
			if (hours) {
				strBuilder.append("s");
			}
		}
		if ((day || hour) && minute) {
			strBuilder.append(" and ");
		}
		if (minute) {
			strBuilder.append(getMinutesToAlarm() + " minute");
			if (minutes) {
				strBuilder.append("s");
			}
		}
		strBuilder.append(" from now.");

		return strBuilder.toString();
	}

	/**
	 * Gets the minutes to the alarm from the current time.
	 * 
	 * @return Minutes to the alarm's minute from now.
	 */
	private int getMinutesToAlarm() {
		int currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
		int minutesToAlarm = (alarmMinutes - currentMinute + Alarm.MINUTES_OF_HOUR)
				% Alarm.MINUTES_OF_HOUR;

		return minutesToAlarm;
	}

	/**
	 * Gets the hours to the alarm from the current time.
	 * 
	 * @return Hours to the alarm's hour from now.
	 */
	private int getHoursToAlarm() {
		int currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
		int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		int hoursToAlarm = (alarmHours - currentHour + Alarm.HOUR_OF_DAY)
				% Alarm.HOUR_OF_DAY;

		if (getMinutesToAlarm() == 0 && hoursToAlarm == 0) {
			return Alarm.HOUR_OF_DAY;
		}

		if (currentMinute > alarmMinutes) {
			if (hoursToAlarm == 0) {
				hoursToAlarm = Alarm.HOUR_OF_DAY;
			}
			hoursToAlarm--;
		}

		return hoursToAlarm;
	}

	/**
	 * Gets the id of the alarm.
	 * 
	 * @return The id of the alarm
	 */
	public int getId() {
		return alarmId;
	}

	@Override
	public int hashCode() {
		return (int) getTimeInMilliSeconds() * Alarm.DAYS_OF_WEEK;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Alarm other = (Alarm) obj;
		return this.getTimeInMilliSeconds() == other.getTimeInMilliSeconds();
	}

	@Override
	public int compareTo(Alarm another) {
		return (int) (this.getTimeInMilliSeconds() - another
				.getTimeInMilliSeconds());
	}

	/**
	 * Gets the state of the alarm.
	 * 
	 * @return true if the alarm is enabled
	 */
	public boolean isEnabled() {
		return alarmEnabled;
	}

	/**
	 * Sets the state of the alarm.
	 * 
	 * @param enabled
	 *            the state to set the alarm to.
	 */
	public void setEnabled(boolean enabled) {
		this.alarmEnabled = enabled;
	}

	/**
	 * Gets the name of the module which should be activated on the alarm
	 * activation.
	 * 
	 * @return The name of the module associated with the alarm
	 */
	public String getModule() {
		return alarmModule;
	}

	/**
	 * Sets the name of the module to be activated on the alarm activation.
	 * @param module
	 *            The name of the module
	 */
	public void setModule(String module) {
		this.alarmModule = module;
	}

	/**
	 * Gets the volume of the alarm.
	 * @return The volume of the alarm
	 */
	public int getVolume() {
		return alarmVolume;
	}

	/**
	 * Sets the volume of the alarm.
	 * @param volume
	 *            The volume to set the alarm to
	 */
	public void setVolume(int volume) {
		this.alarmVolume = volume;
	}

	/**
	 * Gets the days of the alarm when it is recurring an as boolean array.
	 * @return The days which the alarm is recurring as an boolean array
	 */
	public boolean[] getBooleanArrayDayOfWeek() {
		boolean[] days = new boolean[Alarm.DAYS_OF_WEEK];
		for (int i = 0; i < Alarm.DAYS_OF_WEEK; i++) {
			days[i] = (daysOfWeek & (1 << i)) > 0;
		}
		return days;
	}

	/**
	 * Gets the days of the alarm when it is recurring an bits of an integer.
	 * @return The days which the alarm is recurring as bits of an integer
	 */
	public int getDaysOfWeek() {
		return daysOfWeek;
	}

	/**
	 * Sets the day of the week which the alarm should be recurring.
	 * @param days
	 *            The days which the alarm should be recurring
	 */
	public void setDaysOfWeek(int days) {
		this.daysOfWeek = days;
	}

}
