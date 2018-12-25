package com.opservatory.loma.notifications;

// TODO - doc
//P = pay-load type
public interface IListener<P> {
	public void newNotification(P payload);
}
