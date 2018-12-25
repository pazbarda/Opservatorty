package com.opservatory.loma.monitors.logfilemonitor;

public enum LogEventLevel {
	INFO("info"), WARNING("warning"), ERROR("error"), FATAL("fatal");
	
	private final String stringIndicator;
	
	LogEventLevel(String stringIndicator) {
		this.stringIndicator = stringIndicator;
	}

	public String getStringIndicator() {
		return stringIndicator;
	}
}
