package com.motafelipe.api.backoffice.domains.vo.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name="tb_rooms")
public class RoomEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_room", nullable = false)
    private Long idRoom;

    private Long number;

    @Column(name = "is_activated", nullable = false)
    private boolean isActivated;
}
