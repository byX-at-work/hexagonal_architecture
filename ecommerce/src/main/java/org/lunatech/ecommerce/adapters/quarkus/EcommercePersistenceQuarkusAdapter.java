package org.lunatech.ecommerce.adapters.quarkus;

import java.util.Optional;
import org.lunatech.ecommerce.Product;
import org.lunatech.ecommerce.ports.EcommercePersistencePort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import static jakarta.transaction.Transactional.TxType.REQUIRED;
import static jakarta.transaction.Transactional.TxType.SUPPORTS;

/**
 * EcommercePersistenceQuarkusAdapter
 */
@ApplicationScoped
@Transactional(REQUIRED)
public class EcommercePersistenceQuarkusAdapter implements EcommercePersistencePort {

    // because of the transactional annotation, we have to warp it with another method
    public void saveProduct(Product product) {
        this.saveProduct_(product);
    }

    @Transactional(SUPPORTS)
    public void saveProduct_(Product product) {
        var id = Optional.ofNullable(product.getId()).orElse("");
        var entity = Optional.ofNullable(ProductEntity.<ProductEntity>findById(id))
            .orElseGet(() -> new ProductEntity());
        entity.fromDTO(product);
        entity.persist();
        product.setId(entity.id);
    }

    public Product getProduct(String productId) {
        return this.getProduct_(productId);
    }

    @Transactional(SUPPORTS)
    public Product getProduct_(String productId) {
        return Optional.ofNullable(ProductEntity.<ProductEntity>findById(productId))
            .map(entity -> entity.toDTO()).orElseGet(() -> null);
    }
}
