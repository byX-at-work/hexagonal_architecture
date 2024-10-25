package org.lunatech.ecommerce;

import lombok.Data;

/**
 * Product
 */
@Data
public class Product {
    String id;
    String name;
    double price;
    double discount;
}
