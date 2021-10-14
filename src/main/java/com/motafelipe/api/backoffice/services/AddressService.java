package com.motafelipe.api.backoffice.services;

import com.motafelipe.api.backoffice.models.students.AddressModel;
import com.motafelipe.api.backoffice.models.students.StudentModel;
import com.motafelipe.api.backoffice.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Optional;

@Service
public class AddressService {

    private final StudentService studentService;
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(StudentService studentService, AddressRepository addressRepository) {
        this.studentService = studentService;
        this.addressRepository = addressRepository;
    }

    public AddressModel save(Long idStudent, AddressModel addressModel){

        var studentEntity  = this.studentService.getById(idStudent);

        addressModel.setIdStudent(StudentModel.toEntity(studentEntity));

        var data = Optional.of(this.addressRepository.save(AddressModel.toEntity(addressModel)));

        data.orElseThrow(
            () -> new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, " We cannot to save " + null + " in our database, please try it again in a few moment.")
        );

        return AddressModel.toModel(data.get());
    }
}
