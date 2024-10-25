package org.lunatech.ecommerce;

import lombok.Data;

/**
 * Order
 */
@Data
public class Order {
    String id;
    String userId;
    String productId;
    String status;
    long pruchaseTime;
}
