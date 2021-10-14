package com.motafelipe.api.backoffice.repositories;

import com.motafelipe.api.backoffice.domains.vo.entities.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<RoomEntity,Long> {

    Optional<RoomEntity> getRoomByIdRoom(Long idRoom);

}
