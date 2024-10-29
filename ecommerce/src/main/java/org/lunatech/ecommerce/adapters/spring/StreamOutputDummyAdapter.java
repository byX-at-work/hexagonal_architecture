package org.lunatech.ecommerce.adapters.spring;

import org.lunatech.ecommerce.events.Event;
import org.lunatech.ecommerce.ports.StreamOutputPort;
import org.springframework.stereotype.Component;

/**
 * StreamOutputDummyAdapter
 */
@Component
public class StreamOutputDummyAdapter implements StreamOutputPort {

    public void sendEvent(String topic, Event event) {
        // no-op        
    }
}
