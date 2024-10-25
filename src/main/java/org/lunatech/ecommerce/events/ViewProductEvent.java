package org.lunatech.ecommerce.events;

import lombok.Data;

/**
 * ViewProductEvent
 */
@Data
public class ViewProductEvent extends Event {

    String userId;

    String productId;

    long viewTime;
}
