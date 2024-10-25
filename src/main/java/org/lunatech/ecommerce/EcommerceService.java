package org.lunatech.ecommerce;

import org.jboss.logging.Logger;
import org.lunatech.ecommerce.events.ProductEvent;
import org.lunatech.ecommerce.events.ViewProductEvent;
import org.lunatech.ecommerce.ports.EcommercePersistencePort;
import org.lunatech.ecommerce.ports.StreamOutputPort;
import org.lunatech.ecommerce.ports.UserServicePort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * EcommerceService
 * Accroding to the defination, we should only keep business logic here.
 * Just to set up an example, some logics are written by comments instead of actual code.
 */
@ApplicationScoped
public class EcommerceService {
    @Inject
    Logger logger;

    @Inject
    EcommercePersistencePort storage;

    // @Inject
    // StreamOutputPort kafka;

    @Inject
    UserServicePort userService;

    public Product onGetProduct(String userId, String productId) {
        // the business logic could be:
        // 1. check visibility of the product to the user.
        // 2. add the product to user's view history. (to an output adapter to some other microservice)
        // var result = userService.checkUserPrivilege(userId, "getProduct");
        // if (!result)
        //     // product is not visiable to the user
        //     return null;

        // 3. fetch the product
        var product = storage.getProduct(productId);

        // 4. publish an user view event into kafka for metrics. (to Kafka output adapter)
        // ViewProductEvent event = new ViewProductEvent();
        // event.setUserId(userId);
        // event.setProductId(productId);
        // event.setViewTime(System.currentTimeMillis());
        // kafka.sendEvent("product-view", event);

        // 5. other stuff...
        return product;
    }

    public String onAddProduct(String userId, Product product) {
        // the business logic could be:
        // 1. check whether the user has the privilige to add this product. (to an output adapter to some other microservice)
        // var result = userService.checkUserPrivilege(userId, "addProduct");
        // if (!result)
        //     // not authorized
        //     return;

        // 2. add the product.
        storage.saveProduct(product);

        // 3. notify users who are interested in similar products. (to an output adapter to some other microservice)
        // omitted here...

        // 4. publish an add product event into kafka for metrics. (to Kafka output adapter)
        // ProductEvent event = new ProductEvent();
        // event.setId(product.getId());
        // event.setName(product.getName());
        // event.setPrice(product.getPrice());
        // event.setDiscount(product.getDiscount());
        // event.setNew(true);
        // kafka.sendEvent("product", event);

        // 5. other stuff...
        return product.getId();
    }

    public void onSetProductDiscout(String userId, String productId, double discout) {
        // the business logic could be:
        // 1. check whether the user has the privilige to set discout.
        var product = storage.getProduct(productId);
        var result = userService.checkUserPrivilege(userId, "updateProduct");
        if (!result)
            return;

        // 2. add the product.
        product.setDiscount(discout);

        // 3. save the product.
        storage.saveProduct(product);

        // 4. notify users who are interested in this products. (to an output adapter to some other microservice)
        // omitted here..

        // 5. publish an add product event into kafka for metrics. (to Kafka output adapter)
        // ProductEvent event = new ProductEvent();
        // event.setId(product.getId());
        // event.setName(product.getName());
        // event.setPrice(product.getPrice());
        // event.setDiscount(product.getDiscount());
        // event.setNew(false);
        // kafka.sendEvent("product", event);

        // 6. other stuff...
    }

    public void onRenameProduct(String userId, String productId, String name) {
        // the business logic could be:
        // 1. check whether the user has the privilige to rename.
        var product = storage.getProduct(productId);
        var result = userService.checkUserPrivilege(userId, "updateProduct");
        if (!result)
            return;

        // 2. add the product.
        product.setName(name);

        // 3. save the product.
        storage.saveProduct(product);

        // 4. other stuff...
    }
}
