package com.opservatory.loma.monitors.logfilemonitor.linesanalyzer;

import java.util.List;

import com.opservatory.loma.monitors.logfilemonitor.LogEvent;

public interface ILogLinesAnalyzer {
	public List<LogEvent> analyzeLogLines(List<String> logLines);
}
