package com.cgi.scrumescapegame;

import java.time.Duration;
import java.time.LocalTime;

public class Timer {
	private LocalTime startTime;

	public void setStartTime() {
		this.startTime = LocalTime.now();
	}

	public LocalTime getStartTime() {
        return this.startTime;
    }

	public LocalTime getCurrentTime() {
        return LocalTime.now();
    }

	public Duration getTimeSinceStart() {
		return Duration.between(startTime, getCurrentTime());
	}

	public String getTimeSinceStartString() {
		Duration duration = getTimeSinceStart();
		long hoursPart = duration.toHoursPart();
        int minutesPart = duration.toMinutesPart();
        int secondsPart = duration.toSecondsPart();
		return String.format("%02d:%02d:%02d", hoursPart, minutesPart, secondsPart);
	}
}
