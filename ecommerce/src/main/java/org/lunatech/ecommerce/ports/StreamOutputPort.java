package org.lunatech.ecommerce.ports;

import org.lunatech.ecommerce.events.Event;

/**
 * StreamOutputPort
 */
public interface StreamOutputPort {

    public void sendEvent(String topic, Event event);
}
