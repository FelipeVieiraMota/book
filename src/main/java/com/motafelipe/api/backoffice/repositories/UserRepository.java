package com.motafelipe.api.backoffice.repositories;

import com.motafelipe.api.backoffice.domains.enums.Role;
import com.motafelipe.api.backoffice.domains.vo.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    @Query(" select u from tb_user u where email = ?1 and password = ?2 ")
    Optional<UserEntity> login (String email, String password);

    @Transactional(readOnly = false)
    @Modifying
    @Query(" update tb_user set role = ?2 where id_student = ?1 ")
    int updateRole(Long id, Role role);

    Optional<UserEntity> findByEmail(String email);
}
