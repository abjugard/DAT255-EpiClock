package edu.chalmers.dat255.group09.Alarmed.model;

import java.util.Calendar;

public class AlarmTime {

	private final long ONE_HOUR_IN_MILLI_SECONDS = 3600000;
	private final long ONE_MINUTE_IN_MILLI_SECONDS = 60000;
	private final long ONE_SECOND_IN_MILLI_SECONDS = 1000;
	private final int alarmHours;
	private final int alarmMinutes;

	public AlarmTime(int hours, int minutes) throws IllegalArgumentException {

		if (isIllegalHour(hours) || isIllegalMinutes(minutes)) {
			throw new IllegalArgumentException("Illegal constructor argument!");
		}

		this.alarmHours = hours;
		this.alarmMinutes = minutes;
	}

	private boolean isIllegalMinutes(int minutes) {
		return minutes > 59 || minutes < 0;
	}

	private boolean isIllegalHour(int hours) {
		return hours > 23 || hours < 0;
	}

	public long getAlarmTimeInMilliSeconds() {
		long alarmTimeInMilliSeconds = System.currentTimeMillis();
		alarmTimeInMilliSeconds += getTimeDifferenceInMilliSeconds();

		return (alarmTimeInMilliSeconds);
	}

	private long getTimeInMilliSeconds() {
		return getTimeInMilliSeconds(alarmHours, alarmMinutes, 0);
	}

	private long getTimeInMilliSeconds(int hours, int minutes, int seconds) {
		long timeInMilliSeconds = hours * ONE_HOUR_IN_MILLI_SECONDS;

		timeInMilliSeconds += minutes * ONE_MINUTE_IN_MILLI_SECONDS;

		timeInMilliSeconds += seconds * ONE_SECOND_IN_MILLI_SECONDS;

		return timeInMilliSeconds;
	}

	private long getTimeDifferenceInMilliSeconds() {
		Calendar calendar = Calendar.getInstance();
		int currentMinutes = calendar.get(Calendar.MINUTE);
		int currentHours = calendar.get(Calendar.HOUR_OF_DAY);
		int currentSeconds = calendar.get(Calendar.SECOND);
		long currentTime = getTimeInMilliSeconds(currentHours, currentMinutes,
				currentSeconds);
		return Math.abs(getTimeInMilliSeconds() - currentTime);
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