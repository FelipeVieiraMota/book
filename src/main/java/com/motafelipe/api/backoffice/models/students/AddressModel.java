package com.motafelipe.api.backoffice.models.students;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.motafelipe.api.backoffice.domains.vo.entities.AddressEntity;
import com.motafelipe.api.backoffice.domains.vo.entities.StudentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties({"id_student"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id_student")
    private StudentEntity idStudent;

    @JsonProperty("id_address")
    private Long idAddress;

    @JsonProperty("street_name")
    private String streetName;

    @JsonProperty("number")
    private String number;

    @JsonProperty("complement")
    private String complement;

    @JsonProperty("cep")
    private String cep;

    @JsonProperty("neighbor")
    private String neighbor;

    /**
     * AddressModel to AddressEntity
     * @param addressModel - parameter
     * @return AddressEntity
     */
    public static AddressEntity toEntity(AddressModel addressModel){

        return new AddressEntity(
                addressModel.getIdAddress(),
                addressModel.getIdStudent(),
                addressModel.getStreetName(),
                addressModel.getNumber(),
                addressModel.getComplement(),
                addressModel.getCep(),
                addressModel.getNeighbor()
        );
    }

    /**
     *
     * @param addressEntity
     * @return
     */
    public static AddressModel toModel(AddressEntity addressEntity){

        return new AddressModel(
                addressEntity.getFkStudent(),
                addressEntity.getIdAddress(),
                addressEntity.getStreetName(),
                addressEntity.getNumber(),
                addressEntity.getComplement(),
                addressEntity.getCep(),
                addressEntity.getNeighbor()
        );
    }

    /**
     *
     * @param addressEntitiesList
     * @return
     */
    public static List<AddressModel> toModelList(List<AddressEntity> addressEntitiesList){

        return addressEntitiesList
                .stream()
                .map(
                        entity ->
                                new AddressModel(
                                        entity.getFkStudent(),
                                        entity.getIdAddress(),
                                        entity.getStreetName(),
                                        entity.getNumber(),
                                        entity.getComplement(),
                                        entity.getCep(),
                                        entity.getNeighbor()
                                )
                ).collect(Collectors.toList());
    }

    /**
     *
     * @param addressModelList
     * @return
     */
    public static List<AddressEntity> toEntityList(List<AddressModel> addressModelList){

        return addressModelList
                .stream()
                .map(
                        entity ->
                                new AddressEntity(
                                        entity.getIdAddress(),
                                        entity.getIdStudent(),
                                        entity.getStreetName(),
                                        entity.getNumber(),
                                        entity.getComplement(),
                                        entity.getCep(),
                                        entity.getNeighbor()
                                )
                ).collect(Collectors.toList());
    }
}

