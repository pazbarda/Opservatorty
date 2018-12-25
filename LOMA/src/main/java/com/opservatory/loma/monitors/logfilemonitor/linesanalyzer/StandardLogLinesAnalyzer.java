package com.opservatory.loma.monitors.logfilemonitor.linesanalyzer;

import java.util.ArrayList;
import java.util.List;

import com.opservatory.loma.monitors.logfilemonitor.LogEvent;
import com.opservatory.loma.monitors.logfilemonitor.LogEventLevel;

// TODO - make level of log events configurable
public class StandardLogLinesAnalyzer implements ILogLinesAnalyzer {

	@Override
	public List<LogEvent> analyzeLogLines(List<String> logLines) {
		List<LogEvent> ret = new ArrayList<LogEvent>();
		for (String logLine : logLines) {
			String lowerCaseLogLine = logLine.toLowerCase();
			if (lowerCaseLogLine.startsWith(LogEventLevel.FATAL.getStringIndicator())) {
				ret.add(new LogEvent(LogEventLevel.FATAL, logLine));
			} else if (lowerCaseLogLine.startsWith(LogEventLevel.ERROR.getStringIndicator())) {
				ret.add(new LogEvent(LogEventLevel.ERROR, logLine));
			} else if (lowerCaseLogLine.startsWith(LogEventLevel.WARNING.getStringIndicator())) {
				ret.add(new LogEvent(LogEventLevel.WARNING, logLine));
			} else {
				ret.add(new LogEvent(LogEventLevel.INFO, logLine));
			}
		}
		return ret;
	}
		
}
