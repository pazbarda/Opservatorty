package com.opservatory.loma.notifications;

// TODO - doc
// P = pay-load type
public interface INotifier<P> {
	
	public void addListener(IListener<P> listener);
	
	public void removeListener(IListener<P> listener);
	
	public void removeAllListeners();	
}
