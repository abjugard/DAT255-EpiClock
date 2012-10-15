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

import edu.chalmers.dat255.group09.Alarmed.utils.AlarmUtils;

/**
 * The model of the alarm.
 * 
 * @author Joakim Persson
 * @author Daniel Augurell
 * 
 */
public class Alarm implements Comparable<Alarm> {

	private final int alarmHours;
	private final int alarmMinutes;
	private final int alarmId;
	private boolean alarmEnabled;
	private boolean vibrationEnabled;
	private String alarmModule;
	private String toneUri;
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

		this.alarmEnabled = true;
		this.daysOfWeek = 0;
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
		this.alarmVolume = volume;
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
			AlarmUtils.addDays(cal, daysOfWeek);
		}

		cal.set(Calendar.HOUR_OF_DAY, alarmHours);
		cal.set(Calendar.MINUTE, alarmMinutes);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTimeInMillis();
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
		return minutes >= AlarmUtils.MINUTES_OF_HOUR || minutes < 0;
	}

	/**
	 * Checks if the hour is illegal.
	 * 
	 * @param hours
	 *            the hour to be checked.
	 * @return true if the hour is illegal
	 */
	private boolean isIllegalHour(int hours) {
		return hours >= AlarmUtils.HOUR_OF_DAY || hours < 0;
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
		return String.format("%02d:%02d", alarmHours, alarmMinutes);
	}

	/**
	 * Constructs a string with the time left to the next alarm.
	 * 
	 * @return A string with the time to the next alarm.
	 */
	public String getTimeToNextAlarmString() {
		// Format
		int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		int daysLeft = AlarmUtils.getDaysToNextDay(currentDay, daysOfWeek);
		int hoursLeft = AlarmUtils.getHoursToTime(alarmHours, alarmMinutes);
		int minutesLeft = AlarmUtils.getMinutesToTime(alarmMinutes);
		boolean day = daysLeft > 0;
		boolean days = daysLeft > 1;
		boolean hour = hoursLeft > 0;
		boolean hours = hoursLeft > 1;
		boolean minute = minutesLeft > 0;
		boolean minutes = minutesLeft > 1;

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
			strBuilder.append(hoursLeft + " hour");
			if (hours) {
				strBuilder.append("s");
			}
		}
		if ((day || hour) && minute) {
			strBuilder.append(" and ");
		}
		if (minute) {
			strBuilder.append(minutesLeft + " minute");
			if (minutes) {
				strBuilder.append("s");
			}
		}
		strBuilder.append(" from now.");

		return strBuilder.toString();
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
		return (int) getTimeInMilliSeconds() * AlarmUtils.DAYS_OF_WEEK;
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
	 * 
	 * @param module
	 *            The name of the module
	 */
	public void setModule(String module) {
		this.alarmModule = module;
	}

	/**
	 * Gets the volume of the alarm.
	 * 
	 * @return The volume of the alarm
	 */
	public int getVolume() {
		return alarmVolume;
	}

	/**
	 * Sets the volume of the alarm.
	 * 
	 * @param volume
	 *            The volume to set the alarm to
	 */
	public void setVolume(int volume) {
		this.alarmVolume = volume;
	}

	/**
	 * Gets the days of the alarm when it is recurring an bits of an integer.
	 * 
	 * @return The days which the alarm is recurring as bits of an integer
	 */
	public int getDaysOfWeek() {
		return daysOfWeek;
	}

	/**
	 * Sets the day of the week which the alarm should be recurring.
	 * 
	 * @param days
	 *            The days which the alarm should be recurring
	 */
	public void setDaysOfWeek(int days) {
		this.daysOfWeek = days;
	}

	/**
	 * Gets the alarm tone URI
	 * 
	 * @return The alarm tone URI
	 */
	public String getToneUri() {
		return toneUri;
	}

	/**
	 * Sets the alarm tone URI
	 * 
	 * @param toneUri
	 *            The alarm tone URI
	 */
	public void setToneUri(String toneUri) {
		this.toneUri = toneUri;
	}

	/**
	 * Method to check whether the alarm has vibration enabled or not
	 * 
	 * @return Whether or not vibration is enabled for this alarm
	 */
	public boolean isVibrationEnabled() {
		return vibrationEnabled;
	}

	/**
	 * Sets whether or not the alarm should engage the vibration motor
	 * 
	 * @param vibrationEnabled
	 *            Requested status of vibration
	 */
	public void setVibrationEnabled(boolean vibrationEnabled) {
		this.vibrationEnabled = vibrationEnabled;
	}

}
