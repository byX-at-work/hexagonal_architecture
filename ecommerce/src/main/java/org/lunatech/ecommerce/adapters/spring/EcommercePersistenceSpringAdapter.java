package org.lunatech.ecommerce.adapters.spring;

import java.util.Optional;
import org.lunatech.ecommerce.Product;
import org.lunatech.ecommerce.ports.EcommercePersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * EcommercePersistenceSpringAdapter
 */
@Component
public class EcommercePersistenceSpringAdapter implements EcommercePersistencePort {

    @Autowired
    EcommerceJPARepo repo;

    public void saveProduct(Product product) {
        var id = Optional.ofNullable(product.getId()).orElse("");
        var entity = repo.findById(id).orElse(new SpringProductEntity());
        entity.fromDTO(product);
        repo.save(entity);
        product.setId(entity.id);
    }

    public Product getProduct(String productId) {
        return repo.findById(productId).map(entity -> entity.toDTO()).orElseGet(() -> null);
    }


}
