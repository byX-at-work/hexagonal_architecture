package org.lunatech.ecommerce.adapters.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * EcommerceJPARepo
 */
@Repository
public interface EcommerceJPARepo extends JpaRepository<SpringProductEntity, String> {


}
