package com.motafelipe.api.backoffice.repositories;

import com.motafelipe.api.backoffice.domains.vo.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {
}
