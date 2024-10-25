package org.lunatech.ecommerce.adapters.quarkus;

import org.lunatech.ecommerce.Order;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

/**
 * Order
 */
@Entity
public class OrderEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @NotNull
    String userId;
    @NotNull
    String productId;
    @NotNull
    String status;
    long pruchaseTime;

    public Order toDTO() {
        Order order = new Order();
        order.setId(this.id);
        order.setUserId(this.userId);
        order.setProductId(this.productId);
        order.setStatus(this.status);
        order.setPruchaseTime(this.pruchaseTime);
        return order;
    }

    public void fromDTO(Order order) {
        if (order.getId() != null) {
            this.id = order.getId();
        }
        this.userId = order.getUserId();
        this.productId = order.getProductId();
        this.status = order.getStatus();
        this.pruchaseTime = order.getPruchaseTime();
    }
}
