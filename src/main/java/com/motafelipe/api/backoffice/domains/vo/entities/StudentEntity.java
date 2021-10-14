package com.motafelipe.api.backoffice.domains.vo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name="tb_student")
public class StudentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_student", nullable = false)
    private Long idStudent;

    @Column(name = "token", updatable = false, nullable = false)
    private String token;

    @Column(name = "first_name", length = 75, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "rg", nullable = false)
    private String rg;

    @Column(name = "date_of_birth", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "creation_date", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "ra", nullable = false)
    private String ra;

    @Column(name = "student_internal_code", nullable = false)
    private String studentInternalCode;

    @Column(name = "cellphone", length = 50, nullable = false)
    private String cellphone;

    @Getter(onMethod = @__({@JsonIgnore}))
    @OneToMany(mappedBy = "fkStudent", cascade = CascadeType.ALL)
    private List<AddressEntity> address = new ArrayList<>();
}