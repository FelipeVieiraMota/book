package com.motafelipe.api.backoffice.repositories;

import com.motafelipe.api.backoffice.domains.vo.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity,Long>{
}
