package com.iapp.event;

public interface IEventDispatcher {
	public void addEventListener(IEventListener aEventListener);
	public void removeEventListener(IEventListener aEventListener);
	public void dispatchEvent(Event event);
}
