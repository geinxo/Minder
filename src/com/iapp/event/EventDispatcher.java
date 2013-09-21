package com.iapp.event;

import java.util.ArrayList;
import java.util.ListIterator;

public class EventDispatcher implements IEventDispatcher{

	private ArrayList<IEventListener> mArrListeners;
	public EventDispatcher() {
		mArrListeners = new ArrayList<IEventListener>();
	}
	@Override
	public void addEventListener(IEventListener aEventListener) {
		if (mArrListeners.indexOf( aEventListener ) < 0) {
			mArrListeners.add( aEventListener );
		}
	}

	@Override
	public void removeEventListener(IEventListener aEventListener) {
		int index = mArrListeners.indexOf( aEventListener );
		if ( index >= 0) {
			mArrListeners.remove(index);
		}
	}

	@Override
	public void dispatchEvent(Event event) {
		ListIterator<IEventListener> iter = mArrListeners.listIterator();
		while (iter.hasNext()) {
			IEventListener listener = iter.next();
			listener.onEvent( event );
		}
	}
}
