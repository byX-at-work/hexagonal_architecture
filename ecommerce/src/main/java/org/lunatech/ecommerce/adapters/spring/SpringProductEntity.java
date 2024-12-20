package org.lunatech.ecommerce.adapters.spring;

import org.lunatech.ecommerce.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * ProductEntity
 */
@Data
@Entity
public class SpringProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @NotNull
    String name;
    @NotNull
    Double price;
    Double discount;

    public Product toDTO() {
        Product product = new Product();
        product.setId(this.id);
        product.setName(this.name);
        product.setPrice(this.price);
        product.setDiscount(this.discount);
        return product;
    }

    public void fromDTO(Product product) {
        if (product.getId() != null) {
            this.id = product.getId();
        }
        this.name = product.getName();
        this.price = product.getPrice();
        this.discount = product.getDiscount();
    }
}
