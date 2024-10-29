package org.lunatech.ecommerce.adapters.spring;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * EcommerceDataSourceConfiguration
 */
@Configuration
public class EcommerceDataSourceConfiguration {

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/ecommerce_database");
        dataSourceBuilder.username("ecommerce");
        dataSourceBuilder.password("ecommerce");
        return dataSourceBuilder.build();
    }
}
