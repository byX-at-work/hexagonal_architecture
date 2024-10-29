package org.lunatech.ecommerce.adapters.spring;

import org.lunatech.ecommerce.EcommerceService;
import org.lunatech.ecommerce.ports.EcommercePersistencePort;
import org.lunatech.ecommerce.ports.StreamOutputPort;
import org.lunatech.ecommerce.ports.UserServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * EcommerceBeanConfiguration
 */
@Configuration
public class EcommerceBeanConfiguration {

    @Autowired
    EcommercePersistencePort storage;

    @Autowired
    StreamOutputPort kafka;

    @Autowired
    UserServicePort userService;

    @Bean
    EcommerceService getEcommerceService() {
        return new EcommerceService(storage, kafka, userService);
    }


}
