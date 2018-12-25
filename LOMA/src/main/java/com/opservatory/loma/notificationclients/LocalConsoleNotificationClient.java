package com.opservatory.loma.notificationclients;

import java.util.List;

import com.opservatory.loma.monitors.logfilemonitor.LogEvent;
import com.opservatory.loma.notifications.IListener;

public class LocalConsoleNotificationClient implements IListener<List<LogEvent>>{

	@Override
	public void newNotification(List<LogEvent> payload) {
		for (LogEvent logEvent : payload) {
			System.out.println(logEvent.getLevel().getStringIndicator().toUpperCase() + " > " + logEvent.getText());
		}
	}

}
