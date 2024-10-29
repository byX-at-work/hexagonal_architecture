package org.lunatech.ecommerce.adapters;

import org.lunatech.ecommerce.events.Event;
import org.lunatech.ecommerce.ports.StreamOutputPort;

/**
 * StreamOutputMockAdapter
 */
public class StreamOutputMockAdapter implements StreamOutputPort {

    public void sendEvent(String topic, Event event) {
        // no-op
    }
}
