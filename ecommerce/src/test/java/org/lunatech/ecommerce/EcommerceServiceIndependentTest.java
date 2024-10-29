package org.lunatech.ecommerce;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;
import org.lunatech.ecommerce.adapters.EcommercePersistenceMockAdapter;
import org.lunatech.ecommerce.adapters.UserServiceMockAdapter;
import org.lunatech.ecommerce.adapters.StreamOutputMockAdapter;

/**
 * EcommerceServiceIndependentTest
 */
public class EcommerceServiceIndependentTest {

    private static final String GENERATED_PRODUCT_ID = "generated_product_id";

    public Product getTestProduct() {
        Product product = new Product();
        product.setName("TEST_PRODUCT");
        product.setPrice(99.99);
        product.setDiscount(0.99);
        return product;
    }

    @Test
    public void shouldAddProductSuccessfullyWithPermission() {
        String userId = "test_user";
        var product = getTestProduct();

        EcommerceService service = new EcommerceService(
                new EcommercePersistenceMockAdapter(GENERATED_PRODUCT_ID, product),
                new StreamOutputMockAdapter(), new UserServiceMockAdapter(true));
        var productId = service.onAddProduct(userId, product);
        assertEquals(GENERATED_PRODUCT_ID, productId);
    }

    @Test
    public void shouldFailToAddProductWithoutPermission() {
        String userId = "test_user";
        var product = getTestProduct();
        EcommerceService service = new EcommerceService(
                new EcommercePersistenceMockAdapter(GENERATED_PRODUCT_ID, product),
                new StreamOutputMockAdapter(), new UserServiceMockAdapter(false));
        service.onAddProduct(userId, product);

        // the id of the product should not be set because EcommercePersistenceMockAdapter::saveProduct() is never invoked
        assertNotEquals(GENERATED_PRODUCT_ID, product.getId());
    }

    @Test
    public void shouldGetProductSuccessfullyWithPermission() {
        String userId = "test_user";
        var product = getTestProduct();

        EcommerceService service = new EcommerceService(
                new EcommercePersistenceMockAdapter(GENERATED_PRODUCT_ID, product),
                new StreamOutputMockAdapter(), new UserServiceMockAdapter(true));

        var p = service.onGetProduct(userId, GENERATED_PRODUCT_ID);
        assertEquals(p, product);
    }

    @Test
    public void shouldFailToGetProductWithoutPermission() {
        String userId = "test_user";
        var product = getTestProduct();

        EcommerceService service = new EcommerceService(
                new EcommercePersistenceMockAdapter(GENERATED_PRODUCT_ID, product),
                new StreamOutputMockAdapter(), new UserServiceMockAdapter(false));

        var p = service.onGetProduct(userId, GENERATED_PRODUCT_ID);
        assertEquals(null, p);
    }

    @Test
    public void shouldRenameProductSuccessfullyWithPermission() {
        String userId = "test_user";
        var product = getTestProduct();
        String newName = "new test product name";

        EcommerceService service = new EcommerceService(
                new EcommercePersistenceMockAdapter(GENERATED_PRODUCT_ID, product),
                new StreamOutputMockAdapter(), new UserServiceMockAdapter(true));

        service.onRenameProduct(userId, GENERATED_PRODUCT_ID, newName);

        // the product id should be set because EcommercePersistenceMockAdapter::saveProduct() should be invoked
        assertEquals(GENERATED_PRODUCT_ID, product.getId());
        assertEquals(newName, product.getName());
    }

    @Test
    public void shouldFailToRenameProductWithoutPermission() {
        String userId = "test_user";
        String newName = "whatever";
        var product = getTestProduct();

        EcommerceService service = new EcommerceService(
                new EcommercePersistenceMockAdapter(GENERATED_PRODUCT_ID, product),
                new StreamOutputMockAdapter(), new UserServiceMockAdapter(false));
        service.onRenameProduct(userId, GENERATED_PRODUCT_ID, newName);

        assertNotEquals(GENERATED_PRODUCT_ID, product.getId());
        assertNotEquals(newName, product.getName());
    }

    @Test
    public void shouldSetProductDiscountSuccessfullyWithPermission() {
        String userId = "test_user";
        double newDiscount = 0.33;
        var product = getTestProduct();

        EcommerceService service = new EcommerceService(
                new EcommercePersistenceMockAdapter(GENERATED_PRODUCT_ID, product),
                new StreamOutputMockAdapter(), new UserServiceMockAdapter(true));

        service.onSetProductDiscout(userId, GENERATED_PRODUCT_ID, newDiscount);

        assertEquals(GENERATED_PRODUCT_ID, product.getId());
        assertEquals(newDiscount, product.getDiscount());
    }

    @Test
    public void shouldFailToSetProductDiscountWithoutPermission() {
        String userId = "test_user";
        var product = getTestProduct();
        double newDiscount = 0.33;

        EcommerceService service = new EcommerceService(
                new EcommercePersistenceMockAdapter(GENERATED_PRODUCT_ID, product),
                new StreamOutputMockAdapter(), new UserServiceMockAdapter(false));

        service.onSetProductDiscout(userId, GENERATED_PRODUCT_ID, newDiscount);

        assertNotEquals(GENERATED_PRODUCT_ID, product.getId());
        assertNotEquals(newDiscount, product.getDiscount());
    }
}
