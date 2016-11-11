package org.fx.transition.implementation;

import org.fx.transition.EventType;
import org.fx.transition.TransitionEvent;

public class SimpleTransitionEvent implements TransitionEvent {
    private final EventType eventType;

    public SimpleTransitionEvent(final EventType eventType) {
        this.eventType = eventType;
    }

    @Override
    public EventType getEventType() {
        return eventType;
    }

    @Override
    public String toString() {
        return eventType.toString();
    }
}
