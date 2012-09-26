package edu.chalmers.dat255.group09.Alarmed.model;

import java.util.Calendar;

public class Alarm {

	private final int alarmHours;
	private final int alarmMinutes;
	private final int id;

	public Alarm(int hours, int minutes, int id)
			throws IllegalArgumentException {

		if (isIllegalHour(hours) || isIllegalMinutes(minutes)) {
			throw new IllegalArgumentException("Illegal constructor argument!");
		}

		this.alarmHours = hours;
		this.alarmMinutes = minutes;
		this.id = id;
	}

	public long getTimeInMilliSeconds() {
		Calendar cal = Calendar.getInstance();

		if (isHourTomorrow() || isMinuteThisHourTomorrow()) {
			cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + 1);
		}

		cal.set(Calendar.HOUR_OF_DAY, alarmHours);
		cal.set(Calendar.MINUTE, alarmMinutes);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTimeInMillis();
	}

	private boolean isMinuteThisHourTomorrow() {
		Calendar cal = Calendar.getInstance();
		return (cal.get(Calendar.HOUR_OF_DAY) == alarmHours)
				&& cal.get(Calendar.MINUTE) >= alarmMinutes;
	}

	private boolean isHourTomorrow() {
		return Calendar.getInstance().get(Calendar.HOUR_OF_DAY) > alarmHours;
	}

	private boolean isIllegalMinutes(int minutes) {
		return minutes > 59 || minutes < 0;
	}

	private boolean isIllegalHour(int hours) {
		return hours > 23 || hours < 0;
	}

	public int getAlarmHours() {
		return alarmHours;
	}

	public int getAlarmMinutes() {
		return alarmMinutes;
	}

	@Override
	public String toString() {
		// Format
		boolean hour = getHoursToAlarm() > 0;
		boolean hours = getHoursToAlarm() > 1;
		boolean minute = getMinutesToAlarm() > 0;
		boolean minutes = getMinutesToAlarm() > 1;

		StringBuilder strBuilder = new StringBuilder();

		strBuilder.append("Alarm is set for ");
		if (hour) {
			strBuilder.append(getHoursToAlarm() + " hour" + (hours ? "s" : ""));
		}
		if (hour && minute) {
			strBuilder.append(" and ");
		}
		if (minute) {
			strBuilder.append(getMinutesToAlarm() + " minute"
					+ (minutes ? "s" : ""));
		}
		strBuilder.append(" from now.");

		return strBuilder.toString();
	}

	private int getMinutesToAlarm() {
		int currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
		int minutesToAlarm = (alarmMinutes - currentMinute + 60) % 60;

		return minutesToAlarm;
	}

	private int getHoursToAlarm() {
		int currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
		int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		int hoursToAlarm = (alarmHours - currentHour + 24) % 24;

		if (getMinutesToAlarm() == 0 && hoursToAlarm == 0) {
			return 24;
		}

		if (currentMinute > alarmMinutes) {
			if (hoursToAlarm == 0) {
				hoursToAlarm = 24;
			}
			hoursToAlarm--;
		}

		return hoursToAlarm;
	}

	public int getId() {
		return id;
	}
}
