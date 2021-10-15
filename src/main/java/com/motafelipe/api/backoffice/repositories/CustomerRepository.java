package com.motafelipe.api.backoffice.repositories;

import com.motafelipe.api.backoffice.domains.vo.entities.BookingEntity;
import com.motafelipe.api.backoffice.domains.vo.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {

    @Override
    CustomerEntity save(CustomerEntity customerEntity);

    @Query("select c from tb_customers c where id_customer = ?1")
    Optional<CustomerEntity> getCustomerByIdCustomer(Long id);
}
