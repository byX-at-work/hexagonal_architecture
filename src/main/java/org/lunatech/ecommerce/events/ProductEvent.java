package org.lunatech.ecommerce.events;

import lombok.Data;

/**
 * ProductEvent
 */
@Data
public class ProductEvent extends Event {

    String id;

    String name;

    double price;

    double discount;

    boolean isNew;
}
