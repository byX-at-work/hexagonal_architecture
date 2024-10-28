package org.lunatech.ecommerce.adapters.quarkus;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;
import org.lunatech.ecommerce.events.Event;
import org.lunatech.ecommerce.events.ProductEvent;
import org.lunatech.ecommerce.events.ProductViewEvent;
import org.lunatech.ecommerce.ports.StreamOutputPort;
import jakarta.inject.Inject;

/**
 * StreamOutputQuarkusAdapter
 */
public class StreamOutputQuarkusAdapter implements StreamOutputPort {

    @Inject
    Logger logger;

    @Channel("product")
    Emitter<ProductEvent> productEmitter;

    @Channel("product-view")
    Emitter<ProductViewEvent> productViewEmitter;

    public void sendEvent(String topic, Event event) {
        switch (topic) {
            case "product":
                productEmitter.send((ProductEvent) event).toCompletableFuture().join();
                break;

            case "product-view":
                productViewEmitter.send((ProductViewEvent) event).toCompletableFuture().join();
                break;

            default:
                logger.error("unknown topic: " + topic);
                break;
        }
    }
}
