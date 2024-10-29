package org.lunatech.ecommerce;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lunatech.ecommerce.ports.EcommercePersistencePort;
import org.lunatech.ecommerce.ports.StreamOutputPort;
import org.lunatech.ecommerce.ports.UserServicePort;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * EcommerceServiceTest
 */
public class EcommerceServiceTest {

    private static final String GENERATED_PRODUCT_ID = "generated_product_id";

    @Mock
    StreamOutputPort kafka;

    @Mock
    EcommercePersistencePort storage;

    @Mock
    UserServicePort userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

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

        Mockito.when(userService.checkUserPrivilege(userId, "addProduct")).thenReturn(true);
        Mockito.doAnswer((invocation) -> {
            Product p = invocation.getArgument(0);
            p.setId(GENERATED_PRODUCT_ID);
            return null;
        }).when(storage).saveProduct(product);

        EcommerceService service = new EcommerceService(storage, kafka, userService);
        var productId = service.onAddProduct(userId, product);
        assertEquals(GENERATED_PRODUCT_ID, productId);
        Mockito.verify(storage).saveProduct(product);
    }

    @Test
    public void shouldFailToAddProductWithoutPermission() {
        String userId = "test_user";
        Mockito.when(userService.checkUserPrivilege(userId, "addProduct")).thenReturn(false);
        EcommerceService service = new EcommerceService(storage, kafka, userService);
        var product = getTestProduct();
        service.onAddProduct(userId, product);
        Mockito.verify(storage, Mockito.never()).saveProduct(product);
    }

    @Test
    public void shouldGetProductSuccessfullyWithPermission() {
        String userId = "test_user";
        var product = getTestProduct();

        Mockito.when(userService.checkUserPrivilege(userId, "getProduct")).thenReturn(true);
        Mockito.when(storage.getProduct(GENERATED_PRODUCT_ID)).thenReturn(product);

        EcommerceService service = new EcommerceService(storage, kafka, userService);
        var p = service.onGetProduct(userId, GENERATED_PRODUCT_ID);
        assertEquals(p, product);
    }

    @Test
    public void shouldFailToGetProductWithoutPermission() {
        String userId = "test_user";
        Mockito.when(userService.checkUserPrivilege(userId, "getProduct")).thenReturn(false);
        EcommerceService service = new EcommerceService(storage, kafka, userService);
        var product = service.onGetProduct(userId, GENERATED_PRODUCT_ID);
        assertEquals(null, product);
    }

    @Test
    public void shouldRenameProductSuccessfullyWithPermission() {
        String userId = "test_user";
        var product = getTestProduct();
        String newName = "new test product name";

        Mockito.when(userService.checkUserPrivilege(userId, "updateProduct")).thenReturn(true);
        Mockito.when(storage.getProduct(GENERATED_PRODUCT_ID)).thenReturn(product);
        Mockito.doAnswer((invocation) -> {
            Product p = invocation.getArgument(0);
            assertEquals(newName, p.getName());
            return null;
        }).when(storage).saveProduct(Mockito.any());

        EcommerceService service = new EcommerceService(storage, kafka, userService);
        service.onRenameProduct(userId, GENERATED_PRODUCT_ID, newName);
    }

    @Test
    public void shouldFailToRenameProductWithoutPermission() {
        String userId = "test_user";
        Mockito.when(userService.checkUserPrivilege(userId, "updateProduct")).thenReturn(false);
        EcommerceService service = new EcommerceService(storage, kafka, userService);
        var product = getTestProduct();
        service.onRenameProduct(userId, GENERATED_PRODUCT_ID, "whatever");
        Mockito.verify(storage, Mockito.never()).saveProduct(product);
    }

    @Test
    public void shouldSetProductDiscountSuccessfullyWithPermission() {
        String userId = "test_user";
        double newDiscount = 0.33;
        var product = getTestProduct();

        Mockito.when(userService.checkUserPrivilege(userId, "updateProduct")).thenReturn(true);
        Mockito.when(storage.getProduct(GENERATED_PRODUCT_ID)).thenReturn(product);
        Mockito.doAnswer((invocation) -> {
            Product p = invocation.getArgument(0);
            assertEquals(newDiscount, p.getDiscount());
            return null;
        }).when(storage).saveProduct(Mockito.any());

        EcommerceService service = new EcommerceService(storage, kafka, userService);
        service.onSetProductDiscout(userId, GENERATED_PRODUCT_ID, newDiscount);
    }

    @Test
    public void shouldFailToSetProductDiscountWithoutPermission() {
        String userId = "test_user";
        Mockito.when(userService.checkUserPrivilege(userId, "updateProduct")).thenReturn(false);
        EcommerceService service = new EcommerceService(storage, kafka, userService);
        var product = getTestProduct();
        service.onSetProductDiscout(userId, GENERATED_PRODUCT_ID, 0.999);
        Mockito.verify(storage, Mockito.never()).saveProduct(product);
    }

}
