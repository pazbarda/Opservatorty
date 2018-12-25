package com.opservatory.loma.monitors;

import com.opservatory.loma.notifications.INotifier;

public interface IMonitor<P> extends INotifier<P> {
	public void start();
	public void stop();
	public void handleInternalException(Exception e);
}
