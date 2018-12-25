package com.opservatory.loma.monitors.logfilemonitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import com.opservatory.loma.filereader.FileReader;
import com.opservatory.loma.monitors.IMonitor;
import com.opservatory.loma.monitors.logfilemonitor.linesanalyzer.ILogLinesAnalyzer;
import com.opservatory.loma.notifications.IListener;

public class LogFileMonitor implements IMonitor<List<LogEvent>> {
	
	// TODO - make configurable
	private static final int READ_INTERVAL_MILISEC = 1000;

	private List<IListener<List<LogEvent>>> clients = new ArrayList<IListener<List<LogEvent>>>();
	private LogFileMonitorTimerTask timerTask;
	private Timer timer;
	
	public LogFileMonitor(FileReader fileReader, ILogLinesAnalyzer logLinesAnalyzer) {
		this.timerTask = new LogFileMonitorTimerTask(this, fileReader, logLinesAnalyzer);
	}
	
	@Override
	public void addListener(IListener<List<LogEvent>> listener) {
		clients.add(listener);
	}

	@Override
	public void removeListener(IListener<List<LogEvent>> listener) {
		clients.remove(listener);
	}

	@Override
	public void removeAllListeners() {
		clients.clear();
	}

	@Override
	public void start() {
		this.timer = new Timer();
		timer.schedule(timerTask, 0, READ_INTERVAL_MILISEC);
	}

	@Override
	public void stop() {
		timer.cancel();
	}

	@Override
	public void handleInternalException(Exception e) {
		System.out.println(e.getMessage());
		stop();
	}
	
	public void notifyAllClients(List<LogEvent> logEvents) {
		for (IListener<List<LogEvent>> client : clients) {
			client.newNotification(logEvents);
		}
	}

}
