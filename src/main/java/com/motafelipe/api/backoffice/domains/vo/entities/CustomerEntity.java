package com.motafelipe.api.backoffice.domains.vo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name="tb_customers")
public class CustomerEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer", nullable = false)
    private Long idCustomer;

    @Column(name = "first_name", length = 75, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 75)
    private String lastName;

    @Column(length = 100)
    private String email;

    @Getter(onMethod = @__({@JsonIgnore}))
    @Setter(onMethod = @__({@JsonProperty}))
    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 50)
    private String cellphone;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

}
