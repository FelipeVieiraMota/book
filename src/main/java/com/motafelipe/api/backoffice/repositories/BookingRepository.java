package com.motafelipe.api.backoffice.repositories;

import com.motafelipe.api.backoffice.domains.vo.entities.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<BookingEntity,Long>{

    @Query(" select b from tb_booking b order by id_book desc ")
    Optional<BookingEntity> getLastEntity();

    @Override
    BookingEntity save(BookingEntity bookingEntity);

    @Query("select b from tb_booking b where id_customer = ?1")
    Optional<BookingEntity> getBookByIdCustomer(Long id);
}
