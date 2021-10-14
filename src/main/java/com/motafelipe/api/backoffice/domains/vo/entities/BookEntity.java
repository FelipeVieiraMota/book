package com.motafelipe.api.backoffice.domains.vo.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name="tb_books")
public class BookEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book", nullable = false)
    private Long idBook;

    @ManyToOne
    @JoinColumn(name = "id_customer", nullable = false)
    private CustomerEntity customerEntity;

    @ManyToOne
    @JoinColumn(name = "id_room", nullable = false)
    private RoomEntity roomEntity;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "finish_date", nullable = false)
    private Date finishDate;

    @Column(name = "is_occupied", nullable = false)
    private boolean isOccupied;

    @Column(length = 100)
    private String description;
}
