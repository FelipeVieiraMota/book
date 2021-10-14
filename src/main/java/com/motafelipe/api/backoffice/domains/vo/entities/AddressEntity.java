package com.motafelipe.api.backoffice.domains.vo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "tb_address")
public class AddressEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address", nullable = false)
    private Long idAddress;

    @Getter(onMethod = @__({@JsonIgnore}))
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_student")
    private StudentEntity fkStudent;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "number")
    private String number;

    @Column(name = "complement")
    private String complement;

    @Column(name = "cep")
    private String cep;

    @Column(name = "neighbor")
    private String neighbor;
}