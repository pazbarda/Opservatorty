package com.opservatory.loma.monitors.logfilemonitor;

public class LogEvent {
	private final LogEventLevel level;
	private final String text;
	
	public LogEvent(LogEventLevel level, String text) {
		super();
		this.level = level;
		this.text = text;
	}

	public LogEventLevel getLevel() {
		return level;
	}

	public String getText() {
		return text;
	}
}
