package com.opservatory.loma.monitors.logfilemonitor;

import java.util.List;
import java.util.TimerTask;

import com.opservatory.loma.filereader.FileReadException;
import com.opservatory.loma.filereader.FileReader;
import com.opservatory.loma.monitors.logfilemonitor.linesanalyzer.ILogLinesAnalyzer;

public class LogFileMonitorTimerTask extends TimerTask {
	
	private final LogFileMonitor monitor;
	private final FileReader fileReader;
	private final ILogLinesAnalyzer logLinesAnalyzer;
	
	
	public LogFileMonitorTimerTask(LogFileMonitor monitor, FileReader fileReader, ILogLinesAnalyzer logLinesAnalyzer) {
		super();
		this.monitor = monitor;
		this.fileReader = fileReader;
		this.logLinesAnalyzer = logLinesAnalyzer;
	}

	@Override
	public void run() {
		try {
			List<String> newLines = fileReader.getNewLines();
			List<LogEvent> logEvents = logLinesAnalyzer.analyzeLogLines(newLines);
			if (logEvents.size()>0) {
				monitor.notifyAllClients(logEvents);
			}
		} catch (InterruptedException | FileReadException e) {
			monitor.handleInternalException(e);
		}
	}
}
