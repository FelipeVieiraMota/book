package com.motafelipe.api.backoffice.models.students;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.motafelipe.api.backoffice.domains.vo.entities.StudentEntity;
import com.motafelipe.api.backoffice.models.pagination.PageModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentModel {

    @JsonProperty(value = "id_student")
    public Long idStudent;

    @JsonProperty(value = "token")
    public String token;

    @JsonProperty(value = "first_name")
    public String firstName;

    @JsonProperty(value = "last_name")
    public String lastName;

    @JsonProperty(value = "cpf")
    public String cpf;

    @JsonProperty(value = "rg")
    public String rg;

    @JsonProperty(value = "date_of_birth")
    @DateTimeFormat
    @NotNull(message = " Must need have a valid date field. ")
    @NotEmpty(message = " Must need have a valid date field. ")
    @NotBlank(message = " Must need have a valid date field. ")
    public Date dateOfBirth;

    @JsonProperty(value = "date_creation")
    public Date creationDate;

    @JsonProperty(value = "email")
    public String email;

    @JsonProperty(value = "ra")
    public String ra;

    @JsonProperty(value = "student_internal_code")
    public String studentInternalCode;

    @JsonProperty(value = "cellphone")
    public String cellphone;

    @JsonProperty(value = "address")
    public List<AddressModel> address;

    public static StudentModel toModel (StudentEntity studentEntity){
        return new StudentModel(
                studentEntity.getIdStudent(),
                studentEntity.getToken(),
                studentEntity.getFirstName(),
                studentEntity.getLastName(),
                studentEntity.getCpf(),
                studentEntity.getRg(),
                studentEntity.getDateOfBirth(),
                studentEntity.getCreationDate(),
                studentEntity.getEmail(),
                studentEntity.getRa(),
                studentEntity.getStudentInternalCode(),
                studentEntity.getCellphone(),
                AddressModel.toModelList(studentEntity.getAddress())
        );
    }


    public static PageModel<StudentModel> entityPageToStudentPageModel(Page<StudentEntity> pageEntity){

        var listOfStudentModel = StudentModel.toModelList(pageEntity.toList());

        var result = new PageImpl<>(listOfStudentModel);

        return new PageModel<>((int)result.getTotalElements(), result.getSize(), result.getTotalPages(), result.getContent());
    }

    public static List<StudentModel> toModelList (List<StudentEntity> listOfStudentEntity){

        return listOfStudentEntity
                .stream()
                .map(entity -> new StudentModel(
                    entity.getIdStudent(),
                    entity.getToken(),
                    entity.getFirstName(),
                    entity.getLastName(),
                    entity.getCpf(),
                    entity.getRg(),
                    entity.getDateOfBirth(),
                    entity.getCreationDate(),
                    entity.getEmail(),
                    entity.getRa(),
                    entity.getStudentInternalCode(),
                    entity.getCellphone(),
                    AddressModel.toModelList(entity.getAddress())
                ))
                .collect(Collectors.toList());
    }

    public static StudentEntity toEntity(StudentModel studentModel) {
        return new StudentEntity(
                studentModel.getIdStudent(),
                studentModel.getToken(),
                studentModel.getFirstName(),
                studentModel.getLastName(),
                studentModel.getCpf(),
                studentModel.getRg(),
                studentModel.getDateOfBirth(),
                studentModel.getCreationDate(),
                studentModel.getEmail(),
                studentModel.getRa(),
                studentModel.getStudentInternalCode(),
                studentModel.getCellphone(),
                AddressModel.toEntityList(studentModel.getAddress())
        );
    }
}
