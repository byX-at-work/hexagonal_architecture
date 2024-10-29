package org.lunatech.ecommerce.adapters.spring;

import org.lunatech.ecommerce.EcommerceService;
import org.lunatech.ecommerce.Product;
import org.lunatech.ecommerce.ports.EcommerceInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * EcommerceSpringApplication
 */
@RestController
@RequestMapping("/api/ecommerce")
@EnableAutoConfiguration
@SpringBootApplication
public class EcommerceSpringApplication implements EcommerceInputPort {

    @Autowired
    EcommerceService service;


    @PostMapping("/product")
    public String addProduct(@RequestBody Product product) {
        String userId = ""; // should be retrieved from a session or something
        return service.onAddProduct(userId, product);
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") String productId) {
        String userId = "";
        return service.onGetProduct(userId, productId);
    }

    @PutMapping("/product/rename/{id}")
    public void renameProduct(@PathVariable("id") String productId, @RequestBody String name) {
        String userId = "";
        service.onRenameProduct(userId, productId, name);
    }

    @PutMapping("/product/discount/{id}")
    public void setProductDiscout(@PathVariable("id") String productId,
            @RequestBody double discout) {
        String userId = "";
        service.onSetProductDiscout(userId, productId, discout);
    }

    public static void main(String[] args) {
        SpringApplication.run(EcommerceSpringApplication.class, args);
    }
}
