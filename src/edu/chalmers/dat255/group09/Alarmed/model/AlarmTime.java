package edu.chalmers.dat255.group09.Alarmed.model;

import java.util.Calendar;

public class AlarmTime {

	private final int alarmHours;
	private final int alarmMinutes;

	public AlarmTime(int hours, int minutes) throws IllegalArgumentException {

		if (isIllegalHour(hours) || isIllegalMinutes(minutes)) {
			throw new IllegalArgumentException("Illegal constructor argument!");
		}

		this.alarmHours = hours;
		this.alarmMinutes = minutes;
	}
	
	public long getTimeInMilliSeconds() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, alarmHours);
		cal.set(Calendar.MINUTE, alarmMinutes);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTimeInMillis();
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
		StringBuilder strBuilder = new StringBuilder();

		strBuilder.append("Alarm is set for ");

		strBuilder.append(getHoursToAlarm());

		strBuilder.append(" hours");

		strBuilder.append(" and ");

		strBuilder.append(getMinutesToAlarm());

		strBuilder.append(" minutes from now");

		return strBuilder.toString();
	}

	private String getMinutesToAlarm() {
		int currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
		int minutes = (alarmMinutes == 0) ? 60 : alarmMinutes;
		int minutesToAlarm = Math.abs(currentMinute - minutes);

		return "" + minutesToAlarm;
	}

	// TODO do rolen do
	// perhaps convert the time in millis instead
	private String getHoursToAlarm() {
		int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

		int hoursToAlarm = 0;
		if ((alarmHours >= 0) && (alarmHours <= 12)) {
			hoursToAlarm = Math.abs(alarmHours - currentHour);
		} else {
			// hoursToAlarm =
		}

		return String.valueOf(hoursToAlarm);
	}
}
